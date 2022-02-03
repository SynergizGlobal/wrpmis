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
	
	Logger logger = Logger.getLogger(RRStatusController.class);
	
	@Autowired
	RRStatusService service;
	
	@RequestMapping(value="/rr-verification-by",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrVerificationBy);
		try {
			List<Safety> RRStatusList = service.getRRStatusList();
			model.addObject("RRStatusList", RRStatusList);
			TrainingType rrStatusDetails = service.getRRStatusDetails(obj);
			model.addObject("rrStatusDetails",rrStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("RRStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-verification-by", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-verification-by");
			boolean flag =  service.addRRStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			logger.error("addRRStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-verification-by", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-verification-by");
			boolean flag =  service.updateRRStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateRRStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-verification-by", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-verification-by");
			boolean flag =  service.deleteRRStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRStatus : " + e.getMessage());
		}
		return model;
	}
	
}







