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

import com.synergizglobal.wrpmis.reference.Iservice.TrainingCategoryService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class TrainingCategoryController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TrainingCategoryController.class);
	
	@Autowired
	TrainingCategoryService service;
	
	@RequestMapping(value="/training-category",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView trainingCategory(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.trainingCategory);
		try {
			List<TrainingType> trainingCategory = service.getTrainingCategoriesList();
			model.addObject("trainingCategory", trainingCategory);
			TrainingType trainingCategoryDetails = service.getTrainingCategoriesDetails(obj);
			model.addObject("trainingCategoryDetails",trainingCategoryDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("trainingCategory : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-training-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTrainingCategory(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-category");
			boolean flag =  service.addTrainingCategory(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Category Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Training Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Training Category is failed. Try again.");
			logger.error("addTrainingCategory : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-training-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTrainingCategories(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-category");
			boolean flag =  service.updateTrainingCategories(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Category Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Training Category is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Training Category is failed. Try again.");
			logger.error("updateTrainingCategories : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-training-category", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTrainingCategories(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/training-category");
			boolean flag =  service.deleteTrainingCategories(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Category Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteTrainingCategories : " + e.getMessage());
		}
		return model;
	}
	
}


