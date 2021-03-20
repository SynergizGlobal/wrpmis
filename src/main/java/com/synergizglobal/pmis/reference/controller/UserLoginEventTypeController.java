package com.synergizglobal.pmis.reference.controller;

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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.UserLoginEventTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class UserLoginEventTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UserTypeController.class);
	
	@Autowired
	UserLoginEventTypeService service;
	
	
	@RequestMapping(value="/user-login-event-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView userLoginEventType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.userLoginEventType);
		try {
			TrainingType userLoginEventTypeDetails = service.getUserTypeDetails(obj);
			model.addObject("userLoginEventTypeDetails",userLoginEventTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("userLoginEventType :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-user-login-event-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUserLoginEventType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-login-event-type");
			boolean flag =  service.addUserLoginEventType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Login Event Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("addUserLoginEventType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-user-login-event-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUserLoginEventType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-login-event-type");
			boolean flag =  service.updateUserLoginEventType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Login Event Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("updateUserLoginEventType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-user-login-event-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUserLoginEventType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-login-event-type");
			boolean flag =  service.deleteUserLoginEventType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Login Event Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUserLoginEventType : " + e.getMessage());
		}
		return model;
	}
	
}

