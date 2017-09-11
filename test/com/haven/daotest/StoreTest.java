package com.haven.daotest;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.dao.StoreDAO;
import com.haven.dao.TempStoreDAO;
import com.haven.model.Store;
import com.haven.model.TempStore;
import com.haven.util.MyBatisUtil;

public class StoreTest {

	private SqlSession sqlSession = null;
	private TempStoreDAO ti = null;
	private StoreDAO si = null;
	
	@Test
	public void testTempStore_insert() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ti = sqlSession.getMapper(TempStoreDAO.class);
			TempStore ts = new TempStore("Haven", "当当书城", "好书店", "/dangdangshucheng.jpg", "正在审核");
			int count = ti.insert(ts);
			System.out.println("插入行数："+ count);
			System.out.println("返回编号："+ ts.getTempStoreId());
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testTempStore_delete() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ti = sqlSession.getMapper(TempStoreDAO.class);
			// 单条删除
//			int count = ti.deleteOne(4);
//			System.out.println("删除行数："+ count);
			// 批量删除
			int count = ti.deleteBatch(new int[]{2,3,5});
			System.out.println("删除行数："+ count);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTempStore_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ti = sqlSession.getMapper(TempStoreDAO.class);
			TempStore ts = new TempStore();
			ts.setTempStoreId(6);
			ts.setTempStoreState("失败");
			int count = ti.update(ts);
			System.out.println("修改行数："+ count);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTempStore_getTempStoreInfo() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ti = sqlSession.getMapper(TempStoreDAO.class);
			// 获取所有临时店铺
//			List<TempStore> tsList = ti.getTempStoreInfo(null);
			// 根据申请人或者审核状态获取
			TempStore tStore = new TempStore();
//			tStore.setUserName("Haven");
			tStore.setTempStoreState("正在审核");
			List<TempStore> tsList = ti.getTempStoreInfo(tStore);
			for (TempStore ts : tsList) {
				System.out.println("店铺名："+ ts.getTempStoreName());
				System.out.println("申请人："+ ts.getUserName());
				System.out.println("审核状态："+ ts.getTempStoreState());
				System.out.println("------------------");
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStore_insert() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ti = sqlSession.getMapper(TempStoreDAO.class);
			si = sqlSession.getMapper(StoreDAO.class);
			// 获取临时店铺中审核状态为‘通过’的临时店铺，插入店铺表中
			TempStore ts = new TempStore();
			ts.setTempStoreState("通过");
			List<TempStore> tsList = ti.getTempStoreInfo(ts);
			for (TempStore tStore : tsList) {
				Store s = new Store(tStore.getTempStoreName(), tStore.getUserName(), 0, 0, 0, 0, 0, 
						tStore.getTempStoreDescript(), tStore.getTempStoreLicense(), "营业中", "2017-07-20 16:09");
				int count = si.insert(s);
				System.out.println("插入行数："+ count);
				System.out.println("返回编号："+ s.getStoreId());
				System.out.println("---------------------");
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
	public void testStore_delete() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ti = sqlSession.getMapper(TempStoreDAO.class);
			si = sqlSession.getMapper(StoreDAO.class);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testStore_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			si = sqlSession.getMapper(StoreDAO.class);
			Store s = new Store();
			// 修改店铺状态
			s.setStoreName("当当书城");
//			s.setStoreState("休息中");
			// 修改销售量和销售额
//			s.setStoreSaleNum(1);
//			s.setStoreSale(49.99);
			// 修改店铺评分
			s.setStoreScore(4);
			System.out.println("修改行数："+ si.update(s));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testStore_getStoreInfo() {
		try {
			sqlSession = MyBatisUtil.openSession();
			si = sqlSession.getMapper(StoreDAO.class);
			Store s = new Store();
			// 根据用户名获取店铺信息
//			s.setUserName("Haven");
			// 根据店铺名获取
//			s.setStoreName("淘书网");
			List<Store> sList = si.getStoreInfo(s);
			for (Store ss : sList) {
				System.out.println("店铺名："+ ss.getStoreName());
				System.out.println("店主："+ ss.getUserName());
				System.out.println("店铺描述："+ ss.getStoreDescript());
				System.out.println("---------------");
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
