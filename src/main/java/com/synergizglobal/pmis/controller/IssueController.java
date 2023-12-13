package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.User;

@Controller
public class IssueController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(IssueController.class);	
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	ActivitiesService activitiesService;
	
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
	
	@RequestMapping(value="/issues",method=RequestMethod.GET)
	public ModelAndView issues(@ModelAttribute Issue obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.issuesGrid);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("issues : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getWorksListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getWorksListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getContractsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getDepartmentsListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getCategoryListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getCategoryListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getResponsiblePersonsListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getResponsiblePersonsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getStatusListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getHODListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getHODListFilterInIssue(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = issueService.getHODListFilterInIssue(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesList(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> issues = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			issues = issueService.getIssuesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssuesList : " + e.getMessage());
		}
		return issues;
	}
	
	@RequestMapping(value="/add-issue-form",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addIssueForm(HttpSession session,@ModelAttribute Issue obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addIssueForm);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<Issue> projectsList = issueService.getProjectsListForIssueForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Issue> worksList = issueService.getWorkListForIssueForm(obj);
			model.addObject("worksList", worksList);
			
			List<Issue> contractsList = issueService.getContractsListForIssueForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList(obj);
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> issueTitlesList = issueService.getIssueTitlesList(obj);
			model.addObject("issueTitlesList", issueTitlesList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
			List<Issue> reportedByList = issueService.getReportedByList();
			model.addObject("reportedByList", reportedByList);
			
			List<Issue> responsiblePersonList = issueService.getResponsiblePersonList(null);
			model.addObject("responsiblePersonList", responsiblePersonList);
			
			List<Issue> escalatedToList = issueService.getEscalatedToList();
			model.addObject("escalatedToList", escalatedToList);
			
			List<Issue> otherOrganizations = issueService.getOtherOrganizationsList();
			model.addObject("otherOrganizations", otherOrganizations);
			
			List<Issue> issueFileTypes = issueService.getIssueFileTypes();
			model.addObject("issueFileTypes", issueFileTypes);
			
			
			List<Issue> structures = issueService.getStructures(obj);
			model.addObject("structures", structures);
			
			
			List<Issue> components = issueService.getComponents(obj);
			model.addObject("components", components);			
			
			
			model.addObject("iObj", obj);
			
		} catch (Exception e) {
			logger.error("addIssueForm : " + e.getMessage());
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "/ajax/getIssueTitlesListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssueTitlesListForIssuesForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getIssueTitlesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssueTitlesListForIssuesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getStructureListForIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getStructureListForIssue(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getStructureListForIssue(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureListForIssue : " + e.getMessage());
		}
		return objsList;
	}	
	
	
	@RequestMapping(value = "/ajax/getComponentListForIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getComponentListForIssue(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getComponentListForIssue(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getComponentListForIssue : " + e.getMessage());
		}
		return objsList;
	}	
	
	@RequestMapping(value = "/ajax/getIssueCategoryListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssueCategoryListForIssuesForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getIssuesCategoryList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssueCategoryListForIssuesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getProjectsListForIssueForm(HttpSession session,@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objsList = issueService.getProjectsListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getWorkListForIssueForm(HttpSession session,@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objsList = issueService.getWorkListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListForIssueForm(HttpSession session,@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objsList = issueService.getContractsListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getIssueStatusListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssueStatusListForIssuesForm() {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getIssuesStatusList();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssueStatusListForIssuesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-issue",method=RequestMethod.POST)
	public ModelAndView addIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/issues");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			User user = (User)session.getAttribute("user");
			if(!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getEmail_id())) {
				obj.setReported_by_email_id(user.getEmail_id());
			}
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));			
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			obj.setAssigned_date(DateParser.parse(obj.getAssigned_date()));
			if(!StringUtils.isEmpty(obj.getZonal_railway_fk()) && obj.getZonal_railway_fk().equals("MRVC")) {
				obj.setOther_organization(obj.getZonal_railway_fk() + " - " + obj.getOther_organization());
			}
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			obj.setStatus_fk(CommonConstants.ISSUE_STATUS_RAISED);
			
			boolean flag = issueService.addIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue "+obj.getStatus_fk()+" successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding issue failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-issue",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateIssueForm);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<Issue> projectsList = issueService.getProjectsListForIssueForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Issue> worksList = issueService.getWorkListForIssueForm(obj);
			model.addObject("worksList", worksList);
			
			List<Issue> contractsList = issueService.getContractsListForIssueForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList(obj);
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> issueTitlesList = issueService.getIssueTitlesList(obj);
			model.addObject("issueTitlesList", issueTitlesList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
			List<Issue> reportedByList = issueService.getReportedByList();
			model.addObject("reportedByList", reportedByList);
			
			List<Issue> responsiblePersonList = issueService.getResponsiblePersonList(null);
			model.addObject("responsiblePersonList", responsiblePersonList);
			
			List<Issue> escalatedToList = issueService.getEscalatedToList();
			model.addObject("escalatedToList", escalatedToList);
			
			List<Issue> otherOrganizations = issueService.getOtherOrganizationsList();
			model.addObject("otherOrganizations", otherOrganizations);
			
			List<Issue> issueFileTypes = issueService.getIssueFileTypes();
			model.addObject("issueFileTypes", issueFileTypes);
			
			List<Issue> structures = issueService.getStructures(obj);
			model.addObject("structures", structures);
			
			
			List<Issue> components = issueService.getComponents(obj);
			model.addObject("components", components);				
			
			
			Issue issue = issueService.getIssue(obj);
			model.addObject("issue", issue);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getIssue : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getResponsiblePersons(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> objList = null;
		try {
			objList = issueService.getResponsiblePersonList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonsInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value="/get-issue/{issue_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getIssueByIssueId(@ModelAttribute Issue obj,@PathVariable("issue_id") String issue_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateIssueForm);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<Issue> projectsList = issueService.getProjectsListForIssueForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Issue> worksList = issueService.getWorkListForIssueForm(obj);
			model.addObject("worksList", worksList);
			
			List<Issue> contractsList = issueService.getContractsListForIssueForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList(obj);
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> issueTitlesList = issueService.getIssueTitlesList(obj);
			model.addObject("issueTitlesList", issueTitlesList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
			List<Issue> reportedByList = issueService.getReportedByList();
			model.addObject("reportedByList", reportedByList);
			
			List<Issue> responsiblePersonList = issueService.getResponsiblePersonList(null);
			model.addObject("responsiblePersonList", responsiblePersonList);
			
			List<Issue> escalatedToList = issueService.getEscalatedToList();
			model.addObject("escalatedToList", escalatedToList);
			
			List<Issue> otherOrganizations = issueService.getOtherOrganizationsList();
			model.addObject("otherOrganizations", otherOrganizations);
			
			List<Issue> issueFileTypes = issueService.getIssueFileTypes();
			model.addObject("issueFileTypes", issueFileTypes);
			
			List<Issue> structures = issueService.getStructures(obj);
			model.addObject("structures", structures);
			
			
			List<Issue> components = issueService.getComponents(obj);
			model.addObject("components", components);				
			
			Issue issue = issueService.getIssue(obj);
			model.addObject("issue", issue);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-issue",method=RequestMethod.POST)
	public ModelAndView updateIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/issues");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			User user = (User)session.getAttribute("user");
			if(!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getEmail_id())) {
				obj.setReported_by_email_id(user.getEmail_id());
			}
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			obj.setAssigned_date(DateParser.parse(obj.getAssigned_date()));
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			if(!StringUtils.isEmpty(obj.getZonal_railway_fk()) && obj.getZonal_railway_fk().equals("MRVC")) {
				obj.setOther_organization(obj.getZonal_railway_fk() + " - " + obj.getOther_organization());
			}
			
			boolean flag = issueService.updateIssue(obj);
			if(flag) {
				if(!StringUtils.isEmpty(obj.getStatus_fk()) && obj.getStatus_fk().equals(obj.getExisting_status_fk())) {
					attributes.addFlashAttribute("success", "Issue has been updated successfully");
				}else{
					attributes.addFlashAttribute("success", "Issue "+obj.getStatus_fk()+" successfully");
				}
			}else {
				attributes.addFlashAttribute("error", "Updating issue failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-issue",method=RequestMethod.POST)
	public ModelAndView deleteIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/issues");
			boolean flag = issueService.deleteIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting issue is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("deleteIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-issues", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportIssues(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Issue issue,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.issuesGrid);
		List<Issue> dataList = new ArrayList<Issue>();
		String userId = null;String userName = null;
		String user_role_code = null;String user_type = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			user_role_code = (String) session.getAttribute("USER_ROLE_CODE");user_type = (String) session.getAttribute("USER_TYPE");
			issue.setUser_id(userId);
			issue.setUser_role_code(user_role_code);
			issue.setUser_type(user_type);
			
			view.setViewName("redirect:/issues");
			dataList = issueService.getIssuesList(issue);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Issue"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
		        
	            XSSFRow headingRow = sheet.createRow(0);
	            String headerString = "Issue ID^Project^Work^Contract^Short Description^Issue Pending Since^Location/Station/KM^Latitude^Longitude"
	            		+ "^Reported By^Raised On^Responsible Person^Assigned Date^Issue Category^Issue Status^Responsible Organization^Priority^Issue/Action Taken/Remarks^"
	            		+ "Escalated to^Escalation Date^Status After Escalation^Resolved Date^Description";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
	            short rowNo = 1;
	            for (Issue obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                int c = 0;
	                
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIssue_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProject_id_fk() +"-"+obj.getProject_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_id_fk()+"-"+obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_id_fk()+"-"+obj.getContract_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTitle());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDate());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLocation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLatitude());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLongitude());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getReported_by());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCreated_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getResponsible_person_designation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAssigned_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCategory_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStatus_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getOther_organization());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPriority_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCorrective_measure());
					

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getEscalated_to_designation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getEscalation_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getResolved_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDescription());
	                
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Issues_"+dateFormat.format(date);
                
	            try{
	                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
	                workBook.write(fos);
	                fos.flush();*/
	            	
	               response.setContentType("application/.csv");
	 			   response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	 			   response.setContentType("application/vnd.ms-excel");
	 			   // add response header
	 			   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".xlsx");
	 			   
	 			    //copies all bytes from a file to an output stream
	 			   workBook.write(response.getOutputStream()); // Write workbook to response.
		           workBook.close();
	 			    //flushes output stream
	 			    response.getOutputStream().flush();
	            	
	                
	                attributes.addFlashAttribute("success",dataExportSucess);
	            	//response.setContentType("application/vnd.ms-excel");
	            	//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
	            	//XSSFWorkbook  workbook = new XSSFWorkbook ();
	            	// ...
	            	// Now populate workbook the usual way.
	            	// ...
	            	//workbook.write(response.getOutputStream()); // Write workbook to response.
	            	//workbook.close();
	            }catch(FileNotFoundException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportInvalid);
	            }catch(IOException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportError);
	            }
	         }else{
	        	 attributes.addFlashAttribute("error",dataExportNoData);
	         }
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportIssues : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}

	private CellStyle cellFormating(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
		CellStyle style = workBook.createCellStyle();
		//Setting Background color  
		//style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (style instanceof XSSFCellStyle) {
		   XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle)style;
		   xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		//style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);
		
		Font font = workBook.createFont();
        //font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontHeightInPoints((short)fontSize);  
        font.setFontName(fontName);  //"Times New Roman"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
	
	
}
