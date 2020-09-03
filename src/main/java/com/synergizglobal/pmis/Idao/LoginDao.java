package com.synergizglobal.pmis.Idao;

import com.synergizglobal.pmis.model.User;



public interface LoginDao {
	public User validateUser(User user) throws Exception;
	
	public boolean addUserLogin(String user_id) throws Exception;
	
	public String changePassword(User user) throws Exception;

}



