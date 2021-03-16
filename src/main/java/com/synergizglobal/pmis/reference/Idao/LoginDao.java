package com.synergizglobal.pmis.reference.Idao;

import java.sql.SQLException;

import com.synergizglobal.pmis.reference.model.User;
import com.synergizglobal.pmis.exceptions.NoKeyException;



public interface LoginDao {
	public User validateUser(User user) throws SQLException,NoKeyException;
	
	public String changePassword(User user) throws Exception;

}



