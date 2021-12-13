package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.Work;

@Controller
public class HomeController {
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	AlertsService alertsService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	/**
	 * This method welcome() is executed when user login to the Dashboard. 
	 * @param obj is object for the model class Activity. 
	 * @param session it will check the session of the user.
	 * @return type of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView welcome(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 if (session.getAttribute("user") != null ) {
		         model.setViewName(PageConstants.home);
		         //model.addObject("homeHeader", "yes");
		         
		         Project obj = new Project();
		         
		         List<Project> projectsInfo = homeService.getProjectsInformation(obj);
		         model.addObject("projectsInfo", projectsInfo);
			 } else {
				 model.setViewName(PageConstants.login);
			 }				
		} catch (Exception e) {
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
	
	@RequestMapping(value = "/home", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView home(@ModelAttribute User user,HttpSession session){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try{
			
			//model.addObject("homeHeader", "yes");
			if (session.getAttribute("user") != null ) {
				user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
				model.setViewName(PageConstants.home);
				Work work = new Work();
				Project obj = new Project();
				List<Project> projectsInfo = homeService.getProjectsInformation(obj);
		        model.addObject("projectsInfo", projectsInfo);
		        List<Work> workDetails = homeService.getWorkDetails(work);
		        model.addObject("workDetails", workDetails);
			} else {
			  model.setViewName(PageConstants.login);
		    }	
		}catch(Exception e){
			logger.error("home() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/access-denied",method=RequestMethod.GET)
	public ModelAndView accessDenied(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 model.setViewName(PageConstants2.accessDenied);
		} catch (Exception e) {
			logger.error("accessDenied : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/someone-login",method=RequestMethod.GET)
	public ModelAndView someoneLogin(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 model.setViewName(PageConstants.login);
			 model.addObject("message", "Session forced to logout from other device or browser");
		} catch (Exception e) {
			logger.error("someoneLogin : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getDashBoradName", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getDashBoardNames(@ModelAttribute Work obj) {
		List<Work> objList = null;
		try {
			objList = homeService.getDashBoardNames(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashBoardNames : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getSubLink", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getSubLink(@ModelAttribute Work obj) {
		List<Work> objList = null;
		try {
			objList = homeService.getSubLink(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubLink : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getAlertsForHeaderNotifications", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,List<Alerts>> getAlertsForHeaderNotifications(@ModelAttribute Alerts aObj, HttpSession session) {
		Map<String,List<Alerts>> alerts = null;
		try {
			User userDetails = (User)session.getAttribute("user");
			aObj.setUser_id(userDetails.getUser_id());
			aObj.setEmail_id(userDetails.getEmail_id());
			aObj.setUser_role_name(userDetails.getUser_role_name_fk());
			
			alerts = alertsService.getAlertsForHeaderNotifications(aObj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertsForHeaderNotifications : " + e.getMessage());
		}
		return alerts;
	}
	
	@RequestMapping(value = "/ajax/getMesagesForHeader", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Messages> getMesagesForHeader(@ModelAttribute Messages obj, HttpSession session) {
		List<Messages> messages = null;
		try {
			User userDetails = (User)session.getAttribute("user");
			obj.setUser_id_fk(userDetails.getUser_id());
			messages = homeService.getMessages(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertsForHeaderNotifications : " + e.getMessage());
		}
		return messages;
	}
	
	@RequestMapping(value = "/ajax/changeMessagesReadStatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Messages> changeMessagesReadStatus(@ModelAttribute Messages obj, HttpSession session) {
		List<Messages> messages = null;
		try {
			User userDetails = (User)session.getAttribute("user");
			obj.setUser_id_fk(userDetails.getUser_id());
			messages = homeService.changeMessagesReadStatus(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertsForHeaderNotifications : " + e.getMessage());
		}
		return messages;
	}	
	
	/**
	 * This method will call the ajax request and fetch the Project list
	 * @return of this method is projects
	 */
	@RequestMapping(value = "/ajax/getProjectsListForSearch", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Project> getProjectsListForSearch(){
		List<Project> projects = null;
		try{
			projects = homeService.getProjectsList();			
		}catch(Exception e){
			logger.error("getProjectsListForSearch() : "+e.getMessage());
		}
		return projects;
	}
	
	/**
	 * This method will call the ajax request and fetch the Work list
	 * @param obj is object for the model class Activity.
	 * @return type of this method is works
	 */
	@RequestMapping(value = "/ajax/getWorksListForSearch", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksListForSearch(@ModelAttribute Work obj){
		List<Work> works = null;
		try{
			works = homeService.getWorksList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getWorksListForSearch() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksList(@ModelAttribute Work obj){
		List<Work> works = null;
		try{
			works = homeService.getWorksList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	/**
	 * This method setGlobalVariables() is used for setting the global variables.
	 * 
	 * @param globalProjectId it takes the id of the globalProject
	 * @param globalWorkId it takes the id of the globalWork
	 * @param globalWorkName it takes the name of global work.
	 * @param currentUrl it takes the current URL.
	 * @param session it will check the session of the user.
	 * @return type of this method is view.
	 */
	
	@RequestMapping(value = "/set-global-variables", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView setGlobalVariables(@RequestParam String globalProjectId,@RequestParam String globalWorkId,
			@RequestParam String globalWorkName,@RequestParam String currentUrl,HttpSession session){
		ModelAndView view = new ModelAndView();
		try{
			if(!StringUtils.isEmpty(currentUrl)) {
				view.setViewName("redirect:"+currentUrl);	 
			}else {
				view.setViewName("redirect:/home");	 
			}
			session.setAttribute("globalProjectId", globalProjectId);
			session.setAttribute("globalWorkId", globalWorkId);
			session.setAttribute("globalWorkName", globalWorkName);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("setGlobalVariables() : "+e.getMessage());
		}
		return view;
	}
	
}
