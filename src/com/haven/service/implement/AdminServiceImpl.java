package com.haven.service.implement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.haven.bean.PageBean;
import com.haven.dao.StoreDAO;
import com.haven.dao.TempStoreDAO;
import com.haven.dao.UserDAO;
import com.haven.model.Store;
import com.haven.model.TempStore;
import com.haven.model.User;
import com.haven.service.interfaces.AdminService;

@Service("adminService")
@Scope("prototype")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class AdminServiceImpl implements AdminService {

	// 对用户表的操作
	@Resource(name="userDao")
	private UserDAO userDao;
//	public void setUserDao(UserDAO userDao) {
//		this.userDao = userDao;
//	}
	
	@Override
	public Map<String, Object> getUser(String userName, String currentPage) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		User user = new User();
		user.setUserName(userName);// 设置查询参数
		PageBean pb = new PageBean();
		currentPage = currentPage==null ? "1" : currentPage;
		int curPage = Integer.parseInt(currentPage);
		pb.setCurrentPage(curPage);// 设置当前页
		parameter.put("user", user);
		parameter.put("page", pb);
		List<User> userList = userDao.getUserByPage(parameter);
		if(userList != null && !userList.isEmpty()) // 查询结果不为空则返回
			parameter.put("userList", userList);
		return parameter;
	}

	@Override
	public Map<String, Object> deleteUser(String[] ids, String userName, String currentPage) {
		if(ids == null || ids.length <= 0)
			return null;
		boolean flag = false;
		if(ids.length == 1) {
			// 单个删除
			int id = Integer.parseInt(ids[0]);
			flag = userDao.deleteOne(id)>0 ? true : false;
		} else {
			// 批量删除
			int[] idss = new int[ids.length];
			for(int i = 0; i < ids.length; i++)
				idss[i] = Integer.parseInt(ids[i]);
			flag = userDao.deleteBatch(idss)==ids.length ? true : false;
		}
		// 删除成功，获取用户信息，返回
		return flag ? getUser(userName, currentPage) : null;
	}

	@Override
	public Map<String, Object> updateUserType(String userId, String newType, String userName, String currentPage) {
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		user.setUserType(newType);
		int retCount = userDao.update(user);
		return  retCount > 0 ? getUser(userName, currentPage) : null;
	}

	// 对店铺表的操作
	
	@Resource(name="tempStoreDao")
	private TempStoreDAO tsDao;
	@Resource(name="storeDao")
	private StoreDAO storeDao;
	
//	public void setTsDao(TempStoreDAO tsDao) {
//		this.tsDao = tsDao;
//	}
//	public void setStoreDao(StoreDAO storeDao) {
//		this.storeDao = storeDao;
//	}
	
	@Override
	public Map<String, Object> getTempStore(String tempStoreState, String currentPage) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if(tempStoreState != null && !"".equals(tempStoreState)) {
			// 设置查询条件
			TempStore ts = new TempStore();
			ts.setTempStoreState(tempStoreState);
			parameter.put("tempStore", ts);
		}
		currentPage = currentPage==null ? "1" : currentPage;
		int curPage = Integer.parseInt(currentPage);
		PageBean pb = new PageBean();
		pb.setCurrentPage(curPage);
		parameter.put("page", pb);// 设置当前页
		List<TempStore> tsList = tsDao.getTempStoreByPage(parameter);
		if(tsList != null && !tsList.isEmpty()) {
			parameter.put("tempStoreList", tsList);
			return parameter;
		}
		
		return null;
	}

//	@Override
//	public List<TempStore> getTempStoreByState(String tempStoreState) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Map<String, Object> updateTempStore(String tempStoreId, String newState, String tempStoreState, String currentPage) {
		boolean flag = false;
		TempStore ts = new TempStore();
		ts.setTempStoreId(Integer.parseInt(tempStoreId));
		ts.setTempStoreState(newState);
		int retCount = tsDao.update(ts);
		flag = retCount>0 ? true : false;
		if(flag && newState.equals("通过")) {
			// 修改状态为通过，则添加进店铺表
			flag = addStore(tempStoreId)==null ? false : true;
		}
		return flag ? getTempStore(tempStoreState, currentPage) : null;
	}

	@Override
	public Map<String, Object> deleteTempStore(String[] ids, String tempStoreState, String currentPage) {
		if(ids == null || ids.length <= 0)
			return null;
		boolean flag = false;
		if(ids.length == 1) {
			// 单个删除
			int id = Integer.parseInt(ids[0]);
			flag = tsDao.deleteOne(id)>0 ? true : false;
		} else {
			// 批量删除
			int[] idss = new int[ids.length];
			for(int i = 0; i < ids.length; i++)
				idss[i] = Integer.parseInt(ids[i]);
			flag = tsDao.deleteBatch(idss)==ids.length ? true : false;
		}
		
		return flag ? getTempStore(tempStoreState, currentPage) : null;
	}

	@Override
	public Store addStore(String tempStoreId) {
		// 获取临时店铺
		TempStore ts = new TempStore();
		ts.setTempStoreId(Integer.parseInt(tempStoreId));
		ts = tsDao.getTempStoreInfo(ts).get(0);
		// 开店时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String openDate = sdf.format(new Date());
		Store s = new Store(ts.getTempStoreName(), ts.getUserName(), 0, 0, 0, 0, 0, ts.getTempStoreDescript(), ts.getTempStoreLicense(), "营业中", openDate);
		// 添加店铺
		if(storeDao.insert(s) > 0)
			return s;
		return null;
	}

	@Override
	public boolean deleteStore(String[] ids) {
		
		return false;
	}

	@Override
	public Map<String,Object> updateStoreState(String storeName, String storeState, String currentPage) {
		Store s = new Store();
		s.setStoreName(storeName);
		s.setStoreState(storeState);
		boolean flag = false;
		int retCount = storeDao.update(s);
		flag = retCount>0 ? true : false;
		return flag ? getAllStore(storeName, currentPage) : null;
	}

	@Override
	public Store getStoreInfo(String storeName) {
		Store s = new Store();
		s.setStoreName(storeName);
		List<Store> storeList = storeDao.getStoreInfo(s);
		if(storeList != null && !storeList.isEmpty())
			return storeList.get(0);
		return null;
	}

	@Override
	public Map<String, Object> getAllStore(String storeName, String currentPage) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if(storeName != null && !"".equals(storeName)) {
			// 设置查询参数
			Store s = new Store();
			s.setStoreName(storeName);
			parameter.put("store", s);
		}
		currentPage = currentPage==null ? "1" : currentPage;
		int curPage = Integer.parseInt(currentPage);
		PageBean pb = new PageBean();
		pb.setCurrentPage(curPage);
		parameter.put("page", pb);
		List<Store> storeList = storeDao.getStoreByPage(parameter);
		if(storeList != null && !storeList.isEmpty()) {
			parameter.put("storeList", storeList);
			return parameter;
		}
		return null;
	}

}
