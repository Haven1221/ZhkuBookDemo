package com.haven.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.User;

/**
 *  与User.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("userDao")
@Scope("prototype")
public interface UserDAO {

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
	public int deleteBatch(int[] ids);
	
	/**
	 * 修改用户信息
	 */
	public int update(User user);
	
	/**
	 * 获取用户信息
	 */
	public List<User> getUserInfo(User user);
	
	/**
	 * 用于管理员查看用户信息，分页显示
	 */
	public List<User> getUserByPage(Map<String, Object> parameter);
	
}
