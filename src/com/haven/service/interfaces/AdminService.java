package com.haven.service.interfaces;

import java.util.Map;

import com.haven.model.Store;

/**
 * 管理员业务层接口
 */
public interface AdminService {

	// 对用户表的操作
	
	/**
	 * 用户管理员查看所有用户信息，分页显示，也可以根据用户名查询
	 * @param userName		：用户名
	 * @param currentPage	：当前页
	 * @return				：成功则返回用户信息列表，否则返回null。
	 */
	Map<String, Object> getUser(String userName, String currentPage);
	
	/**
	 * 根据用户编号删除用户信息，包括单个删除和批量删除
	 * 删除成功之后按照用户名和当前页获取用户信息，返回当前页用户信息
	 * @param ids			：用户编号
	 * @param userName		：用户名
	 * @param currentPage	：当前页
	 * @return				：成功则返回当前页用户信息， 否则返回null。
	 */
	Map<String, Object> deleteUser(String[] ids, String userName, String currentPage);
	
	/**
	 * 修改用户类型，包括普通用户和管理员
	 * @param userId		：用户编号
	 * @param newType		：用户新类型
	 * @param userName		：用户名
	 * @param currentPage	：当前页
	 * @return				：成功则返回当前页用户信息， 否则返回null。
	 */
	Map<String, Object> updateUserType(String userId, String newType, String userName, String currentPage);
	
	// 对店铺表的操作
	
	/**
	 * 根据当前页数获取临时店铺信息列表
	 * @param currentPage	：当前页
	 * @return				：成功则返回临时店铺信息列表，否则返回null。
	 */
	Map<String, Object> getTempStore(String tempStoreState, String currentPage);
	
	/**
	 * 根据临时店铺进度（状态）获取店铺信息列表
	 * @param tempStoreState	：店铺进度（状态）
	 * @return					：成功则返回历史店铺信息列表，否则返回null。
	 */
//	List<TempStore> getTempStoreByState(String tempStoreState);
	
	/**
	 * 根据临时店铺编号修改申请进度（状态）
	 * @param tempStoreId	：临时店铺编号
	 * @param newState		：修改的进度
	 * @return				：成功则返回true，否则返回false。
	 */
	Map<String, Object> updateTempStore(String tempStoreId, String newState, String tempStoreState, String currentPage);
	
	/**
	 * 根据临时店铺编号删除临时店铺，包括单个删除以及批量删除
	 * @param ids	：临时店铺编号
	 * @return		：成功则返回true，否则返回false。
	 */
	Map<String, Object> deleteTempStore(String[] ids, String tempStoreState, String currentPage);
	
	/**
	 * 将通过审核的临时店铺添加到店铺表中
	 * @param tempStoreId	：临时店铺编号
	 * @return				；成功则返回店铺信息，否则返回null。
	 */
	Store addStore(String tempStoreId);
	
	/**
	 * 根据店铺编号删除店铺信息，包括单个删除和批量删除
	 * @param ids	：店铺编号
	 * @return		：成功则返回true，否则返回false。
	 */
	boolean deleteStore(String[] ids);
	
	/**
	 * 根据店铺名修改店铺状态，修改成功更新用户信息列表
	 * @param storeName		：店铺名称
	 * @param storeState	：修改的状态
	 * @param currentPage	：当前页
	 * @return				：成功则返回店铺信息列表，否则返回null。
	 */
	Map<String, Object> updateStoreState(String storeName, String storeState, String currentPage);
	
	/**
	 * 根据店铺名查询店铺信息
	 * @param storeName	：店铺名称
	 * @return			：成功则返回店铺信息列表，否则返回null。
	 */
	Store getStoreInfo(String storeName);
	
	/**
	 * 查看所有店铺信息，分页显示
	 * @param storeName		：店铺名，不为空则按店铺名查询
	 * @param currentPage	：当前页
	 * @return				：成功则返回店铺信息列表，否则返回null。
	 */
	Map<String, Object> getAllStore(String storeName, String currentPage);
	
}
