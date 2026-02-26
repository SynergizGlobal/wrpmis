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

import com.synergizglobal.wrpmis.reference.Iservice.DeliverableTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class DeliverableTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DeliverableTypeController.class);
	
	@Autowired
	DeliverableTypeService service;
	
	@RequestMapping(value="/deliverable-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deliverableType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.deliverableType);
		try {
			List<TrainingType> deliverableTypeList = service.getDeliverableTypesList();
			model.addObject("deliverableTypeList", deliverableTypeList);
			TrainingType deliverableTypeDetails = service.getDeliverableTypeDetails(obj);
			model.addObject("deliverableTypeDetails",deliverableTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("DeliverableType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-deliverable-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDeliverableType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverable-type");
			boolean flag =  service.addDeliverableType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverable Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Deliverable Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Deliverable Type is failed. Try again.");
			logger.error("addDeliverableType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-deliverable-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDeliverablesType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverable-type");
			boolean flag =  service.updateDeliverableType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverable Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Deliverable Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Deliverable Type is failed. Try again.");
			logger.error("updateDeliverablesType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-deliverable-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteDeliverablesType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverable-type");
			boolean flag =  service.deleteDeliverableType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverable Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteDeliverablesType : " + e.getMessage());
		}
		return model;
	}
	
}
















