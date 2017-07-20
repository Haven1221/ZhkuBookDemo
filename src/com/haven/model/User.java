package com.haven.model;

import java.util.List;

/**
 * 用户实体类，对应数据库中的用户表
 */
public class User {

	private int userId; // 用户编号
	private String userName; // 用户名
	private String password; // 密码
	private String userType; // 用户类型，‘1’表示普通用户，‘2’表示管理员
	private List<Address> addrList; // 用户地址列表
	
	public User() {
	}

	public User(String userName, String password, String userType, List<Address> addrList) {
//		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.addrList = addrList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Address> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<Address> addrList) {
		this.addrList = addrList;
	}
	
}
