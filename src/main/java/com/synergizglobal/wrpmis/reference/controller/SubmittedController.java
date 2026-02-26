package com.synergizglobal.wrpmis.reference.controller;

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
import com.synergizglobal.wrpmis.reference.Iservice.SubmittedService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class SubmittedController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SubmittedController.class);
	
	@Autowired
	SubmittedService service;
	
	@RequestMapping(value="/submitted",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView submitted(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.submittedBy);
		try {
			
			TrainingType submittedDetails = service.getSubmittedDetails(obj);
			model.addObject("submittedDetails",submittedDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("submitted : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-submitted", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSubmitted(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/submitted");
			boolean flag =  service.addSubmitted(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Submitted Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Submitted is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Submitted is failed. Try again.");
			logger.error("addSubmitted : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-submitted", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSubmitted(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/submitted");
			boolean flag =  service.updateSubmitted(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Submitted Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Submitted is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Submitted is failed. Try again.");
			logger.error("updateSubmitted : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-submitted", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSubmitted(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/submitted");
			boolean flag =  service.deleteSubmitted(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Submitted Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSubmitted : " + e.getMessage());
		}
		return model;
	}
	
}


