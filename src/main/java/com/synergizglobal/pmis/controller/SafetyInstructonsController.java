package com.synergizglobal.pmis.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.SafetyInstructonsService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SafetyEquipment;

@Controller
public class SafetyInstructonsController {

	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyInstructonsController.class);

	@Autowired
	SafetyInstructonsService service;
	
	@RequestMapping(value="/safety-instructions",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyInstructions(@ModelAttribute SafetyEquipment obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.safetyInstruction);
		try {
			List<SafetyEquipment> safetyInstructionsList = service.getSafetyInstructionsList(obj);
			model.addObject("safetyInstructionsList", safetyInstructionsList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("safetyInstructions : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-safety-instructions", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updatesafetyInstructions(@ModelAttribute SafetyEquipment obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-instructions");
			boolean flag =  service.updateSafetyInstructions(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety Instructions Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Safety Instructions is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Safety Instructions is failed. Try again.");
			logger.error("updateDocument : " + e.getMessage());
		}
		return model;
	}
	
	
}
