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

import com.synergizglobal.pmis.reference.Iservice.DocumentRevisionStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class DocumentRevisionStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DocumentRevisionStatusController.class);
	
	@Autowired
	DocumentRevisionStatusService service;
	
	@RequestMapping(value="/document-revision-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView documentRevisionStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.documentRevisionStatus);
		try {
			List<Safety> documentRevisionStatusList = service.getDocumentRevisionStatusList();
			model.addObject("documentRevisionStatusList", documentRevisionStatusList);
			TrainingType documentRevisionStatusDetails = service.getDocumentRevisionStatusDetails(obj);
			model.addObject("documentRevisionStatusDetails",documentRevisionStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("documentRevisionStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-document-revision-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDocumentRevisionStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/document-revision-status");
			boolean flag =  service.addDocumentRevisionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success",  "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Document Revision Status is failed. Try again.");
			logger.error("addDocumentRevisionStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-document-revision-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDocumentRevisionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/document-revision-status");
			boolean flag =  service.updateDocumentRevisionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateDocumentRevisionStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-document-revision-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDocumentRevisionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/document-revision-status");
			boolean flag =  service.deleteDocumentRevisionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDocumentRevisionStatus : " + e.getMessage());
		}
		return model;
	}
	
}


