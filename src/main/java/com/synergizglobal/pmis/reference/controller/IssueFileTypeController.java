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
import com.synergizglobal.pmis.reference.Iservice.IssueFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class IssueFileTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(IssueFileTypeController.class);
	
	@Autowired
	IssueFileTypeService service;
	
	@RequestMapping(value="/issue-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView issueFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.issueFileType);
		try {
			
			List<TrainingType> issueFileType = service.getIssueFileType(obj);
			model.addObject("issueFileType",issueFileType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("issueFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-issue-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addIssueFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-file-type");
			boolean flag =  service.addIssueFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Issue File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Issue File Type is failed. Try again.");
			logger.error("addIssueFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-issue-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateIssueFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-file-type");
			boolean flag =  service.updateIssueFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Issue File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Issue File Type is failed. Try again.");
			logger.error("updateIssueFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-issue-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteIssueFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-file-type");
			boolean flag =  service.deleteIssueFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteIssueFileType : " + e.getMessage());
		}
		return model;
	}
	
	
}
