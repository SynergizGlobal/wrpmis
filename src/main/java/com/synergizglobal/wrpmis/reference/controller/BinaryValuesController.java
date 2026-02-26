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

import com.synergizglobal.wrpmis.reference.Iservice.BinaryValuesService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class BinaryValuesController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(BinaryValuesController.class);
	
	@Autowired
	BinaryValuesService service;
	
	@RequestMapping(value="/binary-values",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView binaryValues(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.binaryValues);
		try {
			List<TrainingType> binaryValuesList = service.getBinaryValuesList();
			model.addObject("binaryValuesList", binaryValuesList);
			TrainingType binaryValueDetails = service.getBinaryValueDetails(obj);
			model.addObject("binaryValueDetails", binaryValueDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("binaryValues : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-binary-values", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addBinaryValues(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/binary-values");
			boolean flag =  service.addBinaryValues(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Binary Values Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Binary Values is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Binary Values is failed. Try again.");
			logger.error("addBinaryValues : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-binary-values", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateBinaryValues(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/binary-values");
			boolean flag =  service.updateBinaryValues(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Binary Values Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Binary Values is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Binary Values is failed. Try again.");
			logger.error("updateBinaryValues : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-binary-values", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteBinaryValues(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/binary-values");
			boolean flag =  service.deleteBinaryValues(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Binary Values Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteBinaryValues : " + e.getMessage());
		}
		return model;
	}
}

