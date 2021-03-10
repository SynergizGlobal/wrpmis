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

import com.synergizglobal.pmis.Iservice.DashboardService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Dashboard;
import com.synergizglobal.pmis.model.Design;

@Controller
public class DashboardController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DashboardController.class);
	
	@Autowired
	DashboardService service;
	
	@RequestMapping(value="/dashboards",method={RequestMethod.GET})
	public ModelAndView dashboards(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.dashboardsGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("dashboards : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-dashboards-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dashboard> getDashboardsList(@ModelAttribute Dashboard obj) {
		List<Dashboard> objList = null;
		try {
			objList = service.getDashboardsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashboardsList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getModulesFilterListInDashboard", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dashboard> getModulesList(@ModelAttribute Dashboard obj) {
		List<Dashboard> objList = null;
		try {
			objList = service.getModulesFilterListInDashboard(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getModulesList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDashboardTypesFilterListInDashboard", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dashboard> getDashboardTypesList(@ModelAttribute Dashboard obj) {
		List<Dashboard> objList = null;
		try {
			objList = service.getDashboardTypesFilterListInDashboard(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashboardTypesList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusFilterListInDashboard", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dashboard> getStatusList(@ModelAttribute Dashboard obj) {
		List<Dashboard> objList = null;
		try {
			objList = service.getStatusFilterListInDashboard(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForDashboardForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dashboard> getContractsListForDesignForm(@ModelAttribute Dashboard obj) {
		List<Dashboard> objsList = null;
		try {
			objsList = service.getContractsListForDashboardForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-dashboard-form", method = {RequestMethod.GET})
	public ModelAndView addDashboardForm(@ModelAttribute Dashboard obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDashboardForm);
			model.addObject("action", "add");
			
			List<Dashboard> worksList = service.getWorkListForDashboardForm(obj);
			model.addObject("worksList", worksList);
			
			List<Dashboard> contractsList = service.getContractsListForDashboardForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Dashboard> modulesList = service.getModulesListForDashboardForm(obj);
			model.addObject("modulesList", modulesList);
			
			List<Dashboard> dashboardTypeList = service.getDashboardTypesListForDashboardForm(obj);
			model.addObject("dashboardTypeList", dashboardTypeList);
			
			List<Dashboard> foldersList = service.getFolderssListForDashboardForm(obj);
			model.addObject("foldersList", foldersList);
			
			List<Dashboard> statusList = service.getStatusListForDashboardForm(obj);
			model.addObject("statusList", statusList);
			
			
		}catch (Exception e) {
				logger.error("addDashboardForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-dashboard", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDashboardForm(@ModelAttribute Dashboard obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDashboardForm);
			model.addObject("action", "edit");
			
			List<Dashboard> worksList = service.getWorkListForDashboardForm(obj);
			model.addObject("worksList", worksList);
			
			List<Dashboard> contractsList = service.getContractsListForDashboardForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Dashboard> modulesList = service.getModulesListForDashboardForm(obj);
			model.addObject("modulesList", modulesList);
			
			List<Dashboard> dashboardTypeList = service.getDashboardTypesListForDashboardForm(obj);
			model.addObject("dashboardTypeList", dashboardTypeList);
			
			List<Dashboard> foldersList = service.getFolderssListForDashboardForm(obj);
			model.addObject("foldersList", foldersList);
			
			List<Dashboard> statusList = service.getStatusListForDashboardForm(obj);
			model.addObject("statusList", statusList);
			
			Dashboard dashboardDetails = service.getDashboardForm(obj);
			model.addObject("dashboardDetails", dashboardDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDashboardForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-dashboard", method = {RequestMethod.POST})
	public ModelAndView addDashboard(@ModelAttribute Dashboard obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setPublished_by_user_id_fk(user_Id);
			model.setViewName("redirect:/dashboards");
			boolean flag =  service.addDashboard(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Dashboard is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Dashboard is failed. Try again.");
			logger.error("addDashboard : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-dashboard", method = {RequestMethod.POST})
	public ModelAndView updateDashboard(@ModelAttribute Dashboard obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setModified_by_user_id_fk(user_Id);
			model.setViewName("redirect:/dashboards");
			boolean flag =  service.updateDashboard(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Dashboard is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Dashboard is failed. Try again.");
			logger.error("updateDashboard : " + e.getMessage());
		}
		return model;
	}
}
