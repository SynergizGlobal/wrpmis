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

import com.synergizglobal.wrpmis.reference.Iservice.SafetyImpactService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class SafetyImpactController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyImpactController.class);
	
	@Autowired
	SafetyImpactService service;
	
	@RequestMapping(value="/safety-impact",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyImpact(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.safetyImpact);
		try {
			List<Safety>  safetyImpactList = service.getSafetyImpactsList();
			model.addObject("safetyImpactList",  safetyImpactList);
			TrainingType safetyImpactDetails = service.getSafetyImpactDetails(obj);
			model.addObject("safetyImpactDetails",safetyImpactDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" safetyImpact : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-safety-impact", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSafetyImpact(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-impact");
			boolean flag =  service.addSafetyImpact(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Impact Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Safety Impact is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Safety Impact is failed. Try again.");
			logger.error("add SafetyImpact : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-safety-impact", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSafetyImpact(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-impact");
			boolean flag =  service.updateSafetyImpact(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Impact Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Safety Impact is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating  Safety Impact is failed. Try again.");
			logger.error("updateSafetyImpact : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-safety-impact", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSafetyImpact(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-impact");
			boolean flag =  service.deleteSafetyImpact(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Safety Impact Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSafetyImpact : " + e.getMessage());
		}
		return model;
	}
	
}








