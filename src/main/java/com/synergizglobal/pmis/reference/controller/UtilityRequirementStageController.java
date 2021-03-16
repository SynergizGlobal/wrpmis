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

import com.synergizglobal.pmis.reference.Iservice.UtilityRequirementStageService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

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
	public ModelAndView utilityRequirementStage(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.utilityRequirementStage);
		try {
			List<TrainingType> utilityRequirementStageList = service.getUtilityRequirementStagesList();
			model.addObject("utilityRequirementStageList", utilityRequirementStageList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("utilityRequirementStage : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-requirement-stage", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityRequirementStage(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-requirement-stage");
			boolean flag =  service.addUtilityRequirementStage(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Requirement Stage Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Utility Requirement Stage is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Utility Requirement Stage is failed. Try again.");
			logger.error("addUtilityRequirementStage : " + e.getMessage());
		}
		return model;
	}
	
}


