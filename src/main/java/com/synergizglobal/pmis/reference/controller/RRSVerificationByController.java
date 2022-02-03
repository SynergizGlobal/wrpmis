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

import com.synergizglobal.pmis.reference.Iservice.RRSVerificationByService;
import com.synergizglobal.pmis.reference.Iservice.RRStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class RRSVerificationByController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRSVerificationByController.class);
	
	@Autowired
	RRSVerificationByService service;
	
	@RequestMapping(value="/rr-verification-by",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrSVerificationBy(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrVerificationBy);
		try {
			
			TrainingType rrSVerificationByDetails = service.getRRStatusDetails(obj);
			model.addObject("rrSVerificationByDetails",rrSVerificationByDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("RRSVerificationBy : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-verification-by", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRSVerificationBy(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-verification-by");
			boolean flag =  service.addRRSVerificationBy(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Verification By Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Verification By is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Verification By is failed. Try again.");
			logger.error("addRRSVerificationBy : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-verification-by", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRSVerificationBy(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-verification-by");
			boolean flag =  service.updateRRSVerificationBy(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Verification By Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Verification By is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Verification By is failed. Try again.");
			logger.error("updateRRSVerificationBy : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-verification-by", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRSVerificationBy(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-verification-by");
			boolean flag =  service.deleteRRSVerificationBy(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Verification By Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRSVerificationBy : " + e.getMessage());
		}
		return model;
	}
	
}







