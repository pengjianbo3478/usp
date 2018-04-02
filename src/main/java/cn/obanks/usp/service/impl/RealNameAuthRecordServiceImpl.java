package cn.obanks.usp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.RealNameAuthRecordMapper;
import cn.obanks.usp.model.RealNameAuthRecord;
import cn.obanks.usp.service.RealNameAuthRecordService;

@Service
public class RealNameAuthRecordServiceImpl implements RealNameAuthRecordService {
	@Autowired
	private RealNameAuthRecordMapper realNameAuthRecordMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(RealNameAuthRecord realNnameAuthRecord) {
		this.realNameAuthRecordMapper.add(realNnameAuthRecord);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(RealNameAuthRecord realNnameAuthRecord) {
		this.realNameAuthRecordMapper.merge(realNnameAuthRecord);
	}
}
