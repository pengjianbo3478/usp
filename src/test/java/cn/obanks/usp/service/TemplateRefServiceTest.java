
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
import cn.obanks.usp.model.TemplateRef;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class TemplateRefServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private TemplateRefService templateRefService;
	private TemplateRef templateRef  = new TemplateRef();
	
	
	@Before
	public void init() throws Exception {
		this.templateRef.setProviderId(1000L);
		this.templateRef.setTemplateId(1002L);
		this.templateRef.setCode(49481l);
		this.templateRef.setCreatedBy(0l);
		this.templateRef.setCreatedDate(new Date());
		this.templateRef.setUpdatedBy(0l);
		this.templateRef.setUpdatedDate(new Date());
		this.templateRef.setDeletedFlag(DeletedFlag.NORMAL.getValue());
	}

	@Test
	public void add() throws Exception {
		this.templateRefService.add(templateRef);
	}
}
