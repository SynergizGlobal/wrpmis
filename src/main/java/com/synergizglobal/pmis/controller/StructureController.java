package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.synergizglobal.pmis.Iservice.StructureService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Structure;

@Controller
public class StructureController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(StructureController.class);

	@Autowired
	StructureService structureService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/structure",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView design(@ModelAttribute Design obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.structureGrid);		
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("structure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getProjectsListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getProjectsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getWorksListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getContractsListFilterInIssue(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getDepartmentsListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
		
	@RequestMapping(value = "/add-structure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addStructureForm(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditStructure);	
			model.addObject("action", "add");			
			
		}catch (Exception e) {
			logger.error("addStructureForm : " + e.getMessage());
		}
		return model;
	}	
	@RequestMapping(value = "/update-structure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateStructureForm(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditStructure);	
			model.addObject("action", "edit");			
			
		}catch (Exception e) {
			logger.error("updateStructureForm : " + e.getMessage());
		}
		return model;
	}	
	
	
}