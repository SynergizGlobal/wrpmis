package com.synergizglobal.pmis.login.filter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.Iservice.WebDocumentsService;
import com.synergizglobal.pmis.Iservice.WebLinksService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.WebDocuments;
import com.synergizglobal.pmis.model.WebLinks;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
	
	@Autowired
	LoginService  loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
		try {
			// Avoid a redirect loop for some urls
			if( !request.getRequestURI().equals("/pmis/") && !request.getRequestURI().equals("/pmis/login") && !request.getRequestURI().equals("/") && !request.getRequestURI().equals("/login")){
			    User userData = (User) request.getSession().getAttribute("user");
			    if(userData == null){
			    	String URL = "/pmis/login";
			    	if(request.getRequestURI().contains("/pmis/")){
			    		response.sendRedirect(URL);
			    	}else{
			    		response.sendRedirect("/login");
			    	}
				    return false;
			    }   
			}
		} catch (Exception e) {
			logger.error("preHandle : " + e.getMessage());
		}
		
		return true;
	}
	
	@Value("${message.password.expired}")
	public String passwordExpired;
	
	@Autowired
	HomeService service;
	
	@Autowired
	WebDocumentsService webDocumentsService;
	
	@Autowired
	WebLinksService webLinksService;
	
	@Autowired
	AlertsService alertsService;
	
	@Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView model) throws Exception {
		String clientId = null;
		String userId = null;String userName = null;
		try {
			User userDetails = (User)request.getSession().getAttribute("user");
			
			if(!StringUtils.isEmpty(userDetails)) {
				userId = userDetails.getUser_id();
				userName = userDetails.getUser_name();
				if(!StringUtils.isEmpty(userDetails.getPasswordExpiredTime()) && Integer.parseInt(userDetails.getPasswordExpiredTime()) <= 0){
					model.setViewName(PageConstants.passwordReset);
					model.addObject("message", passwordExpired);
				}
				
				String base = "web";
				
				String dashboardType = "Module";
				List<TableauDashboard> modulesList = service.getDashboardsList(dashboardType,base);
				model.addObject("dashboardModulesList", modulesList);
				
				dashboardType = "Project";
				List<TableauDashboard> projectsList = service.getDashboardsList(dashboardType,base);
				model.addObject("dashboardProjectsList", projectsList);
				
				
				List<Forms> forms = service.getFormsList(base,userDetails);
				model.addObject("forms", forms);
				
				List<WebDocuments> webDocumentTypes = webDocumentsService.getWebDocumentTypes(null);
				model.addObject("webDocumentTypes", webDocumentTypes);
				
				List<WebLinks> webLinksList = webLinksService.getWebLinks(null);
				model.addObject("webLinksList", webLinksList);
				
				List<Forms> reportForms = service.getReportFormsList(base);
				model.addObject("reportForms", reportForms);
				
				Alerts aObj = new Alerts();
				aObj.setEmail_id(userDetails.getEmail_id());
				aObj.setUser_role_name(userDetails.getUser_role_name_fk());
				Map<String,List<Alerts>> alerts = alertsService.getAlertsForHeaderNotifications(aObj);
				model.addObject("alerts", alerts);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(!StringUtils.isEmpty(clientId)){
				logger.error("postHandle() : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			}
		}
		
        super.postHandle(request, response, handler, model);
    }
}
