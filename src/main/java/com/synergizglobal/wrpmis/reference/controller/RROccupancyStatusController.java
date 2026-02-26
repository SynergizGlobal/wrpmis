package com.synergizglobal.wrpmis.reference.controller;

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
import com.synergizglobal.wrpmis.reference.Iservice.RROccupancyStatusService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class RROccupancyStatusController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RROccupancyStatusController.class);
	
	@Autowired
	RROccupancyStatusService service;
	
	@RequestMapping(value="/rr-occupancy-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView occupancyStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrOccupancyStatus);
		try {
			TrainingType rrOccupancyStatus = service.getRROccupancyStatusDetails(obj);
			model.addObject("rrOccupancyStatus",rrOccupancyStatus);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("occupancyStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-occupancy-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRROccupancyStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-occupancy-status");
			boolean flag =  service.addRROccupancyStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Occupancy Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Occupancy Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Occupancy Status is failed. Try again.");
			logger.error("addRROccupancyStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-occupancy-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRROccupancyStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-occupancy-status");
			boolean flag =  service.updateRROccupancyStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Occupancy Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Occupancy Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Occupancy Status is failed. Try again.");
			logger.error("updateRROccupancyStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-occupancy-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRROccupancyStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-occupancy-status");
			boolean flag =  service.deleteRROccupancyStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Occupancy Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRROccupancyStatus : " + e.getMessage());
		}
		return model;
	}
	
}


