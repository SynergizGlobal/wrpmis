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

import com.synergizglobal.pmis.reference.Iservice.TrainingTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;


@Controller
public class TrainingTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TrainingTypeController.class);
	
	@Autowired
	TrainingTypeService service;
	
	@RequestMapping(value="/training-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView trainingType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.trainingType);
		try {
			List<TrainingType> trainingType = service.getTrainingTypesList();
			model.addObject("trainingType", trainingType);
			TrainingType trainingTypeDetails = service.getTrainingTypeDetails(obj);
			model.addObject("trainingTypeDetails",trainingTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("trainingType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-training-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTrainingType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-type");
			boolean flag =  service.addTrainingType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Training Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Training Type is failed. Try again.");
			logger.error("addTrainingType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-training-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTrainingType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-type");
			boolean flag =  service.updateTrainingType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Training Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Training Type is failed. Try again.");
			logger.error("updateTrainingType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-training-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTrainingType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-type");
			boolean flag =  service.deleteTrainingType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteTrainingType : " + e.getMessage());
		}
		return model;
	}
	
}

