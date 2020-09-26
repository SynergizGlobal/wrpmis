package com.synergizglobal.pmis.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Project;

@Controller
public class IssueController {
	Logger logger = Logger.getLogger(IssueController.class);	
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	StripChartService stripChartService;
	
	@Autowired
	HomeService homeService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/issues",method=RequestMethod.GET)
	public ModelAndView issues(@ModelAttribute Issue obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.issuesGrid);
			/*List<Issue> issues = issueService.getIssuesList(obj);
			model.addObject("issues", issues);*/
			List<Contract> contracts = stripChartService.getContractsList(null);
			model.addObject("contracts", contracts);
		} catch (Exception e) {
			logger.info("issues : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getIssuesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesList(@ModelAttribute Issue obj) {
		List<Issue> issues = null;
		try {
			issues = issueService.getIssuesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getIssuesList : " + e.getMessage());
		}
		return issues;
	}
	
	@RequestMapping(value="/add-issue-form",method=RequestMethod.GET)
	public ModelAndView addIssueForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addIssueForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> activityList = issueService.getActivityList();
			model.addObject("activityList", activityList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
		} catch (Exception e) {
			logger.info("addIssueForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-issue",method=RequestMethod.POST)
	public ModelAndView addIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/issues");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			if(!StringUtils.isEmpty(obj.getDate())){
				Date convertedDate = sdf.parse(obj.getDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setDate(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getResolved_date())){
				Date convertedDate = sdf.parse(obj.getResolved_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setResolved_date(currentDate);
			}
			
			MultipartFile file = obj.getIssueFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
			}
			
			boolean flag = issueService.addIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding issue is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("addIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-issue",method=RequestMethod.POST)
	public ModelAndView getIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateIssueForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> activityList = issueService.getActivityList();
			model.addObject("activityList", activityList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			Issue issue = issueService.getIssue(obj);
			model.addObject("issue", issue);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("getIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-issue",method=RequestMethod.POST)
	public ModelAndView updateIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/issues");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			if(!StringUtils.isEmpty(obj.getDate())){
				Date convertedDate = sdf.parse(obj.getDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setDate(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getResolved_date())){
				Date convertedDate = sdf.parse(obj.getResolved_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setResolved_date(currentDate);
			}
			
			MultipartFile file = obj.getIssueFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
			}
			
			boolean flag = issueService.updateIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating issue is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("updateIssue : " + e.getMessage());
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
			logger.info("deleteIssue : " + e.getMessage());
		}
		return model;
	}
	
}
