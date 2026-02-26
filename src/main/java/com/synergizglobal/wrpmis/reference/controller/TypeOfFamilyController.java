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
import com.synergizglobal.wrpmis.reference.Iservice.TypeOfFamilyService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class TypeOfFamilyController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TypeOfFamilyController.class);
	
	@Autowired
	TypeOfFamilyService service;
	
	@RequestMapping(value="/type-of-family",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView typeOfFamily(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrTypeOfFamily);
		try {
			
			TrainingType typeOfFamilyDetails = service.getTypeOfFamilyDetails(obj);
			model.addObject("typeOfFamilyDetails",typeOfFamilyDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("typeOfFamily : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-type-of-family", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTypeOfFamily(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/type-of-family");
			boolean flag =  service.addTypeOfFamily(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Type Of Family Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Type Of Family is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Type Of Family is failed. Try again.");
			logger.error("addTypeOfFamily : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-type-of-family", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTypeOfFamily(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/type-of-family");
			boolean flag =  service.updateTypeOfFamily(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Type Of Family Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Type Of Family is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Type Of Family is failed. Try again.");
			logger.error("updateTypeOfFamily : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-type-of-family", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTypeOfFamily(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/type-of-family");
			boolean flag =  service.deleteTypeOfFamily(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Type Of Family Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteTypeOfFamily : " + e.getMessage());
		}
		return model;
	}
}

