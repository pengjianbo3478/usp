package cn.obanks.usp.service;

import cn.obanks.usp.model.TemplateRef;

public interface TemplateRefService {
	
	void add(TemplateRef templateRef);

	TemplateRef search(TemplateRef templateRef);
}
