package com.synergizglobal.pmis.Iservice;

import com.synergizglobal.pmis.model.User;



public interface ProfileService {
	
	public User getUserProfile(String userId) throws Exception;

}
