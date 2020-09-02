package com.synergizglobal.pmis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.dao.HomeDao;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;

@Service
public class HomeService {
	@Autowired
	HomeDao dao;
	
	/**
	 * This method get the menu list
	 * @return type of this method is  menuList that is List type object
	 * @throws Exception will raise an exception when abnormal termination occur.
	 */
	public List<TableauDashboard> getMenuList() throws Exception {
		return dao.getMenuList();
	}
	/**
	 * This method get the forms list
	 * @param base it is string type variable that holds the base
	 * * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Forms> getFormsList(String base) throws Exception {
		return dao.getFormsList(base);
	}
	/**
	 * This method get the projects list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getProjectsList() throws Exception {
		return dao.getProjectsList();
	}
	/**
	 * This method get the work list by project
	 * 
	 * @param obj is object for the model class Activity 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 * 
	 */
	public List<Activity> getWorksListByProject(Activity obj) throws Exception {
		return dao.getWorksListByProject(obj);
	}
	/**
	 * This method get the modules list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getModulesList() throws Exception {
		return dao.getModulesList();
	}
	/**
	 * This method get the work module status
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is workModuleStatus that is object type of model class Activity 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Activity getWorkModuleStatus(Activity obj) throws Exception {
		return dao.getWorkModuleStatus(obj);
	}
	public List<User> getUserDetails(String user_Id) throws Exception {
		return dao.getUserList(user_Id);
	}
}
