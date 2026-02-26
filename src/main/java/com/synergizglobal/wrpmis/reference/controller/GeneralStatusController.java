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

import com.synergizglobal.wrpmis.reference.Iservice.GeneralStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class GeneralStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(GeneralStatusController.class);
	
	@Autowired
	GeneralStatusService service;
	
	@RequestMapping(value="/general-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generalStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.generalStatus);
		try {
			List<Safety>  generalStatusList = service.getGeneralStatusList();
			model.addObject("generalStatusList",  generalStatusList);
			TrainingType generalStatusDetails = service.getGeneralStatusDetails(obj);
			model.addObject("generalStatusDetails",generalStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" generalStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-general-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addGeneralStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/general-status");
			boolean flag =  service.addGeneralStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " General Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  General Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  General Status is failed. Try again.");
			logger.error("add GeneralStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-general-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateGeneralStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/general-status");
			boolean flag =  service.updateGeneralStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "General Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating General Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating General Status is failed. Try again.");
			logger.error("updateGeneralStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-general-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteGeneralStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/general-status");
			boolean flag =  service.deleteGeneralStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "General Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteGeneralStatus : " + e.getMessage());
		}
		return model;
	}
	
}








