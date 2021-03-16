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

import com.synergizglobal.pmis.reference.Iservice.ManualFoldersService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class ManualFoldersController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ManualFoldersController.class);
	
	@Autowired
	ManualFoldersService service;
	
	@RequestMapping(value="/manual-folders",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView manualFolders(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.manualFolders);
		try {
			List<Safety>  manualFoldersList = service.getManualFoldersList();
			model.addObject("manualFoldersList",  manualFoldersList);
			TrainingType manualFoldersDetails = service.getManualFoldersDetails(obj);
			model.addObject("manualFoldersDetails",manualFoldersDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(" manualFolders : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-manual-folders", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addManualFolders(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/manual-folders");
			boolean flag =  service.addManualFolders(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Manual Folders Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Manual Folders is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Manual Folders is failed. Try again.");
			logger.error("add ManualFolders : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/update-manual-folders", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateManualFolders(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/manual-folders");
			boolean flag =  service.updateManualFolders(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Manual Folders Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Manual Folders is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Manual Folders is failed. Try again.");
			logger.error("updateManualFolders : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-manual-folders", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteManualFolders(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/manual-folders");
			boolean flag =  service.deleteManualFolders(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Manual Folders Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteManualFolders : " + e.getMessage());
		}
		return model;
	}
	
}






