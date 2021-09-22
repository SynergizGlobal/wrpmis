package com.synergizglobal.pmis.webview.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewWorkController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewWorkController.class);
	
	@Autowired
	WorkService workService;
	
	@Autowired
	HomeService homeService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${record.dataexport.success}")
	public String dataExportSucess;
	
	@Value("${record.dataexport.invalid.directory}")
	public String dataExportInvalid;
	
	@Value("${record.dataexport.error}")
	public String dataExportError;
	
	@Value("${record.dataexport.nodata}")
	public String dataExportNoData;
	
	
	@RequestMapping(value="/work",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Work(HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.workGrid);
		try {
			List<Work> workList = workService.getWorkList(null);
			model.addObject("workList", workList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksList(@ModelAttribute Work obj) {
		List<Work> worksList = null;
		try {
			worksList = workService.getWorksList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getWorkStatusList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorkStatusList(@ModelAttribute Work obj) {
		List<Work> workStatusList = null;
		try {
			workStatusList = workService.getWorkStatusList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusList : " + e.getMessage());
		}
		return workStatusList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInWork", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getProjectsList(@ModelAttribute Work obj) {
		List<Work> projectsList = null;
		try {
			projectsList = workService.getWorktProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/get-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkDetails(@ModelAttribute Work work,Railway rail){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName(MobilePageConstants2.workForm);
			model.addObject("action", "edit");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);
			
			List<Work> unitsList = workService.getUnitsList();
			model.addObject("unitsList", unitsList);
			
			workId= work.getWork_id();
			Work workDetails = workService.getWork(workId, work);
			model.addObject("workDetails", workDetails);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWorkForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.workForm);
			model.addObject("action", "add");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			List<Work> unitsList = workService.getUnitsList();
			model.addObject("unitsList", unitsList);
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);

		}catch (Exception e) {
				logger.error("Work : " + e.getMessage());
			}
			return model;
	 }
	
	
	@RequestMapping(value = "/update-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mobileappwebview/work");
			
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			/*
			 * MultipartFile file = work.getWorkFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.WORK_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); work.setAttachment(fileName); }
			 */			
			boolean flag =  workService.updateWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWork(@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mobileappwebview/work");
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			/*MultipartFile file = work.getWorkFile();
			
			 * if (null != file && !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.WORK_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); work.setAttachment(fileName); }
			 */
			boolean flag =  workService.addWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			logger.error("addWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteRow", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteRow(@ModelAttribute Work work){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName("redirect:/mobileappwebview/work");
			workId = work.getWork_id();
			boolean flag =  workService.deleteRow(workId,work);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	
	}
}
	
	
	
	
	
	
	
	
	