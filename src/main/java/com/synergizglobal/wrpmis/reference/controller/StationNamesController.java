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
import com.synergizglobal.wrpmis.reference.Iservice.StationNamesService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class StationNamesController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(StationNamesController.class);
	
	@Autowired
	StationNamesService service;
	
	@RequestMapping(value="/station-name",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView StationNames(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.stationNames);
		try {
			List<TrainingType> StationNames = service.getStationNamesList();
			model.addObject("StationNames", StationNames);
			TrainingType stationNamesDetails = service.getStationNamesDetails(obj);
			model.addObject("stationNamesDetails",stationNamesDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("StationNames : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-station-name", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addStationNames(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/station-name");
			boolean flag =  service.addStationNames(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Station Name Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Station Name is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Station Name is failed. Try again.");
			logger.error("addStationNames : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-station-name", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateStationNames(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/station-name");
			boolean flag =  service.updateStationNames(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Station Name Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Station Name is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Station Name is failed. Try again.");
			logger.error("updateStationNames : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-station-name", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteStationNames(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/station-name");
			boolean flag =  service.deleteStationNames(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Station Name Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteStationNames : " + e.getMessage());
		}
		return model;
	}
	
}


