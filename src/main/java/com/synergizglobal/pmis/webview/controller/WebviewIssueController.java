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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.controller.IssueController;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Project;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewIssueController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(IssueController.class);	
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	StripChartService stripChartService;
	
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
	public List<Issue> getWorksListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getContractsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getDepartmentsListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getCategoryListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getCategoryListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getResponsiblePersonsListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getResponsiblePersonsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getStatusListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesList(@ModelAttribute Issue obj) {
		List<Issue> issues = null;
		try {
			issues = issueService.getIssuesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssuesList : " + e.getMessage());
		}
		return issues;
	}
	
	@RequestMapping(value="/add-issue-form",method=RequestMethod.GET)
	public ModelAndView addIssueForm(HttpSession session,@ModelAttribute Issue obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.issueForm);
			model.addObject("action", "add");
			
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
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
		} catch (Exception e) {
			logger.error("addIssueForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getProjectsListForIssueForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getProjectsListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getWorkListForIssueForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getWorkListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListForIssueForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getContractsListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-issue",method=RequestMethod.POST)
	public ModelAndView addIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/mobileappwebview/issues");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));			
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			
			boolean flag = issueService.addIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding issue is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("addIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-issue",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.issueForm);
			model.addObject("action", "edit");
			
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
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
			Issue issue = issueService.getIssue(obj);
			model.addObject("issue", issue);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-issue/{issue_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getIssueByIssueId(@ModelAttribute Issue obj,@PathVariable("issue_id") String issue_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.issueForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			obj.setIssue_id(issue_id);
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
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			
			boolean flag = issueService.updateIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating issue is failed. Try again.");
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
