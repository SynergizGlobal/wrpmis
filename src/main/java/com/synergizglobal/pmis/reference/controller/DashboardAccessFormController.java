package com.synergizglobal.pmis.reference.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.reference.Iservice.DashboardAccessFormService;
import com.synergizglobal.pmis.reference.model.DashboardAccessForm;
import com.synergizglobal.pmis.reference.model.DashboardAccessForm;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Dashboard;

@Controller
public class DashboardAccessFormController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DashboardAccessFormController.class);
	
	@Autowired
	DashboardAccessFormService service;

	@RequestMapping(value="/dashboard-access-form",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView DashboardAccessForm(HttpSession session,@ModelAttribute DashboardAccessForm obj){
		ModelAndView model = new ModelAndView(PageConstants.dashboardAccessForm);
		try {
			List<DashboardAccessForm> projectsList = service.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
		
			
			List<DashboardAccessForm> worksList = service.getWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<DashboardAccessForm> usersDetails = service.getUsersDetails(obj);
			model.addObject("usersDetails",usersDetails);	
			
			List<DashboardAccessForm> user_roles = service.getUserRolesInDashboardAccess(obj);
			model.addObject("user_roles", user_roles);
			
			List<DashboardAccessForm> user_types = service.getUserTypesInDashboardAccess(obj);
			model.addObject("user_types", user_types);
			
			List<DashboardAccessForm> users = service.getUsersInDashboardAccess(obj);
			model.addObject("users", users);
			
			List<DashboardAccessForm> dashboardNames = service.getdashboardNames(obj);
			model.addObject("dashboardNames", dashboardNames);			
			
			DashboardAccessForm DashboardAccessFormDetails = service.getDashboardAccessFormDetails(obj);
			
			
			model.addObject("DashboardAccessFormDetails", DashboardAccessFormDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("DashboardAccessForm : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getWorksListForDashboardAccessForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DashboardAccessForm> getWorksListForDashboardAccessForm(@ModelAttribute DashboardAccessForm obj,HttpSession session) {
		List<DashboardAccessForm> worksList = null;  
		try {
			worksList = service.getWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForDashboardAccessForm : " + e.getMessage());
		}
		return worksList;
	}	
	
	@RequestMapping(value = "/ajax/getDashboardUserAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DashboardAccessForm> getDashboardUserAccess(@ModelAttribute DashboardAccessForm obj,HttpSession session) {
		List<DashboardAccessForm> worksList = null;  
		try {
			worksList = service.getDashboardUserAccess(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashboardUserAccess : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/addDashboardUserAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addDashboardUserAccess(@ModelAttribute DashboardAccessForm obj,HttpSession session) throws Exception {
		boolean flag = false;
		try {
			flag = service.addDashboardUserAccess(obj);
		} catch (SQLException e) {
			logger.error("addDashboardUserAccess : " + e.getMessage());
		}
		return flag;
	}	
	
	
	@RequestMapping(value = "/ajax/updateWorkAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean updateWorkAccess(@ModelAttribute DashboardAccessForm obj,HttpSession session) throws Exception {
		boolean flag = false;
		try {
			flag = service.updateWorkAccess(obj);
		} catch (SQLException e) {
			logger.error("updateWorkAccess : " + e.getMessage());
		}
		return flag;
	}	
}




