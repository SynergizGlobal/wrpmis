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

import com.synergizglobal.pmis.reference.Iservice.PMISSubCategoryService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class PMISSubCategoryController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(PMISSubCategoryController.class);
	
	@Autowired
	PMISSubCategoryService service;
	
	@RequestMapping(value="/pmis-sub-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.pmisSubCategory);
		try {
			List<Safety> subCategory = service.getSubCategoryList();
			model.addObject("subCategory", subCategory);
			TrainingType pmisSubCategoryDetails = service.getPmisSubCategoryDetails(obj);
			model.addObject("pmisSubCategoryDetails",pmisSubCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("subCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-pmis-sub-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addSubCategory(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/pmis-sub-category");
			boolean flag =  service.addSubCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "PMIS Sub-Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding PMIS Sub-Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding PMIS Sub-Category is failed. Try again.");
			logger.error("addSubCategory : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-pmis-sub-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updatePmisSubCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/pmis-sub-category");
			boolean flag =  service.updatePmisSubCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "PMIS Sub-Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating PMIS Sub-Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating PMIS Sub-Category is failed. Try again.");
			logger.error("updatePmisSubCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-pmis-sub-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deletePmisSubCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/pmis-sub-category");
			boolean flag =  service.deletePmisSubCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "PMIS Sub-Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deletePmisSubCategory : " + e.getMessage());
		}
		return model;
	}
	
}








