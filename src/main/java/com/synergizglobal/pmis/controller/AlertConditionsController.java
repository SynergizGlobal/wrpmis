package com.synergizglobal.pmis.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.AlertConditionsService;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.AlertConditions;
@Controller
public class AlertConditionsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(AlertsController.class);
	
	@Autowired
	AlertConditionsService service;
	
	@Value("${common.error.message}")
	public String commonError;	
	
	@RequestMapping(value="/alert-conditions",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView alertConditions(){		
		 ModelAndView model = new ModelAndView(PageConstants2.alertConditions);	    
	     try {
	    	 List<AlertConditions> alert_types = service.getAlertTypes();
	    	 model.addObject("alertTypes", alert_types);
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("alertConditions() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value = "/ajax/getAlertConditions", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<AlertConditions> getAlertConditions(@ModelAttribute AlertConditions obj) {
		List<AlertConditions> objsList = null;  
		try {
			objsList = service.getAlertConditions(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertConditions : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getAlertCondition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AlertConditions getAlertCondition(@ModelAttribute AlertConditions obj) {
		AlertConditions aObj = null;  
		try {
			aObj = service.getAlertCondition(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertCondition : " + e.getMessage());
		}
		return aObj;
	}
	
	@RequestMapping(value = "/ajax/updateAlertCondition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean updateAlertCondition(@ModelAttribute AlertConditions obj) {
		boolean flag = false;
		try {
			flag = service.updateAlertCondition(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("updateAlertCondition : " + e.getMessage());
		}
		return flag;
	}
}
