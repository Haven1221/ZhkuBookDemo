package com.haven.interfaces;

import java.util.List;

import com.haven.model.Cart;

/**
 *  与Cart.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
public interface CartInterface {

	/**
	 * 往购物车中插入一条记录
	 */
	public int insert(Cart cart);
	
	/**
	 * 删除一条记录
	 */
	public int deleteOne(int id);
	
	/**
	 * 批量删除
	 */
	public int deleteBatch(int[] ids);
	
	/**
	 * 修改购买的图书数量
	 */
	public int update(Cart cart);
	
	/**
	 * 根据不同条件获取购物车中记录
	 * 可以根据用户名、店铺名或者图书名获取
	 */
	public List<Cart> getCart(Cart cart);
	
}
