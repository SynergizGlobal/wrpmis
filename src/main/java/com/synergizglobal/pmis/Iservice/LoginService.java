package com.synergizglobal.pmis.Iservice;

import java.sql.SQLException;

import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.model.User;


public interface LoginService {

	public User validateUser(User user) throws SQLException,NoKeyException;
	
	public String changePassword(User user) throws Exception;

	public boolean addUserLogoutDateTime(User uObj) throws SQLException;

	public boolean updateSingleLoginSessionId(String single_login_session_id, String user_id) throws SQLException;

	public boolean logoutFromAllDevices(User obj) throws SQLException;

	public String getSingleLoginSessionId(User userDetails) throws SQLException;
	
}
