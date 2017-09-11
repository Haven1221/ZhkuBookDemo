package com.haven.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.Address;

/**
 * 与Address.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("addressDao")
@Scope("prototype")
public interface AddressDAO {

	/**
	 * 插入一条地址记录
	 */
	public int insert(Address addr);
	
	/**
	 * 删除一条地址记录
	 */
	public int deleteOne(int addrId);
	
	/**
	 * 批量删除
	 */
	public int deleteBatch(int[] ids);
	
	/**
	 * 修改地址信息，根据订单编号修改其它信息
	 */
	public int update(Address addr);
	
	/**
	 * 根据用户编号获取地址信息
	 */
	public List<Address> getAddrInfo(int userId);
	
	/**
	 * 下单时选择地址
	 */
	public Address chooseAddr(int addrId);
	
}
