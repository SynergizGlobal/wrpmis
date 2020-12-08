package com.synergizglobal.pmis.controller;

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

@Controller
public class SafetyInstructonsController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyInstructonsController.class);
	
	
	@RequestMapping(value="/safety-instructions",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyInstructions(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.budgetGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("safetyInstructions : " + e.getMessage());
		}
		return model;
	}
	
	
	
}
