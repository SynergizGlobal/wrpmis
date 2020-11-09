package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.ProgressBulkUpdateService;
import com.synergizglobal.pmis.Iservice.ProgressBulkUpdateService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.StripChart;

@Controller
public class ProgressBulkUpdateController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	ProgressBulkUpdateService progressBulkUpdateervice;
	
	
	@RequestMapping(value="/progressBulkUpload",method=RequestMethod.GET)
	public ModelAndView progressBulkUpload(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.progressBulkUpload);
		try {
			List<StripChart> projectsList = progressBulkUpdateervice.getProgressBulkUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = progressBulkUpdateervice.getProgressBulkUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<StripChart> contractsList = progressBulkUpdateervice.getProgressBulkUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_id())) {
				StripChart  ProgressBulkData = progressBulkUpdateervice.getProgressBulkData(obj);
				model.addObject(" ProgressBulkData",  ProgressBulkData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("progressBulkUpload : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateProjectsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateProjectsList(@ModelAttribute StripChart obj){
		List<StripChart> projects = null;
		try{
			projects = progressBulkUpdateervice.getProgressBulkUpdateProjectsList(obj);			
		}catch(Exception e){
			logger.error("getProgressBulkUpdateProjectsList() : "+e.getMessage());
		}
		return projects;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateWorksList(@ModelAttribute StripChart obj){
		List<StripChart> works = null;
		try{
			works = progressBulkUpdateervice.getProgressBulkUpdateWorksList(obj);			
		}catch(Exception e){
			logger.error("geStripCharttWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateContractsList(@ModelAttribute StripChart obj){
		List<StripChart> contracts = null;
		try{
			contracts = progressBulkUpdateervice.getProgressBulkUpdateContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getProgressBulkUpdateContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateStructures(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = progressBulkUpdateervice.getProgressBulkUpdateStructures(obj);			
		}catch(Exception e){
			logger.error("getProgressBulkUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateLines", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateLines(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = progressBulkUpdateervice.getProgressBulkUpdateLines(obj);			
		}catch(Exception e){
			logger.error("getProgressBulkUpdateLines() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateSections", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateSections(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = progressBulkUpdateervice.getProgressBulkUpdateSections(obj);			
		}catch(Exception e){
			logger.error("getProgressBulkUpdateSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkUpdateComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = progressBulkUpdateervice.getProgressBulkUpdateComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getProgressBulkUpdateComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProgressBulkActivitiesList(@ModelAttribute StripChart obj){
		List<StripChart> activities = null;
		try{
			activities = progressBulkUpdateervice.getProgressBulkActivitiesList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getProgressBulkActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getProgressBulkUpdateDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StripChart getProgressBulkUpdateDetails(@ModelAttribute StripChart obj){
		StripChart data = null;
		try{
			data = progressBulkUpdateervice.getProgressBulkUpdateDetails(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getProgressBulkUpdateDetails() : "+e.getMessage());
		}
		return data;
	}
}
