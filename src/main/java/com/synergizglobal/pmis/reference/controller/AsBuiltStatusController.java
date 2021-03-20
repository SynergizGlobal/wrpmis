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
import com.synergizglobal.pmis.reference.Iservice.AsBuiltStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;


@Controller
public class AsBuiltStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(AsBuiltStatusController.class);
	
	@Autowired
	AsBuiltStatusService service;
	
	@RequestMapping(value="/as-built-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView asBuiltStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.asBuiltStatus);
		try {
			TrainingType asBuiltStatusDetails = service.getAsBuiltStatusDetails(obj);
			model.addObject("asBuiltStatusDetails",asBuiltStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("asBuiltStatus :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-as-built-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addAsBuiltStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/as-built-status");
			boolean flag =  service.addAsBuiltStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			logger.error("addAsBuiltStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-as-built-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateAsBuiltStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/as-built-status");
			boolean flag =  service.updateAsBuiltStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateAsBuiltStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-as-built-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteAsBuiltStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/as-built-status");
			boolean flag =  service.deleteAsBuiltStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteAsBuiltStatus : " + e.getMessage());
		}
		return model;
	}
	
}




