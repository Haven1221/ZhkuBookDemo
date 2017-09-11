package com.haven.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.haven.model.Address;
import com.haven.model.User;
import com.haven.service.interfaces.UserService;

@Controller
@Scope("request")
//@RequestMapping(value="user")
@SessionAttributes({"user", "addressList"})
public class UserController {

	@Resource
	private UserService userService;
	
	// 显示登录页面
	@RequestMapping(value="login")
	public String login() {
		return "loginForm";
	}
	// 显示注册页面
	@RequestMapping(value="register")
	public String register() {
		return "retister";
	}
	
	// 处理用户登录
	@RequestMapping(value="doLogin")
	public String doLogin(String userName, String password, Model model) {
		// 验证登录
		User user = userService.login(userName, password);
		if(user == null) {
			// 登录失败，提示错误信息你，返回登录页面
			model.addAttribute("loginError", "用户名或密码错误");
			return "loginForm";
		}
		// 登录 成功，保存用户
		model.addAttribute("user", user);// 返回页面前会将user存入session中
		if(user.getUserType().equals("2"))
			return "redirect:/admin/index";	// 如果是管理员，则跳转到管理首页
		return "redirect:/index";
	}
	
	// 处理用户注册
	@RequestMapping(value="doRegister")
	public String doRegister(String userName, String password, String confirmPassword, Model model) {
		User user = userService.register(userName, password, confirmPassword);
		if(user == null) {
			// 注册失败，提示错误信息，返回注册页面
			model.addAttribute("registerError", "注册失败，用户名已被占用！");
			return "register";
		}
		// 注册成功，保存用户信息，跳转到首页
		model.addAttribute("user", user);
		return "redirect:/index";
	}
	
	// 进入修改密码页面
	@RequestMapping(value="user/updatePassword")
	public String updatePassword() {
		return "user/updatePassword";
	}
	
	// 处理用户修改密码
	@RequestMapping(value="user/doUpdatePassword")
	public String doUpdatePassword(String oldPassword, String newPassword, String confirmNewPassword, 
			HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if(user == null)	// 用户没有登录，跳转到登录页面
			return "loginForm";
		if(userService.updatePassword(user.getUserId(), user.getUserName(), oldPassword, newPassword, confirmNewPassword)) {
			// 修改密码成功，提示成功信息
			user.setPassword(newPassword);
			session.setAttribute("user", user);// 更新密码
			model.addAttribute("updateSusccess", "成功修改密码");
		} else {
			// 修改失败，提示错误信息
			model.addAttribute("updateError", "业务异常，修改密码失败");
		}
		
		return "user/updatePassword";
	}
	
	// 获取用户地址
	@RequestMapping(value="user/address/getAddress")
	public String addressList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "loginForm";// 登录失效，返回登录界面
		// 获取地址信息
		session.setAttribute("addressList", userService.getAddrInfo(user.getUserId()));
		return "user/address/addressList";
	}
	
	// 显示添加地址页面
	@RequestMapping(value="user/address/addressInput")
	public String addressInput() {
		return "user/address/addressInput";
	}
	
	// 处理用户添加地址
	@RequestMapping(value="user/address/addAddress")
	public String addAddress(Address address, Model model, HttpSession session) {
		if(userService.addAddr(address) != null) {
			// 添加成功，返回地址列表页面
			model.addAttribute("addAddressSuccess", "添加地址成功");
			@SuppressWarnings("unchecked")
			List<Address> addrList = (List<Address>) session.getAttribute("addressList");
			addrList.add(address);
			session.setAttribute("addressList", addrList);
			return "user/address/addressList";
		} else {
			// 添加失败，返回添加地址页面
			model.addAttribute("addAddressError", "添加地址失败");
			return "user/address/addressInput";
		}
	}
	
	// 修改地址准备
	@RequestMapping(value="user/address/editAddress")
	public String editAddress(int index, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Address> addrList = (List<Address>) session.getAttribute("addressList");
		model.addAttribute("index", index);
		model.addAttribute("address", addrList.get(index));
		return "user/address/addressInput";
	}
	
	// 修改地址
	@RequestMapping(value="user/address/doEditAddress")
	public String doEditAddress(Address address, int index, HttpSession session, Model model) {
		if(userService.updateAddr(address)) {
			// 修改成功，返回地址列表页面
			@SuppressWarnings("unchecked")
			List<Address> addrList = (List<Address>) session.getAttribute("addressList");
			addrList.set(index, address);
			session.setAttribute("addressList", addrList);
			model.addAttribute("updateAddressSuccess", "修改地址成功");
			return "user/address/addressList";
		} else {
			// 修改失败，提示错误信息，返回修改地址页面
			model.addAttribute("updateAddressError", "修改地址失败");
			return "user/address/addressInput";
		}
	}
	
	// 删除地址
	@RequestMapping(value="user/address/deleteAddress")
	public String deleteAddress(String[] ids, HttpSession session, Model model) {
		if(userService.deleteAddr(ids)) {
			// 删除成功，更新地址列表
			User user = (User) session.getAttribute("user");
			session.setAttribute("addressList", userService.getAddrInfo(user.getUserId()));
		} else {
			// 修改失败
			model.addAttribute("deleteError", "删除失败");
		}
		return "user/address/addressList";
	}
	
}
