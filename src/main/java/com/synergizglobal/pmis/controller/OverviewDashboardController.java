package com.synergizglobal.pmis.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class OverviewDashboardController {
	Logger logger = Logger.getLogger(OverviewDashboardController.class);
	
	@Value("${common.error.message}")
	public String commonError;

	@RequestMapping(value="overview-dashboard",method=RequestMethod.GET)
	public ModelAndView welcome(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 if (session.getAttribute("user") != null ) {
		         model.setViewName(PageConstants.overviewDashboard);
			 }
		} catch (Exception e) {
			logger.error("welcome : " + e.getMessage());
		}
		return model;
	}
			
}
