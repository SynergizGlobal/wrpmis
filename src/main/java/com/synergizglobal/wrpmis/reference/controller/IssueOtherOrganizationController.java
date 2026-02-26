package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.reference.Iservice.IssueOtherOrganizationService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class IssueOtherOrganizationController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(IssueOtherOrganizationController.class);
	
	@Autowired
	IssueOtherOrganizationService service;
	
	@RequestMapping(value="/issue-other-organization",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView issueOtherOrganization(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.issueOtherOrganization);
		try {
			
			List<TrainingType> issueOtherOrganizationDetails = service.getIssueOtherOrganizationDetails(obj);
			model.addObject("issueOtherOrganizationDetails",issueOtherOrganizationDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("issueOtherOrganization : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-issue-other-organization", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addIssueOtherOrganization(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-other-organization");
			boolean flag =  service.addIssueOtherOrganization(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue Other Organization Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Issue Other Organization is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Issue Other Organization is failed. Try again.");
			logger.error("addIssueOtherOrganization : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-issue-other-organization", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateIssueOtherOrganization(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-other-organization");
			boolean flag =  service.updateIssueOtherOrganization(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue Other Organization Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Issue Other Organization is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Issue Other Organization is failed. Try again.");
			logger.error("updateIssueOtherOrganization : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-issue-other-organization", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteIssueOtherOrganization(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-other-organization");
			boolean flag =  service.deleteIssueOtherOrganization(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue Other Organization Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteIssueOtherOrganization : " + e.getMessage());
		}
		return model;
	}
	
}
