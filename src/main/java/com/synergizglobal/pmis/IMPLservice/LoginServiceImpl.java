package com.synergizglobal.pmis.IMPLservice;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.LoginDao;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.model.User;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	/**
	 * This method validate the user
	 * 
	 * @param user is object for the model class User
	 * @return type of this method is userDetails
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public User validateUser(User user) throws SQLException,NoKeyException {
		return loginDao.validateUser(user);
	}
	
	/**
	 * This method change the password
	 * 
	 * @param user is object for the model class User 
	 * @return type of this method is temp that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public String changePassword(User user) throws Exception {
		return loginDao.changePassword(user);
	}

	@Override
	public boolean saveLogoutAction(String userId) throws Exception {
		return loginDao.saveLogoutAction(userId);
	}

	@Override
	public boolean addUserLogoutDateTime(User uObj) throws Exception {
		return loginDao.addUserLogoutDateTime(uObj);
	}

	
}
