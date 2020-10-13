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

import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.WorkContractModuleStatusService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contractor;
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

	@RequestMapping(value="/workContractModuleStatusGrid",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workContractModuleStatusGrid(HttpSession session,@ModelAttribute Work obj){
		ModelAndView model = new ModelAndView(PageConstants.workContractModuleStatusGrid);
		try {
				List<Work> workList = workService.getWorkList(obj);
				model.addObject("workList", workList);
				List<Project> projectsList = budgetService.getProjectsList();
				model.addObject("projectsList", projectsList);
				List<WorkContractModuleStatus> contractsList = service.getContractsList();
				model.addObject("contractsList", contractsList);
				
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("workContractModuleStatusGrid : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorkContractModule", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkContractModuleStatus> getWorkStatusList(@ModelAttribute WorkContractModuleStatus obj) {
		List<WorkContractModuleStatus> budgetList = null;
		try {
			budgetList = service.workStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("workStatusList : " + e.getMessage());
		}
		return budgetList;
	}
	
	@RequestMapping(value = "/add-workContractModuleStatusGrid", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractorForm(@ModelAttribute Work obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWorkContractModuleStatus);
			model.addObject("action", "add");
			List<Work> workList = workService.getWorkList(obj);
			model.addObject("workList", workList);
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<WorkContractModuleStatus> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			
		}catch (Exception e) {
				logger.info("Contractor : " + e.getMessage());
		}
		return model;
	 }

	@RequestMapping(value = "/get-workstatus", method = {RequestMethod.POST})
	public ModelAndView getWorkstatus(@ModelAttribute WorkContractModuleStatus wObj,Work obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWorkContractModuleStatus);
			model.addObject("action", "edit");
			List<Work> workList = workService.getWorkList(obj);
			model.addObject("workList", workList);
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<WorkContractModuleStatus> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			WorkContractModuleStatus workStatusDetails = service.getWorkStatus(wObj);
			model.addObject("workStatusDetails", workStatusDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.info("getWorkStatus : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-workstatus", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWorkstatus(@ModelAttribute WorkContractModuleStatus obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/workContractModuleStatusGrid");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.addWorkstatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Module Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something Went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something Went Wrong. Try again.");
			logger.info("addWorkstatus : " + e.getMessage());
		}
		return model;
	}
}
