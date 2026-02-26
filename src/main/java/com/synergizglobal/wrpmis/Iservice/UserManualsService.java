package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.UserManuals;

public interface UserManualsService {

	List<UserManuals> getUserManuals(UserManuals obj) throws Exception;

	List<UserManuals> getStatusFilterListInUserManuals(UserManuals obj) throws Exception;

	boolean addUserManual(UserManuals obj) throws Exception;

	boolean updateUserManual(UserManuals obj) throws Exception;

	boolean deleteUserManual(UserManuals obj) throws Exception;

}
