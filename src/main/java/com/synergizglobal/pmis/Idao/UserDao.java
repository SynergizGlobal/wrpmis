package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.User;

public interface UserDao {

	List<User> getUserRoles() throws Exception;

	List<User> getUserDepartments() throws Exception;

	List<User> getUserReportingToList() throws Exception;

	List<User> getUsersList(User obj) throws Exception;

}
