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
import com.synergizglobal.pmis.reference.Iservice.StructureFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class StructureFileTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(StructureFileTypeController.class);
	
	@Autowired
	StructureFileTypeService service;
	
	@RequestMapping(value="/structure-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView StructureFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.structureFileType);
		try {
			
			List<TrainingType> StructureFileType = service.getStructureFileType(obj);
			model.addObject("StructureFileType",StructureFileType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("StructureFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-structure-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addStructureFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-file-type");
			boolean flag =  service.addStructureFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "structure File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding structure File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding structure File Type is failed. Try again.");
			logger.error("addStructureFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-structure-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateStructureFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-file-type");
			boolean flag =  service.updateStructureFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "structure File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating structure File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating structure File Type is failed. Try again.");
			logger.error("updateStructureFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-structure-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteStructureFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-file-type");
			boolean flag =  service.deleteStructureFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "structure File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteStructureFileType : " + e.getMessage());
		}
		return model;
	}
	
	
}
