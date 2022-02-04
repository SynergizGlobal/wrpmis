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
import com.synergizglobal.pmis.reference.Iservice.RRMaritalStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RRMaritalStatusController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRMaritalStatusController.class);
	
	@Autowired
	RRMaritalStatusService service;
	
	@RequestMapping(value="/rr-marital-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView MaritalStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrMaritalStatus);
		try {
			TrainingType rrMaritalStatus = service.getRRMaritalStatusDetails(obj);
			model.addObject("rrMaritalStatus",rrMaritalStatus);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Marital Status : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-marital-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRMaritalStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-marital-status");
			boolean flag =  service.addRRMaritalStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Marital Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Marital Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Marital Status is failed. Try again.");
			logger.error("addRRMaritalStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-marital-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRMaritalStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-marital-status");
			boolean flag =  service.updateRRMaritalStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Marital Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Marital Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Marital Status is failed. Try again.");
			logger.error("updateRRMaritalStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-marital-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRMaritalStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-marital-status");
			boolean flag =  service.deleteRRMaritalStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Marital Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRMaritalStatus : " + e.getMessage());
		}
		return model;
	}
	
}


