package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.UserManualsDao;
import com.synergizglobal.pmis.Iservice.UserManualsService;
import com.synergizglobal.pmis.model.UserManuals;

@Service
public class UserManualsServiceImpl implements UserManualsService{
	
	@Autowired
	UserManualsDao dao;
	
	@Override
	public List<UserManuals> getUserManuals(UserManuals obj) throws Exception {
		return dao.getUserManuals(obj);
	}

	@Override
	public List<UserManuals> getStatusFilterListInUserManuals(UserManuals obj) throws Exception {
		return dao.getStatusFilterListInUserManuals(obj);
	}

	@Override
	public boolean addUserManual(UserManuals obj) throws Exception {
		return dao.addUserManual(obj);
	}

	@Override
	public boolean updateUserManual(UserManuals obj) throws Exception {
		return dao.updateUserManual(obj);
	}

	@Override
	public boolean deleteUserManual(UserManuals obj) throws Exception {
		return dao.deleteUserManual(obj);
	}

}
