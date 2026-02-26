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

import com.synergizglobal.wrpmis.reference.Iservice.SafetyStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class SafetyStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyStatusController.class);
	
	@Autowired
	SafetyStatusService service;
	
	@RequestMapping(value="/safety-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.safetyStatus);
		try {
			List<Safety> safetyStatus = service.getSafetyStatusList();
			model.addObject("safetyStatus", safetyStatus);
			TrainingType safetyStatusDetails = service.getSafetyStatusDetails(obj);
			model.addObject("safetyStatusDetails",safetyStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("safetyStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-safety-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSafetyStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-status");
			boolean flag =  service.addSafetyStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Safety Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Safety Status is failed. Try again.");
			logger.error("addSafetyStatus : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/update-safety-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSafetyStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-status");
			boolean flag =  service.updateSafetyStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateSafetyStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-safety-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSafetyStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-status");
			boolean flag =  service.deleteSafetyStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSafetyStatus : " + e.getMessage());
		}
		return model;
	}
	
}

