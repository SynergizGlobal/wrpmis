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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.ContarctFileTypeService;
import com.synergizglobal.pmis.reference.Iservice.FOBFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class FOBFileTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FOBFileTypeController.class);
	
	@Autowired
	FOBFileTypeService service;
	
	@RequestMapping(value="/fob-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView fobFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.fobFileType);
		try {
			
			List<TrainingType> fobFileType = service.getfobFileType(obj);
			model.addObject("fobFileType",fobFileType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("fobFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-fob-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addfobFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/fob-file-type");
			boolean flag =  service.addFOBFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding FOB File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding FOB File Type is failed. Try again.");
			logger.error("addFOBFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-fob-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateFOBFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/fob-file-type");
			boolean flag =  service.updateFOBFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating FOB File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating FOB File Type is failed. Try again.");
			logger.error("updateFOBFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-fob-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteFOBFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/fob-file-type");
			boolean flag =  service.deleteFOBFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteFOBFileType : " + e.getMessage());
		}
		return model;
	}
	
	
}
