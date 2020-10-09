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

import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

@Controller
public class BudgetController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(BudgetController.class);
	
	@Autowired
	WorkService workService;
	
	@Autowired
	BudgetService budgetService;
	
	
	@RequestMapping(value="/budget",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Contract(HttpSession session,@ModelAttribute Work obj){
		ModelAndView model = new ModelAndView(PageConstants.budgetGrid);
		try {
			List<Work> workList = workService.getWorkList(obj);
			model.addObject("workList", workList);
			List<Work> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getBudget", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getContractList(@ModelAttribute Budget obj) {
		List<Budget> budgetList = null;
		try {
			budgetList = budgetService.budgetList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("budgetList : " + e.getMessage());
		}
		return budgetList;
	}
	
	
	@RequestMapping(value = "/add-budget-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractorForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBudget);
			model.addObject("action", "add");
			
		}catch (Exception e) {
				logger.info("Contractor : " + e.getMessage());
		}
		return model;
	 }
}
