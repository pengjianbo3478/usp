package cn.obanks.usp.service;
import cn.obanks.usp.model.Provider;

public interface ProviderService {
	void add(Provider provider);

	Provider search(Long id);
}
