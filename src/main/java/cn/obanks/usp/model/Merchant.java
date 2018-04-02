package cn.obanks.usp.model;
import java.util.Date;

/**
 * 
 * @ClassName: Merchant
 * @Description:实名认证商户信息
 * @author: pengjianbo3478
 * @date: 2015年11月19日 下午2:44:37
 */
public class Merchant {
	private Long id;
	private String merId;
	private String merName;
	private String secretKey;
	private String publicKey;
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

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
