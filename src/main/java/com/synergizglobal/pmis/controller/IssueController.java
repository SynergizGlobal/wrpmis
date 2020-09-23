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
public class IssueController {
	Logger logger = Logger.getLogger(IssueController.class);	
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/issues",method=RequestMethod.GET)
	public ModelAndView issues(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.issuesGrid);
		} catch (Exception e) {
			logger.info("issues : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-issue-form",method=RequestMethod.GET)
	public ModelAndView addIssueForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addIssueForm);
		} catch (Exception e) {
			logger.info("addIssueForm : " + e.getMessage());
		}
		return model;
	}
	
}
