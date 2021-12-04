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
import com.synergizglobal.pmis.reference.Iservice.ExecutionStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class ExecutionStatusController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ExecutionStatusController.class);
	
	@Autowired
	ExecutionStatusService service;
	
	@RequestMapping(value="/execution-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView executionStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.executionStatus);
		try {
			List<Safety>  executionStatusList = service.getExecutionStatusList();
			model.addObject("executionStatusList",  executionStatusList);
			TrainingType executionStatusDetails = service.getExecutionStatusDetails(obj);
			model.addObject("executionStatusDetails",executionStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" executionStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-execution-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addExecutionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/execution-status");
			boolean flag =  service.addExecutionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Execution Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Execution Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Execution Status is failed. Try again.");
			logger.error("add ExecutionStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-execution-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateExecutionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/execution-status");
			boolean flag =  service.updateExecutionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Execution Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Execution Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Execution Status is failed. Try again.");
			logger.error("updateExecutionStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-execution-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteExecutionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/execution-status");
			boolean flag =  service.deleteExecutionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Execution Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteExecutionStatus : " + e.getMessage());
		}
		return model;
	}
}
