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
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			activities = service.getApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getApprovableActivities() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getWorksInApprovableActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getWorksInApprovableActivities(@ModelAttribute Activity obj,HttpSession session){
		List<Activity> objsList = null;
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			objsList = service.getWorksInApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getWorksInApprovableActivities() : "+e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsInApprovableActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getContractsInApprovableActivities(@ModelAttribute Activity obj,HttpSession session){
		List<Activity> objsList = null;
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			objsList = service.getContractsInApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getContractsInApprovableActivities() : "+e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getStructuresInApprovableActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getStructuresInApprovableActivities(@ModelAttribute Activity obj,HttpSession session){
		List<Activity> objsList = null;
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			objsList = service.getStructuresInApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getStructuresInApprovableActivities() : "+e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsInApprovableActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getDepartmentsInApprovableActivities(@ModelAttribute Activity obj,HttpSession session){
		List<Activity> objsList = null;
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			objsList = service.getDepartmentsInApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getDepartmentsInApprovableActivities() : "+e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getUpdatedByListInApprovableActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getUpdatedByListInApprovableActivities(@ModelAttribute Activity obj,HttpSession session){
		List<Activity> objsList = null;
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			objsList = service.getUpdatedByListInApprovableActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getUpdatedByListInApprovableActivities() : "+e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/approveActivityProgress", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Activity approveActivityProgress(@ModelAttribute Activity obj,HttpSession session){
		Activity aObj = new Activity();
		try{
			String user_id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setDyhod_user_id_fk(user_id);
			obj.setUser_role_code(user_role_code);
			aObj = service.approveActivityProgress(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getUpdatedByListInApprovableActivities() : "+e.getMessage());
		}
		return aObj;
	}
	
}
