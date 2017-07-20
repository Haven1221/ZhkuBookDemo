package com.haven.model;

/**
 * 临时店铺实体类，对应数据库中的临时店铺表
 */
public class TempStore {

	private int tempStoreId; // 临时店铺编号
	private String userName; // 申请开店的用户名
	private String tempStoreName; // 临时店铺名称
	private String tempStoreDescript; // 店铺描述
	private String tempStoreLicense; // 店铺营业执照
	private String tempStoreState; // 审核状态，进度
	
	public TempStore() {
	}

	public TempStore(String userName, String tempStoreName, String tempStoreDescript, 
			String tempStoreLicense, String tempStoreState) {
//		super();
		this.userName = userName;
		this.tempStoreName = tempStoreName;
		this.tempStoreDescript = tempStoreDescript;
		this.tempStoreLicense = tempStoreLicense;
		this.tempStoreState = tempStoreState;
	}

	public int getTempStoreId() {
		return tempStoreId;
	}

	public void setTempStoreId(int tempStoreId) {
		this.tempStoreId = tempStoreId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTempStoreName() {
		return tempStoreName;
	}

	public void setTempStoreName(String tempStoreName) {
		this.tempStoreName = tempStoreName;
	}

	public String getTempStoreDescript() {
		return tempStoreDescript;
	}

	public void setTempStoreDescript(String tempStoreDescript) {
		this.tempStoreDescript = tempStoreDescript;
	}

	public String getTempStoreLicense() {
		return tempStoreLicense;
	}

	public void setTempStoreLicense(String tempStoreLicense) {
		this.tempStoreLicense = tempStoreLicense;
	}

	public String getTempStoreState() {
		return tempStoreState;
	}

	public void setTempStoreState(String tempStoreState) {
		this.tempStoreState = tempStoreState;
	}
	
}
