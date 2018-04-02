package cn.obanks.usp.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import cn.obanks.common.AjaxResultStatus;
import cn.obanks.common.ToString;
import cn.obanks.usp.enums.SendMsgResponseCode;
import cn.obanks.usp.model.Customer;
import cn.obanks.usp.model.CustomerProviderRef;
import cn.obanks.usp.model.SendMsgBean;
import cn.obanks.usp.model.SendMsgRecord;
import cn.obanks.usp.model.SendMsgResult;
import cn.obanks.usp.model.Template;
import cn.obanks.usp.model.TemplateRef;
import cn.obanks.usp.service.CustomerProviderRefService;
import cn.obanks.usp.service.CustomerService;
import cn.obanks.usp.service.SendMsgRecordService;
import cn.obanks.usp.service.SendMsgService;
import cn.obanks.usp.service.TemplateRefService;
import cn.obanks.usp.service.TemplateService;
import cn.obanks.usp.utils.Md5Utils;
import cn.obanks.usp.utils.RSAUtils;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

@Service
public class SendMsgServiceImpl implements SendMsgService {
	private static final Logger LOG = LoggerFactory.getLogger(SendMsgServiceImpl.class);
	private static final String SEND_MSG_RETURN_STATUS_CODE = "statusCode";
	private static final String SEND_MSG_RETURN_STATUS_MSG = "statusMsg";
	@Autowired
	private CustomerProviderRefService customerProviderRefService;
	@Autowired
	private TemplateRefService templateRefService;
	@Autowired
	private CustomerService customerService;
	@Resource
	private Map<String, String> sendMsgResponseCodeMap;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SendMsgRecordService sendMsgRecordService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SendMsgResult sendMsg(SendMsgBean sendMsgBean) {
		SendMsgResult sendMsgResult = this.validationData(sendMsgBean);
		if (StringUtils.isNotEmpty(sendMsgResult.getRespCode())) return sendMsgResult;
		CustomerProviderRef customerProviderRef = this.searchProviderInfo(sendMsgBean.getCustomerId());
		SendMsgRecord sendMsgRecord = this.addSendMsgRecord(sendMsgBean, customerProviderRef.getProviderId());
		long requestStart = System.currentTimeMillis();
		sendMsgResult = this.send(sendMsgBean, sendMsgResult, customerProviderRef);
		BigDecimal respElapsed = new BigDecimal((System.currentTimeMillis() - requestStart) / 1000.0) ;
		sendMsgRecord.setRespCode(sendMsgResult.getRespCode());
		sendMsgRecord.setRespMsg(sendMsgResult.getRespMsg());
		sendMsgRecord.setRespElapsed(respElapsed);
		this.sendMsgRecordService.merge(sendMsgRecord);
		return sendMsgResult;
	}

	private SendMsgResult send(SendMsgBean sendMsgBean, SendMsgResult sendMsgResult, CustomerProviderRef customerProviderRef) {
		String msgServiceUrl = customerProviderRef.getServiceUrl();
		String msgServicePort = customerProviderRef.getPort();
		String msgAccountSid = customerProviderRef.getAccount();
		String msgAuthToken = customerProviderRef.getAuthToken();
		String msgAppId = customerProviderRef.getAppId();
		Long providerId = customerProviderRef.getProviderId();
		Long templateId = sendMsgBean.getTemplateId();
		TemplateRef templateRef = this.searchTemplateInfo(providerId, templateId);
		LOG.debug("templateRef={}", ToString.reflectionToString(templateRef));
		Long code = templateRef.getCode();
		String mobile = sendMsgBean.getMobile();
		String[] content = sendMsgBean.getContent();
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init(msgServiceUrl, msgServicePort);
		restAPI.setAccount(msgAccountSid, msgAuthToken);
		restAPI.setAppId(msgAppId);
		HashMap<String, Object> result = restAPI.sendTemplateSMS(mobile, code.toString(), content);
		LOG.debug("send msg result={}", ToString.reflectionToString(result));
		if (!CollectionUtils.isEmpty(result)) {
			sendMsgResult.setRespCode((String) result.get(SEND_MSG_RETURN_STATUS_CODE));
			sendMsgResult.setRespMsg((String) result.get(SEND_MSG_RETURN_STATUS_MSG));
		}
		else {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_03.getValue());
			sendMsgResult.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_03.getValue()));
		}
		return sendMsgResult;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private SendMsgRecord addSendMsgRecord(SendMsgBean sendMsgBean, Long providerId) {
		SendMsgRecord sendMsgRecord = new SendMsgRecord();
		sendMsgRecord.setPhoneNum(sendMsgBean.getMobile());
		sendMsgRecord.setContent(Arrays.toString(sendMsgBean.getContent()));
		sendMsgRecord.setCustomerId(sendMsgBean.getCustomerId());
		sendMsgRecord.setProviderId(providerId);
		sendMsgRecord.setSignature(sendMsgBean.getSignature());
		sendMsgRecord.setTemplateId(sendMsgBean.getTemplateId());
		this.sendMsgRecordService.add(sendMsgRecord);
		return sendMsgRecord;
	}

	/**
	 * 
	 * @Title: validationData
	 * @Description: 校验客户号模板号是否存在
	 * @param: @param sendMsgBean
	 * @param: @return
	 * @return: SendMsgResult
	 * @throws
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	private SendMsgResult validationData(SendMsgBean sendMsgBean) {
		SendMsgResult sendMsgResult = new SendMsgResult();
		Long customerId = sendMsgBean.getCustomerId();
		Customer customer = this.customerService.search(customerId);
		if (ObjectUtils.equals(customer, null)) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_01.getValue());
			sendMsgResult.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_01.getValue()));
			return sendMsgResult;
		}
		Long templateId = sendMsgBean.getTemplateId();
		Template template = this.templateService.search(templateId);
		if (ObjectUtils.equals(template, null)) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_02.getValue());
			sendMsgResult.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_02.getValue()));
			return sendMsgResult;
		}
		if (!this.validationSignature(sendMsgBean, customer.getPublicKey())) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_04.getValue());
			sendMsgResult.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_04.getValue()));
			return sendMsgResult;
		}
		return sendMsgResult;
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
	private boolean validationSignature(SendMsgBean sendMsgBean, String publicKey) {
		String signature = sendMsgBean.getSignature();
		StringBuffer data = new StringBuffer();
		data.append("mobile=").append(sendMsgBean.getMobile());
		data.append("&customerId=").append(sendMsgBean.getCustomerId().toString());
		data.append("&templateId=").append(sendMsgBean.getTemplateId().toString());
		data.append("&content=").append(ArrayUtils.toString(sendMsgBean.getContent()));
		LOG.info("data:{}", data.toString());
		LOG.info("md5RsaData:{}", Md5Utils.md5(data.toString()));
		try {
			return RSAUtils.verify(Md5Utils.md5(data.toString()).getBytes("UTF-8"), publicKey, signature);
		}
		catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 
	 * @Title: searchProviderInfo
	 * @Description: 根据客户号查询客户默认发送短信供应商连接信息
	 * @param: @param customerId
	 * @param: @return
	 * @return: CustomerProviderRef
	 * @throws
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	private CustomerProviderRef searchProviderInfo(Long customerId) {
		CustomerProviderRef customerProviderRef = new CustomerProviderRef();
		customerProviderRef.setCustomerId(customerId);
		customerProviderRef.setIsDefault(AjaxResultStatus.Y.getValue());
		customerProviderRef = this.customerProviderRefService.search(customerProviderRef);
		return customerProviderRef;
	}

	/**
	 * 
	 * @Title: searchTemplateInfo
	 * @Description: 根据供应商ID，平台模板ID查询客户对应供应商模板ID
	 * @param: @param providerId
	 * @param: @param templateId
	 * @param: @return
	 * @return: TemplateRef
	 * @throws
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	private TemplateRef searchTemplateInfo(Long providerId, Long templateId) {
		TemplateRef templateRef = new TemplateRef();
		templateRef.setProviderId(providerId);
		templateRef.setTemplateId(templateId);
		templateRef = this.templateRefService.search(templateRef);
		return templateRef;
	}
}
