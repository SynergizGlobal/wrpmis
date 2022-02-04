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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.MotherTongueService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class MotherTongueController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(MotherTongueController.class);
	
	@Autowired
	MotherTongueService service;
	
	@RequestMapping(value="/mother-tongue",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView motherTongue(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrMotherToungue);
		try {
			
			TrainingType motherTongueDetails = service.getMotherTongueDetails(obj);
			model.addObject("motherTongueDetails",motherTongueDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("motherTongue : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-mother-tongue", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addMotherTongue(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mother-tongue");
			boolean flag =  service.addMotherTongue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Mother Tongue Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Mother Tongue is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Mother Tongue is failed. Try again.");
			logger.error("addMotherTongue : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-mother-tongue", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateMotherTongue(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mother-tongue");
			boolean flag =  service.updateMotherTongue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Mother Tongue Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Mother Tongue is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Mother Tongue is failed. Try again.");
			logger.error("updateMotherTongue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-mother-tongue", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteMotherTongue(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mother-tongue");
			boolean flag =  service.deleteMotherTongue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Mother Tongue Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteMotherTongue : " + e.getMessage());
		}
		return model;
	}
}
