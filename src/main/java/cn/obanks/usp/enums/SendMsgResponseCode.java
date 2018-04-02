package cn.obanks.usp.enums;
public enum SendMsgResponseCode {
	// 01手机号为空 02客户号为空 03调用短线接口无返回 04 签名无效
	SEND_MSG_RESPONSE_CODE_00("00"),SEND_MSG_RESPONSE_CODE_01("01"), SEND_MSG_RESPONSE_CODE_02("02"), SEND_MSG_RESPONSE_CODE_03("03"), SEND_MSG_RESPONSE_CODE_04("04"), SEND_MSG_RESPONSE_CODE_05("05"), SEND_MSG_RESPONSE_CODE_06("06"), SEND_MSG_RESPONSE_CODE_07("07"), SEND_MSG_RESPONSE_CODE_08("08"), SEND_MSG_RESPONSE_CODE_09("09"), REALNAME_RESPONSE_SUCCESS("000000");
	private final String value;

	SendMsgResponseCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
