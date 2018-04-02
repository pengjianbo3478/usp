package cn.obanks.usp.service;
import cn.obanks.usp.model.Customer;

public interface CustomerService {
	void add(Customer customer);

	Customer search(Long id);
}
