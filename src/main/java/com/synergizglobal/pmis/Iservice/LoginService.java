package com.synergizglobal.pmis.Iservice;

import com.synergizglobal.pmis.model.User;


public interface LoginService {

	public User validateUser(User user) throws Exception;
	
	public String changePassword(User user) throws Exception;
	
}
