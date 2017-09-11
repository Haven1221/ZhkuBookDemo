package com.haven.service.interfaces;

import java.util.List;
import java.util.Map;

import com.haven.model.Cart;

/**
 * 订单业务层接口
 */
public interface OrderService {
	
	/**
	 * 从购物车中生成订单，将用户一次下单按店铺名划分为多个订单
	 * @param ids	：购买商品的购物车编号
	 * @return		：成功则返回要购买的商品信息列表，否则返回null
	 */
	Map<String, List<Cart>> generateOrder(String[] ids);
	
	/**
	 * 添加订单
	 * @param cartMap	：订单包含的商品，以店铺名分类
	 * @param addrId	：订单地址编号
	 * @param userName	：购买用户
	 * @return			：成功则返回true， 否则返回false。
	 */
	boolean addOrder(Map<String, List<Cart>> cartMap, String addrId, String userName);
	
	/**
	 * 根据订单编号修改订单状态
	 * @param orderId		：订单编号
	 * @param orderState	：订单状态
	 * @return				：成功则返回true，否则返回false。
	 */
	boolean updateOrder(String orderId, String orderState);
	
	/**
	 * 根据用户名、店铺名或者订单状态获取订单信息，分页显示
	 * @param userName		：用户名
	 * @param storeName		：店铺名
	 * @param orderState	：订单状态
	 * @param currentPage	：当前页
	 * @return				：成功则返回当前页的订单信息列表，否则返回null。
	 */
	Map<String, Object> getOrderInfo(String userName, String storeName, String orderState, String currentPage);
	
	/**
	 * 获取订单的详细信息，包括订单包含的商品信息以及订单收货地址
	 * 其中，根据订单编号获取订单包含商品信息，根据地址编号获取订单收货地址
	 * @param orderId	：订单编号
	 * @param addrId	：地址编号
	 * @return			：成功则返回订单详情，否则返回null。
	 */
	Map<String, Object> getOrderDetail(String orderId, String addrId);
	
}
