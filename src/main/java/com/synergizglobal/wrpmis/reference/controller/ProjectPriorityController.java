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

import com.synergizglobal.wrpmis.reference.Iservice.ProjectPriorityService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class ProjectPriorityController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ProjectPriorityController.class);
	
	@Autowired
	ProjectPriorityService service;
	
	@RequestMapping(value="/project-priority",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView projectPriority(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.projectPriority);
		try {
			List<Safety> projectPriority = service.getProjectPriorityList();
			model.addObject("projectPriority", projectPriority);
			TrainingType projectPriorityDetails = service.getProjectPriorityDetails(obj);
			model.addObject("projectPriorityDetails",projectPriorityDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("projectPriority : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-project-priority", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addProjectPriority(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project-priority");
			boolean flag =  service.addProjectPriority(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Project Priority Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Project Priority is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Project Priority is failed. Try again.");
			logger.error("addProjectPriority : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-project-priority", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateProjectPriority(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project-priority");
			boolean flag =  service.updateProjectPriority(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Project Priority Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Project Priority is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Project Priority is failed. Try again.");
			logger.error("updateProjectPriority : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-project-priority", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteProjectPriority(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project-priority");
			boolean flag =  service.deleteProjectPriority(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Project Priority Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteProjectPriority : " + e.getMessage());
		}
		return model;
	}
	
}






