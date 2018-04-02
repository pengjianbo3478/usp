package cn.obanks.usp.service;
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
import cn.obanks.usp.model.RealNameAuthResult;
import cn.obanks.usp.model.RealNameBean;
import cn.obanks.usp.utils.Md5Utils;
import cn.obanks.usp.utils.RSAUtils;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class RealNameAuthenticateServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(RealNameAuthenticateServiceTest.class);
	@Autowired
	private RealNameAuthenticateService realNameAuthenticateService;
	@Autowired
	private MerchantService merchantService;
	private RealNameBean realNameBean = new RealNameBean();

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
		byte[] bytes = Md5Utils.md5(md5RsaData.toString()).getBytes("UTF-8");
		String signature = RSAUtils.sign(bytes, privateKey);
		realNameBean.setSignature(signature);
	}

	@Test(timeout = 32000)
	public void authenticate() throws Exception {
		RealNameAuthResult realNameAuthResult = this.realNameAuthenticateService.authenticate(realNameBean);
		LOGGER.info("realNameAuthResult={}", ToString.reflectionToString(realNameAuthResult));
	}
}
