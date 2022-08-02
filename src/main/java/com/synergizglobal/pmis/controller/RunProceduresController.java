package com.synergizglobal.pmis.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class RunProceduresController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(RunProceduresController.class);
	
	@Autowired
	AlertsService alertService;
	
	@RequestMapping(value = "/run_planned_normal_distribution_day", method = {RequestMethod.GET})
	public ModelAndView runPlannedNormalDistributionDay(RedirectAttributes attributes){
		ModelAndView model = new ModelAndView("redirect:/");
		try {
			boolean flag = alertService.runPlannedNormalDistributionDay();
			logger.error("runPlannedNormalDistributionDay() : "+flag);
			if(flag) {
				attributes.addFlashAttribute("procedureResult", "Scurve refreshed successfully");
			}else {
				attributes.addFlashAttribute("procedureResult", "Procedure not executed");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("procedureResult", "Oops! Something went wrong");
			logger.error("runPlannedNormalDistributionDay() : "+e.getMessage());
		}
		return model;
	}
}
