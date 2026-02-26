package com.synergizglobal.wrpmis.controller;

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

import com.synergizglobal.wrpmis.Iservice.UserManualsService;
import com.synergizglobal.wrpmis.constants.PageConstants2;
import com.synergizglobal.wrpmis.model.UserManuals;

@Controller
public class UserManualsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UserManualsController.class);
	
	@Autowired
	UserManualsService service;
	
	@RequestMapping(value="/user-manuals",method={RequestMethod.GET})
	public ModelAndView userManuals(@ModelAttribute UserManuals obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants2.userManuals);
		try {
			List<UserManuals> statusList = service.getStatusFilterListInUserManuals(obj);
			model.addObject("statusList", statusList);
		}catch (Exception e) {
			logger.error("userManuals : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getUserManuals", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserManuals> getUserManuals(@ModelAttribute UserManuals obj) {
		List<UserManuals> objList = null;
		try {
			objList = service.getUserManuals(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserManuals : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value = "/add-user-manual", method = {RequestMethod.POST})
	public ModelAndView addUserManual(@ModelAttribute UserManuals obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by(userName);
			model.setViewName("redirect:/user-manuals");
			boolean flag =  service.addUserManual(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Manual Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Manual is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Manual is failed. Try again.");
			logger.error("addUserManual : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-user-manual", method = {RequestMethod.POST})
	public ModelAndView updateUserManual(@ModelAttribute UserManuals obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/user-manuals");
			boolean flag =  service.updateUserManual(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Manual Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Manual is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Manual is failed. Try again.");
			logger.error("updateUserManual : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-user-manual", method = {RequestMethod.POST})
	public ModelAndView deleteUserManual(@ModelAttribute UserManuals obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/user-manuals");
			boolean flag =  service.deleteUserManual(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Manual deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Deleting Manual is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Deleting Manual is failed. Try again.");
			logger.error("updateUserManual : " + e.getMessage());
		}
		return model;
	}
	
}
