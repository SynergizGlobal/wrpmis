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
	
	@Autowired
	LoginService  loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
		try {
			// Avoid a redirect loop for some urls
			if( !request.getRequestURI().equals("/pmis/") && !request.getRequestURI().equals("/pmis/login") 
					&& !request.getRequestURI().equals("/") && !request.getRequestURI().equals("/login") 
					&& !request.getRequestURI().equals("/pmis/someone-login") && !request.getRequestURI().equals("/pmis/someone-login") 
					&& !request.getRequestURI().equals("/someone-login") && !request.getRequestURI().equals("/someone-login")){
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
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	UserManualsService userManualsService;
	
	@Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView model) throws Exception {
		try {
			User userDetails = (User)request.getSession().getAttribute("user");
			String single_login_session_id = (String)request.getSession().getAttribute("SINGLE_LOGIN_SESSION_ID");
			
			if(!StringUtils.isEmpty(userDetails)) {
				
				boolean flag = service.addUserLastActiveDateTime(userDetails);
				
				String single_session_id = loginService.getSingleLoginSessionId(userDetails);
				if(!StringUtils.isEmpty(single_login_session_id) && StringUtils.isEmpty(single_session_id)){
					request.getSession().invalidate();
					model.setViewName("redirect:/login");
				}else if(!StringUtils.isEmpty(single_login_session_id) && !single_login_session_id.equals(single_session_id)){
					request.getSession().invalidate();
					model.setViewName("redirect:/someone-login");
				}
				
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
			    
				/*boolean url_access_flag = false;
				for (Forms formObj : forms) {
					if(request.getRequestURI().contains("/pmis/"+formObj.getWebFormUrl())){
						System.out.println(request.getRequestURI());
						url_access_flag = true;
					}
					for (Forms formSubObj : formObj.getFormsSubMenu()) {
						if(request.getRequestURI().contains("/pmis/"+formSubObj.getWebFormUrl())){
				    		System.out.println(request.getRequestURI());
				    		url_access_flag = true;
				    	}
					}
				}
				if(!url_access_flag) {
					request.getSession().invalidate();
					model.setViewName("redirect:/home");
				}*/
				
				Alerts aObj = new Alerts();
				aObj.setUser_id(userDetails.getUser_id());
				aObj.setEmail_id(userDetails.getEmail_id());
				aObj.setUser_role_name(userDetails.getUser_role_name_fk());
				
				List<Alerts> alertTypes = alertsService.getAlertTypes(aObj);
				model.addObject("alertTypes", alertTypes);
				
				/*Map<String,List<Alerts>> alerts = alertsService.getAlertsForHeaderNotifications(aObj);
				model.addObject("alerts", alerts);*/
				
				Messages mObj = new Messages();
				mObj.setUser_id_fk(userDetails.getUser_id());
				
				List<Messages> messageTypes = service.getMessageTypes(mObj);
				model.addObject("messageTypes", messageTypes);
				
				/*List<Messages> messages = service.getMessages(mObj);
				model.addObject("messages", messages);*/
			}
			
			UserManuals userManual = new UserManuals();
			userManual.setStatus("Active");
			List<UserManuals> userManuals = userManualsService.getUserManuals(userManual);
			model.addObject("userManuals", userManuals);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postHandle() : "+e.getMessage());
		}
		
        super.postHandle(request, response, handler, model);
    }
}
