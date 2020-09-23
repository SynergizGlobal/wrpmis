package com.synergizglobal.pmis.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class SafetyController {
	Logger logger = Logger.getLogger(SafetyController.class);	
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/safety",method=RequestMethod.GET)
	public ModelAndView safety(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.safetyGrid);
		} catch (Exception e) {
			logger.info("safety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-safety-form",method=RequestMethod.GET)
	public ModelAndView addSafetyForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addSafetyForm);
		} catch (Exception e) {
			logger.info("addSafetyForm : " + e.getMessage());
		}
		return model;
	}
}
