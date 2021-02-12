package com.synergizglobal.pmis.controller;

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

import com.synergizglobal.pmis.Iservice.ZonalRailwayService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.ZonalRailway;

@Controller
public class ZonalRailwayController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ZonalRailwayController.class);
	
	@Autowired
	ZonalRailwayService service;
	
	@RequestMapping(value="/zonal-railway",method={RequestMethod.GET})
	public ModelAndView ZonalRailway(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.zonalRailwayGrid);
		try {
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("ZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-zonal-railway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getZonalRailwayList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> zonalRailwayList = null;
		try {
			zonalRailwayList = service.getZonalRailwayList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getZonalRailwayList : " + e.getMessage());
		}
		return zonalRailwayList;
	}
	@RequestMapping(value = "/ajax/getWorksFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getWorksList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getProjectsList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getExecutionAgencyRailwayFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getExecutionAgencyRailwayList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayExecutionAgencyRailwayList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutionAgencyRailwayList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getSourceOfFundsFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getSourceOfFundsList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwaySourceOfFundsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSourceOfFundsList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getStatusList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForZonalRailwayForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getWorkListForZonalRailwayForm(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objsList = null;
		try {
			objsList = service.getWorkListForZonalRailwayForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForZonalRailwayForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getRailwayListForZonalRailwayForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getRailwayListForZonalRailwaysForm(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objsList = null;
		try {
			objsList = service.getRailwayListForZonalRailwaysForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRailwayListForZonalRailwaysForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-zonal-railway-form", method = {RequestMethod.GET})
	public ModelAndView addZonalRailwaytForm(@ModelAttribute ZonalRailway obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "add");
			
			List<ZonalRailway> projectsList = service.getProjectsListForZonalRailwayForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<ZonalRailway> worksList = service.getWorkListForZonalRailwayForm(obj);
			model.addObject("worksList", worksList);
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
			
		}catch (Exception e) {
				logger.error("addZonalRailwaytForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-zonal-railway", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getZonalRailwayForm(@ModelAttribute ZonalRailway obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "edit");
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			ZonalRailway zonalRailwayDetails = service.getZonalRailway(obj);
			model.addObject("zonalRailwayDetails", zonalRailwayDetails);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getZonalRailwayForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-zonal-railway", method = {RequestMethod.POST})
	public ModelAndView addZonalRailway(@ModelAttribute ZonalRailway obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway");
			obj.setAs_on_date(DateParser.parse(obj.getAs_on_date()));
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setExpected_finish(DateParser.parse(obj.getExpected_finish()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.addZonalRailway(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Zonal Railway is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Zonal Railway is failed. Try again.");
			logger.error("addZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-zonal-railway", method = {RequestMethod.POST})
	public ModelAndView updateZonalRailway(@ModelAttribute ZonalRailway obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway");
			obj.setAs_on_date(DateParser.parse(obj.getAs_on_date()));
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setExpected_finish(DateParser.parse(obj.getExpected_finish()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.updateZonalRailway(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Zonal Railway is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Zonal Railway is failed. Try again.");
			logger.error("updateZonalRailway : " + e.getMessage());
		}
		return model;
	}
}
