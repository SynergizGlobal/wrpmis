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

import com.synergizglobal.pmis.reference.Iservice.UserAccessTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class UserAccessTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UserAccessTypeController.class);
	
	@Autowired
	UserAccessTypeService service;
	
	@RequestMapping(value="/user-access-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView userAccessType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.userAccessType);
		try {
			List<TrainingType> userAccessTypeList = service.getUserRolesList();
			model.addObject("userAccessTypeList", userAccessTypeList);
			TrainingType userAccessTypeDetails = service.getUserAccessTypeDetails(obj);
			model.addObject("userAccessTypeDetails",userAccessTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("userAccessType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-user-access-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUserAccessType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-access-type");
			boolean flag =  service.addUserAccessType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Access Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding User Access Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding User Access Type is failed. Try again.");
			logger.error("addUserAccessType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-user-access-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateAccessType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-access-type");
			boolean flag =  service.updateUserAccessType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Access Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating User Access Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating User Access Type is failed. Try again.");
			logger.error("updateAccessType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-user-access-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteAccessType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/user-access-type");
			boolean flag =  service.deleteUserAccessType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User Access Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteAccessType : " + e.getMessage());
		}
		return model;
	}
	
}







