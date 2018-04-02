package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.CustomerProviderRef;

@Repository
public interface CustomerProviderRefMapper {
	int add(CustomerProviderRef customerProviderRef);

	CustomerProviderRef search(CustomerProviderRef customerProviderRef);
}
