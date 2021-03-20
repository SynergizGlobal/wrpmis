package com.synergizglobal.pmis.reference.controller;

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
import com.synergizglobal.pmis.reference.Iservice.YesOrNoStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class YesOrNoStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(YesOrNoStatusController.class);
	
	@Autowired
	YesOrNoStatusService service;
	

	@RequestMapping(value="/yes-or-no-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView yesOrNoStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.yesOrNoStatus);
		try {
			TrainingType yesOrNoStatusDetails = service.getYesOrNoStatusDetails(obj);
			model.addObject("yesOrNoStatusDetails",yesOrNoStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("yesOrNoStatus :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-yes-or-no-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addYesOrNoStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/yes-or-no-status");
			boolean flag =  service.addYesOrNoStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("addYesOrNoStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-yes-or-no-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateYesOrNoStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/yes-or-no-status");
			boolean flag =  service.updateYesOrNoStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("updateYesOrNoStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-yes-or-no-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteYesOrNoStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/yes-or-no-status");
			boolean flag =  service.deleteYesOrNoStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteYesOrNoStatus : " + e.getMessage());
		}
		return model;
	}
	
}



