package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.User;

public interface UserService {

	List<User> getUserRoles() throws Exception;

	List<User> getUserDepartments() throws Exception;

	List<User> getUserReportingToList() throws Exception;

	List<User> getUsersList(User obj) throws Exception;

	String addUser(User obj) throws Exception;

	User getUser(User obj) throws Exception;
	
	boolean updateUser(User obj) throws Exception;
	
	boolean deleteUser(User obj) throws Exception;

	List<User> getWorksForUserAccessTypes(User obj) throws Exception;

	List<User> getModulesForUserAccessTypes(User obj) throws Exception;

	List<User> getDepartmentsForUserAccessTypes(User obj) throws Exception;

	List<User> getContractsForUserAccessTypes(User obj) throws Exception;

	List<User> getUserAccessTypes(User obj) throws Exception;

	int uploadUsers(List<User> usersList) throws Exception;

	List<User> getPmisKeys() throws Exception;
	
}
