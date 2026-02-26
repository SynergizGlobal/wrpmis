package com.synergizglobal.wrpmis.reference.Idao;

import java.sql.SQLException;

import com.synergizglobal.wrpmis.exceptions.NoKeyException;
import com.synergizglobal.wrpmis.reference.model.User;



public interface LoginDao {
	public User validateUser(User user) throws SQLException,NoKeyException;
	
	public String changePassword(User user) throws Exception;

}



