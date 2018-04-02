package cn.obanks.usp.service;
import cn.obanks.usp.model.SendMsgRecord;

public interface SendMsgRecordService {
	void add(SendMsgRecord sendMsgRecord);

	void merge(SendMsgRecord sendMsgRecord);
}
