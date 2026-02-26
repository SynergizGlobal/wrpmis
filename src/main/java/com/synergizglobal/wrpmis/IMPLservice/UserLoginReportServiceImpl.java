package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.UserLoginReportDao;
import com.synergizglobal.wrpmis.Iservice.UserLoginReportService;
import com.synergizglobal.wrpmis.model.User;

@Service
public class UserLoginReportServiceImpl implements UserLoginReportService{

	@Autowired
	UserLoginReportDao dao;

	@Override
	public List<User> getDepartmentList(User dObj) throws Exception {
		return dao.getDepartmentList(dObj);
	}

	@Override
	public User getUserLoginDetails(User dObj) throws Exception {
		return dao.getUserLoginDetails(dObj);
	}

	@Override
	public List<User> getDesignationList(User dObj) throws Exception {
		return dao.getDesignationList(dObj);
	}

	@Override
	public List<User> getUserLoginList(User dObj) throws Exception {
		return dao.getUserLoginList(dObj);
	}
}
