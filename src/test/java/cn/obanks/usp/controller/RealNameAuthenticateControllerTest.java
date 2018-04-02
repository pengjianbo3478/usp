package cn.obanks.usp.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import cn.obanks.common.ToString;
import cn.obanks.usp.model.RealNameBean;
import cn.obanks.usp.utils.HttpSendResult;
import cn.obanks.usp.utils.Md5Utils;
import cn.obanks.usp.utils.RSAUtils;
import cn.obanks.usp.utils.SimpleHttpsClient;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:obanks-context-web-test.xml" })
public class RealNameAuthenticateControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(RealNameAuthenticateControllerTest.class);
	@Autowired
	RealNameAuthenticateController realNameAuthenticateController;
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private RealNameBean realNameBean = new RealNameBean();
	private Map<String, String> params = new HashMap<String, String>();
	private String encoding = "UTF-8";
	private String url = "http://localhost:8080/usp/realnameauthenticate/authenticate.do";

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Before
	public void init() throws Exception {
		realNameBean.setUserName("张洪龙");
		realNameBean.setIdCard("210726198802161330");
		realNameBean.setCardNum("6230580000011968620");
		realNameBean.setPhoneNum("18038054690");
		realNameBean.setOrderNumber("20151116184034");
		realNameBean.setMerId("123456");
		StringBuffer md5RsaData = new StringBuffer();
		md5RsaData.append("merId=").append(realNameBean.getMerId());
		md5RsaData.append("&orderNumber=").append(realNameBean.getOrderNumber());
		md5RsaData.append("&idCard=").append(realNameBean.getIdCard());
		md5RsaData.append("&userName=").append(realNameBean.getUserName());
		md5RsaData.append("&phoneNum=").append(realNameBean.getPhoneNum());
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO4F8kofFZMPo/WOODr5I76rUYnAMy9uEFQ3mfUmeNGfYJPDKcsT6Z6KwavKsQNw0ewEPP4xW2zRVdAN6E8MvoIl3nkQQF73PO7LLEJPGAmOmh49VXZCgRNtmXa32zu4FcBsuFd1wWWhF38V6JF/hCJKPkgQEeI7k4P1F2OPz3xTAgMBAAECgYEA2KC02Nzdh9COSqg7aQDilV/af+JbM2malutbxZBUGYRSI2lWGitvTgm/Jhj9bcI6a/GXiUlmie0jio19xqQ0pQF6hvKuSKcxS4H/6Sr6FZJRDknaIgKjoUL6QAWKxLvPYS9SwuI9KCWME872odm0v0QqlA8Ct4DHyznot83YLXECQQD3cbUHpusDQVUz2h9HCl7qAu2PHTyNMEbsmVwYQnAdeyQik3Bc0OqoBQUItlTeKSAl7Z9ex7S2/75BHEBosea9AkEA9kDZKYOBrlu/AMtwzGpEp6tnH/SeXFXshQCswgp9K2x8CV6jPs/MrBcQOUpNhak4rjek7zysScngh32QQuboTwJAdZmR6vgWm25yevlAgjfYkikgFDNV8tgt7QaZ8WGp/SjoLJKcBVC5JW91TAWz4f3zrHL5mkcZiRuUpUczCU77lQJAU4N2op+Y0VsIEers3EaF9h8pICtDsajqR8kMzdnkzV9DSaIw1tJ+ZTaFKAjM9bGIKX1xkDkRbQzr+sdYr3fD/QJBAMVf8o2Q1IYiwozZCX0NjzUnkRLj6oGykUGF2X17PgQWzQiIpwptu2QFYaTi8WzOYZCNv2tKcpu0FP0Lwm9VceM=";
		byte[] bytes = Md5Utils.md5(md5RsaData.toString()).getBytes(this.encoding);
		String signature = RSAUtils.sign(bytes, privateKey);
		realNameBean.setSignature(signature);
		LOG.info("signature={}", signature);
		params.put("merId", realNameBean.getMerId());
		params.put("orderNumber", realNameBean.getOrderNumber());
		params.put("idCard", realNameBean.getIdCard());
		params.put("cardNum", realNameBean.getCardNum());
		params.put("userName", realNameBean.getUserName());
		params.put("phoneNum", realNameBean.getPhoneNum());
		params.put("signature", realNameBean.getSignature());
	}

	@Test
	@Ignore
	public void authenticate() throws Exception {
		MockHttpServletRequestBuilder builder = post("/realnameauthenticate/authenticate.do");
		builder.param("merId", realNameBean.getMerId());
		builder.param("orderNumber", realNameBean.getOrderNumber());
		builder.param("cardNum", realNameBean.getCardNum());
		builder.param("userName", realNameBean.getUserName());
		builder.param("idCard", realNameBean.getIdCard());
		builder.param("phoneNum", realNameBean.getPhoneNum());
		builder.param("signature", realNameBean.getSignature());
		builder.accept(MediaType.APPLICATION_JSON);
		ResultActions resultActions = this.mockMvc.perform(builder);
		resultActions.andExpect(status().isOk());
		resultActions.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test() throws Exception {
		SimpleHttpsClient simpleHttpsClient = new SimpleHttpsClient();
		HttpSendResult httpSendResult = simpleHttpsClient.postRequest(url, this.params, 20000, this.encoding);
		LOG.info("httpSendResult:{}", ToString.reflectionToString(httpSendResult));
	}
	
}
