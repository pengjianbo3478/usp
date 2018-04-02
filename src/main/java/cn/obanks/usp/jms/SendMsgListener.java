package cn.obanks.usp.jms;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.obanks.common.ToString;
import cn.obanks.usp.model.SendMsgBean;
import cn.obanks.usp.service.SendMsgService;

@Component
public class SendMsgListener {
	private static final Logger LOG = LoggerFactory.getLogger(SendMsgListener.class);
	@Autowired
	private SendMsgService sendMsgService;

	public void sendMsg(String message) {
		try {
			SendMsgBean sendMsgBean = JsonSerializationUtil.deserialzie(message, SendMsgBean.class);
			LOG.debug("received " + ToString.reflectionToString(sendMsgBean));
			this.sendMsgService.sendMsg(sendMsgBean);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
