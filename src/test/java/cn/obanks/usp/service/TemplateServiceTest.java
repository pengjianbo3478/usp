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
import cn.obanks.usp.model.Template;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:obanks-context-service-test.xml" })
@Rollback(false)
public class TemplateServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private TemplateService templateService;
	private Template template = new Template();

	@Before
	public void init() throws Exception {
		this.template.setCustomerId(1002l);
		this.template.setContent("您的验证码是{}");
		this.template.setCreatedBy(0l);
		this.template.setCreatedDate(new Date());
		this.template.setUpdatedBy(0l);
		this.template.setUpdatedDate(new Date());
		this.template.setDeletedFlag(DeletedFlag.NORMAL.getValue());
	}

	@Test
	public void add() throws Exception {
		this.templateService.add(template);
	}
}
