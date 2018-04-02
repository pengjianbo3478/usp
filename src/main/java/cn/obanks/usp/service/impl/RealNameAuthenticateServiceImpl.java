package cn.obanks.usp.service.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import chinapay.Base64;
import cn.obanks.common.ToString;
import cn.obanks.usp.enums.RealNameResponseCode;
import cn.obanks.usp.model.Merchant;
import cn.obanks.usp.model.RealNameAuthRecord;
import cn.obanks.usp.model.RealNameAuthResult;
import cn.obanks.usp.model.RealNameBean;
import cn.obanks.usp.service.MerchantService;
import cn.obanks.usp.service.RealNameAuthRecordService;
import cn.obanks.usp.service.RealNameAuthenticateService;
import cn.obanks.usp.utils.Md5Utils;
import cn.obanks.usp.utils.RSAUtils;
import cn.obanks.usp.utils.SimpleHttpsSocketFactory;

@Service
public class RealNameAuthenticateServiceImpl implements RealNameAuthenticateService {
	private static final Logger LOG = LoggerFactory.getLogger(RealNameAuthenticateServiceImpl.class);
	private static final SimpleDateFormat FMT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String REAL_NAME_RESPONSE_CODE = "respCode";
	private static final String REAL_NAME_RESPONSE_MSG = "respmsg";
	private static final String REAL_NAME_RESPONSE_ELAPSED = "respElapsed";
	@Value("${real.auth.req.addr}")
	private String reqAddr;
	@Value("${real.auth.modulus}")
	private String modulus;
	@Value("${real.auth.exponent}")
	private String exponent;
	@Value("${real.auth.encoding}")
	private String encoding;
	@Value("${real.auth.secret.key}")
	private String secret_key;
	@Value("${real.auth.mer.name}")
	private String merName;
	@Value("${real.auth.mer.Id}")
	private String merchantId;
	@Value("${real.auth.https.connectionmanagertimeout}")
	private int connectionManagerTimeout;
	@Value("${real.auth.https.connectiontimeout}")
	private int connectionTimeout;
	@Value("${real.auth.https.sotimeout}")
	private int soTimeout;
	@Value("${real.auth.https.maxtotalconnections}")
	private int maxTotalConnections;
	@Value("${real.auth.https.defaultmaxconnectionsperhost}")
	private int defaultMaxconnectionsPerhost;
	
	@Resource
	private Map<String, String> realNameResponseCodeMap;
	@Autowired
	private RealNameAuthRecordService realNameAuthRecordService;
	@Autowired
	private MerchantService merchantService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RealNameAuthResult authenticate(RealNameBean realNameBean) {
		Date now = new Date();
		realNameBean.setOrderTime(FMT.format(now));
		RealNameAuthResult realNameAuthResult = this.validationData(realNameBean);
		if (StringUtils.isNotEmpty(realNameAuthResult.getRespCode())) return realNameAuthResult;
		RealNameAuthRecord realNameAuthRecord = this.addRealNameAuthRecord(realNameBean);
		StringBuffer rsaData = new StringBuffer();
		rsaData.append("cardNum=").append(realNameBean.getCardNum());
		rsaData.append("&idCard=").append(realNameBean.getIdCard());
		rsaData.append("&userName=").append(realNameBean.getUserName());
		rsaData.append("&phoneNum=").append(realNameBean.getPhoneNum());
		StringBuffer md5RsaData = new StringBuffer();
		md5RsaData.append("merId=").append(merchantId);
		md5RsaData.append("&merName=").append(merName);
		md5RsaData.append("&orderNumber=").append(realNameBean.getOrderNumber());
		md5RsaData.append("&orderTime=").append(realNameBean.getOrderTime());
		Map<String, String> map = this.realNamePost(realNameBean, rsaData, md5RsaData);
		if (CollectionUtils.isEmpty(map) == false) {
			realNameAuthResult = new RealNameAuthResult(map.get(REAL_NAME_RESPONSE_CODE), map.get(REAL_NAME_RESPONSE_MSG));
			this.mergeRealNameAuthRecord(realNameAuthRecord, map);
		}
		LOG.debug("realNameAuthResult:{}", ToString.reflectionToString(realNameAuthResult));
		return realNameAuthResult;
	}

	/**
	 * 
	 * @Title: mergeRealNameAuthRecord
	 * @Description: 更新认证记录
	 * @param: @param realNameAuthRecord
	 * @param: @param respElapsed
	 * @param: @param respCode
	 * @param: @param respMsg
	 * @return: void
	 * @throws
	 */
	private void mergeRealNameAuthRecord(RealNameAuthRecord realNameAuthRecord, Map<String, String> map) {
		String respCode = map.get(REAL_NAME_RESPONSE_CODE);
		String respMsg = map.get(REAL_NAME_RESPONSE_MSG);
		BigDecimal respElapsed = new BigDecimal(map.get(REAL_NAME_RESPONSE_ELAPSED));
		realNameAuthRecord.setRespCode(respCode);
		realNameAuthRecord.setRespMsg(respMsg);
		realNameAuthRecord.setRespElapsed(respElapsed);
		this.realNameAuthRecordService.merge(realNameAuthRecord);
	}

	/**
	 * 
	 * @Title: validationData
	 * @Description: 校验实名认证记录
	 * @param: @param realNameBean
	 * @param: @return
	 * @return: RealNameAuthResult
	 * @throws
	 */
	private RealNameAuthResult validationData(RealNameBean realNameBean) {
		RealNameAuthResult realNameAuthResult = new RealNameAuthResult();
		if (!this.validationMerChant(realNameBean.getMerId())) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_02.getValue());
			realNameAuthResult.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_02.getValue()));
			return realNameAuthResult;
		}
		if (!this.validationSignature(realNameBean)) {
			String value = RealNameResponseCode.REALNAME_RESPONSE_CODE_01.getValue();
			realNameAuthResult.setRespCode(value);
			realNameAuthResult.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_01.getValue()));
			return realNameAuthResult;
		}
		return realNameAuthResult;
	}

	/**
	 * 
	 * @Title: realNamePost
	 * @Description: 实名认证请求
	 * @param: @param realNameBean
	 * @param: @param realNameAuthRecord
	 * @param: @param rsaData
	 * @param: @param md5RsaData
	 * @param: @return
	 * @return: RealNameAuthResult
	 * @throws
	 */
	private Map<String, String> realNamePost(RealNameBean realNameBean, StringBuffer rsaData, StringBuffer md5RsaData) {
		long requestStart = System.currentTimeMillis();
		byte[] encryptedContentKey;
		PostMethod postMethod = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String responseString = "";
		try {
			encryptedContentKey = RSAUtils.encryptByPublicKey(rsaData.toString().getBytes(encoding), modulus, exponent);
			char[] base64Key = Base64.encode(encryptedContentKey);
			String paydata = String.valueOf(base64Key);
			String md5Date = md5RsaData.append("&paydata=").append(paydata).append("&").append(Md5Utils.md5(secret_key)).toString();
			String signature = Md5Utils.md5(md5Date);
			Protocol authhttps = new Protocol("https", new SimpleHttpsSocketFactory(), 443);
			Protocol.registerProtocol("https", authhttps);
			postMethod = new PostMethod(reqAddr);
			NameValuePair[] nameValuePairArray = new NameValuePair[6];
			nameValuePairArray[0] = new NameValuePair("merId", merchantId);
			nameValuePairArray[1] = new NameValuePair("merName", merName);
			nameValuePairArray[2] = new NameValuePair("orderNumber", realNameBean.getOrderNumber());
			nameValuePairArray[3] = new NameValuePair("orderTime", realNameBean.getOrderTime());
			nameValuePairArray[4] = new NameValuePair("paydata", paydata);
			nameValuePairArray[5] = new NameValuePair("signature", signature);
			postMethod.getParams().setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
			postMethod.setRequestBody(nameValuePairArray);
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.HTTP_CONTENT_CHARSET, encoding);
			params.setConnectionManagerTimeout(this.connectionManagerTimeout);
			HttpConnectionManagerParams connManagerParams = new HttpConnectionManagerParams();
			connManagerParams.setConnectionTimeout(this.connectionTimeout);
			connManagerParams.setSoTimeout(this.soTimeout);
			connManagerParams.setMaxTotalConnections(this.maxTotalConnections);
			connManagerParams.setDefaultMaxConnectionsPerHost(this.defaultMaxconnectionsPerhost);
			MultiThreadedHttpConnectionManager connMgr = new MultiThreadedHttpConnectionManager();
			connMgr.setParams(connManagerParams);
			HttpClient httpClient = new HttpClient(params, connMgr);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == 200) {
				responseString = IOUtils.toString(postMethod.getResponseBodyAsStream());
				map = this.parseResponseToMap(responseString);
			}
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		catch (Exception ex) {
			if (LOG.isErrorEnabled()) LOG.error("实名认证失败:", ex);
			map.put(REAL_NAME_RESPONSE_CODE, "error");
			map.put(REAL_NAME_RESPONSE_MSG, ex.getMessage());
			if (null != postMethod) {
				postMethod.releaseConnection();
			}
		}
		Double respElapsed = (System.currentTimeMillis() - requestStart) / 1000.0;
		map.put(REAL_NAME_RESPONSE_ELAPSED, respElapsed.toString());
		return map;
	}

	/**
	 * 
	 * @Title: parseResponseToMap
	 * @Description: 转换实名认证结果为map
	 * @param: @param responseString
	 * @param: @return
	 * @return: HashMap<String,String>
	 * @throws
	 */
	private HashMap<String, String> parseResponseToMap(String responseString) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] ary = responseString.split("&");
		for (int i = 0; i < ary.length; i++) {
			String[] info = ary[i].split("=");
			if (info.length == 2) {
				map.put(info[0], info[1]);
			}
			else
				if (info.length == 1) {
					map.put(info[0], "无错误信息");
				}
		}
		return map;
	}

	/**
	 * 
	 * @Title: addRealNameAuthRecord
	 * @Description:写入实名认证记录
	 * @param: @param realNameBean
	 * @param: @return
	 * @return: RealNameAuthRecord
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private RealNameAuthRecord addRealNameAuthRecord(RealNameBean realNameBean) {
		RealNameAuthRecord realNnameAuthRecord = new RealNameAuthRecord();
		realNnameAuthRecord.setCardNum(realNameBean.getCardNum());
		realNnameAuthRecord.setCreatedDate(new Date());
		realNnameAuthRecord.setIdCard(realNameBean.getIdCard());
		realNnameAuthRecord.setMerId(realNameBean.getMerId());
		realNnameAuthRecord.setOrderNumber(realNameBean.getOrderNumber());
		realNnameAuthRecord.setPhoneNum(realNameBean.getPhoneNum());
		realNnameAuthRecord.setSignature(realNameBean.getSignature());
		realNnameAuthRecord.setUserName(realNameBean.getUserName());
		this.realNameAuthRecordService.add(realNnameAuthRecord);
		return realNnameAuthRecord;
	}

	/**
	 * 
	 * @Title: vaildateKey
	 * @Description: 校验商户号
	 * @param: @param merId
	 * @param: @return
	 * @return: RealNameAuthResult
	 * @throws
	 */
	private boolean validationMerChant(String merId) {
		Merchant merchant = this.merchantService.search(merId);
		if (merchant == null) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * 
	 * @Title: vaildatKey
	 * @Description: 验证商户签名
	 * @param: @param merchant
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	private boolean validationSignature(RealNameBean realNameBean) {
		String merId = realNameBean.getMerId();
		String signature = realNameBean.getSignature();
		LOG.debug("signature:{}", signature);
		StringBuffer md5RsaData = new StringBuffer();
		md5RsaData.append("merId=").append(realNameBean.getMerId());
		md5RsaData.append("&orderNumber=").append(realNameBean.getOrderNumber());
		md5RsaData.append("&idCard=").append(realNameBean.getIdCard());
		md5RsaData.append("&userName=").append(realNameBean.getUserName());
		md5RsaData.append("&phoneNum=").append(realNameBean.getPhoneNum());
		Merchant merchant = this.merchantService.search(merId);
		String publicKey = merchant.getPublicKey();
		LOG.debug("publicKey:{}", publicKey);
		try {
			return RSAUtils.verify(Md5Utils.md5(md5RsaData.toString()).getBytes("UTF-8"), publicKey, signature);
		}
		catch (Exception ex) {
			if (LOG.isErrorEnabled()) LOG.error("验证商户签名失败", ex);
		}
		return false;
	}
}
