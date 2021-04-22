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
import com.synergizglobal.pmis.reference.Iservice.ProjectFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class ProjectFileTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ProjectFileTypeController.class);
	
	@Autowired
	ProjectFileTypeService service;
	
	@RequestMapping(value="/project-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView projectFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.projectFileType);
		try {
			
			List<TrainingType> projectFileType = service.getProjectFileType(obj);
			model.addObject("projectFileType",projectFileType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("projectFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-project-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addProjectFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project-file-type");
			boolean flag =  service.addProjectFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Project File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Project File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Project File Type is failed. Try again.");
			logger.error("addProjectFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-project-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateProjectFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project-file-type");
			boolean flag =  service.updateProjectFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Project File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Project File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Project File Type is failed. Try again.");
			logger.error("updateProjectFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-project-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteProjectFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project-file-type");
			boolean flag =  service.deleteProjectFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Project File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteProjectFileType : " + e.getMessage());
		}
		return model;
	}
	
	
}
