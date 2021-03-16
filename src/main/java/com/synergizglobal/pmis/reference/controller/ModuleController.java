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

import com.synergizglobal.pmis.reference.Iservice.ModuleService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class ModuleController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ModuleController.class);
	
	@Autowired
	ModuleService service;
	
	@RequestMapping(value="/module",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView module(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.module);
		try {
			List<Safety> module = service.getModuleList();
			model.addObject("module", module);
			TrainingType moduleDetails = service.getModuleDetails(obj);
			model.addObject("moduleDetails",moduleDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("module : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-module", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addModule(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/module");
			boolean flag =  service.addModule(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Module Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Module is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Module is failed. Try again.");
			logger.error("addModule : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-module", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateModule(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/module");
			boolean flag =  service.updateModule(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Module Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Module is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Module is failed. Try again.");
			logger.error("updateModule : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-module", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteModule(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/module");
			boolean flag =  service.deleteModule(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Module Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteModule : " + e.getMessage());
		}
		return model;
	}
	
}










