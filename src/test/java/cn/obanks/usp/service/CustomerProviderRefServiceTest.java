package cn.obanks.usp.service;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.obanks.common.AjaxResultStatus;
import cn.obanks.common.DeletedFlag;
import cn.obanks.usp.model.CustomerProviderRef;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class CustomerProviderRefServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private CustomerProviderRefService customerProviderRefService;
	private CustomerProviderRef customerProviderRef = new CustomerProviderRef();

	@Before
	public void init() throws Exception {
		this.customerProviderRef.setAccount("8a48b5514c1103b4014c20bd2bb30807");
		this.customerProviderRef.setAppId("8a48b55150b36d920150b756f18a0b90");
		this.customerProviderRef.setAuthToken("7188599c27a446a9b9fbbb47b1458d95");
		this.customerProviderRef.setPort("8883");
		this.customerProviderRef.setProviderId(1000l);
		this.customerProviderRef.setCustomerId(1000l);
		this.customerProviderRef.setServiceUrl("sandboxapp.cloopen.com");
		this.customerProviderRef.setIsDefault(AjaxResultStatus.Y.getValue());
		this.customerProviderRef.setCreatedBy(0l);
		this.customerProviderRef.setCreatedDate(new Date());
		this.customerProviderRef.setUpdatedBy(0l);
		this.customerProviderRef.setUpdatedDate(new Date());
		this.customerProviderRef.setDeletedFlag(DeletedFlag.NORMAL.getValue());
	}

	@Test
	public void add() throws Exception {
		this.customerProviderRefService.add(customerProviderRef);
	}
}
