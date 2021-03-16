package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UserRoleDao;
import com.synergizglobal.pmis.reference.Iservice.UserRoleService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	UserRoleDao dao;

	@Override
	public List<TrainingType> getUserRolesList() throws Exception {
		return dao.getUserRolesList();
	}

	@Override
	public boolean addUserRole(TrainingType obj) throws Exception {
		return dao.addUserRole(obj);
	}

	@Override
	public TrainingType getUserRoleDetails(TrainingType obj) throws Exception {
		return dao.getUserRoleDetails(obj);
	}

	@Override
	public boolean updateUserRole(TrainingType obj) throws Exception {
		return dao.updateUserRole(obj);
	}

	@Override
	public boolean deleteUserRole(TrainingType obj) throws Exception {
		return dao.deleteUserRole(obj);
	}
}
