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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.AlertLevelService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class AlertLevelController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(AlertLevelController.class);
	
	@Autowired
	AlertLevelService service;
	
	@RequestMapping(value="/alert-level",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView alertLevel(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.alertLevel);
		try {
			List<TrainingType> alertLevel = service.getAlertLevelList();
			model.addObject("alertLevel", alertLevel);
			TrainingType alertLevelDetails = service.getAlertLevelDetails(obj);
			model.addObject("alertLevelDetails",alertLevelDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("alertLevel : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-alert-level", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addAlertLevel(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/alert-level");
			boolean flag =  service.addAlertLevel(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Alert Level Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Alert Level is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Alert Level is failed. Try again.");
			logger.error("addAlertLevel : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-alert-level", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateAlertLevel(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/alert-level");
			boolean flag =  service.updateAlertLevel(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Alert Level Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Alert Level is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Alert Level is failed. Try again.");
			logger.error("updateAlertLevel : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-alert-level", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteAlertLevel(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/alert-level");
			boolean flag =  service.deleteAlertLevel(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Alert Level Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteAlertLevel : " + e.getMessage());
		}
		return model;
	}
	
}


