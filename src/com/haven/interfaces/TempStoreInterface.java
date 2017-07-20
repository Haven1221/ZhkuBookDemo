package com.haven.interfaces;

import java.util.List;

import com.haven.model.TempStore;

/**
 *  与TempStore.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
public interface TempStoreInterface {

	/**
	 * 插入一条记录
	 */
	public int insert(TempStore ts);
	
	/**
	 * 删除一条记录
	 */
	public int deleteOne(int tempStoreId);
	
	/**
	 * 批量删除
	 */
	public int deleteBatch(int[] ids);
	
	/**
	 * 修改店铺状态
	 */
	public int update(TempStore ts);
	
	/**
	 * 获取临时店铺信息，可根据用户名或者店铺状态获取
	 */
	public List<TempStore> getTempStoreInfo(TempStore ts);
	
}
