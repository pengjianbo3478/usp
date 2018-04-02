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

import cn.obanks.common.DeletedFlag;
import cn.obanks.usp.model.Provider;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class ProviderServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private ProviderService providerService;
	private Provider provider = new Provider();

	@Before
	public void init() throws Exception {
		this.provider.setName("容联云通讯");
		this.provider.setCreatedBy(0l);
		this.provider.setCreatedDate(new Date());
		this.provider.setUpdatedBy(0l);
		this.provider.setUpdatedDate(new Date());
		this.provider.setDeletedFlag(DeletedFlag.NORMAL.getValue());
	}

	@Test
	public void add() throws Exception {
		this.providerService.add(provider);
	}
}
