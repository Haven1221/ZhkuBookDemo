package com.haven.service.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.haven.dao.CartDAO;
import com.haven.model.Book;
import com.haven.model.Cart;
import com.haven.service.interfaces.CartService;

@Service("cartService")
@Scope("prototype")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class CartServiceImpl implements CartService {

	@Resource(name="cartDao")
	private CartDAO cartDao;
//	public void setCartDao(CartDAO cartDao) {
//		this.cartDao = cartDao;
//	}
	
	@Override
	public boolean addInCart(String userName, Book book, int bookCount) {
		// 1.根据用户名和图书编号判断购物车是否已经存在
		List<Cart> cartList = cartDao.getCart(new Cart(book.getBookId(), userName, null, null, 0.0, 0, null)); 
		Cart cart = null;
		if(cartList != null && !cartList.isEmpty()) {
			// 购物车已存在，数量+1
			cart = cartList.get(0);
			cart.setBookCount(cart.getBookCount() + 1);
			if(cartDao.update(cart) > 0)
				return true;
		} else {
			// 不存在，新添加进购物车
			cart = new Cart(0, userName, book.getStoreName(), book.getBookName(), book.getBookPrice(), bookCount, book.getBookImage());
			if(cartDao.insert(cart) > 0)
				return true;
		}
		
		return false;
	}

	@Override
	public Map<String, List<Cart>> getCartInfo(String userName) {
		// 根据用户名获取店铺名
		List<Cart> storeList = cartDao.getStoreName(userName);
		if(storeList == null || !storeList.isEmpty())
			return null;	// 购物车为空，返回null。
		Map<String, List<Cart>> map = new HashMap<String, List<Cart>>();
		for (Cart c : storeList) {
			String storeName = c.getStoreName();
			Cart cart = new Cart();
			cart.setUserName(userName);
			cart.setStoreName(storeName);
			// 根据用户名和店铺名获取购物车信息
			List<Cart> cartList = cartDao.getCart(cart);
			// 保存店铺名对应的购物车商品
			map.put(storeName, cartList);
		}
		
		return map;
	}

	@Override
	public boolean deleteCart(String[] ids) {
		// 1.判断是单条删除还是批量删除
		if(ids != null && ids.length == 1) {
			// 单条删除
			int id = Integer.parseInt(ids[0]);
			if(cartDao.deleteOne(id) > 0)
				return true;
		} else {
			// 批量删除
			int[] idss = new int[ids.length];
			for(int i = 0; i < ids.length; i++)
				idss[i] = Integer.parseInt(ids[i]);
			if(cartDao.deleteBatch(idss) == ids.length)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean updateCart(String cartId, String bookCount) {
		Cart cart = new Cart();
		cart.setCartId(Integer.parseInt(cartId));
		cart.setBookCount(Integer.parseInt(bookCount));
		if(cartDao.update(cart) > 0)
			return true;
		return false;
	}

}
