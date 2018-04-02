package cn.obanks.usp.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.usp.model.RealNameAuthRecord;

@Repository
public interface RealNameAuthRecordMapper {
	int add(RealNameAuthRecord realNnameAuthRecord);

	int merge(RealNameAuthRecord realNnameAuthRecord);
}
