package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.User;

public interface UserLoginReportDao {

	List<User> getDepartmentList(User dObj) throws Exception;

	User getUserLoginDetails(User dObj) throws Exception;

	List<User> getDesignationList(User dObj) throws Exception;

	List<User> getUserLoginList(User dObj) throws Exception;

}
