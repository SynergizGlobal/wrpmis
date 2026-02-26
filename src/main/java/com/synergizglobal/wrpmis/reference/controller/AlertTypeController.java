package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.reference.Iservice.AlertTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class AlertTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(AlertTypeController.class);
	
	@Autowired
	AlertTypeService service;
	
	@RequestMapping(value="/alert-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView alertType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.alertType);
		try {
			
			TrainingType alertTypeDetails = service.getAlertTypeDetails(obj);
			model.addObject("alertTypeDetails",alertTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("alertType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-alert-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addAlertType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/alert-type");
			boolean flag =  service.addAlertType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Alert Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Alert Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Alert Type is failed. Try again.");
			logger.error("addAlertType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-alert-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateAlertType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/alert-type");
			boolean flag =  service.updateAlertType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Alert Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Alert Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Alert Type is failed. Try again.");
			logger.error("updateAlertType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-alert-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteAlertType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/alert-type");
			boolean flag =  service.deleteAlertType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Alert Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteAlertType : " + e.getMessage());
		}
		return model;
	}
	
}

