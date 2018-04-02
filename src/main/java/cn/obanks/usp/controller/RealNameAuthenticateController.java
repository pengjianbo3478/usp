package cn.obanks.usp.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.obanks.usp.enums.RealNameResponseCode;
import cn.obanks.usp.jms.JsonSerializationUtil;
import cn.obanks.usp.model.RealNameAuthResult;
import cn.obanks.usp.model.RealNameBean;
import cn.obanks.usp.service.RealNameAuthenticateService;

@Controller
@RequestMapping("/realnameauthenticate")
public class RealNameAuthenticateController {
	@Autowired
	private AmqpTemplate realnameAmqpTemplate;

	@Autowired
	private RealNameAuthenticateService realNameAuthenticateService;
	@Resource
	private Map<String, String> realNameResponseCodeMap;
	// @Value("${usp.realname.queue.name}")
	// private String realnameQueue;
	// @Autowired
	// private ConnectionFactory connectionFactory;

	@RequestMapping(method = RequestMethod.POST, value = "authenticate")
	@ResponseBody
	public RealNameAuthResult authenticate(Model model, @ModelAttribute RealNameBean realNameBean)
			throws AmqpException, IOException {
		RealNameAuthResult realNameAuthResult = new RealNameAuthResult(
				RealNameResponseCode.REALNAME_RESPONSE_CODE_00.getValue(),
				realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_00.getValue()));
		String cardNum = realNameBean.getCardNum();
		if (StringUtils.isEmpty(cardNum)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_03.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_03.getValue()));
			return realNameAuthResult;
		}
		String idCard = realNameBean.getIdCard();
		if (StringUtils.isEmpty(idCard)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_04.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_04.getValue()));
			return realNameAuthResult;
		}
		String merId = realNameBean.getMerId();
		if (StringUtils.isEmpty(merId)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_05.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_05.getValue()));
			return realNameAuthResult;
		}
		String orderNumber = realNameBean.getOrderNumber();
		if (StringUtils.isEmpty(orderNumber)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_06.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_06.getValue()));
			return realNameAuthResult;
		}
		String phoneNum = realNameBean.getPhoneNum();
		if (StringUtils.isEmpty(phoneNum)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_07.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_07.getValue()));
			return realNameAuthResult;
		}
		String signature = realNameBean.getSignature();
		if (StringUtils.isEmpty(signature)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_08.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_08.getValue()));
			return realNameAuthResult;
		}
		String userName = realNameBean.getUserName();
		if (StringUtils.isEmpty(userName)) {
			realNameAuthResult.setRespCode(RealNameResponseCode.REALNAME_RESPONSE_CODE_09.getValue());
			realNameAuthResult
					.setRespMsg(realNameResponseCodeMap.get(RealNameResponseCode.REALNAME_RESPONSE_CODE_09.getValue()));
			return realNameAuthResult;
		}
		// MessageUtils.send(connectionFactory, realnameQueue, realNameBean);
//		realnameAmqpTemplate.convertAndSend(JsonSerializationUtil.serialize(realNameBean));
		return realNameAuthenticateService.authenticate(realNameBean);
//		return realNameAuthResult;
	}
}
