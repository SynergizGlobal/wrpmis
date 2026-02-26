package com.synergizglobal.wrpmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.reference.Iservice.LACategoryService;
import com.synergizglobal.wrpmis.reference.Iservice.LASubCategoryService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class LASubCategoryController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(BackgroundTypeController.class);
	
	@Autowired
	LASubCategoryService service;
	
	@Autowired
	LACategoryService categoryService;
	
	@RequestMapping(value="/la-sub-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView LASubCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.laSubCategory);
		try {
			List<TrainingType> LACategorysList = categoryService.getLACategoryList();
			model.addObject("LACategorysList", LACategorysList);
			
			List<TrainingType> LASubCategoryList = service.getLASubCategoryList();
			model.addObject("LASubCategoryList", LASubCategoryList);
			TrainingType landAcquisitionSubCategoryDetails = service.getLandAcquisitionSubCategoryDetails(obj);
			model.addObject("landAcquisitionSubCategoryDetails", landAcquisitionSubCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("LASubCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-la-sub-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addLASubCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-sub-category");
			boolean flag =  service.addLASubCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA Sub Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding LA Sub Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding LA Sub Category is failed. Try again.");
			logger.error("addLASubCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getLASubCategory", method = { RequestMethod.GET,RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getLASubCategory(@ModelAttribute TrainingType obj) {
		List<TrainingType> objList = null;
		try {
			objList = service.getLASubCategory(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLASubCategory : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/update-la-sub-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateLandAcquisitionSubCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-sub-category");
			boolean flag =  service.updateLandAcquisitionSubCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA Sub Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating LA Sub Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating LA Sub Category is failed. Try again.");
			logger.error("updateLandAcquisitionSubCategory : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-la-sub-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteLandAcquisitionSubCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-sub-category");
			boolean flag =  service.deleteLandAcquisitionSubCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA Sub Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteLandAcquisitionSubCategory : " + e.getMessage());
		}
		return model;
	}
	
}


