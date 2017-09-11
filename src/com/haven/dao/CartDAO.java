package com.haven.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.Cart;

/**
 *  与Cart.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("cartDao")
@Scope("prototype")
public interface CartDAO {

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
	
	/**
	 * 根据用户名获取购物车中的店铺名
	 */
	public List<Cart> getStoreName(String userName);
	
	/**
	 * 根据购物车编号获取购物车信息列表
	 */
	public List<Cart> getCartById(int[] ids);
	
}
