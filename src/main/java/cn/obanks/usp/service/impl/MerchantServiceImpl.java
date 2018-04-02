
package cn.obanks.usp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.obanks.usp.mapper.MerchantMapper;
import cn.obanks.usp.model.Merchant;
import cn.obanks.usp.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired
	private MerchantMapper merchantMapper;

	/**   
	 * <p>Title: search</p>   
	 * <p>Description: </p>   
	 * @param merId
	 * @return   
	 * @see cn.obanks.p2p.service.MerchantService#search(java.lang.Long)   
	 */ 
	@Override
	public Merchant search(String merId) {
		return this.merchantMapper.search(merId) ;
	}
	
}
