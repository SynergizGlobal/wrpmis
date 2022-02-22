package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
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
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.reference.Iservice.RrResponsibleExecutivesService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RrResponsibleExecutivesController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RrResponsibleExecutivesController.class);
	@Autowired
	RrResponsibleExecutivesService service;
	
	@RequestMapping(value="/rr-executives",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView executives(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrExecutives);
		try {
			
			List<TrainingType> executivesDetails = service.getExecutivesDetails(obj);
			model.addObject("executivesDetails",executivesDetails);
			
			List<TrainingType> workDetails = service.getWorkDetails(obj);
			model.addObject("workDetails",workDetails);
			
			List<TrainingType> usersDetails = service.getUsersDetails(obj);
			model.addObject("usersDetails",usersDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("executives : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-executives", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getExecutivesList(@ModelAttribute TrainingType obj,HttpSession session) {
		List<TrainingType> executivesList = null;
		try {
			executivesList = service.executivesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutivesList : " + e.getMessage());
		}
		return executivesList;
	}
	
	
	@RequestMapping(value = "/add-rr-executives", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRExecutives(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-executives");
			boolean flag =  service.addRRExecutives(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Executives Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding RR Executives is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding RR Executives is failed. Try again.");
			logger.error("addRRExecutives : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-executives", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRExecutives(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-executives");
			boolean flag =  service.updateRRExecutives(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Executives Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","RR Updating Executives is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating RR Executives is failed. Try again.");
			logger.error("updateRRExecutives : " + e.getMessage());
		}
		return model;
	}
	
}
