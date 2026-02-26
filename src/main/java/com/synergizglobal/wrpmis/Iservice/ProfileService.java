package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.FOB;
import com.synergizglobal.wrpmis.model.User;



public interface ProfileService {
	
	public User getUserProfile(String userId) throws Exception;

	public boolean updateProfile(User user) throws Exception;
	
	public boolean insertLeaveResponsibility(User user) throws Exception;
	public boolean checkLeaveResponsibility(User user) throws Exception;
	public boolean deleteLeaveResponsibility(User user) throws Exception;
	public boolean generateAutoResponsibility() throws Exception;
	public boolean generateRevertAutoResponsibility() throws Exception;
	List<User> getPastLeaves(User obj) throws Exception;
	public int getModulesCount() throws Exception;

}
