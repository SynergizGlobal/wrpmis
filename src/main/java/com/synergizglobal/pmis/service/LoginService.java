package com.synergizglobal.pmis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.dao.LoginDao;
import com.synergizglobal.pmis.model.User;


@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	/**
	 * This method validate the user
	 * 
	 * @param user is object for the model class User
	 * @return type of this method is userDetails
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public User validateUser(User user) throws Exception {
		return loginDao.validateUser(user);
	}
	
	/**
	 * This method change the password
	 * 
	 * @param user is object for the model class User 
	 * @return type of this method is temp that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public String changePassword(User user) throws Exception {
		return loginDao.changePassword(user);
	}

	
}
