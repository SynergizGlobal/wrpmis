package com.synergizglobal.pmis.Idao;

import java.sql.SQLException;

import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.model.User;



public interface LoginDao {
	public User validateUser(User user, String single_login_session_id) throws SQLException,NoKeyException;
	
	public String changePassword(User user) throws Exception;

	public boolean addUserLogoutDateTime(User uObj) throws SQLException;

	public boolean logoutFromAllDevices(User obj) throws SQLException;

	public String getSingleLoginSessionId(User obj) throws SQLException;

	public boolean checkUserId(String UserName) throws SQLException;

	public boolean checkUserEmail(String Email) throws SQLException;

	public String resetPassword(User user) throws Exception;

	public int encryptUserPasswords() throws Exception;
}



