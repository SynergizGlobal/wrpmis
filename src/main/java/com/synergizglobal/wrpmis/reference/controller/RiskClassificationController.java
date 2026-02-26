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

import com.synergizglobal.wrpmis.reference.Iservice.RiskClassificationService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class RiskClassificationController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskClassificationController.class);
	
	@Autowired
	RiskClassificationService service;
	
	@RequestMapping(value="/risk-classification",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskClassification(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.riskClassification);
		try {
			List<Risk> riskClassificationList = service.getRiskClassificationsList();
			model.addObject("riskClassificationList", riskClassificationList);
			
			TrainingType riskClassificationDetails = service.getRiskClassificationDetails(obj);
			model.addObject("riskClassificationDetails",riskClassificationDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskClassification : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-risk-classification", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRiskClassification(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-classification");
			boolean flag =  service.addRiskClassification(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "risk Classification Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding risk Classification is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding risk Classification is failed. Try again.");
			logger.error("addRiskClassification : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-risk-classification", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRiskClassification(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-classification");
			boolean flag =  service.updateRiskClassification(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Classification Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk Classification is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Risk Classification is failed. Try again.");
			logger.error("updateRiskClassification : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-risk-classification", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRiskClassification(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-classification");
			boolean flag =  service.deleteRiskClassification(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Classification Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRiskClassification : " + e.getMessage());
		}
		return model;
	}
	
}




