package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.User;



public interface ProfileService {
	
	public List<User> getUserProfile(String userId) throws Exception;

}
