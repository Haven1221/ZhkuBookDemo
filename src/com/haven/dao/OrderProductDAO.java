package com.haven.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.OrderProduct;

/**
 *  与OrderProduct.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("productDao")
@Scope("prototype")
public interface OrderProductDAO {

	/**
	 * 批量插入订单商品
	 */
	public int insertBatch(List<OrderProduct> productList);
	
	/**
	 * 根据订单编号删除对应的商品信息，删除订单时顺带删除
	 */
	public int deleteOne(int orderId);
	
	/**
	 * 批量删除
	 */
	public int deleteBatch(int[] ids);
	
	/**
	 * 根据订单编号获取订单商品信息
	 */
	public List<OrderProduct> getOrderProduct(int orderId);
	
}
