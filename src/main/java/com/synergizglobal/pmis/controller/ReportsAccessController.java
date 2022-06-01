package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ReportsAccessService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Form;
import com.synergizglobal.pmis.model.Report;

@Controller
public class ReportsAccessController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ReportsAccessController.class);
	
	@Autowired
	ReportsAccessService service;
	
	@RequestMapping(value="/access-reports",method={RequestMethod.GET})
	public ModelAndView accessReports(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants2.reportsAccessGridNew);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("accessReports : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-reports-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Report> getReportsList(@ModelAttribute Report obj) {
		List<Report> objList = null;
		try {
			objList = service.getReportsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getReportsList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getModulesFilterListInReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Report> getModulesList(@ModelAttribute Report obj) {
		List<Report> objList = null;
		try {
			objList = service.getModulesFilterListInReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getModulesList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusFilterListInReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Report> getStatusList(@ModelAttribute Report obj) {
		List<Report> objList = null;
		try {
			objList = service.getStatusFilterListInReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getUserRolesInReportAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Report> getUserRolesInDashboardAccess(@ModelAttribute Report obj) {
		List<Report> objsList = null;
		try {
			objsList = service.getUserRolesInReportAccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserRolesInReportAccess : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getUserTypesInReportAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Report> getUserTypesInReportAccess(@ModelAttribute Report obj) {
		List<Report> objsList = null;
		try {
			objsList = service.getUserTypesInReportAccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserTypesInReportAccess : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getUsersInReportAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Report> getUsersInReportAccess(@ModelAttribute Report obj) {
		List<Report> objsList = null;
		try {
			objsList = service.getUsersInReportAccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUsersInReportAccess : " + e.getMessage());
		}
		return objsList;
	}
	
	
	
	@RequestMapping(value="/get-access-report",method={RequestMethod.POST})
	public ModelAndView getAccessReportDetails(@ModelAttribute Report obj,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditReportAccessFormNew);
			
			List<Report> modulesList = service.getModulesListForReportAccess(obj);
			model.addObject("modulesList", modulesList);
			
			List<Report> foldersList = service.getFolderssListForReportAccess(obj);
			model.addObject("foldersList", foldersList);
			
			List<Report> statusList = service.getStatusListForReportAccess(obj);
			model.addObject("statusList", statusList);
			
			List<Report> user_roles = service.getUserRolesInReportAccess(obj);
			model.addObject("user_roles", user_roles);
			
			List<Report> user_types = service.getUserTypesInReportAccess(obj);
			model.addObject("user_types", user_types);
			
			List<Report> users = service.getUsersInReportAccess(obj);
			model.addObject("users", users);
			
			Report reportDetails = service.getReport(obj);
			model.addObject("reportDetails", reportDetails);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAccessReportDetails : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-access-report", method = {RequestMethod.POST})
	public ModelAndView updateAccessReport(@ModelAttribute Report obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			
			model.setViewName("redirect:/access-reports");
			boolean flag =  service.updateAccessReport(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Report Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Report is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Report is failed. Try again.");
			logger.error("updateAccessReport : " + e.getMessage());
		}
		return model;
	}
}
