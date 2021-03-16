package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UserAccessTypeDao;
import com.synergizglobal.pmis.reference.Iservice.UserAccessTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class UserAccessTypeServiceImpl implements UserAccessTypeService{

	@Autowired
	UserAccessTypeDao dao;

	@Override
	public List<TrainingType> getUserRolesList() throws Exception {
		return dao.getUserRolesList();
	}

	@Override
	public boolean addUserAccessType(TrainingType obj) throws Exception {
		return dao.addUserAccessType(obj);
	}

	@Override
	public TrainingType getUserAccessTypeDetails(TrainingType obj) throws Exception {
		return dao.getUserAccessTypeDetails(obj);
	}

	@Override
	public boolean updateUserAccessType(TrainingType obj) throws Exception {
		return dao.updateUserAccessType(obj);
	}

	@Override
	public boolean deleteUserAccessType(TrainingType obj) throws Exception {
		return dao.deleteUserAccessType(obj);
	}
}
