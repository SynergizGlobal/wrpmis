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

import com.synergizglobal.pmis.reference.Iservice.RrResponsibleExecutivesService;
import com.synergizglobal.pmis.reference.Iservice.WorkModuleUserAccessService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.reference.model.WorkModuleUserAccess;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class WorkModuleUserAccessController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkModuleUserAccessController.class);
	
	@Autowired
	WorkModuleUserAccessService service;
	
	@Autowired
	RrResponsibleExecutivesService RRservice;	

	@RequestMapping(value="/work-module-user-access",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView WorkModuleUserAccess(HttpSession session,@ModelAttribute WorkModuleUserAccess obj){
		ModelAndView model = new ModelAndView(PageConstants.workModuleUserAccess);
		TrainingType tobj=null;
		
		try {
			List<WorkModuleUserAccess> projectsList = service.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<WorkModuleUserAccess> modulesList = service.getModulesList(obj);
			model.addObject("modulesList", modulesList);
			
			List<WorkModuleUserAccess> worksList = service.getWorksList(obj); 
			model.addObject("worksList", worksList);
			
			List<WorkModuleUserAccess> usersDetails = service.getUsersDetails(obj);
			model.addObject("usersDetails",usersDetails);
			
			List<TrainingType> moduleUsersDetails = RRservice.getUsersDetails(tobj);
			model.addObject("moduleUsersDetails",moduleUsersDetails);				
			
			WorkModuleUserAccess WorkModuleUserAccessDetails = service.getWorkModuleUserAccessDetails(obj);
			model.addObject("WorkModuleUserAccessDetails", WorkModuleUserAccessDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("WorkModuleUserAccess : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksListForUsersDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkModuleUserAccess> getWorksListForUsersDetails(@ModelAttribute WorkModuleUserAccess obj,HttpSession session) {
		List<WorkModuleUserAccess> usersList = null;  
		try {
			usersList = service.getUsersDetails(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForWorkWiseUserAccess : " + e.getMessage());
		}
		return usersList;
	}	
	
	
	@RequestMapping(value = "/ajax/getWorksListForWorkWiseUserAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkModuleUserAccess> getWorksListForWorkWiseUserAccess(@ModelAttribute WorkModuleUserAccess obj,HttpSession session) {
		List<WorkModuleUserAccess> worksList = null;  
		try {
			worksList = service.getWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForWorkWiseUserAccess : " + e.getMessage());
		}
		return worksList;
	}	
	
	
	@RequestMapping(value = "/ajax/getWorkModuleWiseUsers", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkModuleUserAccess> getWorkModuleWiseUsers(@ModelAttribute WorkModuleUserAccess obj,HttpSession session) {
		List<WorkModuleUserAccess> worksList = null;  
		try {
			worksList = service.getWorkModuleWiseUsers(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkModuleWiseUsers : " + e.getMessage());
		}
		return worksList;
	}

	@RequestMapping(value = "/ajax/getSelectedExecutionContracts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkModuleUserAccess> getSelectedExecutionContracts(@ModelAttribute WorkModuleUserAccess obj,HttpSession session) {
		List<WorkModuleUserAccess> worksList = null;  
		try {
			worksList = service.getSelectedExecutionContracts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSelectedExecutionContracts : " + e.getMessage());
		}
		return worksList;
	}	
	
	
	@RequestMapping(value = "/ajax/addUserAccessforExecutionContracts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addUserAccessforExecutionContracts(@ModelAttribute WorkModuleUserAccess obj,HttpSession session) throws Exception {
		boolean flag = false;
		try {
			flag = service.addUserAccessforExecutionContracts(obj);
		} catch (SQLException e) {
			logger.error("addUserAccessforExecutionContracts : " + e.getMessage());
		}
		return flag;
	}

	@RequestMapping(value = "/ajax/addWorkModuleUserAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addWorkModuleUserAccess(@ModelAttribute WorkModuleUserAccess obj,HttpSession session) throws Exception {
		boolean flag = false;
		try {
			flag = service.addWorkModuleUserAccess(obj);
		} catch (SQLException e) {
			logger.error("addWorkModuleUserAccess : " + e.getMessage());
		}
		return flag;
	}	

}




