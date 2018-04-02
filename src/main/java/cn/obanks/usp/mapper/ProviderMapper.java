package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.Provider;

@Repository
public interface ProviderMapper {
	int add(Provider provider);

	Provider search(Long id);
}
