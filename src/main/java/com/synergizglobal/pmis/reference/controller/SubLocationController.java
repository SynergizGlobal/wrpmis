package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
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
import com.synergizglobal.pmis.reference.Iservice.RRLocationService;
import com.synergizglobal.pmis.reference.Iservice.SubLocationService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class SubLocationController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SubLocationController.class);
	
	@Autowired
	SubLocationService service;
	
	@RequestMapping(value="/sub-location",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subLocation(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrSubLocation);
		try {
			List<TrainingType> subLocationList = service.getSubLocationsList();
			model.addObject("subLocationList", subLocationList);
			List<TrainingType> locationList = service.getLocationList();
			model.addObject("locationList", locationList);
			TrainingType subLocationDetails = service.getSubLocationDetails(obj);
			model.addObject("subLocationDetails",subLocationDetails);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("subLocation : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getSubLocations", method = { RequestMethod.GET,RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getSubLocations(@ModelAttribute TrainingType obj) {
		List<TrainingType> objList = null;
		try {
			objList = service.getSubLocations(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubLocations : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/add-sub-location", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSubLocation(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/sub-location");
			boolean flag =  service.addSubLocation(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Sub Location Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Sub Location is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Sub Location is failed. Try again.");
			logger.error("addSubLocation : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-sub-location", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSubLocation(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/sub-location");
			boolean flag =  service.updateSubLocation(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Sub Location Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Sub Location is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Sub Location is failed. Try again.");
			logger.error("updateSubLocation : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-sub-location", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSubLocation(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/sub-location");
			boolean flag =  service.deleteSubLocation(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Sub Location Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSubLocation : " + e.getMessage());
		}
		return model;
	}
}
