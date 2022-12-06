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

import com.synergizglobal.pmis.reference.Iservice.DashboardAccessFormService;
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
			
			List<DashboardAccessForm> modulesList = service.getModulesList(obj);
			model.addObject("modulesList", modulesList);
			
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
	
	
	@RequestMapping(value = "/ajax/getDashboardAccessForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DashboardAccessForm> getDashboardAccessForm(@ModelAttribute DashboardAccessForm obj,HttpSession session) {
		List<DashboardAccessForm> worksList = null;  
		try {
			worksList = service.getWorkModuleWiseUsers(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashboardAccessForm : " + e.getMessage());
		}
		return worksList;
	}	
	
	
	
	@RequestMapping(value = "/add-dashboard-access-form", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDashboardAccessForm(@ModelAttribute DashboardAccessForm obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/dashboard-access-form");
			boolean flag =  service.addDashboardAccessForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Access Form Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Dashboard Access Form is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Dashboard Access Form is failed. Try again.");
			logger.error("addDashboardAccessForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-dashboard-access-form", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDashboardAccessForm(@ModelAttribute DashboardAccessForm obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/dashboard-access-form");
			boolean flag =  service.updateDashboardAccessForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Access Form Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Dashboard Access Form is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Dashboard Access Form is failed. Try again.");
			logger.error("updateDashboardAccessForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-dashboard-access-form", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDashboardAccessForm(@ModelAttribute DashboardAccessForm obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/dashboard-access-form");
			boolean flag =  service.deleteDashboardAccessForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Access Form Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDashboardAccessForm : " + e.getMessage());
		}
		return model;
	}
}




