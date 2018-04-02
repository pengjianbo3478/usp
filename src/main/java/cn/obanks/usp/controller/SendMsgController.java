package cn.obanks.usp.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.obanks.usp.enums.SendMsgResponseCode;
import cn.obanks.usp.jms.JsonSerializationUtil;
import cn.obanks.usp.model.SendMsgBean;
import cn.obanks.usp.model.SendMsgResult;

@Controller
@RequestMapping("/sendmsg")
public class SendMsgController {
	@Autowired
	private AmqpTemplate smsAmqpTemplate;

	// @Autowired
	// private SendMsgService sendMsgService;
	@Resource
	private Map<String, String> sendMsgResponseCodeMap;
	// @Value("${usp.sm.queue.name}")
	// private String smQueue;
	// @Autowired
	// private ConnectionFactory connectionFactory;

	@RequestMapping(method = RequestMethod.POST, value = "send")
	@ResponseBody
	public SendMsgResult send(@ModelAttribute SendMsgBean sendMsgBean) throws AmqpException, IOException {
		SendMsgResult sendMsgResult = new SendMsgResult(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_00.getValue(),
				sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_00.getValue()));
		Long customerId = sendMsgBean.getCustomerId();
		if (customerId == null) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_05.getValue());
			sendMsgResult
					.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_05.getValue()));
			return sendMsgResult;
		}
		String mobile = sendMsgBean.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_06.getValue());
			sendMsgResult
					.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_06.getValue()));
			return sendMsgResult;
		}
		String signature = sendMsgBean.getSignature();
		if (StringUtils.isEmpty(signature)) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_07.getValue());
			sendMsgResult
					.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_07.getValue()));
			return sendMsgResult;
		}
		Long templateId = sendMsgBean.getTemplateId();
		if (templateId == null) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_08.getValue());
			sendMsgResult
					.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_08.getValue()));
			return sendMsgResult;
		}
		String[] content = sendMsgBean.getContent();
		if (ArrayUtils.isEmpty(content)) {
			sendMsgResult.setRespCode(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_09.getValue());
			sendMsgResult
					.setRespMsg(sendMsgResponseCodeMap.get(SendMsgResponseCode.SEND_MSG_RESPONSE_CODE_09.getValue()));
			return sendMsgResult;
		}

		// MessageUtils.send(connectionFactory, smQueue, sendMsgBean);
		smsAmqpTemplate.convertAndSend(JsonSerializationUtil.serialize(sendMsgBean));

		return sendMsgResult;
	}
}
