package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.User;

public interface UserLoginReportService {

	List<User> getDepartmentList(User dObj) throws Exception;

	User getUserLoginDetails(User dObj) throws Exception;

	List<User> getDesignationList(User dObj) throws Exception;

	List<User> getUserLoginList(User dObj) throws Exception;

}
