package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.UserManuals;

public interface UserManualsService {

	List<UserManuals> getUserManuals(UserManuals obj) throws Exception;

	List<UserManuals> getStatusFilterListInUserManuals(UserManuals obj) throws Exception;

	boolean addUserManual(UserManuals obj) throws Exception;

	boolean updateUserManual(UserManuals obj) throws Exception;

	boolean deleteUserManual(UserManuals obj) throws Exception;

}
