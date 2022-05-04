package com.synergizglobal.pmis.controller;

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

import com.synergizglobal.pmis.Iservice.HomeNewService;
import com.synergizglobal.pmis.common.UrlGenerator;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.User;

@Controller
public class HomeNewController {
Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	HomeNewService homeService;
	
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
				
				List<Project> projects = homeService.getProjects();
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
