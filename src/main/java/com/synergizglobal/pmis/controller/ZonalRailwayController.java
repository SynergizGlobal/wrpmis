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

import com.synergizglobal.pmis.Iservice.ZonalRailwayService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
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
	
}
