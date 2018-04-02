package cn.obanks.usp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.CustomerMapper;
import cn.obanks.usp.model.Customer;
import cn.obanks.usp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Customer customer) {
		this.customerMapper.add(customer);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer search(Long id) {
		return this.customerMapper.search(id);
	}
}
