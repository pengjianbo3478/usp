package cn.obanks.usp.service.impl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.usp.mapper.SendMsgRecordMapper;
import cn.obanks.usp.model.SendMsgRecord;
import cn.obanks.usp.service.SendMsgRecordService;

@Service
public class SendMsgRecordServiceImpl implements SendMsgRecordService {
	@Autowired
	private SendMsgRecordMapper sendMsgRecordMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(SendMsgRecord sendMsgRecord) {
		sendMsgRecord.setCreatedDate(new Date());
		sendMsgRecordMapper.add(sendMsgRecord);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(SendMsgRecord sendMsgRecord) {
		this.sendMsgRecordMapper.merge(sendMsgRecord);
	}
}
