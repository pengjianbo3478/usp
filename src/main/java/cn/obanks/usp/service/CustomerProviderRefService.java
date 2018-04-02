
package cn.obanks.usp.service;

import cn.obanks.usp.model.CustomerProviderRef;


public interface CustomerProviderRefService {
	
	void add(CustomerProviderRef customerProviderRef);

	CustomerProviderRef search(CustomerProviderRef customerProviderRef);
}
