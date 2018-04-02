package cn.obanks.usp.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
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
import cn.obanks.usp.model.SendMsgBean;
import cn.obanks.usp.utils.HttpSendResult;
import cn.obanks.usp.utils.Md5Utils;
import cn.obanks.usp.utils.RSAUtils;
import cn.obanks.usp.utils.SimpleHttpsClient;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:obanks-context-web-test.xml" })
public class SendMsgControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(SendMsgControllerTest.class);
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private SendMsgController sendMsgController;
	private MockMvc mockMvc;
	private SendMsgBean sendMsgBean = new SendMsgBean();
	private Map<String, String> params = new HashMap<String, String>();
	private String encoding= "UTF-8";
	private String url = "http://localhost:8080/usp/sendmsg/send.do";

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Before
	public void init() throws Exception {
		sendMsgBean.setMobile("13760447366");
		sendMsgBean.setTemplateId(1002l);
		sendMsgBean.setCustomerId(1000l);
		sendMsgBean.setContent(new String[] { "J608DNVt", "abc" });
		StringBuffer data = new StringBuffer();
		data.append("mobile=").append(sendMsgBean.getMobile());
		data.append("&customerId=").append(sendMsgBean.getCustomerId().toString());
		data.append("&templateId=").append(sendMsgBean.getTemplateId().toString());
		data.append("&content=").append(ArrayUtils.toString(sendMsgBean.getContent()));
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMYocGNGFsSa6ApoG5r/HX+7BpttLEad1nYEfZXCNPXcb3qHRtfFzM1BcqQcOWz2heQt1tcrTPnqoqBCLwfped1gpWaIICh+5EaHZiVeFS1FABWQszEZ0t0tdC4pAgtnJZWm1P1jYUMwPl6v/xhIbUlh7+E8VZGXJOnysWRuGiexAgMBAAECgYB3Axik9xZ7lFHi1i4mRCyyZ2CLfuFFtEx26zGF/XMTawZlP9QTic/8Qt+Yov72a4V0yvcqme4Md5ieycE3bklq1lyB36ncO+IAiMHuhZd5yJVzl1PafKzgEAA2AntxY5uYf3KNz6tXPJPfR4KqVaPMDX7YlI49GBAaCww5VCVQZQJBAOyzN+8npp6LkTSWLAFicrFKJaDahKRHM7BoWtAzkL+HF4Z/tz1lJ5EuiCK4BnVmuIUuSkzldWq7+2LHSyghRJ8CQQDWULVKzVXJwBxwGK+UFdvbaHzYZ0eFjkjrW2g/yiRQrnwubT08kLqnvMPEIBe/VLtTCxVKW2xlqZYiWReCJGGvAkEA6zkPFPNqeZRpMMEr6hRTtivhSQDfYAICNAUAendEGZG+p0K0bqbfsz9yuk323SS/qlpeLBy7mt5E69zxq2ekMQJBANYbIVMOmjMJQx/V+armv5ON6pPDcleCE2VWF2sLBoa4GoXDSpc0O9WRpr5MFY1nGBHZk7aKaVuTNdMw0GSK9i8CQAHjPRu4vF4Q2pdd1uzob9rSuBpo+yOOdt4/h1YkIewuZCaJf+wUsGSDHOZh5sfrVqb+Q75jHhIhi53abZb8UEQ=";
		LOG.info("data:{}", data.toString());
		LOG.info("md5RsaData:{}", Md5Utils.md5(data.toString()));
		byte[] bytes = Md5Utils.md5(data.toString()).getBytes(encoding);
		String signature = RSAUtils.sign(bytes, privateKey);
		LOG.info("signature:{}",signature);
		sendMsgBean.setSignature(signature);
		
		params.put("mobile", sendMsgBean.getMobile().toString());
		params.put("customerId", sendMsgBean.getCustomerId().toString());
		params.put("templateId", sendMsgBean.getTemplateId().toString());
		params.put("content", "J608DNVt,abc");
		params.put("signature", sendMsgBean.getSignature());
	}

	@Test
	@Ignore
	public void send() throws Exception {
		MockHttpServletRequestBuilder builder = post("/sendmsg/send.do");
		builder.param("mobile", this.sendMsgBean.getMobile());
		builder.param("templateId", this.sendMsgBean.getTemplateId().toString());
		builder.param("customerId", this.sendMsgBean.getCustomerId().toString());
		builder.param("content", sendMsgBean.getContent());
		builder.param("signature", this.sendMsgBean.getSignature());
		builder.accept(MediaType.ALL);
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
