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
import com.synergizglobal.wrpmis.reference.Iservice.FinancialYearService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class FinancialYearController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebDocumentCategoryController.class);
	
	@Autowired
	FinancialYearService service;
	

	@RequestMapping(value="/financial-year",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView financialYear(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.financialYear);
		try {
			
			TrainingType financialYearDetails = service.getFinancialYearDetails(obj);
			model.addObject("financialYearDetails",financialYearDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("financialYear : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-financial-year", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addFinancialYear(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/financial-year");
			boolean flag =  service.addFinancialYear(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Financial Year Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Financial Year is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Financial Year is failed. Try again.");
			logger.error("addFinancialYear : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-financial-year", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateFinancialYear(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/financial-year");
			boolean flag =  service.updateFinancialYear(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Financial Year Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Financial Year is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Financial Year is failed. Try again.");
			logger.error("updateFinancialYear : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-financial-year", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteFinancialYear(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/financial-year");
			boolean flag =  service.deleteFinancialYear(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Financial Year Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteFinancialYear : " + e.getMessage());
		}
		return model;
	}
	
}


