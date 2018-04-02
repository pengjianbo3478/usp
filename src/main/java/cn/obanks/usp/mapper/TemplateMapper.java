package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.Template;

@Repository
public interface TemplateMapper {
	int add(Template template);

	Template search(Long id);
}
