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

import com.synergizglobal.pmis.reference.Iservice.DepartmentService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class DepartmentController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService service;
	
	@RequestMapping(value="/department",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView department(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.department);
		try {
			List<TrainingType> departmentList = service.getDepartmentsList();
			model.addObject("departmentList", departmentList);
			TrainingType departmentDetails = service.getDpartmentDetails(obj);
			model.addObject("departmentDetails", departmentDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("department : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-department", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDepartment(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/department");
			boolean flag =  service.addDepartment(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Department Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Department is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Department is failed. Try again.");
			logger.error("addDepartment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-department", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDepartment(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/department");
			boolean flag =  service.updateDepartment(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Department Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Department is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Department is failed. Try again.");
			logger.error("updateDepartment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-department", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDepartment(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/department");
			boolean flag =  service.deleteDepartment(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Department Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDepartment : " + e.getMessage());
		}
		return model;
	}
	
}



