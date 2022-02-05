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

import com.synergizglobal.pmis.reference.Iservice.UtilityCategoryService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class UtilityCategoryController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityCategoryController.class);
	
	@Autowired
	UtilityCategoryService service;
	
	@RequestMapping(value="/utility-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView utilityCategory(HttpSession session,@ModelAttribute Safety obj){
		ModelAndView model = new ModelAndView(PageConstants.utilityCategory);
		try {
			Safety  UtilityCategoryDetails = service.getUtilityCategorysList(obj);
			model.addObject("UtilityCategoryDetails",  UtilityCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" utilityCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityCategory(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-category");
			boolean flag =  service.addUtilityCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Utility Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Utility Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Utility Category is failed. Try again.");
			logger.error("add UtilityCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/update-utility-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateUtilityCategory(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-category");
			boolean flag =  service.updateUtilityCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Utility Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Utility Category is failed. Try again.");
			logger.error("updateUtilityCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-utility-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteUtilityCategory(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-category");
			boolean flag =  service.deleteUtilityCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteUtilityCategory : " + e.getMessage());
		}
		return model;
	}	
	
}









