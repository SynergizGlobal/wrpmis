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

import com.synergizglobal.wrpmis.reference.Iservice.UtilityStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class UtilityStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityStatusController.class);
	
	@Autowired
	UtilityStatusService service;
	
	@RequestMapping(value="/utility-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView UtilityStatus(HttpSession session,@ModelAttribute Safety obj){
		ModelAndView model = new ModelAndView(PageConstants.utilityStatus);
		try {
			Safety  UtilityStatusList = service.getUtilityStatusList(obj);
			model.addObject("utilityStatusList",  UtilityStatusList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" UtilityStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-status");
			boolean flag =  service.addUtilityStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Utility Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Utility Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Utility Status is failed. Try again.");
			logger.error("add UtilityStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/update-utility-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUtilityStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-status");
			boolean flag =  service.updateUtilityStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Utility Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Utility Status is failed. Try again.");
			logger.error("updateUtilityStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-utility-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUtilityStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-status");
			boolean flag =  service.deleteUtilityStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUtilityStatus : " + e.getMessage());
		}
		return model;
	}	
	
}









