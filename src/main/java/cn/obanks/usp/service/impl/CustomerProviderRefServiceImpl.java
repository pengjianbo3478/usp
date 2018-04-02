package cn.obanks.usp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.CustomerProviderRefMapper;
import cn.obanks.usp.model.CustomerProviderRef;
import cn.obanks.usp.service.CustomerProviderRefService;

@Service
public class CustomerProviderRefServiceImpl implements CustomerProviderRefService {
	@Autowired
	private CustomerProviderRefMapper customerProviderRefMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(CustomerProviderRef customerProviderRef) {
		this.customerProviderRefMapper.add(customerProviderRef);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomerProviderRef search(CustomerProviderRef customerProviderRef) {
		return this.customerProviderRefMapper.search(customerProviderRef);
	}
}
