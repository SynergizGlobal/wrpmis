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

import com.synergizglobal.wrpmis.reference.Iservice.RiskAreaService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class RiskAreaController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskAreaController.class);
	
	@Autowired
	RiskAreaService service;
	
	@RequestMapping(value="/risk-area",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskArea(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.riskArea);
		try {
			List<Risk> riskAreaList = service.getRiskAreasList();
			model.addObject("riskAreaList", riskAreaList);
			TrainingType riskAreaDetails = service.getRiskAreaDetails(obj);
			model.addObject("riskAreaDetails",riskAreaDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskArea : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-risk-area", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRiskArea(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-area");
			boolean flag =  service.addRiskArea(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Area Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Risk Area is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Risk Area is failed. Try again.");
			logger.error("addRiskArea : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-risk-area", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRiskArea(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-area");
			boolean flag =  service.updateRiskArea(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Area Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk Area is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Risk Area is failed. Try again.");
			logger.error("updateRiskArea : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-risk-area", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRiskArea(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-area");
			boolean flag =  service.deleteRiskArea(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Area Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRiskArea : " + e.getMessage());
		}
		return model;
	}
	
}




