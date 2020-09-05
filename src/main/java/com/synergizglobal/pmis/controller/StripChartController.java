package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.Work;

@Controller
public class StripChartController {
	
	Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	StripChartService stripChartService;
	
	@Autowired
	HomeService homeService;
	
	
	@RequestMapping(value="/strip-chart",method=RequestMethod.GET)
	public ModelAndView stripChart(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.stripChart);
		try {
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);		
		} catch (Exception e) {
			logger.info("StripChart : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksList(@ModelAttribute Work obj){
		List<Work> works = null;
		try{
			works = homeService.getWorksList(obj);			
		}catch(Exception e){
			logger.error("getWorksList() : "+e.getMessage());
		}
		return works;
	}

}
