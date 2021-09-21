package com.synergizglobal.pmis.webview.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.ProjectService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.ContractorPaginationObject;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.ProjectPaginationObject;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.Year;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewProjectController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

	Logger logger = Logger.getLogger(WebviewProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
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
	
	@RequestMapping(value="/project",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Project(HttpSession session,@ModelAttribute Project project){
		ModelAndView model = new ModelAndView(MobilePageConstants2.projectGrid);
		try {
			List<Project> projectList = projectService.getProjectList(project);
			model.addObject("projectList", projectList);	
		}catch (Exception e) {
			e.printStackTrace();
			
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	
	/**
	public int getTotalRecords(Project obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = projectService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}/**

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	/**
	public List<Project> createPaginationData(int startIndex, int offset,Project obj, String searchParameter) {
		List<Project> objList = null;
		try {
			objList = projectService.getProjectsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}*/
	@RequestMapping(value = "/get-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getProjectDetails(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName(MobilePageConstants2.projectForm);
			model.addObject("action", "edit");
			projectId= project.getProject_id();
			Project projectDetails = projectService.getProject(projectId, project);
			model.addObject("projectDetails", projectDetails);
			
			/*List <Project> fileNames = projectService.getFileNames(projectId);
			model.addObject("fileNames", fileNames);*/	
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
		}catch (Exception e) {
			logger.error("Project : " + e.getMessage());
		}
		return model;

	}
	
	@RequestMapping(value = "/update-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateProject(@ModelAttribute Project project,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			project.setCreated_by(userName);
			model.setViewName("redirect:/mobileappwebview/project");
			/*
			 * MultipartFile file = project.getProjectFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.PROJECT_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); project.setAttachment(fileName); }
			 */	
			boolean flag =  projectService.updateProject(project);
			if(flag == true) {
				attributes.addFlashAttribute("success", "Project Updated Succesfully.");

			}
			else {
				attributes.addFlashAttribute("error","Updating Project is failed. Try again.");

			}
		
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Project is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-project-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProjectForm(){
		ModelAndView model = new ModelAndView();
		
		try{
			model.setViewName(MobilePageConstants2.projectForm);
			model.addObject("action", "add");
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
		}catch (Exception e) {
				logger.error("Work : " + e.getMessage());
			}
			return model;
	 }
	
	@RequestMapping(value = "/add-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProject(@ModelAttribute  Project project,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			project.setCreated_by(userName);
			model.setViewName("redirect:/mobileappwebview/project");
			/*
			 * MultipartFile file = project.getProjectFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.PROJECT_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); project.setAttachment(fileName); }
			*/	
			boolean flag =  projectService.addProject(project);
			if(flag == true) {
				attributes.addFlashAttribute("success", "Project Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Project is failed. Try again.");

			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Project is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	
	}
	
	@RequestMapping(value = "/delete-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteProjectRow(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName("redirect:/mobileappwebview/project");
			projectId= project.getProject_id();
			boolean flag =  projectService.deleteProject(projectId,project);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	
	}
		
}
