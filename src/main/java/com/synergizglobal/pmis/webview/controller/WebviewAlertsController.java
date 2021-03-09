package com.synergizglobal.pmis.webview.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.Alerts;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewAlertsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(WebviewAlertsController.class);
	
	@Autowired
	AlertsService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/getAlertsList",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getAlerts(@ModelAttribute Alerts obj,HttpSession session,RedirectAttributes attributes){		
		 ModelAndView model = new ModelAndView(MobilePageConstants2.notifications);	    
	     try {	    	
	    	 Map<String,List<Alerts>> alerts = service.getAlertsForHeaderNotifications(obj);
	    	 model.addObject("alerts", alerts);
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("getAlerts() : "+e.getMessage());
		 }
	     return model;
	}
}
