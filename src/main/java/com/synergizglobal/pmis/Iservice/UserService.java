package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.User;

public interface UserService {

	List<User> getUserRoles() throws Exception;

	List<User> getUserDepartments() throws Exception;

	List<User> getUserReportingToList() throws Exception;

	List<User> getUsersList(User obj) throws Exception;

}
