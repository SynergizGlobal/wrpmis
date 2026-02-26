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

import com.synergizglobal.wrpmis.reference.Iservice.TrainingStatusService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class TrainingStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TrainingStatusController.class);
	
	@Autowired
	TrainingStatusService service;
	
	@RequestMapping(value="/training-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView trainingStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.trainingStatus);
		try {
			List<TrainingType> trainingStatus = service.getTrainingStatusList();
			model.addObject("trainingStatus", trainingStatus);
			TrainingType trainingStatusDetails = service.getTrainingStatusDetails(obj);
			model.addObject("trainingStatusDetails",trainingStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("trainingStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-training-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTrainingStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-status");
			boolean flag =  service.addTrainingStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Training Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Training Status is failed. Try again.");
			logger.error("addTrainingStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-training-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTrainingStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-status");
			boolean flag =  service.updateTrainingStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateTrainingStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-training-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTrainingStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-status");
			boolean flag =  service.deleteTrainingStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteTrainingStatus : " + e.getMessage());
		}
		return model;
	}
	
}
