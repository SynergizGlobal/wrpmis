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

import com.synergizglobal.pmis.reference.Iservice.SoftDeleteStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class SoftDeleteStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SoftDeleteStatusController.class);
	
	@Autowired
	SoftDeleteStatusService service;
	
	@RequestMapping(value="/soft-delete-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView softDeleteStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.softDeleteStatus);
		try {
			List<TrainingType> softDeleteStatusList = service.getSoftDeleteStatussList();
			model.addObject("softDeleteStatusList", softDeleteStatusList);
			TrainingType softDeleteStatusDetails = service.getSoftDeleteStatusDetails(obj);
			model.addObject("softDeleteStatusDetails",softDeleteStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("softDeleteStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-soft-delete-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSoftDeleteStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/soft-delete-status");
			boolean flag =  service.addSoftDeleteStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Soft Delete Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Soft Delete Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Soft Delete Status is failed. Try again.");
			logger.error("addSoftDeleteStatus : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/update-soft-delete-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSoftDeleteStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/soft-delete-status");
			boolean flag =  service.updateSoftDeleteStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateSoftDeleteStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-soft-delete-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSoftDeleteStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/soft-delete-status");
			boolean flag =  service.deleteSoftDeleteStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSoftDeleteStatus : " + e.getMessage());
		}
		return model;
	}
	
}

