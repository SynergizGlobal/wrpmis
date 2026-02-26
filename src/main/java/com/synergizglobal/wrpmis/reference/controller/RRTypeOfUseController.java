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
import com.synergizglobal.wrpmis.reference.Iservice.RRTypeOfUseService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class RRTypeOfUseController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRTypeOfUseController.class);
	
	@Autowired
	RRTypeOfUseService service;
	
	@RequestMapping(value="/rr-type-of-use",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView RRTypeOfUse(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrTypeOfUse);
		try {
			
			TrainingType rrTypeOfUseDetails = service.getRRTypeOfUseDetails(obj);
			model.addObject("rrTypeOfUseDetails",rrTypeOfUseDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("alertLevel : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-type-of-use", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRTypeOfUse(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-type-of-use");
			boolean flag =  service.addRRTypeOfUse(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Type of Use Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Type of Use is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Type of Use is failed. Try again.");
			logger.error("addRRTypeOfUse : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-type-of-use", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRTypeOfUse(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-type-of-use");
			boolean flag =  service.updateRRTypeOfUse(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Type of Use Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Type of Use is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Type of Use is failed. Try again.");
			logger.error("updateRRTypeOfUse : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-type-of-use", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRTypeOfUse(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-type-of-use");
			boolean flag =  service.deleteRRTypeOfUse(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Type of Use Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRTypeOfUse : " + e.getMessage());
		}
		return model;
	}
	
}


