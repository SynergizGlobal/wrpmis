package com.synergizglobal.pmis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.TableauDashboardService;
import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;

@Controller
public class TableauDashboardController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	
	Logger logger = Logger.getLogger(TableauDashboardController.class);
	
	@Autowired
	TableauDashboardService service;
	
	@Autowired
	IssueService issueService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	
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
		String title = "";
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
				title = title + capitalize(activityWork).toUpperCase() + " - ";
			}
			
			String line = null;
			if(!StringUtils.isEmpty(param1)){
				line = param1.replaceAll("-", " ").toLowerCase();
				line = line.replaceAll("_", " - ").toLowerCase();
				title = title + capitalize(line).toUpperCase() + " - ";
			}
			
			view.addObject("title", title+"PMIS - Syntrack.");
			
			TableauDashboard vo = service.getTableauUrl(activityWork);
			if(!StringUtils.isEmpty(vo) && !StringUtils.isEmpty(vo.getTableauUrl())){
				String[] url = {};
				if(vo.getTableauUrl().contains(".com/")) {
					url = vo.getTableauUrl().split(".com/");
				}else {
					url = vo.getTableauUrl().split(":8000/");
				}
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket();
				CommonConstants cObj = new CommonConstants();
				String baseUrl = cObj.BASE_URL.replace("{0}", trustedTokenId);
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
		String title = "";
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.addObject("active", param);
			view.addObject("tabActive", "dashboard");
			
			User user = (User)session.getAttribute("user");
			String activityWork = null;
			if(!StringUtils.isEmpty(param)){
				activityWork = param.replaceAll("_", " - ").toLowerCase();
				activityWork = activityWork.replaceAll("-", " ").toLowerCase();
				title = title + capitalize(activityWork).toUpperCase() + " - ";
			}
			view.addObject("title", title+"PMIS - Syntrack.");
			
			TableauDashboard vo = service.getTableauUrl(activityWork);
			if(!StringUtils.isEmpty(vo) && !StringUtils.isEmpty(vo.getTableauUrl())){
				String[] url = {};
				if(vo.getTableauUrl().contains(".com/")) {
					url = vo.getTableauUrl().split(".com/");
				}else {
					url = vo.getTableauUrl().split(":8000/");
				}
				
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket();
				CommonConstants cObj = new CommonConstants();
				String baseUrl = cObj.BASE_URL.replace("{0}", trustedTokenId);
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
	
	private String capitalize(final String line) {
	   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	@RequestMapping(value="/InfoViz/issues/{param}/{issue_id}",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView issueTableauDashboard(@ModelAttribute Issue obj,@PathVariable(value = "param") String param,@PathVariable(value = "issue_id") String issue_id,
			HttpSession session,HttpServletRequest request){
		ModelAndView view = new ModelAndView(PageConstants.tableauDashboard);
		String user_Id = null;String userName = null;
		String title = "";
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.addObject("active", param);
			view.addObject("tabActive", "dashboard");
			
			User user = (User)session.getAttribute("user");
			String activityWork = null;
			if(!StringUtils.isEmpty(param)){
				activityWork = param.replaceAll("_", " - ").toLowerCase();
				activityWork = activityWork.replaceAll("-", " ").toLowerCase();
				title = title + capitalize(activityWork).toUpperCase() + " - ";
			}
			
			view.addObject("title", title+"PMIS - Syntrack.");
			
			TableauDashboard vo = service.getTableauUrl(activityWork);
			if(!StringUtils.isEmpty(vo) && !StringUtils.isEmpty(vo.getTableauUrl())){
				String[] url = {};
				if(vo.getTableauUrl().contains(".com/")) {
					url = vo.getTableauUrl().split(".com/");
				}else {
					url = vo.getTableauUrl().split(":8000/");
				}
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket();
				CommonConstants cObj = new CommonConstants();
				String baseUrl = cObj.BASE_URL.replace("{0}", trustedTokenId);
				String tableauUrl = baseUrl + url[1]+"&issue_id="+issue_id+CommonConstants.TABLEAU_PARAMS;
				vo.setTableauUrl(tableauUrl);
			}
			view.addObject("url", vo);
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMessage_id())) {
				boolean flag = issueService.readIssueMessage(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("issueTableauDashboard() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return view;
	}
	
	@RequestMapping(value="/InfoViz/risks/{param}",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView riskTableauDashboard(@ModelAttribute Risk obj,@PathVariable(value = "param") String param,HttpSession session,HttpServletRequest request){
		ModelAndView view = new ModelAndView(PageConstants.tableauDashboard);
		String user_Id = null;String userName = null;
		String title = "";
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.addObject("active", param);
			view.addObject("tabActive", "dashboard");
			
			User user = (User)session.getAttribute("user");
			String activityWork = null;
			if(!StringUtils.isEmpty(param)){
				activityWork = param.replaceAll("_", " - ").toLowerCase();
				activityWork = activityWork.replaceAll("-", " ").toLowerCase();
				title = title + capitalize(activityWork).toUpperCase() + " - ";
			}
			
			view.addObject("title", title+"PMIS - Syntrack.");
			
			TableauDashboard vo = service.getTableauUrl(activityWork);
			if(!StringUtils.isEmpty(vo) && !StringUtils.isEmpty(vo.getTableauUrl())){
				String[] url = {};
				if(vo.getTableauUrl().contains(".com/")) {
					url = vo.getTableauUrl().split(".com/");
				}else {
					url = vo.getTableauUrl().split(":8000/");
				}
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket();
				CommonConstants cObj = new CommonConstants();
				String baseUrl = cObj.BASE_URL.replace("{0}", trustedTokenId);
				String tableauUrl = baseUrl + url[1]+"&Work  ="+obj.getSub_work()+"&Assessment Date="+obj.getAssessment_date()+CommonConstants.TABLEAU_PARAMS;
				vo.setTableauUrl(tableauUrl);
			}
			view.addObject("url", vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("issueTableauDashboard() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return view;
	}
	
	
}
