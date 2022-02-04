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
import com.synergizglobal.pmis.reference.Iservice.RRTenureStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RRTenureStatusController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRTenureStatusController.class);
	
	@Autowired
	RRTenureStatusService service;
	
	@RequestMapping(value="/rr-tenure-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView TenureStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrTenureStatus);
		try {			
			TrainingType TenureStatusDetails = service.getRRTenureStatusDetails(obj);
			model.addObject("TenureStatusDetails",TenureStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("TenureStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-tenure-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRTenureStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-tenure-status");
			boolean flag =  service.addRRTenureStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Tenure Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Tenure Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Tenure Status is failed. Try again.");
			logger.error("addRRTenureStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-tenure-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRTenureStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-tenure-status");
			boolean flag =  service.updateRRTenureStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Tenure Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Tenure Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Tenure Status is failed. Try again.");
			logger.error("updateRRTenureStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-tenure-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRTenureStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-tenure-status");
			boolean flag =  service.deleteRRTenureStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Tenure Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRTenureStatus : " + e.getMessage());
		}
		return model;
	}
	
}


