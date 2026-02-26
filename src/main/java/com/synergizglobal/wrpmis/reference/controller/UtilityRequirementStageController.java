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

import com.synergizglobal.wrpmis.reference.Iservice.UtilityRequirementStageService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class UtilityRequirementStageController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityRequirementStageController.class);
	
	@Autowired
	UtilityRequirementStageService service;
	
	@RequestMapping(value="/utility-requirement-stage",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView UtilityRequirementStage(HttpSession session,@ModelAttribute Safety obj){
		ModelAndView model = new ModelAndView(PageConstants.utilityRequirementStage);
		try {
			Safety  UtilityRequirementStageList = service.getUtilityRequirementStagesList(obj);
			model.addObject("utilityRequirementStageList",  UtilityRequirementStageList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" UtilityRequirementStage : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-requirement-stage", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityRequirementStage(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-requirement-stage");
			boolean flag =  service.addUtilityRequirementStage(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Utility Requirement Stage Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Utility Requirement Stage is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Utility Requirement Stage is failed. Try again.");
			logger.error("add UtilityRequirementStage : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/update-utility-requirement-stage", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUtilityRequirementStage(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-requirement-stage");
			boolean flag =  service.updateUtilityRequirementStage(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Requirement Stage Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Utility Requirement Stage is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Utility Requirement Stage is failed. Try again.");
			logger.error("updateUtilityRequirementStage : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-utility-requirement-stage", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUtilityRequirementStage(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-requirement-stage");
			boolean flag =  service.deleteUtilityRequirementStage(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Requirement Stage Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUtilityRequirementStage : " + e.getMessage());
		}
		return model;
	}	
	
}









