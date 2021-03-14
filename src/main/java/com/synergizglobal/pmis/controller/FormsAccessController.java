package com.synergizglobal.pmis.controller;

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

import com.synergizglobal.pmis.Iservice.FormsAccessService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Form;

@Controller
public class FormsAccessController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FormsAccessController.class);
	
	@Autowired
	FormsAccessService service;
	
	@RequestMapping(value="/forms",method={RequestMethod.GET})
	public ModelAndView forms(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.formsAccessGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("forms : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-forms-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Form> getFormsList(@ModelAttribute Form obj) {
		List<Form> objList = null;
		try {
			objList = service.getFormsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFormsList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getModulesFilterListInForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Form> getModulesList(@ModelAttribute Form obj) {
		List<Form> objList = null;
		try {
			objList = service.getModulesFilterListInForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getModulesList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusFilterListInForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Form> getStatusList(@ModelAttribute Form obj) {
		List<Form> objList = null;
		try {
			objList = service.getStatusFilterListInForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/add-form-access", method = {RequestMethod.GET})
	public ModelAndView addFormAccess(@ModelAttribute Form obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditFormAccessForm);
			model.addObject("action", "add");
			
			List<Form> modulesList = service.getModulesListForFormAccess(obj);
			model.addObject("modulesList", modulesList);
			
			List<Form> foldersList = service.getFolderssListForFormAccess(obj);
			model.addObject("foldersList", foldersList);
			
			List<Form> statusList = service.getStatusListForFormAccess(obj);
			model.addObject("statusList", statusList);
			
			List<Form> user_roles = service.getUserRolesInFormAccess(obj);
			model.addObject("user_roles", user_roles);
			
			List<Form> user_types = service.getUserTypesInFormAccess(obj);
			model.addObject("user_types", user_types);
			
			List<Form> users = service.getUsersInFormAccess(obj);
			model.addObject("users", users);
			
			
		}catch (Exception e) {
				logger.error("addFormAccess : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getUserRolesInFormAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Form> getUserRolesInDashboardAccess(@ModelAttribute Form obj) {
		List<Form> objsList = null;
		try {
			objsList = service.getUserRolesInFormAccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserRolesInFormAccess : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getUserTypesInFormAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Form> getUserTypesInFormAccess(@ModelAttribute Form obj) {
		List<Form> objsList = null;
		try {
			objsList = service.getUserTypesInFormAccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserTypesInFormAccess : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getUsersInFormAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Form> getUsersInFormAccess(@ModelAttribute Form obj) {
		List<Form> objsList = null;
		try {
			objsList = service.getUsersInFormAccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUsersInFormAccess : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/get-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getForm(@ModelAttribute Form obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditFormAccessForm);
			model.addObject("action", "edit");
			
			List<Form> modulesList = service.getModulesListForFormAccess(obj);
			model.addObject("modulesList", modulesList);
			
			List<Form> foldersList = service.getFolderssListForFormAccess(obj);
			model.addObject("foldersList", foldersList);
			
			List<Form> statusList = service.getStatusListForFormAccess(obj);
			model.addObject("statusList", statusList);
			
			List<Form> user_roles = service.getUserRolesInFormAccess(obj);
			model.addObject("user_roles", user_roles);
			
			List<Form> user_types = service.getUserTypesInFormAccess(obj);
			model.addObject("user_types", user_types);
			
			List<Form> users = service.getUsersInFormAccess(obj);
			model.addObject("users", users);
		
			Form formDetails = service.getForm(obj);
			model.addObject("formDetails", formDetails);
			
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-form", method = {RequestMethod.POST})
	public ModelAndView addForm(@ModelAttribute Form obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/forms");
			boolean flag =  service.addForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Form Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Form is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Form is failed. Try again.");
			logger.error("addForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-form", method = {RequestMethod.POST})
	public ModelAndView updateForm(@ModelAttribute Form obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/forms");
			boolean flag =  service.updateForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Form Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Form is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Form is failed. Try again.");
			logger.error("updateForm : " + e.getMessage());
		}
		return model;
	}
}
