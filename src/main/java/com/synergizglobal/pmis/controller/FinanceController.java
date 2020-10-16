package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contractor;

@Controller
public class FinanceController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FinanceController.class);

	
	@RequestMapping(value="/source-of-fund",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sourceOfFund(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.contractorGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("SourceOfFund : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-fund-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addFundForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditContractor);
			model.addObject("action", "add");
		}catch (Exception e) {
				logger.error("SourceOfFund : " + e.getMessage());
		}
		return model;
	 }
	
	
}
