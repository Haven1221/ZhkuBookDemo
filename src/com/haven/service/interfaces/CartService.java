package com.haven.service.interfaces;

import java.util.List;
import java.util.Map;

import com.haven.model.Book;
import com.haven.model.Cart;

/**
 * 购物车服务类
 */
public interface CartService {

	/**
	 * 把图书加入购物车，添加前判断购物车中是否已经存在，若存在则对应的商品数量加1即可。
	 * @param userName	：用户名
	 * @param book		：图书信息
	 * @param bookCount	：加入数量
	 * @return			：成功则返回true，否则返回false。
	 */
	boolean addInCart(String userName, Book book, int bookCount);
	
	/**
	 * 根据用户名获取购物车信息，返回key为店铺名，value为购物车信息列表的map集合
	 * @param userName	：用户名
	 * @return			：成功则返回购物车信息列表，否则返回null。
	 */
	Map<String, List<Cart>> getCartInfo(String userName);
	
	/**
	 * 根据购物车编号删除购物车信息，包括单个删除以及批量删除
	 * @param ids	：购物车编号数组
	 * @return		：成功则返回true，否则返回false。
	 */
	boolean deleteCart(String[] ids);
	
	/**
	 * 根据购物车编号修改购物车商品的购买数量
	 * @param cartId	：购物车编号
	 * @param bookCount	：商品数量
	 * @return			：成功则返回true，否则返回false。
	 */
	boolean updateCart(String cartId, String bookCount);
	
}
