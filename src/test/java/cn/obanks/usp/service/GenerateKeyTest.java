package cn.obanks.usp.service;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.obanks.usp.utils.RSAUtils;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class GenerateKeyTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateKeyTest.class);

	@Test
	public void generateKey() throws Exception {
		Map<String, Object> map = RSAUtils.genKeyPair();
		String privateKey = RSAUtils.getPrivateKey(map);
		String publicKey = RSAUtils.getPublicKey(map);
		LOGGER.info("privateKey={}", privateKey);
		LOGGER.info("privateKey length={}", privateKey.length());
		LOGGER.info("publicKey={}", publicKey);
		LOGGER.info("publicKey length={}", publicKey.length());
	}
}
