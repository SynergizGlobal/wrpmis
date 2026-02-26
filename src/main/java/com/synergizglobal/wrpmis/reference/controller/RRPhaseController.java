package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.reference.Iservice.RRPhaseService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class RRPhaseController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRPhaseController.class);
	
	@Autowired
	RRPhaseService service;
	
	@RequestMapping(value="/rr-phase",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Phase(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrPhase);
		try {
			
			TrainingType rrPhaseDetails = service.getRRPhaseDetails(obj);
			model.addObject("rrPhaseDetails",rrPhaseDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Phase : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-phase", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRPhase(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-phase");
			boolean flag =  service.addRRPhase(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Phase Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Phase is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Phase is failed. Try again.");
			logger.error("addRRPhase : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-phase", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRPhase(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-phase");
			boolean flag =  service.updateRRPhase(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Phase Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Phase is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Phase is failed. Try again.");
			logger.error("updateRRPhase : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-phase", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRPhase(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-phase");
			boolean flag =  service.deleteRRPhase(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Phase Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRPhase : " + e.getMessage());
		}
		return model;
	}
	
}


