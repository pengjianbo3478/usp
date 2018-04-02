package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.Merchant;

@Repository
public interface MerchantMapper {
	Merchant search(String merId);
}
