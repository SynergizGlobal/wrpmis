package com.synergizglobal.pmis.reference.controller;

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

import com.synergizglobal.pmis.reference.Iservice.WorkModuleUserAccessService;
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

	@RequestMapping(value="/work-module-user-access",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView WorkModuleUserAccess(HttpSession session,@ModelAttribute WorkModuleUserAccess obj){
		ModelAndView model = new ModelAndView(PageConstants.workModuleUserAccess);
		try {
			List<WorkModuleUserAccess> projectsList = service.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<WorkModuleUserAccess> modulesList = service.getModulesList(obj);
			model.addObject("modulesList", modulesList);
			
			List<WorkModuleUserAccess> worksList = service.getWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<WorkModuleUserAccess> usersDetails = service.getUsersDetails(obj);
			model.addObject("usersDetails",usersDetails);			
			
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
	
	
	
	@RequestMapping(value = "/add-work-module-user-access", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWorkModuleUserAccess(@ModelAttribute WorkModuleUserAccess obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-module-user-access");
			boolean flag =  service.addWorkModuleUserAccess(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Module User Access Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work Module User Access is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work Module User Access is failed. Try again.");
			logger.error("addWorkModuleUserAccess : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-work-module-user-access", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateWorkModuleUserAccess(@ModelAttribute WorkModuleUserAccess obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-module-user-access");
			boolean flag =  service.updateWorkModuleUserAccess(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Module User Access Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work Module User Access is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Work Module User Access is failed. Try again.");
			logger.error("updateWorkModuleUserAccess : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-work-module-user-access", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteWorkModuleUserAccess(@ModelAttribute WorkModuleUserAccess obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-module-user-access");
			boolean flag =  service.deleteWorkModuleUserAccess(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Module User Access Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteWorkModuleUserAccess : " + e.getMessage());
		}
		return model;
	}
}




