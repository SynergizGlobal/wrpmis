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
import com.synergizglobal.wrpmis.reference.Iservice.ResourceTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class ResourceTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ResourceTypeController.class);
	
	@Autowired
	ResourceTypeService service;
	
	@RequestMapping(value="/resource-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView resourceType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.resourceType);
		try {
			TrainingType resourceTypeDetails = service.getResourceTypeDetails(obj);
			model.addObject("resourceTypeDetails",resourceTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("resourceType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-resource-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addResourceType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/resource-type");
			boolean flag =  service.addResourceType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Resource Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Resource Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Resource Type is failed. Try again.");
			logger.error("addResourceType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-resource-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateResourceType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/resource-type");
			boolean flag =  service.updateResourceType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Resource Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Resource Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Resource Type is failed. Try again.");
			logger.error("updateResourceType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-resource-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteResourceType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/resource-type");
			boolean flag =  service.deleteResourceType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Resource Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteResourceType : " + e.getMessage());
		}
		return model;
	}
	
}
