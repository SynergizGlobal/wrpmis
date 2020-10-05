package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.UserDao;
import com.synergizglobal.pmis.Iservice.UserService;
import com.synergizglobal.pmis.model.User;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUserRoles() throws Exception{
		return userDao.getUserRoles();
	}

	@Override
	public List<User> getUserDepartments() throws Exception {
		return userDao.getUserDepartments();
	}

	@Override
	public List<User> getUserReportingToList() throws Exception {
		return userDao.getUserReportingToList();
	}

	@Override
	public List<User> getUsersList(User obj) throws Exception {
		return userDao.getUsersList(obj);
	}

}
