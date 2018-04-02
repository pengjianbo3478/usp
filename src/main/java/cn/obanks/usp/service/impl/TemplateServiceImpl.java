package cn.obanks.usp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.TemplateMapper;
import cn.obanks.usp.model.Template;
import cn.obanks.usp.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateMapper templateMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Template template) {
		this.templateMapper.add(template);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Template search(Long id) {
		return this.templateMapper.search(id);
	}
}
