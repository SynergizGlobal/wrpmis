package com.synergizglobal.wrpmis.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.wrpmis.Iservice.StructureGalleryPageService;
import com.synergizglobal.wrpmis.Iservice.WbsTreeService;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Structure;
import com.synergizglobal.wrpmis.model.WbsTree;

@Controller
public class WbsTreeController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public static Logger logger = Logger.getLogger(WbsTreeController.class);

	@Autowired
	WbsTreeService service;
	@Autowired
	StructureGalleryPageService service1;
	@RequestMapping(value="/wbs-tree",method={RequestMethod.GET})
	public ModelAndView wbsTree(HttpSession session,@ModelAttribute WbsTree obj){
		ModelAndView model = new ModelAndView(PageConstants.wbsTree);
		try {
			List<WbsTree> level4List = service.getLevel4List(obj);
			model.addObject("level4List", level4List);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("wbsTree : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/wbs-tree/{work_id}",method={RequestMethod.GET})
	public ModelAndView wbsTreeWithWork(HttpSession session,@ModelAttribute WbsTree obj,Structure obj1,@PathVariable("work_id") String work_id){
		ModelAndView model = new ModelAndView(PageConstants.wbsTree);
		try {
			List<WbsTree> level4List = service.getLevel4List(obj);
			model.addObject("level4List", level4List);
			
			List<WbsTree> contractsList = service.getContractsList(obj);
			model.addObject("contractsList", contractsList);
			
			obj1.setWork_id(work_id);
			Structure work = service1.getWorkShortName(obj1);
			model.addObject("work", work);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("wbsTreeWithWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWbslevelsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WbsTree> getWbsActivities(@ModelAttribute WbsTree obj){
		List<WbsTree> activities = null;
		try{
			activities = service.getLevel4List(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return activities;
	}
}
