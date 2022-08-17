package com.synergizglobal.pmis.IMPLservice;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.LoginDao;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.exceptions.NotEnabledTestEnv;
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
	public User validateUser(User user, String single_login_session_id) throws Exception,SQLException,NoKeyException,NotEnabledTestEnv {
		return loginDao.validateUser(user,single_login_session_id);
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
	public boolean addUserLogoutDateTime(User uObj) throws SQLException {
		return loginDao.addUserLogoutDateTime(uObj);
	}

	@Override
	public boolean logoutFromAllDevices(User obj) throws SQLException {
		return loginDao.logoutFromAllDevices(obj);
	}

	@Override
	public String getSingleLoginSessionId(User obj) throws SQLException {
		return loginDao.getSingleLoginSessionId(obj);
	}
	

	@Override
	public boolean checkUserId(String user_id) throws SQLException {
		return loginDao.checkUserId(user_id);
	}
	

	@Override
	public boolean checkUserEmail(String Email) throws SQLException {
		return loginDao.checkUserEmail(Email);
	}
	
	@Override
	public String resetPassword(User user) throws Exception {
		return loginDao.resetPassword(user);
	}

	@Override
	public int encryptUserPasswords() throws Exception {
		return loginDao.encryptUserPasswords();
	}
	
	@Override
	public String getEmailbyUserId(String UserId) throws Exception
	{
		return loginDao.getEmailbyUserId(UserId);
	}
	
	@Override
	public String getUserNameByEmail(String Email) throws Exception
	{
		return loginDao.getUserNameByEmail(Email);
	}	
	
}
