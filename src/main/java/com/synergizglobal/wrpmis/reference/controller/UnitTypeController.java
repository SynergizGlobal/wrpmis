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

import com.synergizglobal.wrpmis.reference.Iservice.UnitTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class UnitTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UnitTypeController.class);
	
	@Autowired
	UnitTypeService service;
	
	@RequestMapping(value="/unit-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView unitType(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.unitType);
		try {
			List<TrainingType> unitType = service.getUnitTypesList();
			model.addObject("unitType", unitType);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("unitType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-unit-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addUnitType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/unit-type");
			boolean flag =  service.addUnitType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Unit Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Unit Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Unit Type is failed. Try again.");
			logger.error("addUnitType : " + e.getMessage());
		}
		return model;
	}
	
}

