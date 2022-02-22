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
import com.synergizglobal.pmis.reference.Iservice.LandResponsibleExecutivesService;
import com.synergizglobal.pmis.reference.Iservice.RrResponsibleExecutivesService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Controller
public class LandResponsibleExecutivesController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LandResponsibleExecutivesController.class);
	@Autowired
	RrResponsibleExecutivesService service;
	
	
	@Autowired
	LandResponsibleExecutivesService mainService;
	
	@RequestMapping(value="/land-executives",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView executives(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.landExecutives);
		try {
			
			List<TrainingType> executivesDetails = mainService.getExecutivesDetails(obj);
			model.addObject("executivesDetails",executivesDetails);
			
			List<TrainingType> workDetails = mainService.getWorkDetails(obj);
			model.addObject("workDetails",workDetails);
			
			List<TrainingType> usersDetails = service.getUsersDetails(obj);
			model.addObject("usersDetails",usersDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("executives : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-land-executives", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addLandAcquisitionExecutives(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/land-executives");
			boolean flag =  mainService.addLandAcquisitionExecutives(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Executives Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Executives is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Executives is failed. Try again.");
			logger.error("addLandAcquisitionExecutives : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-land-executives", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateLandAcquisitionExecutives(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/land-executives");
			boolean flag =  mainService.updateLandAcquisitionExecutives(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Executives Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Executives is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Executives is failed. Try again.");
			logger.error("updateLandAcquisitionExecutives : " + e.getMessage());
		}
		return model;
	}
}
