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

import com.synergizglobal.pmis.reference.Iservice.P6WbsCategoryService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class P6wbsCategoryController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(P6wbsCategoryController.class);
	
	@Autowired
	P6WbsCategoryService service;
	
	@RequestMapping(value="/p6wbs-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView p6wbsCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.p6wbsCategory);
		try {
			List<Safety> p6wbsCategory = service.getP6WbsCategoryList();
			model.addObject("p6wbsCategory", p6wbsCategory);
			TrainingType p6WbsCategoryDetails = service.getP6WbsCategoryDetails(obj);
			model.addObject("p6WbsCategoryDetails",p6WbsCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("p6wbsCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-p6wbs-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addP6WbsCategory(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/p6wbs-category");
			boolean flag =  service.addP6WbsCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "P6Wbs Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding P6Wbs Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding P6Wbs Category is failed. Try again.");
			logger.error("addP6WbsCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-p6wbs-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateP6WbsCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/p6wbs-category");
			boolean flag =  service.updateP6WbsCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "P6WbsCategory Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating P6WbsCategory is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating P6WbsCategory is failed. Try again.");
			logger.error("updateP6WbsCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-p6wbs-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteP6WbsCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/p6wbs-category");
			boolean flag =  service.deleteP6WbsCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "P6WbsCategory Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteP6WbsCategory : " + e.getMessage());
		}
		return model;
	}
	
}



