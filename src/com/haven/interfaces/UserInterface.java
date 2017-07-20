package com.haven.interfaces;

import java.util.List;

import com.haven.model.User;

/**
 *  与User.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
public interface UserInterface {

	/**
	 * 用户注册
	 */
	public int register(User user);
	
	/**
	 * 删除单个用户
	 */
	public int deleteOne(int userId);
	
	/**
	 * 批量删除用户
	 */
	public int deleteBatch(List<Integer> ids);
	
	/**
	 * 修改用户信息
	 */
	public int update(User user);
	
	/**
	 * 获取用户信息
	 */
	public List<User> getUserInfo(User user);
	
}
