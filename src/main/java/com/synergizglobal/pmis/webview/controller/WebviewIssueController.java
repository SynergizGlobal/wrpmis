package com.synergizglobal.pmis.webview.controller;

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
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.controller.IssueController;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.User;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewIssueController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewIssueController.class);	
	
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
			model.setViewName(MobilePageConstants2.issuesGrid);			
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
			model.setViewName(MobilePageConstants2.addIssueForm);
			
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
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/mobileappwebview/issues");
			user_Id = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
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
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/mobileappwebview/issues");
			user_Id = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
			User user = (User)session.getAttribute("user");
			if(!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getEmail_id())) {
				obj.setReported_by_email_id(user.getEmail_id());
			}
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			obj.setAssigned_date(DateParser.parse(obj.getAssigned_date()));
			obj.setCreated_by_user_id_fk(user_Id);
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
			model.setViewName("redirect:/mobileappwebview/issues");
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
	
	
}