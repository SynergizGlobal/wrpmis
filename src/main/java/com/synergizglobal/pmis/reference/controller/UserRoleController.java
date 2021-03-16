package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.reference.Iservice.UserRoleService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class UserRoleController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UserRoleController.class);
	
	@Autowired
	UserRoleService service;
	
	@RequestMapping(value="/user-role",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView userRole(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.userRole);
		try {
			List<TrainingType> userRoleList = service.getUserRolesList();
			model.addObject("userRoleList", userRoleList);
			TrainingType userRoleDetails = service.getUserRoleDetails(obj);
			model.addObject("userRoleDetails",userRoleDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("userRole : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-user-role", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUserRole(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-role");
			boolean flag =  service.addUserRole(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Role Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding User Role is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding User Role is failed. Try again.");
			logger.error("addUserRole : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/update-user-role", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUserRole(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-role");
			boolean flag =  service.updateUserRole(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Role Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating User Role is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating User Role is failed. Try again.");
			logger.error("updateUserRole : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-user-role", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUserRole(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-role");
			boolean flag =  service.deleteUserRole(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Role Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUserRole : " + e.getMessage());
		}
		return model;
	}
	
}



