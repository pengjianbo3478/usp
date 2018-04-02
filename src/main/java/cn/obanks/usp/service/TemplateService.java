package cn.obanks.usp.service;

import cn.obanks.usp.model.Template;

public interface TemplateService {
	
	void add(Template template);

	Template search(Long id);
}
