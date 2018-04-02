package cn.obanks.usp.model;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: RealNnameAuthRecord
 * @Description:实名认证记录
 * @author: pengjianbo3478
 * @date: 2015年11月19日 下午2:48:42
 */
public class RealNameAuthRecord {
	private Long id;
	private String merId;
	private String orderNumber;
	private String cardNum;
	private String userName;
	private String idCard;
	private String phoneNum;
	private String signature;
	private String respCode;
	private String respMsg;
	private BigDecimal respElapsed;
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRespCode() {
		return respCode;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getRespElapsed() {
		return respElapsed;
	}

	public void setRespElapsed(BigDecimal respElapsed) {
		this.respElapsed = respElapsed;
	}
}
