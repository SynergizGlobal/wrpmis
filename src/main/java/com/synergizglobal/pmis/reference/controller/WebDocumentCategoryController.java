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
import com.synergizglobal.pmis.reference.Iservice.WebDocumentCategoryService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class WebDocumentCategoryController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebDocumentCategoryController.class);
	
	@Autowired
	WebDocumentCategoryService service;
	
	@RequestMapping(value="/web-documents-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView webDocumentsCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.webDocumentsCategory);
		try {
			List<TrainingType> documentType = service.getDocumentType(obj);
			model.addObject("documentType",documentType);
			
			TrainingType webDocumentsCategoryDetails = service.getWebDocumentsCategoryDetails(obj);
			model.addObject("webDocumentsCategoryDetails",webDocumentsCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("webDocumentsCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-web-documents-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWebDocumentsCategory(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-documents-category");
			boolean flag =  service.addWebDocumentsCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Documents Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Web Documents Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Web Documents Category is failed. Try again.");
			logger.error("addWebDocumentsCategory : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-web-documents-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateWebDocumentsCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-documents-category");
			boolean flag =  service.updateWebDocumentsCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Documents Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Web Documents Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Web Documents Category is failed. Try again.");
			logger.error("updateWebDocumentsCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-web-documents-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteWebDocumentsCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-documents-category");
			boolean flag =  service.deleteWebDocumentsCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Documents Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteWebDocumentsCategory : " + e.getMessage());
		}
		return model;
	}
	
}

