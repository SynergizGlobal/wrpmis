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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.Iservice.UserManualsService;
import com.synergizglobal.pmis.Iservice.WebDocumentsService;
import com.synergizglobal.pmis.Iservice.WebLinksService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Admin;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.UserManuals;
import com.synergizglobal.pmis.model.WebDocuments;
import com.synergizglobal.pmis.model.WebLinks;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
	
	@Value("${message.password.expired}")
	public String passwordExpired;
	
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
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
		try {
			String requestURI = request.getRequestURI();
			// Avoid a redirect loop for some urls
			if( !requestURI.equals("/pmis/") && !requestURI.equals("/pmis/login") 
					&& !requestURI.equals("/") && !requestURI.equals("/login") 
					&& !requestURI.equals("/pmis/someone-login") && !requestURI.equals("/someone-login") 
					&& !requestURI.equals("/pmis/access-denied") && !request.getRequestURI().equals("/access-denied")){
			    User userData = (User) request.getSession().getAttribute("user");
			    if(userData == null){
			    	if(request.getRequestURI().contains("/pmis/")){
			    		response.sendRedirect("/pmis/login");
			    	}else{
			    		response.sendRedirect("/login");
			    	}
				    return false;
				} else {
					/************************************************************************************************/
					String single_login_session_id = (String)request.getSession().getAttribute("SINGLE_LOGIN_SESSION_ID");
					String single_session_id = loginService.getSingleLoginSessionId(userData);
					if(!StringUtils.isEmpty(single_login_session_id) && StringUtils.isEmpty(single_session_id)){
						request.getSession().invalidate();
						if(request.getRequestURI().contains("/pmis/")){
				    		response.sendRedirect("/pmis/login");
				    	}else{
				    		response.sendRedirect("/login");
				    	}
						return false;
					}else if(!StringUtils.isEmpty(single_login_session_id) && !single_login_session_id.equals(single_session_id)){
						request.getSession().invalidate();
						if(request.getRequestURI().contains("/pmis/")){
				    		response.sendRedirect("/pmis/someone-login");
				    	}else{
				    		response.sendRedirect("/someone-login");
				    	}
						return false;
					}else {
						boolean flag = service.checkURLAccessPermission(userData,requestURI);
						if(flag) {
							return true;
						}else {
							if(requestURI.contains("/pmis/")){
						   		response.sendRedirect("/pmis/access-denied");
						   	}else{
						   		response.sendRedirect("/access-denied");
						   	}
							return false;
						}
					}
					/************************************************************************************************/
				}
			}
		} catch (Exception e) {
			logger.error("preHandle : " + e.getMessage());
		}
		
		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView model) throws Exception {
		String requestURI = null;
		try {
			requestURI = request.getRequestURI();
			//System.out.println(requestURI);
			User userDetails = (User)request.getSession().getAttribute("user");
			if(!StringUtils.isEmpty(userDetails)) {	
				boolean flag = service.addUserLastActiveDateTime(userDetails);
				
				String base = "web";
				
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
				
				List<WebDocuments> webDocumentTypes = webDocumentsService.getWebDocumentTypes(null);
				model.addObject("webDocumentTypes", webDocumentTypes);
				
				List<WebLinks> webLinksList = webLinksService.getWebLinks(null);
				model.addObject("webLinksList", webLinksList);
				
				List<Admin> adminForms = service.getAdminList(null);
			    model.addObject("adminForms", adminForms);
				
				Alerts aObj = new Alerts();
				aObj.setUser_id(userDetails.getUser_id());
				aObj.setEmail_id(userDetails.getEmail_id());
				aObj.setUser_role_name(userDetails.getUser_role_name_fk());
				
				List<Alerts> alertTypes = alertsService.getAlertTypes(aObj);
				model.addObject("alertTypes", alertTypes);
				
				Messages mObj = new Messages();
				mObj.setUser_id_fk(userDetails.getUser_id());
				
				List<Messages> messageTypes = service.getMessageTypes(mObj);
				model.addObject("messageTypes", messageTypes);
			}
			UserManuals userManual = new UserManuals();
			userManual.setStatus("Active");
			List<UserManuals> userManuals = userManualsService.getUserManuals(userManual);
			model.addObject("userManuals", userManuals);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postHandle() >> URL="+requestURI+" : "+e.getMessage());
		}
		
        super.postHandle(request, response, handler, model);
    }
}
