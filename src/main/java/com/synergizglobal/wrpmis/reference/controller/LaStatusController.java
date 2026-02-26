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

import com.synergizglobal.wrpmis.reference.Iservice.LaStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class LaStatusController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LaStatusController.class);
	
	@Autowired
	LaStatusService service;
	
	@RequestMapping(value="/la-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView laStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.laStatus);
		try {
			List<Safety> laStatusList = service.getIaStatusList();
			model.addObject("laStatusList", laStatusList);
			TrainingType landAcquisitionStatusDetails = service.getLandAcquisitionStatusDetails(obj);
			model.addObject("landAcquisitionStatusDetails", landAcquisitionStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("laStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-la-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addLaStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-status");
			boolean flag =  service.addLaStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			logger.error("addLaStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-la-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateLandAcquisitionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-status");
			boolean flag =  service.updatelandAcquisitionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updatelandAcquisitionStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-la-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteLandAcquisitionStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-status");
			boolean flag =  service.deletelandAcquisitionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deletelandAcquisitionStatus : " + e.getMessage());
		}
		return model;
	}
	
}





