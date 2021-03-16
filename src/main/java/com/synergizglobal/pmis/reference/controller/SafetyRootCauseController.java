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

import com.synergizglobal.pmis.reference.Iservice.SafetyRootCauseService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class SafetyRootCauseController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyRootCauseController.class);
	
	@Autowired
	 SafetyRootCauseService service;
	
	@RequestMapping(value="/safety-root-cause",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyRootCause(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.safetyRootCause);
		try {
			List<Safety> safetyRootCause = service.getSafetyRootCauseList();
			model.addObject("safetyRootCause", safetyRootCause);
			TrainingType safetyRootCauseDetails = service.getSafetyRootCauseDetails(obj);
			model.addObject("safetyRootCauseDetails",safetyRootCauseDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("safetyRootCause : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-safety-root-cause", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSafetyRootCause(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-root-cause");
			boolean flag =  service.addSafetyRootCause(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Root Cause Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Safety Root Cause is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Safety Root Cause is failed. Try again.");
			logger.error("addSafetyRootCause : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-safety-root-cause", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSafetyRootCause(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-root-cause");
			boolean flag =  service.updateSafetyRootCause(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Root Cause Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Safety Root Cause is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating  Safety Root Cause is failed. Try again.");
			logger.error("updateSafetyRootCause : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-safety-root-cause", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSafetyRootCause(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-root-cause");
			boolean flag =  service.deleteSafetyRootCause(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Safety Root Cause Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSafetyRootCause : " + e.getMessage());
		}
		return model;
	}
	
}
