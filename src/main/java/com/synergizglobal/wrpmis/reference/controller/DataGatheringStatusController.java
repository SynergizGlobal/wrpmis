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

import com.synergizglobal.wrpmis.reference.Iservice.DataGatheringsService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class DataGatheringStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DataGatheringStatusController.class);
	
	@Autowired
	DataGatheringsService service;
	
	@RequestMapping(value="/data-gathering-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView dataGatheringStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.dataGatherings);
		try {
			List<TrainingType> dataGatheringsList = service.getDataGatheringsList();
			model.addObject("dataGatheringsList", dataGatheringsList);
			TrainingType dataGatheringStatusDetails = service.getDataGatheringStatusDetails(obj);
			model.addObject("dataGatheringStatusDetails", dataGatheringStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("dataGatheringStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-data-gathering-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDataGatherings(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering-status");
			boolean flag =  service.addDataGatherings(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			logger.error("addDataGatherings : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-data-gathering-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDataGatheringStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering-status");
			boolean flag =  service.updateDataGatheringStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateDataGatheringStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-data-gathering-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDataGatheringStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering-status");
			boolean flag =  service.deleteDataGatheringStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDataGatheringStatus : " + e.getMessage());
		}
		return model;
	}
	
}
















