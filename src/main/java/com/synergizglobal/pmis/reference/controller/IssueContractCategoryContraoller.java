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
import com.synergizglobal.pmis.reference.Iservice.IssueContractCategoryService;
import com.synergizglobal.pmis.reference.Iservice.RiskWorkHodService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class IssueContractCategoryContraoller {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(IssueContractCategoryContraoller.class);
	
	@Autowired
	IssueContractCategoryService service;
	
	@RequestMapping(value="/issue-contract-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView issueContractCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.issueContractCategory);
		try {
			List<TrainingType> contractTypeDetails = service.getContractTypeDetails(obj);
			model.addObject("contractTypeDetails",contractTypeDetails);
			
			List<TrainingType> issueCategoryDetails = service.gtIssueCategoryDetails(obj);
			model.addObject("issueCategoryDetails",issueCategoryDetails);
			
			List<TrainingType> issueContractCategory = service.getIssueContractCategory(obj);
			model.addObject("issueContractCategory",issueContractCategory);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("issueContractCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-issue-contract-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addIssueContractCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-contract-category");
			boolean flag =  service.addIssueContractCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue Contract Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Issue Contract Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Issue Contract Category is failed. Try again.");
			logger.error("addIssueContractCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-issue-contract-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateIssueContractCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-contract-category");
			boolean flag =  service.updateIssueContractCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue Contract Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Issue Contract Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Issue Contract Category is failed. Try again.");
			logger.error("updateIssueContractCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-issue-contract-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteIssueContractCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/issue-contract-category");
			boolean flag =  service.deleteIssueContractCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue Contract Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteIssueContractCategory : " + e.getMessage());
		}
		return model;
	}
}
