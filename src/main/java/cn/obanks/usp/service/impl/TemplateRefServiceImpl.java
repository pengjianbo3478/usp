package cn.obanks.usp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.TemplateRefMapper;
import cn.obanks.usp.model.TemplateRef;
import cn.obanks.usp.service.TemplateRefService;

@Service
public class TemplateRefServiceImpl implements TemplateRefService {
	@Autowired
	private TemplateRefMapper templateRefMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(TemplateRef templateRef) {
		this.templateRefMapper.add(templateRef);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TemplateRef search(TemplateRef templateRef) {
		return this.templateRefMapper.search(templateRef);
	}
}
