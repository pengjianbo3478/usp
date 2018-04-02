package cn.obanks.usp.service;
import cn.obanks.usp.model.SendMsgBean;
import cn.obanks.usp.model.SendMsgResult;

public interface SendMsgService {
	SendMsgResult sendMsg(SendMsgBean sendMsgBean);
}
