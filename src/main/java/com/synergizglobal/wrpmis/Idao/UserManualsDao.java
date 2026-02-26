package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.UserManuals;

public interface UserManualsDao {
	List<UserManuals> getUserManuals(UserManuals obj) throws Exception;

	List<UserManuals> getStatusFilterListInUserManuals(UserManuals obj) throws Exception;

	boolean addUserManual(UserManuals obj) throws Exception;

	boolean updateUserManual(UserManuals obj) throws Exception;

	boolean deleteUserManual(UserManuals obj) throws Exception;
}
