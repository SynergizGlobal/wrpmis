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

import com.synergizglobal.pmis.reference.Iservice.ReferenceFormsAccessService;
import com.synergizglobal.pmis.reference.model.ReferenceForms;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;


@Controller
public class ReferenceFormsAccessController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ReferenceFormsAccessController.class);
	
	@Autowired
	ReferenceFormsAccessService service;
	
	@RequestMapping(value="/reference-forms",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView referenceForms(HttpSession session,@ModelAttribute ReferenceForms obj){
		ModelAndView model = new ModelAndView(PageConstants.referenceFormsMainPage);
		try {
			List<ReferenceForms> referenceForms = service.getReferenceForms();
			model.addObject("referenceForms", referenceForms);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("referenceForms : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getReferencePagesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ReferenceForms> getReferencePagesList(@ModelAttribute ReferenceForms obj) {
		List<ReferenceForms> referencePagesList = null;
		try {
			referencePagesList = service.getReferencePagesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getReferencePagesList : " + e.getMessage());
		}
		return referencePagesList;
	}

	
	
}
