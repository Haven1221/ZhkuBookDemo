package com.haven.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;

import com.haven.service.interfaces.AdminService;
import com.haven.service.interfaces.OrderService;

@Controller
@Scope("request")
@RequestMapping(value = "admin")
//@SessionAttributes({ "userList", "tempStoreList", "userPage", "tempStorePage", "storeList", "storePage",
//		"userOrderList", "userOrderPage", "storeOrderList", "storeOrderPage", "userOrderProduct", "userOrderAddress",
//		"storeOrderProduct", "storeOrderAddress" })
public class AdminController {

	@Resource
	private AdminService adminService;

	@Resource
	private OrderService orderService;

	// 进入管理首页
	@RequestMapping(value = "index")
	public String index() {
		return "admin/index";
	}
	
	// 查看用户基本信息
	@RequestMapping(value = "userInfo")
	public String getUserInfo(String userName, String currentPage, Model model) {
		Map<String, Object> resultMap = adminService.getUser(userName, currentPage);
		if (resultMap != null) {
			model.addAttribute("userList", resultMap.get("userList"));
			model.addAttribute("userPage", resultMap.get("page"));
		}
		if(userName != null && !"".equals(userName))
			model.addAttribute("keyword", userName);// 保存查询条件
		return "admin/user/userInfo";
	}

	// 删除用户
	@RequestMapping(value = "deleteUser")
	public String deleteUser(String[] id, String currentPage, String userName, Model model) {
		Map<String, Object> resultMap = adminService.deleteUser(id, userName, currentPage);
		if (resultMap == null) {
			// 删除失败，提示错误信息
			model.addAttribute("deleteError", "删除用户失败");
		} else {
			// 删除成功，重新获取用户信息
			model.addAttribute("userList", resultMap.get("userList"));
			model.addAttribute("userPage", resultMap.get("page"));
		}

		return "admin/user/userInfo";
	}

	// 修改用户类型
	@RequestMapping(value = "updateUser")
	public String udpateUser(String userId, String userType, String currentPage, String userName, Model model) {
		Map<String, Object> resultMap = adminService.updateUserType(userId, userType, userName, currentPage);
		if (resultMap == null) {
			// 修改失败
			model.addAttribute("updateError", "修改用户失败");
		} else {
			// 修改成功，重新获取用户信息
			model.addAttribute("userList", resultMap.get("userList"));
			model.addAttribute("userPage", resultMap.get("page"));
		}
		return "admin/user/userInfo";
	}

	// 查看用户订单
	@RequestMapping(value = "userOrderInfo")
	public String getUserOrder(String userName, String currentPage, Model model) {
		Map<String, Object> resultMap = orderService.getOrderInfo(userName, null, null, currentPage);
		if (resultMap != null) {
			model.addAttribute("userOrderList", resultMap.get("orderList"));
			model.addAttribute("userOrderPage", resultMap.get("page"));
		}
		// 保存用户名称
		model.addAttribute("userName", userName);
		return "admin/user/order/orderInfo";
	}

	// 查看用户订单详情
	@RequestMapping(value = "userOrderDetail")
	public String getUserOrderDetail(String orderId, String addrId, Model model) {
		Map<String, Object> resultMap = orderService.getOrderDetail(orderId, addrId);
		if (resultMap != null) {
			model.addAttribute("userOrderProduct", resultMap.get("productList"));
			model.addAttribute("userOrderAddress", resultMap.get("address"));
		}
		return "admin/user/order/orderDetail";
	}

	// 获取临时店铺信息
	@RequestMapping(value = "tempStoreInfo")
	public String getTempStoreInfo(String tempStoreState, String currentPage, Model model) {
		Map<String, Object> resultMap = adminService.getTempStore(tempStoreState, currentPage);
		if (resultMap != null) {
			model.addAttribute("tempStoreList", resultMap.get("tempStoreList"));
			model.addAttribute("tempStorePage", resultMap.get("page"));
		}
		return "admin/tempStore/tempStoreInfo";
	}

	// 删除临时店铺信息
	@RequestMapping(value = "deleteTempStore")
	public String deleteTempStore(String[] id, String tempStoreState, String currentPage, Model model) {
		Map<String, Object> resultMap = adminService.deleteTempStore(id, tempStoreState, currentPage);
		if (resultMap == null) {
			// 删除失败
			model.addAttribute("deleteError", "删除临时店铺失败");
		} else {
			// 删除成功，更新列表
			model.addAttribute("tempStoreList", resultMap.get("tempStoreList"));
			model.addAttribute("tempStorePage", resultMap.get("page"));
		}
		return "admin/tempStore/tempStoreInfo";
	}

	// 修改临时店铺审核进度（状态）
	@RequestMapping(value = "updateTempStore")
	public String updateTempStore(String tempStoreId, String newState, String tempStoreState, String currentPage,
			Model model) {
		Map<String, Object> resultMap = adminService.updateTempStore(tempStoreId, newState, tempStoreState,
				currentPage);
		if (resultMap == null) {
			// 修改失败
			model.addAttribute("upateError", "修改临时店铺失败");
		} else {
			// 修改成功
			model.addAttribute("tempStoreList", resultMap.get("tempStoreList"));
			model.addAttribute("tempStorePage", resultMap.get("page"));
		}
		return "admin/tempStore/tempStoreInfo";
	}

	// 修改店铺状态
	@RequestMapping(value = "updateStoreState")
	public String updateStoreState(String storeName, String storeState, String currentPage, Model model) {
		Map<String, Object> resultMap = adminService.updateStoreState(storeName, storeState, currentPage);
		if (resultMap == null) {
			// 修改失败
			model.addAttribute("updateError", "修改店铺状态失败");
		} else {
			// 修改成功
			model.addAttribute("storeList", resultMap.get("storeList"));
			model.addAttribute("storePage", resultMap.get("page"));
		}
		return "admin/store/storeInfo";
	}

	// 获取店铺信息
	@RequestMapping(value = "StoreInfo")
	public String getStore(String storeName, String currentPage, Model model) {
		Map<String, Object> resultMap = adminService.getAllStore(storeName, currentPage);
		if (resultMap != null) {
			model.addAttribute("storeList", resultMap.get("storeList"));
			model.addAttribute("storePage", resultMap.get("page"));
		}
		return "admin/store/storeInfo";
	}

	// 查看店铺订单
	@RequestMapping(value = "storeOrder")
	public String getStoreOrder(String storeName, String currentPage, Model model) {
		Map<String, Object> resultMap = orderService.getOrderInfo(null, storeName, null, currentPage);
		if (resultMap != null) {
			model.addAttribute("storeOrderList", resultMap.get("orderList"));
			model.addAttribute("storeOrderPage", resultMap.get("page"));
		}
		return "admin/store/order/orderInfo";
	}

	// 查看店铺订单详情
	@RequestMapping(value = "storeOrderDetail")
	public String getStoreOrderDetail(String orderId, String addrId, Model model) {
		Map<String, Object> resultMap = orderService.getOrderDetail(orderId, addrId);
		if (resultMap != null) {
			model.addAttribute("storeOrderProduct", resultMap.get("productList"));
			model.addAttribute("storeOrderAddress", resultMap.get("address"));
		}
		return "admin/store/order/orderDetail";
	}

}
