package com.synergizglobal.pmis.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.service.HomeService;

@Controller
public class HomeController {
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	HomeService homeService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${input.sheet.task.update}")
	public String taskUpdate;
	
	@Value("${input.sheet.task.complete}")
	public String taskComplete;
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	/**
	 * This method welcome() is executed when user login to the Dashboard. 
	 * @param obj is object for the model class Activity. 
	 * @param session it will check the session of the user.
	 * @return type of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView welcome(@ModelAttribute Activity obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			 if (session.getAttribute("user") != null ) {
		         model.setViewName(PageConstants.home);
		         
		         model.addObject("homeHeader", "yes");
				 
				 List<Activity> projects = homeService.getProjectsList();
				 model.addObject("projects", projects);
				 
				 List<Activity> modules = homeService.getModulesList();
				 model.addObject("modules", modules);
				 
			 } else {
				 model.setViewName(PageConstants.login);
			 }				
		} catch (Exception e) {
			logger.info("welcome : " + e.getMessage());
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
			
			model.addObject("homeHeader", "yes");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			model.setViewName(PageConstants.home);
			
			List<User> userDetails=homeService.getUserDetails(user_Id);
			model.addObject("userDetailsList", userDetails);
			
			//List<Activity> projects = homeService.getProjectsList();
			//model.addObject("projects", projects);
			
			//List<Activity> modules = homeService.getModulesList();
			//model.addObject("modules", modules);
			
			/*TableauDashboard dashBoard = service.getTableauDashBoard();
			String url = "/InfoViz";
			
			if (!StringUtils.isEmpty(dashBoard)) {						
				String dashboardName = dashBoard.getTableauDashboardName();
				if(!StringUtils.isEmpty(dashboardName)){
					dashboardName = dashboardName.toLowerCase().replaceAll(" - ", "_");
					dashboardName = dashboardName.toLowerCase().replaceAll(" ", "-");
				}
				
				if(!StringUtils.isEmpty(dashboardName)){
					url = url+"/"+dashboardName;
				}
			}
			
			model.setViewName("redirect:"+url);*/	
			
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("home() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return model;
	}
	
	/**
	 *  This method getWorksListByProject() will call the ajax request to display the list of projects
	 * 
	 * @param obj is object for the model class Activity.
	 * @return type of this method is works
	 */
	@RequestMapping(value = "/ajax/getWorksListByProject", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getWorksListByProject(@ModelAttribute Activity obj){
		List<Activity> works = null;
		try{
			works = homeService.getWorksListByProject(obj);			
		}catch(Exception e){
			logger.error("getWorksListByProject() : "+e.getMessage());
		}
		return works;
	}
	
	/**
	 * This method getWorkModuleStatus() will call ajax request to display the work module status.
	 * 
	 * @param obj is object for the model class Activity.
	 * @return type of this method is workModuleStatus
	 */
	@RequestMapping(value = "/ajax/getWorkModuleStatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Activity getWorkModuleStatus(@ModelAttribute Activity obj){
		Activity workModuleStatus = null;
		try{
			workModuleStatus = homeService.getWorkModuleStatus(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getWorkModuleStatus() : "+e.getMessage());
		}
		return workModuleStatus;
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
