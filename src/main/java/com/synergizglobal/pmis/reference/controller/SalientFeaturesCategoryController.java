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

import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.WorkFeatures;
import com.synergizglobal.pmis.reference.Iservice.SalientFeaturesCategoryService;

@Controller
public class SalientFeaturesCategoryController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SalientFeaturesCategoryController.class);
	
	@Autowired
	SalientFeaturesCategoryService service;
	
	@RequestMapping(value="/salient-features-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView category(HttpSession session,@ModelAttribute WorkFeatures obj){
		ModelAndView model = new ModelAndView(PageConstants2.salientFeaturesCategory);
		try {
			WorkFeatures categoryDetails = service.getCategoryDetails(obj);
			model.addObject("categoryDetails",categoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("category :" + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-salient-features-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addCategory(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/salient-features-category");
			boolean flag =  service.addCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Category is failed. Try again.");
			logger.error("addCategory : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-salient-features-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateCategory(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/salient-features-category");
			boolean flag =  service.updateCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Category is failed. Try again.");
			logger.error("updateCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-salient-features-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteCategory(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/salient-features-category");
			boolean flag =  service.deleteCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Category deleted succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteCategory : " + e.getMessage());
		}
		return model;
	}
}
