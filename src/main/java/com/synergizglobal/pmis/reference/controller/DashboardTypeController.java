package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.reference.Iservice.DashboardTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class DashboardTypeController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DashboardTypeController.class);
	
	@Autowired
	DashboardTypeService service;
	
	@RequestMapping(value="/dashboard-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView dashboardType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.dashboardType);
		try {
			List<TrainingType>  dashboardTypeList = service.getDashboardTypesList();
			model.addObject("dashboardTypeList",  dashboardTypeList);
			TrainingType dashBoardTypeDetails = service.getDashBoardTypeDetails(obj);
			model.addObject("dashBoardTypeDetails", dashBoardTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" dashboardType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-dashboard-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDashboardType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/dashboard-type");
			boolean flag =  service.addDashboardType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Dashboard Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Dashboard Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Dashboard Type is failed. Try again.");
			logger.error("add DashboardType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-dashboard-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDashBoardType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/dashboard-type");
			boolean flag =  service.updateDashBoardType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Dashboard Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Dashboard Type is failed. Try again.");
			logger.error("updateDashBoardType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-dashboard-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDashBoardType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/dashboard-type");
			boolean flag =  service.deleteDashBoardType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Dashboard Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDashBoardType : " + e.getMessage());
		}
		return model;
	}
	
}













