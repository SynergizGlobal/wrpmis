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
	public boolean addUserLogoutDateTime(User uObj) throws SQLException {
		return loginDao.addUserLogoutDateTime(uObj);
	}

	@Override
	public boolean updateSingleLoginSessionId(String single_login_session_id, String user_id) throws SQLException {
		return loginDao.updateSingleLoginSessionId(single_login_session_id,user_id);
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
	public boolean checkUserName(String UserName) throws SQLException {
		return loginDao.checkUserName(UserName);
	}
	

	@Override
	public boolean checkUserEmail(String Email) throws SQLException {
		return loginDao.checkUserEmail(Email);
	}
	
	@Override
	public String resetPassword(User user) throws Exception {
		return loginDao.resetPassword(user);
	}
	
}
