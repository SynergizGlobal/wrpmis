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
	public List<User> getUserProfile(String userId) throws Exception {
		return profileDao.getUserProfile(userId);
	}

}
