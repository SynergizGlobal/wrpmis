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

import com.synergizglobal.wrpmis.reference.Iservice.SafetyCategoryService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class SafetyCategoryController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyCategoryController.class);
	
	@Autowired
	SafetyCategoryService service;
	
	@RequestMapping(value="/safety-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView SafetyCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.safetyCategory);
		try {
			List<Safety> safetyCategoryList = service.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			TrainingType safetyCategoryDetails = service.getSafetyCategoryDetails(obj);
			model.addObject("safetyCategoryDetails",safetyCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("SafetyCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-safety-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSafetyCategory(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-category");
			boolean flag =  service.addSafetyCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Safety Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Safety Category is failed. Try again.");
			logger.error("addSafetyCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-safety-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateSafetyCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-category");
			boolean flag =  service.updateSafetyCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Safety Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Safety Category is failed. Try again.");
			logger.error("updateSafetyCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-safety-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteSafetyCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-category");
			boolean flag =  service.deleteSafetyCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteSafetyCategory : " + e.getMessage());
		}
		return model;
	}
	
}






