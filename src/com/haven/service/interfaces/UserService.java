package com.haven.service.interfaces;

import java.util.List;

import com.haven.model.Address;
import com.haven.model.Store;
import com.haven.model.TempStore;
import com.haven.model.User;

/**
 * 用户业务层接口：包括地址管理业务、购物车管理业务以及个人信息管理业务
 */
public interface UserService {

	// 对用户表的操作
	
	/**
	 * 登录
	 * @param userName	：用户名
	 * @param password	：密码
	 * @return			：登录成功返回用户对象，否则返回null。
	 */
	User login(String userName, String password);
	
	/**
	 * 注册
	 * @param userName			：用户名
	 * @param password			：密码
	 * @param confirmPassword	：确认密码
	 * @return					：注册成功返回用户对象，否则返回null。
	 */
	User register(String userName, String password, String confirmPassword);
	
	/**
	 * 根据用户编号修改密码
	 * @param userId				：用户编号
	 * @param userName				：用户名
	 * @param oldPassword			：旧密码
	 * @param newPassword			：新密码
	 * @param confirmNewPassword	：确认新密码
	 * @return						：修改成功返回true，否则返回false。
	 */
	boolean updatePassword(int userId, String userName, String oldPassword, String newPassword, String confirmNewPassword);
	
	// 对地址表的操作
	
	/**
	 * 用户添加地址
	 * @param a	：地址信息
	 * @return	：成功则返回地址信息，否则返回null。
	 */
	Address addAddr(Address a);
	
	/**
	 * 根据地址编号删除地址信息，包括单条删除以及批量删除
	 * @param ids	：地址编号
	 * @return		：成功则返回true，否则返回false。
	 */
	boolean deleteAddr(String[] ids);
	
	/**
	 * 根据地址编号修改地址信息
	 * @param a	：地址信息
	 * @return	：成功则返回true，否则返回false。
	 */
	boolean updateAddr(Address a);
	
	/**
	 * 根据用户编号获取用户地址
	 * @param userId	：用户编号
	 * @return			：成功则返回地址列表信息，否则返回null。
	 */
	List<Address> getAddrInfo(int userId);
	
	/**
	 * 根据地址编号获取地址信息
	 * @param addrId	：地址编号
	 * @return			：成功则返回地址信息，否则返回null。
	 */
	Address chooseAddr(String addrId);
	
	// 对店铺表的操作
	
	/**
	 * 用于用户申请开店铺，插入前判断店铺名是否已经存在
	 * @param ts	：临时店铺对象
	 * @return		：成功则返回临时店铺信息，否则返回null。
	 */
	TempStore addStore(TempStore ts);
	
	/**
	 * 用于用户查看申请店铺进度（状态）
	 * @param userName	：用户名
	 * @return			：成功则返回该用户申请的临时店铺信息，否则返回null。
	 */
	TempStore checkState(String userName);
	
	/**
	 * 用于用户进入店铺
	 * @param storeId	：店铺编号
	 * @param userName	：店主
	 * @return			：成功则返回店铺信息，否则返回null。
	 */
	Store enterStore(String storeId, String userName);
	
}
