package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.RRLocationService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RRLocationController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRLocationController.class);
	
	@Autowired
	RRLocationService service;
	
	@RequestMapping(value="/rr-location",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrLocation(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrLocation);
		try {
			
			TrainingType rrLocationDetails = service.getRRLocationDetails(obj);
			model.addObject("rrLocationDetails",rrLocationDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("RRLocation : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-location", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRLocation(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-location");
			boolean flag =  service.addRRLocation(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Location Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Location is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Location is failed. Try again.");
			logger.error("addRRLocation : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-location", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRLocation(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-location");
			boolean flag =  service.updateRRLocation(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Location Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Location is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Location is failed. Try again.");
			logger.error("updateRRLocation : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-location", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRLocation(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-location");
			boolean flag =  service.deleteRRLocation(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Location Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRLocation : " + e.getMessage());
		}
		return model;
	}
	
}







