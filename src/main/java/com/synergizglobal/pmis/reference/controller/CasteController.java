package com.synergizglobal.pmis.reference.controller;

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
import com.synergizglobal.pmis.reference.Iservice.CasteService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class CasteController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(CasteController.class);
	
	@Autowired
	CasteService service;
	
	@RequestMapping(value="/caste",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView caste(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrCaste);
		try {
			
			TrainingType casteDetails = service.getCasteDetails(obj);
			model.addObject("casteDetails",casteDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("caste : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-caste", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addCaste(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/caste");
			boolean flag =  service.addCaste(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Caste Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Caste is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Caste is failed. Try again.");
			logger.error("addCaste : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-caste", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateCaste(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/caste");
			boolean flag =  service.updateCaste(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Caste Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Caste is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Caste is failed. Try again.");
			logger.error("updateCaste : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-caste", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteCaste(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/caste");
			boolean flag =  service.deleteCaste(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Caste Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteCaste : " + e.getMessage());
		}
		return model;
	}
}
