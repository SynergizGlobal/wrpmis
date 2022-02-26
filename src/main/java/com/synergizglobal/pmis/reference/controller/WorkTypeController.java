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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.WorkTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class WorkTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkTypeController.class);
	
	@Autowired
	WorkTypeService service;
	
	@RequestMapping(value="/work-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.workType);
		try {
			
			TrainingType workTypeDetails = service.getWorkTypeDetails(obj);
			model.addObject("workTypeDetails",workTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("workType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWorkType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-type");
			boolean flag =  service.addWorkType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work Type is failed. Try again.");
			logger.error("addWorkType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-work-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateWorkType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-type");
			boolean flag =  service.updateWorkType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Work Type is failed. Try again.");
			logger.error("updateWorkType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-work-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteWorkType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-type");
			boolean flag =  service.deleteWorkType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteWorkType : " + e.getMessage());
		}
		return model;
	}
	
}
