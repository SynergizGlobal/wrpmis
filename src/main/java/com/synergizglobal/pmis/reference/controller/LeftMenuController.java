package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
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
import com.synergizglobal.pmis.reference.Iservice.LeftMenuService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class LeftMenuController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LeftMenuController.class);
	
	@Autowired
	LeftMenuService service;
	
	@RequestMapping(value="/left-menu",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView alertLevel(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.leftMenu);
		try {
			List<TrainingType> parentList = service.getParentList();
			model.addObject("parentList", parentList);
			List<TrainingType> statusList = service.getStatusList();
			model.addObject("statusList", statusList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("alertLevel : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getLeftMenuList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getRisksList(@ModelAttribute TrainingType obj) {
		List<TrainingType> dataList = null;  
		try {
			dataList = service.getLeftMenuList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRisksList : " + e.getMessage());
		}
		return dataList;
	}
	@RequestMapping(value = "/ajax/getStatusFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getStatusFilterList(@ModelAttribute TrainingType obj) {
		List<TrainingType> dataList = null;  
		try {
			dataList = service.getStatusFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubWorkFilterList : " + e.getMessage());
		}
		return dataList;
	}
	@RequestMapping(value = "/ajax/getParentFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getParentFilterList(@ModelAttribute TrainingType obj) {
		List<TrainingType> dataList = null;  
		try {
			dataList = service.getParentFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubWorkFilterList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/add-left-menu", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addLeftMenu(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/left-menu");
			boolean flag =  service.addLeftMenu(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Left Menu Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding  Left Menu is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding  Left Menu is failed. Try again.");
			logger.error("addLeftMenu : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-left-menu", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateLeftMenu(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/left-menu");
			boolean flag =  service.updateLeftMenu(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Left Menu Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Left Menu is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Left Menu is failed. Try again.");
			logger.error("updateLeftMenu : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-left-menu", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteLeftMenu(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/left-menu");
			boolean flag =  service.deleteLeftMenu(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Left Menu Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteLeftMenu : " + e.getMessage());
		}
		return model;
	}
}
