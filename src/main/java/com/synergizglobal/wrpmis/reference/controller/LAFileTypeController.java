package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.reference.Iservice.LAFileTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class LAFileTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LAFileTypeController.class);
	
	@Autowired
	LAFileTypeService service;
	
	@RequestMapping(value="/la-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView laFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.laFileType);
		try {
			
			TrainingType laFileTypeDetails = service.getLAFileTypeDetails(obj);
			model.addObject("laFileTypeDetails",laFileTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("laFileType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-la-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addLAFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-file-type");
			boolean flag =  service.addLAFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding LA File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding LA File Type is failed. Try again.");
			logger.error("addLAFileType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-la-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateLAFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-file-type");
			boolean flag =  service.updateLAFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating LA File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating LA File Type is failed. Try again.");
			logger.error("updateLAFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-la-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteLAFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/la-file-type");
			boolean flag =  service.deleteLAFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "LA File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteLAFileType : " + e.getMessage());
		}
		return model;
	}
	
}



