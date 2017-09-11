package com.haven.daotest;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.dao.AddressDAO;
import com.haven.model.Address;
import com.haven.util.MyBatisUtil;

public class AddressTest {

	private SqlSession sqlSession = null;
	private AddressDAO ai = null;
	
	@Test
	public void testAddress_insert() {
		try {
			sqlSession = MyBatisUtil.openSession();
			 ai = sqlSession.getMapper(AddressDAO.class);
			 Address addr = new Address(1, "广东省广州市", "海珠区仲恺路500号", "18814142805", "黄华冬");
			 int count = ai.insert(addr);
			 System.out.println("插入行数："+ count);
			 System.out.println("返回的编号："+ addr.getAddrId());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testAddress_delete() {
		try {
			sqlSession = MyBatisUtil.openSession();
			 ai = sqlSession.getMapper(AddressDAO.class);
			 int count = ai.deleteOne(2);
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
	public void testAddress_deleteBatch() {
		try {
			sqlSession = MyBatisUtil.openSession();
			 ai = sqlSession.getMapper(AddressDAO.class);
			 int count = ai.deleteBatch(new int[]{3});
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
	public void testAddress_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			 ai = sqlSession.getMapper(AddressDAO.class);
			 Address addr = new Address();
			 addr.setAddrId(1);
			 addr.setAddrProvince("广东省茂名市化州市");
			 addr.setAddrDetail("化州市第一中学");
			 addr.setAddrPhone("15800000000");
			 addr.setAddrConsinee("吴虹婷");
			 int count = ai.update(addr);
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
	public void testAddress_getAddrInfo() {
		try {
			sqlSession = MyBatisUtil.openSession();
			 ai = sqlSession.getMapper(AddressDAO.class);
			 List<Address> addrList = ai.getAddrInfo(1);
			 if(addrList != null && !addrList.isEmpty()) {
				 for (Address a : addrList) {
					System.out.println("收货地址："+ a.getAddrProvince() +" "+ a.getAddrDetail());
					System.out.println("收货电话："+ a.getAddrPhone());
					System.out.println("收货人："+ a.getAddrConsinee());
					System.out.println("------------------------");
				}
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
