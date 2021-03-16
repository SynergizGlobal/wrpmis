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

import com.synergizglobal.pmis.reference.Iservice.UtilityExecutionAgencyService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class UtilityExecutionAgencyController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityExecutionAgencyController.class);
	
	@Autowired
	UtilityExecutionAgencyService service;
	
	@RequestMapping(value="/utility-execution-agency",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView utilityExecutionAgency(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.utilityExecutionAgency);
		try {
			List<TrainingType> utilityExecutionAgencyList = service.getUtilityExecutionAgencysList();
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("utilityExecutionAgency : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-execution-agency", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityExecutionAgency(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-execution-agency");
			boolean flag =  service.addUtilityExecutionAgency(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Execution Agency Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Utility Execution Agency is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Utility Execution Agency is failed. Try again.");
			logger.error("addUtilityExecutionAgency : " + e.getMessage());
		}
		return model;
	}
	
}



