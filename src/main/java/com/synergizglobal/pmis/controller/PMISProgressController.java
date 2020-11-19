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
public class PMISProgressController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(PMISProgressController.class);

	
	@RequestMapping(value="/progress-form",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView progressForm(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.PMISprogressForm);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("progressForm : " + e.getMessage());
		}
		return model;
	}
	
	
	
	
	
	
	
	
}
