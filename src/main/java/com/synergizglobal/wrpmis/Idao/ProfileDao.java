package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Form;
import com.synergizglobal.wrpmis.model.User;

public interface ProfileDao {
	
	public User getUserProfile(String userId) throws Exception;

	public boolean updateProfile(User user) throws Exception;

	public boolean insertLeaveResponsibility(User user) throws Exception;

	public List<User> getPastLeaves(User user) throws Exception;

	public boolean checkLeaveResponsibility(User user) throws Exception;

	public boolean generateAutoResponsibility() throws Exception;

	public boolean generateRevertAutoResponsibility() throws Exception;

	public int getModulesCount() throws Exception;

	public boolean deleteLeaveResponsibility(User user) throws Exception;

}
