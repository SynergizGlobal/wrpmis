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

import com.synergizglobal.pmis.reference.Iservice.UtilityTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class UtilityTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityTypeController.class);
	
	@Autowired
	 UtilityTypeService service;
	
	@RequestMapping(value="/utility-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView utilityType(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.utilityType);
		try {
			List<TrainingType> utilityTypeList = service.getUtilityTypesList();
			model.addObject("utilityTypeList", utilityTypeList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("utilityType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-utility-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUtilityType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/utility-type");
			boolean flag =  service.addUtilityType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Utility Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Utility Type is failed. Try again.");
			logger.error("addUtilityType : " + e.getMessage());
		}
		return model;
	}
	
}


