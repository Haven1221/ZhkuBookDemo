package com.haven.daotest;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.dao.UserDAO;
import com.haven.model.User;
import com.haven.util.MyBatisUtil;

public class UserInterfaceTest {

	private SqlSession sqlSession = null;

	@Test
	public void testUser_register() {
		try {
			sqlSession = MyBatisUtil.openSession();
			UserDAO ui = sqlSession.getMapper(UserDAO.class);
			User user = new User("Haven", "asdfasdfasdf", "2", null);
			// 注册前判断用户名是否已存在
			List<User> userList = ui.getUserInfo(user);
			if (userList != null && !userList.isEmpty()) {
				System.out.println("用户名已存在！");
			} else {
				int count = ui.register(user);
				System.out.println("插入的行数：" + count);
				System.out.println("用户编号：" + user.getUserId());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	@Test
	public void testUser_deleteOne() {
		try {
			sqlSession = MyBatisUtil.openSession();
			UserDAO ui = sqlSession.getMapper(UserDAO.class);
			int count = ui.deleteOne(4);
			System.out.println("删除行数：" + count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	@Test
	public void testUser_deleteBatch() {
		try {
			sqlSession = MyBatisUtil.openSession();
			UserDAO ui = sqlSession.getMapper(UserDAO.class);
			int[] ids = {2,3};
			int count = ui.deleteBatch(ids);
			System.out.println("删除的行数：" + count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	@Test
	public void testUser_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			UserDAO ui = sqlSession.getMapper(UserDAO.class);
			User user = new User();
			user.setUserId(1);
			user.setUserName("Jayin");
			user.setPassword("asdfasdf");
			user.setUserType("1");
			int count = ui.update(user);
			System.out.println("修改的行数：" + count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	@Test
	public void testUser_getUserInfo() {
		try {
			sqlSession = MyBatisUtil.openSession();
			UserDAO ui = sqlSession.getMapper(UserDAO.class);
			User user = new User();
			List<User> userList = null;
			// 查询单个用户
			user.setUserName("Jayin");
			user.setPassword("asdfasdf");
			userList = ui.getUserInfo(user);
			if (userList.size() == 1) {
				user = userList.get(0);
				System.out.println("用户名：" + user.getUserName());
				System.out.println("密码：" + user.getPassword());
				System.out.println("用户类型：" + user.getUserType());
			} else {
				System.out.println("查询结果为空!");
			}
			// 查询多个用户
//			userList = ui.getUserInfo(null);
//			if(userList != null && !userList.isEmpty()) {
//				for (User u : userList) {
//					System.out.println("用户编号："+ u.getUserId());
//					System.out.println("用户名："+ u.getUserName());
//					System.out.println("密码："+ u.getPassword());
//					System.out.println("用户类型："+ u.getUserType());
//					System.out.println("=================================");
//				}
//			} else {
//				System.out.println("查询结果为空！");
//			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

}
