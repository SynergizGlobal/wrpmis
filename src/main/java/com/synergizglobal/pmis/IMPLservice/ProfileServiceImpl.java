package com.synergizglobal.pmis.IMPLservice;

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

}
