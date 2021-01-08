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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.WorkContractModuleStatusService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

@Controller
public class WorkContractModuleStatusController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkContractModuleStatusController.class);
	
	@Autowired
	WorkService workService;
	
	@Autowired
	BudgetService budgetService;
	
	@Autowired
	WorkContractModuleStatusService service;

	@RequestMapping(value="/work-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workContractModuleStatus(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.workContractModuleStatusGrid);
		try {
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("WorkContractModuleStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-work-contract-modules", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getWorkStatusList(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> workStatusList = null;
		try {
			workStatusList = service.workStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("workStatusList : " + e.getMessage());
		}
		return workStatusList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInWorkstatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getWorkStatusWorksList(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> worksList = null;
		try {
			worksList = service.getWorkStatusWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInWorkstatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getWorkStatusContractsList(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> contractsList = null;
		try {
			contractsList = service.getWorkStatusContractsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusContractsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInWorkstatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getWorkStatusProjectsList(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> projectsList = null;
		try {
			projectsList = service.getWorkStatusProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/add-work-status-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWorkStatusForm(@ModelAttribute WorkContractModuleStatus obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWorkContractModuleStatus);
			model.addObject("action", "add");
			List<WorkContractModuleStatus> modulesList = service.getModulesList();
			model.addObject("modulesList", modulesList);

			List<WorkContractModuleStatus> projectsList = service.getProjectsListForWorkContractModuleStatusForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<WorkContractModuleStatus> worksList = service.getWorkListForWorkContractModuleStatusForm(obj);
			model.addObject("worksList", worksList);
			
			List<WorkContractModuleStatus> contractsList = service.getContractsListForWorkContractModuleStatusForm(obj);
			model.addObject("contractsList", contractsList);
			
		}catch (Exception e) {
				logger.error("addWorkStatusForm : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getProjectsListForWorkContractModuleStatusForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getProjectsListForWorkContractModuleStatusForm(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> objsList = null;
		try {
			objsList = service.getProjectsListForWorkContractModuleStatusForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForWorkContractModuleStatusForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForWorkContractModuleStatusForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getWorkListForWorkContractModuleStatusForm(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> objsList = null;
		try {
			objsList = service.getWorkListForWorkContractModuleStatusForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForWorkContractModuleStatusForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForWorkContractModuleStatusForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getContractsListForWorkContractModuleStatusForm(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> objsList = null;
		try {
			objsList = service.getContractsListForWorkContractModuleStatusForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForWorkContractModuleStatusForm : " + e.getMessage());
		}
		return objsList;
	}

	@RequestMapping(value = "/get-work-status", method = {RequestMethod.POST})
	public ModelAndView getWorkstatus(@ModelAttribute WorkContractModuleStatus wObj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWorkContractModuleStatus);
			model.addObject("action", "edit");
			List<WorkContractModuleStatus> projectsList = service.getProjectsListForWorkContractModuleStatusForm(wObj);
			model.addObject("projectsList", projectsList);
			WorkContractModuleStatus workStatusDetails = service.getWorkStatus(wObj);
			model.addObject("workStatusDetails", workStatusDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getWorkStatus : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-work-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWorkstatus(@ModelAttribute WorkContractModuleStatus obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-status");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.addWorkstatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Statuses Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something Went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something Went Wrong. Try again.");
			logger.error("addWorkstatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-work-status", method = {RequestMethod.POST})
	public ModelAndView updateWorkStatus(@ModelAttribute WorkContractModuleStatus obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-status");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag = service.updateWorkStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Statuses Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something Went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something Went Wrong. Try again.");
			logger.error("updateWorkStatus : " + e.getMessage());
		}
		return model;
	}
}
