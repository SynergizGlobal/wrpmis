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
import com.synergizglobal.pmis.reference.Iservice.RiskWorkHodService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RiskWorkHodController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskWorkHodController.class);
	
	@Autowired
	RiskWorkHodService service;
	
	
	@RequestMapping(value="/risk-work-hod",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskWorkHOD(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.riskWorkHod);
		try {
			List<TrainingType> workHODDetails = service.getRiskWorkHODDetails(obj);
			model.addObject("workHODDetails",workHODDetails);
			
			List<TrainingType> workDetails = service.getWorkDetails(obj);
			model.addObject("workDetails",workDetails);
			
			List<TrainingType> HODDetails = service.getHODDetails(obj);
			model.addObject("HODDetails",HODDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskWorkHOD : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-risk-work-hod", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRiskWorkHOD(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-work-hod");
			boolean flag =  service.addRiskWorkHOD(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Work HOD Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Risk Work HOD is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Risk Work HOD is failed. Try again.");
			logger.error("addRiskWorkHOD : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-risk-work-hod", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRiskWorkHOD(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-work-hod");
			boolean flag =  service.updateRiskWorkHOD(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Work HOD Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk Work HOD is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Risk Work HOD is failed. Try again.");
			logger.error("updateRiskWorkHOD : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-risk-work-hod", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRiskWorkHOD(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-work-hod");
			boolean flag =  service.deleteRiskWorkHOD(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Work HOD Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRiskWorkHOD : " + e.getMessage());
		}
		return model;
	}
}
