package com.haven.servicetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haven.model.User;
import com.haven.service.interfaces.UserService;

//@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/haven/config/spring+mybatis.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void test_login() {
		String userName = "Haven";
		String password = "123456";
		User user = userService.login(userName, password);
		if(user == null) {
			System.out.println("登录失败");
			return;
		}
		System.out.println("登录成功");
		System.out.println("用户编号："+ user.getUserId());
		System.out.println("用户类型："+ user.getUserType());
			
	}
	
}
