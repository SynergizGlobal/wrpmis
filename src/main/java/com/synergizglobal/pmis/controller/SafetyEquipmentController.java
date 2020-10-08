package com.synergizglobal.pmis.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.SafetyEquipmentService;
import com.synergizglobal.pmis.model.SafetyEquipment;


@Controller
public class SafetyEquipmentController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyEquipmentController.class);
		
	@Autowired
	SafetyEquipmentService service;
		
	@RequestMapping(value="/safetyEquipment",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyEquipment(HttpSession session){
	//	ModelAndView model = new ModelAndView(PageConstants.contractorGrid);
		try {
			//List<SafetyEquipment> safetyEquipmentList = service.getSafetyEquipmentListList();
			//model.addObject("safetyEquipmentList", safetyEquipmentList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("SafetyEquipment : " + e.getMessage());
		}
	//	return model;
		return null;
	}
	

}
