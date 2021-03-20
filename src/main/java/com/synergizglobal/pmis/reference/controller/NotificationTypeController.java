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

import com.synergizglobal.pmis.reference.Iservice.NotificationTypeService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class NotificationTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(NotificationTypeController.class);
	
	@Autowired
	NotificationTypeService service;
	
	@RequestMapping(value="/notification-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView notificationType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.notificationType);
		try {
			List<Risk> notificationTypeList = service.getNotificationTypeList();
			model.addObject("notificationTypeList", notificationTypeList);
			
			TrainingType notificationTypeDetails = service.getNotificationTypeDetails(obj);
			model.addObject("notificationTypeDetails",notificationTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("notificationType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-notification-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addNotificationType(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/notification-type");
			boolean flag =  service.addNotificationType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Notification Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Notification Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Notification Type is failed. Try again.");
			logger.error("addNotificationType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-notification-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateNotificationType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/notification-type");
			boolean flag =  service.updateNotificationType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Notification Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Notification Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Notification Type is failed. Try again.");
			logger.error("updateNotificationType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-notification-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteNotificationType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/notification-type");
			boolean flag =  service.deleteNotificationType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Notification Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteNotificationType : " + e.getMessage());
		}
		return model;
	}
	
}








