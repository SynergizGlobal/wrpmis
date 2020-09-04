package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.User;

public interface ProfileDao {
	
	public List<User> getUserProfile(String userId) throws Exception;
}
