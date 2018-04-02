package cn.obanks.usp.service;
import cn.obanks.usp.model.RealNameAuthResult;
import cn.obanks.usp.model.RealNameBean;

public interface RealNameAuthenticateService {
	RealNameAuthResult authenticate(RealNameBean realNameBean);
}
