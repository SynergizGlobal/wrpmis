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

	@Override
	public String addUser(User obj) throws Exception {
		return userDao.addUser(obj);
	}

	@Override
	public User getUser(User obj) throws Exception {
		return userDao.getUser(obj);
	}

	@Override
	public boolean updateUser(User obj) throws Exception {
		return userDao.updateUser(obj);
	}

	@Override
	public boolean deleteUser(User obj) throws Exception {
		return userDao.deleteUser(obj);
	}

	@Override
	public List<User> getWorksForUserAccessTypes(User obj) throws Exception {
		return userDao.getWorksForUserAccessTypes(obj);
	}

	@Override
	public List<User> getModulesForUserAccessTypes(User obj) throws Exception {
		return userDao.getModulesForUserAccessTypes(obj);
	}

	@Override
	public List<User> getDepartmentsForUserAccessTypes(User obj) throws Exception {
		return userDao.getDepartmentsForUserAccessTypes(obj);
	}

	@Override
	public List<User> getContractsForUserAccessTypes(User obj) throws Exception {
		return userDao.getContractsForUserAccessTypes(obj);
	}

	@Override
	public List<User> getUserAccessTypes(User obj) throws Exception {
		return userDao.getUserAccessTypes(obj);
	}

	@Override
	public int uploadUsers(List<User> usersList) throws Exception {
		return userDao.uploadUsers(usersList);
	}

	@Override
	public List<User> getPmisKeys() throws Exception {
		return userDao.getPmisKeys();
	}

	@Override
	public String checkPMISKeyAvailability(User obj) throws Exception {
		return userDao.checkPMISKeyAvailability(obj);
	}

}
