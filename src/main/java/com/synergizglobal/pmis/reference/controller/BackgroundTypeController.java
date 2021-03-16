package com.synergizglobal.pmis.reference.controller;

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

import com.synergizglobal.pmis.reference.Iservice.BackgroundTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class BackgroundTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(BackgroundTypeController.class);
	
	@Autowired
	BackgroundTypeService service;
	
	@RequestMapping(value="/bank-guarantee-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView backgroundType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.bankGuaranteeType);
		try {
			List<TrainingType> backgroundTypeList = service.getBackgroundTypesList();
			model.addObject("backgroundTypeList", backgroundTypeList);
			TrainingType bankGuaranteeDetails = service.getBankGuaranteeDetails(obj);
			model.addObject("bankGuaranteeDetails", bankGuaranteeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("backgroundType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-bank-guarantee-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addBackgroundType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/bank-guarantee-type");
			boolean flag =  service.addBackgroundType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Bank Guarantee Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Bank Guarantee Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Bank Guarantee Type is failed. Try again.");
			logger.error("addBackgroundType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-bank-guarantee-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateBackgroundType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/bank-guarantee-type");
			boolean flag =  service.updateBackgroundType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Bank Guarantee Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Bank Guarantee Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Bank Guarantee Type is failed. Try again.");
			logger.error("updateBackgroundType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-bank-guarantee-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteBankGuaranteeType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/bank-guarantee-type");
			boolean flag =  service.deleteBankGuaranteeType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Bank Guarantee Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteBankGuaranteeType : " + e.getMessage());
		}
		return model;
	}
}




