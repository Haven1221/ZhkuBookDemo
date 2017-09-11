package com.haven.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.Address;
import com.haven.model.Order;

/**
 *  与Order.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("orderDao")
@Scope("prototype")
public interface OrderDAO {

	/**
	 * 插入一条订单信息
	 */
	public int insert(Order o);
	
	/**
	 * 删除一条订单信息
	 */
	public int deleteOne(int orderId);
	
	/**
	 * 批量删除订单
	 */
	public int deleteBatch(int[] ids);
	
	/**
	 * 修改订单状态，传入的参数是订单的编号和新的状态
	 */
	public int update(Order o);
	
	/**
	 * 获取订单信息。可以根据用户名、店铺名或者订单状态获取
	 */
	public List<Order> getOrderInfoByPage(Map<String, Object> parameter);
	
	/**
	 * 获取订单送货地址
	 */
	public Address getOrderAddr(int orderId);
	
}
