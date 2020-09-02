package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.service.ActivityService;
import com.synergizglobal.pmis.service.TableauDashboardService;

@Controller
public class TableauDashboardController {
	Logger logger = Logger.getLogger(TableauDashboardController.class);
	@Autowired
	TableauDashboardService service;
	
	@Autowired
	ActivityService aservice;
	
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
	 * This method tableauView() is used for tableau dashboard view. 
	 * 
	 * @param param1 it takes the second parameter value from the URL
	 * @param param it takes the first parameter value from the URL
	 * @param session it will check the session of the user
	 * @param request it receives the request from the server with header information.
	 * @return of this method is view
	 */
	
	@RequestMapping(value="/InfoViz/{param1}/{param}",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView tableauView(@PathVariable(value = "param1") String param1,@PathVariable(value = "param") String param,
			HttpSession session,HttpServletRequest request){
		ModelAndView view = new ModelAndView(PageConstants.tableauDashboard);
		String user_Id = null;String userName = null;
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.addObject("active", param);
			view.addObject("param1", param1);
			view.addObject("tabActive", "dashboard");
			
			User user = (User)session.getAttribute("user");
			String activityWork = null;
			if(!StringUtils.isEmpty(param)){
				activityWork = param.replaceAll("_", " - ").toLowerCase();
				activityWork = activityWork.replaceAll("-", " ").toLowerCase();
			}
			TableauDashboard vo = service.getTableauUrl(activityWork);
			if(!StringUtils.isEmpty(vo) && !StringUtils.isEmpty(vo.getTableauUrl())){
				String[] url = vo.getTableauUrl().split(".com/");
				String trustedTokenId =  TableauTrustedTicket.getTrustedTicket();
				String baseUrl = CommonConstants.BASE_URL.replace("{0}", trustedTokenId);
				String tableauUrl = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;
				vo.setTableauUrl(tableauUrl);
			}
			view.addObject("url", vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("tableauView() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return view;
	}
	
	
	/**
	 * This method tableauView() is used for tableau dashboard view. 
	 * 
	 * @param param it takes the parameter value from the URL
	 * @param session it will check the session of the user
	 * @param request it receives the request from the server with header information.
	 * @return of this method is view
	 */
	@RequestMapping(value="/InfoViz/{param}",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView tableauView(@PathVariable(value = "param") String param,
			HttpSession session,HttpServletRequest request){
		ModelAndView view = new ModelAndView(PageConstants.tableauDashboard);
		String user_Id = null;String userName = null;
		
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.addObject("active", param);
			view.addObject("tabActive", "dashboard");
			
			User user = (User)session.getAttribute("user");
			String activityWork = null;
			if(!StringUtils.isEmpty(param)){
				activityWork = param.replaceAll("_", " - ").toLowerCase();
				activityWork = activityWork.replaceAll("-", " ").toLowerCase();
			}
			TableauDashboard vo = service.getTableauUrl(activityWork);
			if(!StringUtils.isEmpty(vo) && !StringUtils.isEmpty(vo.getTableauUrl())){
				
				String[] url = vo.getTableauUrl().split(".com/");
				String trustedTokenId =  TableauTrustedTicket.getTrustedTicket();
				String baseUrl = CommonConstants.BASE_URL.replace("{0}", trustedTokenId);
				String tableauUrl = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;
				vo.setTableauUrl(tableauUrl);
			}
			view.addObject("url", vo);
			
			Activity activity = new Activity();
			List<Activity> works = aservice.getWorksList(activity);
			view.addObject("works", works);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("tableauView() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return view;
	}
}
