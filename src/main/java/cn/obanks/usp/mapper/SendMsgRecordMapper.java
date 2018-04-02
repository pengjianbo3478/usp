package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.SendMsgRecord;

@Repository
public interface SendMsgRecordMapper {
	int add(SendMsgRecord sendMsgRecord);

	int merge(SendMsgRecord sendMsgRecord);
}
