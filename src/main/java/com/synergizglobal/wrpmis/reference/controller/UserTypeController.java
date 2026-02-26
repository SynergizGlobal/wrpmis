package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.reference.Iservice.UserTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class UserTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UserTypeController.class);
	
	@Autowired
	UserTypeService service;
	
	@RequestMapping(value="/user-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView userType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.userType);
		try {
			TrainingType userTypeDetails = service.getUserTypeDetails(obj);
			model.addObject("userTypeDetails",userTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("userType :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-user-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUserType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-type");
			boolean flag =  service.addUserType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding User Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding User Type is failed. Try again.");
			logger.error("addUserType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-user-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUserType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-type");
			boolean flag =  service.updateUserType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating User Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating User Type is failed. Try again.");
			logger.error("updateUserType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-user-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUserType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-type");
			boolean flag =  service.deleteUserType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUserType : " + e.getMessage());
		}
		return model;
	}
	
}
