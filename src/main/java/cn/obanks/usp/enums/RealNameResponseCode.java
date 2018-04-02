package cn.obanks.usp.enums;
public enum RealNameResponseCode {
	REALNAME_RESPONSE_CODE_00("00"),REALNAME_RESPONSE_CODE_01("01"), REALNAME_RESPONSE_CODE_02("02"),REALNAME_RESPONSE_CODE_03("03"),REALNAME_RESPONSE_CODE_04("04"),REALNAME_RESPONSE_CODE_05("05"),REALNAME_RESPONSE_CODE_06("06"),REALNAME_RESPONSE_CODE_07("07"),REALNAME_RESPONSE_CODE_08("08"),REALNAME_RESPONSE_CODE_09("09");
	private final String value;

	RealNameResponseCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
