package cn.obanks.usp.service;

import cn.obanks.usp.model.RealNameAuthRecord;

public interface RealNameAuthRecordService {
	
	void add(RealNameAuthRecord realNnameAuthRecord);

	void merge(RealNameAuthRecord realNnameAuthRecord);
}
