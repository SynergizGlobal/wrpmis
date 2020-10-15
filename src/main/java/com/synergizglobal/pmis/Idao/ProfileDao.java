package com.synergizglobal.pmis.Idao;

import com.synergizglobal.pmis.model.User;

public interface ProfileDao {
	
	public User getUserProfile(String userId) throws Exception;
}
