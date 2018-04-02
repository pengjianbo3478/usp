package cn.obanks.usp.model;
public class RealNameAuthResult {
	private String respCode;
	private String respMsg;

	public RealNameAuthResult(String respCode, String respMsg) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getRespCode() {
		return respCode;
	}

	public RealNameAuthResult() {
		super();
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
}
