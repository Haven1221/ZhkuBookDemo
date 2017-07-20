package com.haven.model;

/**
 * 店铺实体类，对应数据库中的店铺表
 */
public class Store {

	private int storeId;// 店铺编号
	private String storeName;// 店铺名称
	private String userName;// 店铺主人
	private int storeSaleNum;// 店铺图书总销量
	private double storeSale;// 店铺总销售额
	private int storeScore;// 店铺总评分
	private int storeComment;// 店铺总评论条数
	private double storeEvaluate;// 店铺平均评价
	private String storeDescript;// 店铺描述
	private String storeLicense;// 店铺营业执照
	private String storeState;// 店铺状态（营业中、休息中、整改中）
	private String storeOpenDate;// 店铺开店日期
	
	public Store() {
	}

	public Store(String storeName, String userName, int storeSaleNum, int storeSale, int storeScore, int storeComment,
			int storeEvaluate, String storeDescript, String storeLicense, String storeState, String storeOpenDate) {
//		super();
		this.storeName = storeName;
		this.userName = userName;
		this.storeSaleNum = storeSaleNum;
		this.storeSale = storeSale;
		this.storeScore = storeScore;
		this.storeComment = storeComment;
		this.storeEvaluate = storeEvaluate;
		this.storeDescript = storeDescript;
		this.storeLicense = storeLicense;
		this.storeState = storeState;
		this.storeOpenDate = storeOpenDate;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStoreSaleNum() {
		return storeSaleNum;
	}

	public void setStoreSaleNum(int storeSaleNum) {
		this.storeSaleNum = storeSaleNum;
	}

	public double getStoreSale() {
		return storeSale;
	}

	public void setStoreSale(double storeSale) {
		this.storeSale = storeSale;
	}

	public int getStoreScore() {
		return storeScore;
	}

	public void setStoreScore(int storeScore) {
		this.storeScore = storeScore;
	}

	public int getStoreComment() {
		return storeComment;
	}

	public void setStoreComment(int storeComment) {
		this.storeComment = storeComment;
	}

	public double getStoreEvaluate() {
		return storeEvaluate;
	}

	public void setStoreEvaluate(double storeEvaluate) {
		this.storeEvaluate = storeEvaluate;
	}

	public String getStoreDescript() {
		return storeDescript;
	}

	public void setStoreDescript(String storeDescript) {
		this.storeDescript = storeDescript;
	}

	public String getStoreLicense() {
		return storeLicense;
	}

	public void setStoreLicense(String storeLicense) {
		this.storeLicense = storeLicense;
	}

	public String getStoreState() {
		return storeState;
	}

	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}

	public String getStoreOpenDate() {
		return storeOpenDate;
	}

	public void setStoreOpenDate(String storeOpenDate) {
		this.storeOpenDate = storeOpenDate;
	}
	
}
