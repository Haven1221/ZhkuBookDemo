package com.haven.service.implement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.haven.dao.AddressDAO;
import com.haven.dao.StoreDAO;
import com.haven.dao.TempStoreDAO;
import com.haven.dao.UserDAO;
import com.haven.model.Address;
import com.haven.model.Store;
import com.haven.model.TempStore;
import com.haven.model.User;
import com.haven.service.interfaces.UserService;

@Service("userService")
@Scope("prototype")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class UserServiceImpl implements UserService {

	// 对用户表的操作
	
	@Resource(name="userDao")
	private UserDAO userDao;
//	public void setUserDao(UserDAO userDao) {
//		this.userDao = userDao;
//	}

	@Override
	public User login(String userName, String password) {
		if(userName != null && !"".equals(userName) && password != null && !"".equals(password))
			return isExisted(userName, password);
		return null;
	}

	@Override
	public User register(String userName, String password, String confirmPassword) {
		if(!password.equals(confirmPassword))
			return null;	// 两次密码不一致，直接返回null
		// 密码一致，判断用户名是否已被占用
		if(isExisted(userName, password) == null) {
			// 没有占用，则注册用户
			User user = new User(userName, password, "1", null);
			if(userDao.register(user) > 0)
				return user;	// 注册成功，返回用户对象
		}
		
		return null;
	}

	@Override
	public boolean updatePassword(int userId, String userName, String oldPassword, String newPassword,
			String confirmNewPassword) {
		boolean flag = false;
		if(!newPassword.equals(confirmNewPassword)) {
			// 1.判断两次新密码是否一致
			flag = false;
		} else if(isExisted(userName, oldPassword) == null) {
			// 2.判断旧密码是否正确
			flag = false;
		} else {
			// 3.判断无误，修改密码
			User user = new User();
			user.setUserId(userId);
			user.setPassword(newPassword);
			if(userDao.update(user) > 0)
				flag = true;	// 修改密码成功
		}
		return flag;
	}
	
	// 判断用户是否存在
	private User isExisted(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		List<User> userList = userDao.getUserInfo(user);
		if(userList != null && !userList.isEmpty())
			return userList.get(0);
		return null;
	}

	// 对地址表的操作
	
	@Resource(name="addressDao")
	private AddressDAO addrDao;
//	public void setAddrDao(AddressDAO addrDao) {
//		this.addrDao = addrDao;
//	}
	
	@Override
	public Address addAddr(Address a) {
		return addrDao.insert(a) > 0 ? a : null;
	}

	@Override
	public boolean deleteAddr(String[] ids) {
		// 1.判断是删除一条记录还是批量删除
		if(ids != null && ids.length == 1) {
			// 单条删除
			if(addrDao.deleteOne(Integer.parseInt(ids[0])) > 0)
				return true;
		} else if(ids.length > 1) {
			// 批量删除
			int[] idss = new int[ids.length];
			for(int i = 0; i < ids.length; i++)
				idss[i] = Integer.parseInt(ids[i]);
			if(addrDao.deleteBatch(idss) == ids.length)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean updateAddr(Address a) {
		if(addrDao.update(a) > 0)
			return true;
		return false; 
	}

	@Override
	public List<Address> getAddrInfo(int userId) {
		return addrDao.getAddrInfo(userId);
	}

	@Override
	public Address chooseAddr(String addrId) {
		return addrDao.chooseAddr(Integer.parseInt(addrId));
	}

	// 对店铺表的操作
	
	@Resource(name="tempStoreDao")
	private TempStoreDAO tsDao;
	@Resource(name="storeDao")
	private StoreDAO storeDao;
	
//	public void setTsDao(TempStoreDAO tsDao) {
//		this.tsDao = tsDao;
//	}
//
//	public void setStoreDao(StoreDAO storeDao) {
//		this.storeDao = storeDao;
//	}

	@Override
	public TempStore addStore(TempStore ts) {
		// 1.判断店铺名是否存在
		Store store = new Store();
		store.setStoreName(ts.getTempStoreName());	// 设置参数
		List<Store> storeList = storeDao.getStoreInfo(store);
		if(storeList != null && !storeList.isEmpty()) {
			// 店铺名被占用，返回null
			return null;
		} else {
			// 没有占用，添加
			int retCount = tsDao.insert(ts);
			if(retCount > 0) {
				// 添加成功，返回ts。
				return ts;
			}
		}
		
		return null;
	}

	@Override
	public Store enterStore(String storeId, String userName) {
		Store store = new Store();
		store.setStoreId(Integer.parseInt(storeId));
		store.setUserName(userName);
		List<Store> storeList = storeDao.getStoreInfo(store);
		if(storeList != null && !storeList.isEmpty())
			return storeList.get(0);
		return null;
	}

	@Override
	public TempStore checkState(String userName) {
		TempStore ts = new TempStore();
		ts.setUserName(userName);
		List<TempStore> tsList = tsDao.getTempStoreInfo(ts);
		if(tsList != null && !tsList.isEmpty())
			return tsList.get(0);
		return null;
	}

}
