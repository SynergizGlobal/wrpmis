package com.synergizglobal.pmis.Iservice;

import java.sql.SQLException;

import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.model.User;


public interface LoginService {

	public User validateUser(User user) throws SQLException,NoKeyException;
	
	public String changePassword(User user) throws Exception;

	public boolean saveLogoutAction(String userId) throws Exception;

	public boolean addUserLogoutDateTime(User uObj) throws Exception;
	
}
