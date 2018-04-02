package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.TemplateRef;

@Repository
public interface TemplateRefMapper {
	int add(TemplateRef templateRef);

	TemplateRef search(TemplateRef templateRef);
}
