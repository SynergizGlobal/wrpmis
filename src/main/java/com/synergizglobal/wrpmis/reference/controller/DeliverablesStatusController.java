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

import com.synergizglobal.wrpmis.reference.Iservice.DeliverablesStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class DeliverablesStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DeliverablesStatusController.class);
	
	@Autowired
	DeliverablesStatusService service;
	
	@RequestMapping(value="/deliverables-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deliverablesStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.deliverablesStatus);
		try {
			List<Safety> deliverablesStatusList = service.getDeliverablesStatusList();
			model.addObject("deliverablesStatusList", deliverablesStatusList);
			TrainingType deliverablesStatusDetails = service.getDeliverablesStatusDetails(obj);
			model.addObject("deliverablesStatusDetails",deliverablesStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("DeliverablesStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-deliverables-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDeliverablesStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables-status");
			boolean flag =  service.addDeliverablesStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Status is failed. Try again.");
			logger.error("addDeliverablesStatus : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-deliverables-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDeliverablesStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables-status");
			boolean flag =  service.updateDeliverablesStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Status is failed. Try again.");
			logger.error("updateDeliverablesStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-deliverables-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDeliverablesStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables-status");
			boolean flag =  service.deleteDeliverablesStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDeliverablesStatus : " + e.getMessage());
		}
		return model;
	}
	
}













