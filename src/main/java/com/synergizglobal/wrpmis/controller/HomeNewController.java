package com.synergizglobal.wrpmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.wrpmis.Iservice.AlertsService;
import com.synergizglobal.wrpmis.Iservice.HomeNewService;
import com.synergizglobal.wrpmis.Iservice.HomeService;
import com.synergizglobal.wrpmis.Iservice.IssueService;
import com.synergizglobal.wrpmis.Iservice.LoginService;
import com.synergizglobal.wrpmis.Iservice.UserManualsService;
import com.synergizglobal.wrpmis.Iservice.WebDocumentsService;
import com.synergizglobal.wrpmis.Iservice.WebLinksService;
import com.synergizglobal.wrpmis.common.UrlGenerator;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.constants.PageConstants2;
import com.synergizglobal.wrpmis.model.Forms;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.TableauDashboard;
import com.synergizglobal.wrpmis.model.User;

@Controller
public class HomeNewController {
Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	HomeNewService homeService;
	
	@Autowired
	LoginService  loginService;
	
	@Autowired
	HomeService service;
	
	@Autowired
	WebDocumentsService webDocumentsService;
	
	@Autowired
	WebLinksService webLinksService;
	
	@Autowired
	AlertsService alertsService;
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	UserManualsService userManualsService;	
	
	@Value("${common.error.message}")
	public String commonError;
	
	/**
	 * This method welcome() is executed when user login to the Dashboard. 
	 * @param obj is object for the model class Activity. 
	 * @param session it will check the session of the user.
	 * @return type of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	//@RequestMapping(value="/home-new",method=RequestMethod.GET)
	@RequestMapping(value="/",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView welcome(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 if (session.getAttribute("user") != null ) {
				model.setViewName(PageConstants2.NEW_HOME);
				
				List<Project> projects = homeService.getprojectTypeswithProject();
		        model.addObject("projects", projects);
		        
			 } else {
				 model.setViewName(PageConstants.login);
			 }				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("welcome : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method home() will execute when user click on the home link.
	 * 
	 * @param obj is object for the model class Activity. 
	 * @param session it will check the session of the user.
	 * @return type of this method is model.
	 * 
	 */
	
	//@RequestMapping(value = "/new-home", method = {RequestMethod.GET,RequestMethod.POST})
	@RequestMapping(value = "/home", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView home(@ModelAttribute User user,HttpSession session){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try{
			
			//model.addObject("homeHeader", "yes");	
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			model.setViewName(PageConstants2.NEW_HOME);
			
			List<Project> projects = homeService.getProjects();
	        model.addObject("projects", projects);
	        
			List<Project> projectsforHeader = homeService.getProjectsforHeader();
	        model.addObject("projectsforHeader", projectsforHeader);	        
	        
			List<Project> AllLengths = homeService.getAllLengthsByProjectTypes();
	        model.addObject("alllengths", AllLengths);	        
	        
			List<Project> projectTypes = homeService.getprojectTypes();
	        model.addObject("projectTypes", projectTypes);	
	        
	        List<Project> projectStats = homeService.getProjectStatsByType();
	        model.addObject("projectStatsList", projectStats); 
	        
	        List<Project> projectTypeswithProject = homeService.getprojectTypeswithProject();
	        model.addObject("projectTypeswithProject", projectTypeswithProject);	        
	        
	        
	        String base = "web";
	        
	        User userDetails = (User) session.getAttribute("user");

	        if (userDetails == null) {
	            model.setViewName("redirect:/login");
	            return model;
	        }

	        user_Id = (String) session.getAttribute("USER_ID");
	        userName = (String) session.getAttribute("USER_NAME");
			
			String dashboardType = "Module";
			List<TableauDashboard> modulesList = service.getDashboardsList(dashboardType,base,userDetails);
			model.addObject("dashboardModulesList", modulesList);
			
			dashboardType = "Project";
			List<TableauDashboard> projectsList = service.getDashboardsList(dashboardType,base,userDetails);
			model.addObject("dashboardProjectsList", projectsList);				
			
			List<Forms> forms = service.getFormsList(base,userDetails);
			model.addObject("forms", forms);
			
			List<Forms> reportForms = service.getReportFormsList(base,userDetails);
			model.addObject("reportForms", reportForms);
			
	        
	        
	        
	       
		}catch(Exception e){
			logger.error("home() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/project-overview",method=RequestMethod.POST)
	public ModelAndView projectOverview(Project obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 if (session.getAttribute("user") != null ) {
		         model.setViewName(PageConstants2.NEW_HOME_DETAILS);
		         
		         Project projectOverview = homeService.getProjectOverview(obj);
		         model.addObject("projectOverview", projectOverview);
			 } else {
				 model.setViewName(PageConstants.login);
			 }				
		} catch (Exception e) {
			logger.error("projectOverview : " + e.getMessage());
		}
		return model;
	}
}
