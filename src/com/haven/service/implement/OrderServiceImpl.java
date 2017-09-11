package com.haven.service.implement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.haven.bean.PageBean;
import com.haven.dao.AddressDAO;
import com.haven.dao.BookDAO;
import com.haven.dao.CartDAO;
import com.haven.dao.OrderDAO;
import com.haven.dao.OrderProductDAO;
import com.haven.dao.StoreDAO;
import com.haven.model.Address;
import com.haven.model.Book;
import com.haven.model.Cart;
import com.haven.model.Order;
import com.haven.model.OrderProduct;
import com.haven.model.Store;
import com.haven.service.interfaces.OrderService;

@Service("orderService")
@Scope("prototype")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class OrderServiceImpl implements OrderService {

	@Resource(name="orderDao")
	private OrderDAO orderDao;
	
	@Resource
	private CartDAO cartDao;
	
	@Resource
	private BookDAO bookDao;
	
	@Resource
	private StoreDAO storeDao;

	@Override
	public Map<String, List<Cart>> generateOrder(String[] ids) {
		if(ids == null || ids.length <= 0)
			return null;
		Map<String, List<Cart>> map = new HashMap<String, List<Cart>>();// 店铺名与商品信息列表对应
		int[] idss = new int[ids.length];
		for(int i = 0; i < ids.length; i++)
			idss[i] = Integer.parseInt(ids[i]);
		List<Cart> cartList = cartDao.getCartById(idss);
		// 按店铺名分类
		// 首先确定有多少店铺
		Set<String> storeSet = new HashSet<String>();
		for(Cart c : cartList)
			storeSet.add(c.getStoreName());
		String[] storeArr = (String[]) storeSet.toArray();
		for(int i = 0; i < storeArr.length; i++) {
			// 遍历店铺
			String storeName = storeArr[i];
			List<Cart> storeList = new ArrayList<Cart>();
			for(int j = 0; j < cartList.size(); j++) {
				// 遍历购买商品，找到属于该店铺的商品，加入购买清单
				Cart c = cartList.get(j);
				if(c.getStoreName().equals(storeName)) {
					// 属于该店铺，加入
					storeList.add(c);
				}
			}
			// 作为一条订单加入
			map.put(storeName, storeList);
		}
		
		return map.size()>0 ? map : null;
	}

	@Override
	public boolean addOrder(Map<String, List<Cart>> cartMap, String addrId, String userName) {
		if(cartMap == null || cartMap.size() <= 0)
			return false;
		// 设置订单日期、状态、收货地址
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String orderDate = sdf.format(new Date());
		String orderState = "待发货";
		int addressId = Integer.parseInt(addrId);
		// 遍历商品列表，按店铺下单
		for(String storeName :cartMap.keySet()) {
			List<Cart> cartList = cartMap.get(storeName);
			// 添加店铺商品信息
			double orderPrice = 0.0;
			int saleNum = 0;
			for (Cart cart : cartList) {
				// 统计订单总价和总量
				orderPrice += cart.getBookPrice()*cart.getBookCount();
				saleNum += cart.getBookCount();
			}
			Order o = new Order(storeName, orderDate, orderPrice, userName, orderState, addressId);
			if(orderDao.insert(o) <= 0)
				return false;
			// 添加订单成功，获取订单编号
			int orderId = o.getOrderId();
			// 添加商品信息，顺便获取购物车编号
			List<OrderProduct> productList = new ArrayList<OrderProduct>();
			int[] ids = new int[cartList.size()];
			for(int i = 0; i < cartList.size(); i++) {
				Cart c = cartList.get(i);
				ids[i] = c.getCartId();
				OrderProduct product = new OrderProduct(orderId, c.getBookId(), c.getBookName(), c.getBookPrice(), c.getBookCount(), c.getBookImage());
				productList.add(product);
			}
			if(productDao.insertBatch(productList) != productList.size())
				return false;
			// 添加订单商品成功，删除购物车信息
			if(cartDao.deleteBatch(ids) != ids.length)
				return false;
			// 删除成功，修改对应图书的销售量和库存量
			for(OrderProduct product : productList) {
				Book book = new Book();
				book.setBookId(product.getBookId());
				book.setBookSaleNum(product.getProductCount());
				book.setBookSumNum(product.getProductCount());
				if(bookDao.updateFromOrder(book) <= 0)
					return false;
				// 修改成功， 修改对应店铺的销售额和销售量
				Store s = new Store();
				s.setStoreName(storeName);
				s.setStoreSale(orderPrice);
				s.setStoreSaleNum(saleNum);
				if(storeDao.update(s) <= 0)
					return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean updateOrder(String orderId, String orderState) {
		Order o = new Order();
		o.setOrderId(Integer.parseInt(orderId));
		o.setOrderState(orderState);
		int retCount = orderDao.update(o);
		return retCount>0 ? true : false;
	}

	@Override
	public Map<String, Object> getOrderInfo(String userName, String storeName, String orderState, String currentPage) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		PageBean pb = new PageBean();
		currentPage = currentPage == null ? "1" : currentPage;
		int curPage = Integer.parseInt(currentPage);
		pb.setCurrentPage(curPage);// 设置当前页
		parameter.put("page", pb);
		Order o = new Order();
		// 设置查询参数
		o.setUserName(userName);
		o.setOrderState(orderState);
		o.setStoreName(storeName);
		parameter.put("order", o);
		List<Order> orderList = orderDao.getOrderInfoByPage(parameter);
		if(orderList != null && orderList.size() > 0) {
			parameter.put("orderList", orderList);
			return parameter;
		}
		return null;
	}

	@Resource
	private OrderProductDAO productDao;

	@Resource
	private AddressDAO addressDao;
	
	@Override
	public Map<String, Object> getOrderDetail(String orderId, String addrId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取订单商品信息
		List<OrderProduct> productList = productDao.getOrderProduct(Integer.parseInt(orderId));
		// 获取订单收货地址
		Address addr = addressDao.chooseAddr(Integer.parseInt(addrId));
		if(productList != null && !productList.isEmpty() && addr != null) {
			map.put("productList", productList);
			map.put("address", addr);
			return map;
		}
		
		return null;
	}

}
