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

import com.synergizglobal.pmis.reference.Iservice.DrawingTypeService;
import com.synergizglobal.pmis.reference.Iservice.SourceOfFundsService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class SourceOfFundsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SourceOfFundsController.class);
	
	@Autowired
	SourceOfFundsService service;
	
	@RequestMapping(value="/source-of-fund",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sourceOfFund(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.sourceOfFunds);
		try {
			List<TrainingType> sourceOfFundsList = service.getSourceOfFundsList();
			model.addObject("sourceOfFundsList", sourceOfFundsList);
			TrainingType sourceOfFundsDetails = service.getSourceOfFundsDetails(obj);
			model.addObject("sourceOfFundsDetails",sourceOfFundsDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("sourceOfFund : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-source-of-fund", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSourceOfFund(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			boolean flag =  service.addSourceOfFund(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Source Of Fund Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Source Of Fund is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Source Of Fund is failed. Try again.");
			logger.error("addSourceOfFund : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/update-source-of-fund", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSourceOfFunds(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			boolean flag =  service.updateSourceOfFunds(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Source Of Fund Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Source Of Fund is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Source Of Fund is failed. Try again.");
			logger.error("updateSourceOfFunds : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-source-of-fund", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSourceOfFunds(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			boolean flag =  service.deleteSourceOfFunds(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Source Of Fund Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSourceOfFunds : " + e.getMessage());
		}
		return model;
	}
	
}



