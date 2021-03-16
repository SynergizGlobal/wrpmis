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

import com.synergizglobal.pmis.reference.Iservice.LACategoryService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class LACategoryController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LACategoryController.class);
	
	@Autowired
	LACategoryService service;
	
	@RequestMapping(value="/la-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView LACategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.laCategory);
		try {
			List<TrainingType> LACategoryList = service.getLACategoryList();
			model.addObject("LACategoryList", LACategoryList);
			TrainingType landAcquisitionCategoryDetails = service.getLandAcquisitionCategoryDetails(obj);
			model.addObject("landAcquisitionCategoryDetails",landAcquisitionCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("LACategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-la-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addLACategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-category");
			boolean flag =  service.addLACategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding LA Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding LA Category is failed. Try again.");
			logger.error("addLACategory : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-la-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateLandAcquisitionCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-category");
			boolean flag =  service.updateLandAcquisitionCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating LA Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating LA Category is failed. Try again.");
			logger.error("updateLandAcquisitionCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-la-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteLandAcquisitionCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-category");
			boolean flag =  service.deleteLandAcquisitionCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteLandAcquisitionCategory : " + e.getMessage());
		}
		return model;
	}
	
}







