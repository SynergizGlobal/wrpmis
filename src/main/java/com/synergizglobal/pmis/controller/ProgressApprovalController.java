package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.synergizglobal.pmis.Iservice.ProgressApprovalService;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Activity;

@Controller
public class ProgressApprovalController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(ProgressApprovalController.class);
	
	@Autowired
	ProgressApprovalService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/progress-approval-page",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView progressApprovalPage(){		
		 ModelAndView model = new ModelAndView(PageConstants2.approveActivityProgress);	    
	     try {
	    	
		 } catch (Exception e) {
			logger.error("progressApprovalPage() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value = "/ajax/getApprovableActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getApprovableActivities(@ModelAttribute Activity obj,HttpSession session){
		List<Activity> activities = null;
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			obj.setUser_id(user_id);
			activities = service.getApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getApprovableActivities() : "+e.getMessage());
		}
		return activities;
	}
	
}
