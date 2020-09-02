package com.synergizglobal.pmis.login.filter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.synergizglobal.pmis.service.ActivityService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Notification;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.service.HomeService;
import com.synergizglobal.pmis.service.LoginService;

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
			    	String taskId = request.getParameter("taskId");
			    	String workId = request.getParameter("workId");
			    	String workName = request.getParameter("workName");
			    	String notificationId = request.getParameter("notificationId");
			    	//request.setAttribute("taskId", taskId);
			    	//request.setAttribute("workId", workId);
			    	
			    	String params = "?taskId="+taskId+"&workId="+workId+"&workName="+workName+"&notificationId="+notificationId;
			    	String URL = "/pmis/login";
			    	if(!StringUtils.isEmpty(taskId) && !StringUtils.isEmpty(workId)) {
			    		URL = URL+params;
			    	}
			    	
			    	if(request.getRequestURI().contains("/pmis/")){
			    		//RequestDispatcher dispatcher = request.getRequestDispatcher("/pmis/login");
			    		//dispatcher.forward(request, response);
			    		response.sendRedirect(URL);
			    	}else{
			    		//RequestDispatcher dispatcher = request.getRequestDispatcher("/pmis/login");
			    		//dispatcher.forward(request, response);
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
	ActivityService aService;
	
	@Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView model) throws Exception {
		String clientId = null;
		String userId = null;String userName = null;
		try {
			User userDetails = (User)request.getSession().getAttribute("user");
			
			if(!StringUtils.isEmpty(userDetails)) {
				userId = userDetails.getUserId();
				userName = userDetails.getUserName();
				if(!StringUtils.isEmpty(userDetails.getPasswordExpiredTime()) && Integer.parseInt(userDetails.getPasswordExpiredTime()) <= 0){
					model.setViewName(PageConstants.passwordReset);
					model.addObject("message", passwordExpired);
				}
				
				List<TableauDashboard> menuList = service.getMenuList();
				model.addObject("menuList", menuList);
				
				String base = "web";
				List<Forms> forms = service.getFormsList(base);
				model.addObject("forms", forms);
				
				String workId = (String)request.getSession().getAttribute("globalWorkId");	
				
				List<Notification> dueActivities = aService.getDueActivities(workId);
				model.addObject("dueActivities", dueActivities);
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
