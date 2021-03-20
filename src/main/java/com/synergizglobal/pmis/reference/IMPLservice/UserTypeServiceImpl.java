package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UserTypeDao;
import com.synergizglobal.pmis.reference.Iservice.UserTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class UserTypeServiceImpl implements UserTypeService{
	@Autowired
	UserTypeDao dao;

	@Override
	public TrainingType getUserTypeDetails(TrainingType obj) throws Exception {
		return dao.getUserTypeDetails(obj);
	}

	@Override
	public boolean addUserType(TrainingType obj) throws Exception {
		return dao.addUserType(obj);
	}

	@Override
	public boolean updateUserType(TrainingType obj) throws Exception {
		return dao.updateUserType(obj);
	}

	@Override
	public boolean deleteUserType(TrainingType obj) throws Exception {
		return dao.deleteUserType(obj);
	}
}
