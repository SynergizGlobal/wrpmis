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

import com.synergizglobal.pmis.Iservice.GalleryPageService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Structure;

@Controller
public class GalleryPageController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(GalleryPageController.class);
	
	@Autowired
	GalleryPageService service;
	
	@RequestMapping(value="/gallery-page",method={RequestMethod.GET})
	public ModelAndView galleryPage(HttpSession session,@ModelAttribute Structure obj){
		ModelAndView model = new ModelAndView(PageConstants.galleryPage);
		try {
			List<Structure> dates = service.getMonthList(null);
			model.addObject("dates", dates);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("galleryPage : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getGalleryList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getGalleryList(@ModelAttribute Structure obj) {
		List<Structure> objList = null;
		try {
			objList = service.getGalleryList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getGalleryList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getMonthList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getMonthList(@ModelAttribute Structure obj) {
		List<Structure> objList = null;
		try {
			objList = service.getMonthList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getMonthList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStructuresLists", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getStructuresList(@ModelAttribute Structure obj) {
		List<Structure> objList = null;
		try {
			objList = service.getStructuresList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructuresList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStructureIdList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getStructureIdList(@ModelAttribute Structure obj) {
		List<Structure> objList = null;
		try {
			objList = service.getStructureIdList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureIdList : " + e.getMessage());
		}
		return objList;
	}
}
