package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.OverviewDashboardService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.OverviewDashboard;

@Controller
public class OverviewDashboardController {
	Logger logger = Logger.getLogger(OverviewDashboardController.class);
	
	@Autowired
	OverviewDashboardService overviewDashboardService;
	
	@Value("${common.error.message}")
	public String commonError;

	@RequestMapping(value="overview-dashboard",method=RequestMethod.GET)
	public ModelAndView welcome(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
				List<OverviewDashboard> forms = overviewDashboardService.getFormsList();
				model.addObject("overviewDashboardForms", forms);
			    model.setViewName(PageConstants.overviewDashboard);
		} catch (Exception e) {
			logger.error("welcome : " + e.getMessage());
		}
		return model;
	}
			
}
