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
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class ContractFileTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ContractFileTypeController.class);
	
	@Autowired
	ContarctFileTypeService service;
	
	@RequestMapping(value="/contract-file-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView contractFileType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.contractFileType);
		try {
			
			List<TrainingType> contractFileType = service.getcontractFileType(obj);
			model.addObject("contractFileType",contractFileType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("contractFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-contract-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addcontractFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract-file-type");
			boolean flag =  service.addContractFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract File Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Contract File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contract File Type is failed. Try again.");
			logger.error("addContractFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contract-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateContractFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract-file-type");
			boolean flag =  service.updateContractFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract File Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Contract File Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Contract File Type is failed. Try again.");
			logger.error("updateContractFileType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-contract-file-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteContractFileType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract-file-type");
			boolean flag =  service.deleteContractFileType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract File Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteContractFileType : " + e.getMessage());
		}
		return model;
	}
	
	
}
