package cn.obanks.usp.service;
import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.obanks.common.DeletedFlag;
import cn.obanks.usp.model.Customer;
import cn.obanks.usp.utils.RSAUtils;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class CustomerServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private CustomerService customerService;
	private Customer customer = new Customer();

	@Before
	public void init() throws Exception {
		this.customer.setName("欧巴金融");
		Map<String, Object> genKeyPair = RSAUtils.genKeyPair();
		String privateKey = RSAUtils.getPrivateKey(genKeyPair);
		String publicKey = RSAUtils.getPublicKey(genKeyPair);
		this.customer.setPublicKey(publicKey);
		this.customer.setSecretKey(privateKey);
		this.customer.setCreatedBy(0l);
		this.customer.setCreatedDate(new Date());
		this.customer.setUpdatedBy(0l);
		this.customer.setUpdatedDate(new Date());
		this.customer.setDeletedFlag(DeletedFlag.NORMAL.getValue());
	}

	@Test
	public void add() throws Exception {
		this.customerService.add(customer);
	}
}
