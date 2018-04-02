package cn.obanks.usp.service;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.obanks.common.ToString;
import cn.obanks.usp.model.SendMsgBean;
import cn.obanks.usp.model.SendMsgResult;
import cn.obanks.usp.utils.Md5Utils;
import cn.obanks.usp.utils.RSAUtils;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class SendMsgServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgServiceTest.class);
	@Autowired
	private SendMsgService sendMsgService;
	private SendMsgBean sendMsgBean = new SendMsgBean();

	@Before
	public void init() throws Exception {
		sendMsgBean.setMobile("13760447366");
		sendMsgBean.setTemplateId(1002l);
		sendMsgBean.setCustomerId(1000l);
		sendMsgBean.setContent(new String[] { "J608DNVt" });
		StringBuffer data = new StringBuffer();
		data.append("mobile=").append(sendMsgBean.getMobile());
		data.append("&customerId=").append(sendMsgBean.getCustomerId().toString());
		data.append("&templateId=").append(sendMsgBean.getTemplateId().toString());
		data.append("&content=").append(ArrayUtils.toString(sendMsgBean.getContent()));
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMYocGNGFsSa6ApoG5r/HX+7BpttLEad1nYEfZXCNPXcb3qHRtfFzM1BcqQcOWz2heQt1tcrTPnqoqBCLwfped1gpWaIICh+5EaHZiVeFS1FABWQszEZ0t0tdC4pAgtnJZWm1P1jYUMwPl6v/xhIbUlh7+E8VZGXJOnysWRuGiexAgMBAAECgYB3Axik9xZ7lFHi1i4mRCyyZ2CLfuFFtEx26zGF/XMTawZlP9QTic/8Qt+Yov72a4V0yvcqme4Md5ieycE3bklq1lyB36ncO+IAiMHuhZd5yJVzl1PafKzgEAA2AntxY5uYf3KNz6tXPJPfR4KqVaPMDX7YlI49GBAaCww5VCVQZQJBAOyzN+8npp6LkTSWLAFicrFKJaDahKRHM7BoWtAzkL+HF4Z/tz1lJ5EuiCK4BnVmuIUuSkzldWq7+2LHSyghRJ8CQQDWULVKzVXJwBxwGK+UFdvbaHzYZ0eFjkjrW2g/yiRQrnwubT08kLqnvMPEIBe/VLtTCxVKW2xlqZYiWReCJGGvAkEA6zkPFPNqeZRpMMEr6hRTtivhSQDfYAICNAUAendEGZG+p0K0bqbfsz9yuk323SS/qlpeLBy7mt5E69zxq2ekMQJBANYbIVMOmjMJQx/V+armv5ON6pPDcleCE2VWF2sLBoa4GoXDSpc0O9WRpr5MFY1nGBHZk7aKaVuTNdMw0GSK9i8CQAHjPRu4vF4Q2pdd1uzob9rSuBpo+yOOdt4/h1YkIewuZCaJf+wUsGSDHOZh5sfrVqb+Q75jHhIhi53abZb8UEQ=";
		byte[] bytes = Md5Utils.md5(data.toString()).getBytes();
		String signature = RSAUtils.sign(bytes, privateKey);
		sendMsgBean.setSignature(signature);
	}

	@Test
	public void send() throws Exception {
		SendMsgResult sendMsgResult = this.sendMsgService.sendMsg(sendMsgBean);
		LOGGER.info("sendMsgResult={}", ToString.reflectionToString(sendMsgResult));
	}
}
