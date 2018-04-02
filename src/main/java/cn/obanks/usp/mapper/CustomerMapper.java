package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.Customer;

@Repository
public interface CustomerMapper {
	int add(Customer customer);

	Customer search(Long id);
}
