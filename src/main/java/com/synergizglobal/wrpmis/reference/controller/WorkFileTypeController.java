package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.reference.Iservice.WorkFileTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class WorkFileTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkFileTypeController.class);
	
	@Autowired
	WorkFileTypeService service;
	
	@RequestMapping(value="/work-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.workFileType);
		try {
			
			List<TrainingType> workFileType = service.getWorkFileType(obj);
			model.addObject("workFileType",workFileType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("workFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-work-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWorkFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-file-type");
			boolean flag =  service.addWorkFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work File Type is failed. Try again.");
			logger.error("addWorkFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-work-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateWorkFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-file-type");
			boolean flag =  service.updateWorkFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Work File Type is failed. Try again.");
			logger.error("updateWorkFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-work-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteWorkFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-file-type");
			boolean flag =  service.deleteWorkFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Work File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteWorkFileType : " + e.getMessage());
		}
		return model;
	}
	
}
