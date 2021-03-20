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
import com.synergizglobal.pmis.reference.Iservice.WebDocumentsTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class WebDocumentsTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebDocumentsTypeController.class);
	
	@Autowired
	WebDocumentsTypeService service;
	
	@RequestMapping(value="/web-documents-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView webDocumentsType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.webDocumentsType);
		try {
			TrainingType webDocumentsTypeDetails = service.getWebDocumentsTypeDetails(obj);
			model.addObject("webDocumentsTypeDetails",webDocumentsTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("webDocumentsType :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-web-documents-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWebDocumentsType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-documents-type");
			boolean flag =  service.addWebDocumentsType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Documents Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("addWebDocumentsType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-web-documents-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateWebDocumentsType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-documents-type");
			boolean flag =  service.updateWebDocumentsType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Documents Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("updateWebDocumentsType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-web-documents-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteWebDocumentsType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-documents-type");
			boolean flag =  service.deleteWebDocumentsType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Documents Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteWebDocumentsType : " + e.getMessage());
		}
		return model;
	}
	
}


