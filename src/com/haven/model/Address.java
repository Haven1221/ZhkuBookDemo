package com.haven.model;

/**
 * 地址实体类，对应数据库中的地址表
 */
public class Address {

	private int addrId;
	private int userId;
	private String addrProvince;
	private String addrDetail;
	private String addrPhone;
	private String addrConsinee;
	
	public Address() {
	}

	public Address(int userId, String addrProvince, String addrDetail, String addrPhone, String addrConsinee) {
//		super();
		this.userId = userId;
		this.addrProvince = addrProvince;
		this.addrDetail = addrDetail;
		this.addrPhone = addrPhone;
		this.addrConsinee = addrConsinee;
	}

	public int getAddrId() {
		return addrId;
	}

	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getAddrPhone() {
		return addrPhone;
	}

	public void setAddrPhone(String addrPhone) {
		this.addrPhone = addrPhone;
	}
	
	public String getAddrConsinee() {
		return addrConsinee;
	}
	
	public void setAddrConsinee(String addrConsinee) {
		this.addrConsinee = addrConsinee;
	}
	
}
