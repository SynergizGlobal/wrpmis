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

import com.synergizglobal.wrpmis.reference.Iservice.ContractTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class ContractTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ContractTypeController.class);
	
	@Autowired
	ContractTypeService service;
	
	@RequestMapping(value="/contract-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView contractType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.contractType);
		try {
			List<TrainingType> contractTypeList = service.getContractTypesList();
			model.addObject("contractTypeList", contractTypeList);
			TrainingType contractTypeDetails = service.getContractTypeDetails(obj);
			model.addObject("contractTypeDetails", contractTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("contractType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-contract-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addContractType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract-type");
			boolean flag =  service.addContractType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Contract Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contract Type is failed. Try again.");
			logger.error("addContractType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contract-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateContractType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract-type");
			boolean flag =  service.updateContractType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Contract Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Contract Type is failed. Try again.");
			logger.error("updateContractType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-contract-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteContractType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract-type");
			boolean flag =  service.deleteContractType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteContractType : " + e.getMessage());
		}
		return model;
	}
	
}






