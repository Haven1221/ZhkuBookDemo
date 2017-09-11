package com.haven.daotest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.bean.PageBean;
import com.haven.dao.BookDAO;
import com.haven.dao.CartDAO;
import com.haven.dao.OrderDAO;
import com.haven.dao.OrderProductDAO;
import com.haven.model.Address;
import com.haven.model.Book;
import com.haven.model.Cart;
import com.haven.model.Order;
import com.haven.model.OrderProduct;
import com.haven.util.MyBatisUtil;

public class OrderTest {

	private SqlSession sqlSession = null;
	private OrderDAO oi = null;
	private OrderProductDAO pi = null;
	private CartDAO ci = null;
	private BookDAO bi = null;
	
	@Test
	public void testOrder_insert() {
		try {
			sqlSession = MyBatisUtil.openSession();
			oi = sqlSession.getMapper(OrderDAO.class);
			pi = sqlSession.getMapper(OrderProductDAO.class);
			ci = sqlSession.getMapper(CartDAO.class);
			bi = sqlSession.getMapper(BookDAO.class);
			// 生成订单
			int orderCount = 0;	// 统计订单条数
			Map<String, List<Cart>> cartMap = getFromCart(ci);
			for(String storeName : cartMap.keySet()) {
				Order o = new Order(storeName, "2017-07-19 21:37", 99.99, "Haven", "正在出库", 1, null, null);
				int count = oi.insert(o);
				orderCount += count;
				int orderId = o.getOrderId();
				List<Cart> cartList = cartMap.get(storeName);
				List<OrderProduct> proList = changeCartToProduct(cartMap.get(storeName), orderId);
				int proCount = pi.insertBatch(proList);
				// 插入成功，删除购物车信息，修改图书的销售量以及库存量
				int[] ids = new int[cartList.size()];
				for(int i = 0; i < cartList.size(); i++) {
					Cart cc = cartList.get(i);
					ids[i] = cc.getCartId();
					Book book = new Book();
					book.setBookId(cc.getBookId());
					book.setBookSaleNum(cc.getBookCount());
					book.setBookSumNum(cc.getBookCount());
					System.out.println("修改图书行数："+ bi.updateFromOrder(book));
				}
				System.out.println("删除购物车信息："+ ci.deleteBatch(ids));
				
				System.out.println("订单编号："+ orderId +","+ "包含商品种类："+ proCount);
				System.out.println("商品信息如下：");
				for (OrderProduct pro : proList) {
					System.out.println("商品名称："+ pro.getProductName());
					System.out.println("商品单价："+ pro.getProductPrice());
					System.out.println("商品数量："+ pro.getProductCount());
					System.out.println("-------------------------");
				}
				System.out.println("===========================");
			}
			System.out.println("插入订单条数："+ orderCount);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testOrder_deleteOne() {
		try {
			sqlSession = MyBatisUtil.openSession();
			oi = sqlSession.getMapper(OrderDAO.class);
			pi = sqlSession.getMapper(OrderProductDAO.class);
			int orderCount;
			int proCount;
			// 单条删除
//			int orderId = 8;
//			orderCount = oi.deleteOne(orderId);
//			proCount = pi.deleteOne(orderId);
			// 批量删除
			int[] ids = { 5, 6, 7};
			orderCount = oi.deleteBatch(ids);
			proCount = pi.deleteBatch(ids);
			System.out.println("删除订单数："+ orderCount);
			System.out.println("删除商品数："+ proCount);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testOrder_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			oi = sqlSession.getMapper(OrderDAO.class);
			Order o = new Order();
			o.setOrderId(1);
			o.setOrderState("配送中");
			System.out.println("修改行数："+ oi.update(o));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testOrder_getOrderInfoByPage() {
		try {
			sqlSession = MyBatisUtil.openSession();
			oi = sqlSession.getMapper(OrderDAO.class);
			pi = sqlSession.getMapper(OrderProductDAO.class);
			Order o = new Order();
			o.setUserName("Haven");
//			o.setStoreName("淘书网");
			o.setOrderState("正在出库");
			PageBean pb = new PageBean();
			pb.setCurrentPage(1);
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("order", o);
			parameter.put("page", pb);
			List<Order> orderList = oi.getOrderInfoByPage(parameter);
			// 循环打印订单
			for (Order oo : orderList) {
				int orderId = oo.getOrderId();
				System.out.println("基本信息如下：");
				System.out.println("  订单编号："+ orderId);
				System.out.println("  下单日期："+ oo.getOrderDate());
				System.out.println("  订单状态："+ oo.getOrderState());
				System.out.println("  订单总价："+ oo.getOrderPrice());
				System.out.println("  下单用户："+ oo.getUserName());
				System.out.println("  所属店铺："+ oo.getStoreName());
				List<OrderProduct> proList = pi.getOrderProduct(orderId);
				System.out.println("包含的商品信息如下：");
				for (OrderProduct pro : proList) {
					System.out.println("  商品名称："+ pro.getProductName());
					System.out.println("  商品单价："+ pro.getProductPrice());
					System.out.println("  购买数量："+ pro.getProductCount());
					System.out.println("-----------------------");
				}
				Address addr = oi.getOrderAddr(orderId);
				System.out.println("订单地址信息如下：");
				System.out.println("  配送地址："+ addr.getAddrProvince() +" "+ addr.getAddrDetail() );
				System.out.println("  配送电话："+ addr.getAddrPhone());
				System.out.println("  收货人："+ addr.getAddrConsinee());
				System.out.println("=============================");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	// 从购车中获取要购买的商品信息
	private Map<String, List<Cart>> getFromCart(CartDAO ci) {
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
		
		return cartMap;
	}
	
	// 将购物车转换成商品
	private List<OrderProduct> changeCartToProduct(List<Cart> cartList, int orderId) {
		List<OrderProduct> proList = new ArrayList<OrderProduct>();
		for(Cart c : cartList) {
			OrderProduct pro = new OrderProduct();
			pro.setBookId(c.getBookId());
			pro.setOrderId(orderId);
			pro.setProductCount(c.getBookCount());
			pro.setProductImage(c.getBookImage());
			pro.setProductName(c.getBookName());
			pro.setProductPrice(c.getBookPrice());
			proList.add(pro);
		}
		return proList;
	}
	
}
