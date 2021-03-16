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

import com.synergizglobal.pmis.reference.Iservice.RRDocumentTypeService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class RRDocumentTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRDocumentTypeController.class);
	
	@Autowired
	RRDocumentTypeService service;
	
	@RequestMapping(value="/rr-document-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrDocumentType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrDocumentType);
		try {
			List<Safety> rrDocumentTypeList = service.getRRDocumentTypeList();
			model.addObject("rrDocumentTypeList", rrDocumentTypeList);
			TrainingType rrDocumentTypeDetails = service.getRRDocumentTypeDetails(obj);
			model.addObject("rrDocumentTypeDetails",rrDocumentTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("rrDocumentType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-document-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRDocumentType(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-document-type");
			boolean flag =  service.addRRDocumentType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Document Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Document Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Document Type is failed. Try again.");
			logger.error("addRRDocumentType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-rr-document-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRDocumentType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-document-type");
			boolean flag =  service.updateRRDocumentType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Document Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Document Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating  Document Type is failed. Try again.");
			logger.error("updateRRDocumentType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-document-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRDocumentType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-document-type");
			boolean flag =  service.deleteRRDocumentType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Document Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRDocumentType : " + e.getMessage());
		}
		return model;
	}
	
}




