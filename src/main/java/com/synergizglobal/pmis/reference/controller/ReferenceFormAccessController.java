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
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.reference.Iservice.ReferenceFormAccessService;
import com.synergizglobal.pmis.reference.model.ReferenceForms;

@Controller
public class ReferenceFormAccessController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ReferenceFormAccessController.class);
	
	@Autowired
	ReferenceFormAccessService service;
	
	@RequestMapping(value="/reference-form",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView referenceForm(HttpSession session,@ModelAttribute ReferenceForms obj){
		ModelAndView model = new ModelAndView(PageConstants.referenceFormsAccessGrid);
		try {
			List<ReferenceForms> referenceForm = service.getReferenceForm(obj);
			model.addObject("referenceForm", referenceForm);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("referenceForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-reference-forms-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ReferenceForms> getReferenceFormssList(@ModelAttribute ReferenceForms obj) {
		List<ReferenceForms> referenceFormsList = null;
		try {
			referenceFormsList = service.getReferenceFormssList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getReferenceFormssList : " + e.getMessage());
		}
		return referenceFormsList;
	}
	
	@RequestMapping(value = "/ajax/getModulesFilterListInReferenceForms", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ReferenceForms> getModulesFilterListInReferenceForms(@ModelAttribute ReferenceForms obj) {
		List<ReferenceForms> referenceFormsList = null;
		try {
			referenceFormsList = service.getReferenceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getModulesFilterListInReferenceForms : " + e.getMessage());
		}
		return referenceFormsList;
	}

	@RequestMapping(value = "/add-reference-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addreferenceForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditReferenceFormsAccessForm);
			model.addObject("action", "add");
			List<ReferenceForms> modules = service.getModuleNmae();
			model.addObject("modules", modules);
		}catch (Exception e) {
				logger.error("addreferenceForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-reference-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getReferenceForm(@ModelAttribute ReferenceForms obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditReferenceFormsAccessForm);
			model.addObject("action", "edit");
			
			List<ReferenceForms> modules = service.getModuleNmae();
			model.addObject("modules", modules);
			
			ReferenceForms refrenceFormDetails = service.getReferenceFormDetails(obj);
			model.addObject("refrenceFormDetails", refrenceFormDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getReferenceForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-referenceForm", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addReferenceForm(@ModelAttribute ReferenceForms obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/reference-form");
			boolean flag =  service.addReferenceForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "ReferenceForm Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding ReferenceForm is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding ReferenceForm is failed. Try again.");
			logger.error("ReferenceForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-reference-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateReferenceForm(@ModelAttribute ReferenceForms obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/reference-form");
			boolean flag =  service.updateReferenceForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "ReferenceForm Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating ReferenceForm is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating ReferenceForm is failed. Try again.");
			logger.error("Reference : " + e.getMessage());
		}
		return model;
	}
}
