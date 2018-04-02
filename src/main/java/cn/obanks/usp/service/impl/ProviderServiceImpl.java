package cn.obanks.usp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.ProviderMapper;
import cn.obanks.usp.model.Provider;
import cn.obanks.usp.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper providerMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Provider provider) {
		this.providerMapper.add(provider);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Provider search(Long id) {
		return this.providerMapper.search(id);
	}
}
