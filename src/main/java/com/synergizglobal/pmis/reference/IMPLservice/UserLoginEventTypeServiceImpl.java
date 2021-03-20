package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UserLoginEventTypeDao;
import com.synergizglobal.pmis.reference.Iservice.UserLoginEventTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class UserLoginEventTypeServiceImpl implements UserLoginEventTypeService{
	@Autowired
	UserLoginEventTypeDao dao;

	@Override
	public TrainingType getUserTypeDetails(TrainingType obj) throws Exception {
		return dao.getUserTypeDetails(obj);
	}

	@Override
	public boolean addUserLoginEventType(TrainingType obj) throws Exception {
		return dao.addUserLoginEventType(obj);
	}

	@Override
	public boolean updateUserLoginEventType(TrainingType obj) throws Exception {
		return dao.updateUserLoginEventType(obj);
	}

	@Override
	public boolean deleteUserLoginEventType(TrainingType obj) throws Exception {
		return dao.deleteUserLoginEventType(obj);
	}


}
