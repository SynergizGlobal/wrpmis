package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ProfileDao;
import com.synergizglobal.pmis.Iservice.ProfileService;
import com.synergizglobal.pmis.model.User;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileDao profileDao;
	
	@Override
	public User getUserProfile(String userId) throws Exception {
		return profileDao.getUserProfile(userId);
	}

	@Override
	public boolean updateProfile(User user) throws Exception {
		return profileDao.updateProfile(user);
	}
	
	@Override
	public boolean insertLeaveResponsibility(User user) throws Exception {
		return profileDao.insertLeaveResponsibility(user);
	}
	@Override
	public boolean checkLeaveResponsibility(User user) throws Exception {
		return profileDao.checkLeaveResponsibility(user);
	}
	@Override
	public boolean deleteLeaveResponsibility(User user) throws Exception {
		return profileDao.deleteLeaveResponsibility(user);
	}	
	@Override
	public boolean generateAutoResponsibility() throws Exception {
		return profileDao.generateAutoResponsibility();
	}	
	
	@Override
	public boolean generateRevertAutoResponsibility() throws Exception {
		return profileDao.generateRevertAutoResponsibility();
	}	
	
	@Override
	public List<User> getPastLeaves(User user) throws Exception {
		return profileDao.getPastLeaves(user);
	}	
	@Override
	public int getModulesCount() throws Exception {
		return profileDao.getModulesCount();
	}	
	
}
