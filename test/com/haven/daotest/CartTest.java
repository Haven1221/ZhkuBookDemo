package com.haven.daotest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.dao.CartDAO;
import com.haven.model.Cart;
import com.haven.util.MyBatisUtil;

public class CartTest {

	private SqlSession sqlSession = null;
	private CartDAO ci = null;
	
	@Test
	public void testCart_insert() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(CartDAO.class);
			// 往购物车表中插入一条记录，插入前判断该图书是否存在，若存在，则数量+1即可
			Cart cart = new Cart(9, "Haven", "京东购书", "道德情操论", 39.99, 1, "/daodeqingcaolun.jpg");
			List<Cart> cartList = ci.getCart(cart);
			if(cartList != null && !cartList.isEmpty()) {
				// 存在，数量+1
				Cart c = cartList.get(0);
				int bookCount = c.getBookCount();
				System.out.println(bookCount);
				c.setBookCount(bookCount+1);
				int count = ci.update(c);
				System.out.println("修改行数："+ count);
				System.out.println("修改id："+ c.getCartId());
			} else {
				int count = ci.insert(cart);
				System.out.println("插入行数："+ count);
				System.out.println("返回的id："+ cart.getCartId());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testCart_deleteOne() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(CartDAO.class);
			int count = ci.deleteOne(4);
			System.out.println("删除行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testCart_deleteBatch() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(CartDAO.class);
			int count = ci.deleteBatch(new int[]{5,6,7,8,9,10,11});
			System.out.println("删除行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testCart_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(CartDAO.class);
			Cart cart = new Cart();
			cart.setCartId(1);
			cart.setBookCount(3);
			int count = ci.update(cart);
			System.out.println("修改行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testCart_getCart() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(CartDAO.class);
			Cart cart = new Cart();
			// 根据用户名获取购物车信息
			String userName = "Haven";
			cart.setUserName(userName);
			List<Cart> cartList = ci.getCart(cart);
			Map<String, List<Cart>> cartMap = new HashMap<String, List<Cart>>();
			for (Cart c : cartList) {
				// 按店铺分类
				String storeName = c.getStoreName();
				if(!cartMap.containsKey(storeName)) {
					Cart sCart = new Cart();
					sCart.setStoreName(storeName);
					sCart.setUserName(userName);
					List<Cart> cList = ci.getCart(sCart);
					cartMap.put(storeName, cList);
				}
			}
			// 输出验证
			for(String key : cartMap.keySet()) {
				System.out.println("店铺名："+ key);
				System.out.println("商品信息如下：");
				for(Cart cc : cartMap.get(key)) {
					System.out.println("书名："+ cc.getBookName());
					System.out.println("单价："+ cc.getBookPrice());
					System.out.println("数量："+ cc.getBookCount());
					System.out.println("-------------------------");
				}
				System.out.println("==============================");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
}
