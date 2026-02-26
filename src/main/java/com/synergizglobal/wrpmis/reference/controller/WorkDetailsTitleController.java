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

import com.synergizglobal.wrpmis.constants.PageConstants2;
import com.synergizglobal.wrpmis.model.WorkFeatures;
import com.synergizglobal.wrpmis.reference.Iservice.WorkDetailsTitleService;

@Controller
public class WorkDetailsTitleController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkDetailsTitleController.class);
	
	@Autowired
	WorkDetailsTitleService service;
	
	@RequestMapping(value="/work-details-title",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView category(HttpSession session,@ModelAttribute WorkFeatures obj){
		ModelAndView model = new ModelAndView(PageConstants2.workDetailsTitle);
		try {
			WorkFeatures titleDetails = service.getTitleDetails(obj);
			model.addObject("titleDetails",titleDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("category :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work-details-title", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTitle(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-details-title");
			boolean flag =  service.addTitle(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Title Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Title is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Title is failed. Try again.");
			logger.error("addTitle : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-work-details-title", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTitle(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-details-title");
			boolean flag =  service.updateTitle(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Title Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Title is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Title is failed. Try again.");
			logger.error("updateTitle : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-work-details-title", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTitle(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-details-title");
			boolean flag =  service.deleteTitle(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Title deleted succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteTitle : " + e.getMessage());
		}
		return model;
	}
}
