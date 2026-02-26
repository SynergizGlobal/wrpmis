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

import com.synergizglobal.wrpmis.reference.Iservice.DivisionService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class DivisionController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DivisionController.class);
	
	@Autowired
	DivisionService service;
	
	@RequestMapping(value="/division",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView division(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.division);
		try {
			List<TrainingType> divisionList = service.getDivisionsList();
			model.addObject("DivisionList", divisionList);
			TrainingType divisionDetails = service.getDivisionDetails(obj);
			model.addObject("DivisionDetails", divisionDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Division : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-division", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView adddivision(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/division");
			boolean flag =  service.addDivision(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Division Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding division is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding division is failed. Try again.");
			logger.error("adddivision : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-division", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updatedivision(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/division");
			boolean flag =  service.updateDivision(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Division Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating division is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating division is failed. Try again.");
			logger.error("updatedivision : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-division", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deletedivision(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/division");
			boolean flag =  service.deleteDivision(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Division Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deletedivision : " + e.getMessage());
		}
		return model;
	}
	
}



