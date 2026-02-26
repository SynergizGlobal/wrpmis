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

import com.synergizglobal.wrpmis.reference.Iservice.UtilityExecutionAgencyService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class UtilityExecutionAgencyController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityExecutionAgencyController.class);
	
	@Autowired
	UtilityExecutionAgencyService service;
	
	@RequestMapping(value="/utility-execution-agency",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView UtilityExecutionAgency(HttpSession session,@ModelAttribute Safety obj){
		ModelAndView model = new ModelAndView(PageConstants.utilityExecutionAgency);
		try {
			Safety  UtilityExecutionAgencyList = service.getUtilityExecutionAgencysList(obj);
			model.addObject("utilityExecutionAgencyList",  UtilityExecutionAgencyList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" UtilityExecutionAgency : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-execution-agency", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityExecutionAgency(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-execution-agency");
			boolean flag =  service.addUtilityExecutionAgency(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Utility Execution Agency Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Utility Execution Agency is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Utility Execution Agency is failed. Try again.");
			logger.error("add UtilityExecutionAgency : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/update-utility-execution-agency", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUtilityExecutionAgency(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-execution-agency");
			boolean flag =  service.updateUtilityExecutionAgency(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Execution Agency Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Utility Execution Agency is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Utility Execution Agency is failed. Try again.");
			logger.error("updateUtilityExecutionAgency : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-utility-execution-agency", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUtilityExecutionAgency(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-execution-agency");
			boolean flag =  service.deleteUtilityExecutionAgency(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Execution Agency Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUtilityExecutionAgency : " + e.getMessage());
		}
		return model;
	}	
	
}









