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
import com.synergizglobal.pmis.reference.Iservice.RRBsesFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RRBsesFileTypeController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRBsesFileTypeController.class);
	
	@Autowired
	RRBsesFileTypeService service;
	
	@RequestMapping(value="/bses-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView bsesFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrBsesFileType);
		try {
			TrainingType bsesFileType = service.getRRBsesFileTypeList(obj);
			model.addObject("bsesFileType", bsesFileType);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("bsesFileType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-bses-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRBsesFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/bses-file-type");
			boolean flag =  service.addRRBsesFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Bses File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Bses File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Bses File Type is failed. Try again.");
			logger.error("addRRBsesFileType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-bses-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRBsesFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/bses-file-type");
			boolean flag =  service.updateRRBsesFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Bses File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Bses File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Bses File Type is failed. Try again.");
			logger.error("updateRRBsesFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-bses-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRBsesFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/bses-file-type");
			boolean flag =  service.deleteRRBsesFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Bses File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRBsesFileType : " + e.getMessage());
		}
		return model;
	}
	
}


