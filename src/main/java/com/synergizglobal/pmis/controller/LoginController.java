package com.synergizglobal.pmis.controller;

import java.sql.SQLException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.Iservice.ProfileService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.RandomGenerator;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.model.User;
@Controller
public class LoginController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	ProfileService  profileService;
	
	
	
	@Value("${Login.Form.Invalid}")
	public String invalidUserName;
	
	@Value("${no.pmis.form.Key}")
	public String noKeyAssigned;
	
	@Value("${Logout.Message}")
	private String logOutMessage;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${message.password.expired}")
	public String passwordExpired;
	
	@Value("${message.password.reset.fail}")
	public String passwordResetFail;
	
	@Value("${message.password.reset.success}")
	public String passwordResetSuccess;
	
	@Value("${message.wrong.password.entered}")
	public String wrongPasswordEntered;
	
	@Value("${already.logged.in.some.other.device.or.browser}")
	public String alreadyLoggedInSomeOtherDeviceOrBrowser;
	
	
	/**
	 * This login() method is used for user login validation. 
	 * @param user is the User class type variable that will get and set the value in User model
	 * @param session will create session for the user.
	 * @param attributes will show the flash message on the current request.
	 * @param request it receives the request from the server with header information.
	 * @return type of this method is model.
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(@ModelAttribute User user, HttpSession session,RedirectAttributes attributes,HttpServletRequest request){
		User userDetails = null;
		ModelAndView model = new ModelAndView();
		try{
			
			String single_login_session_id = (String) session.getAttribute("SINGLE_LOGIN_SESSION_ID");
			if(!StringUtils.isEmpty(user.getUser_id()) && !StringUtils.isEmpty(user.getPassword())){
				userDetails = loginService.validateUser(user);
				if(!StringUtils.isEmpty(userDetails)) {
					if(!StringUtils.isEmpty(userDetails.getSingle_login_session_id()) && !StringUtils.isEmpty(single_login_session_id) && userDetails.getSingle_login_session_id().equals(single_login_session_id)) {
						model.setViewName("redirect:/home");
						session.setAttribute("user", userDetails);
						session.setAttribute("USER_ID", userDetails.getUser_id());
						session.setAttribute("USER_NAME", userDetails.getUser_name());
						session.setAttribute("USER_ROLE_NAME", userDetails.getUser_role_name_fk());
						session.setAttribute("USER_ROLE_CODE", userDetails.getUser_role_code());
						session.setAttribute("USER_TYPE", userDetails.getUser_type_fk());
						session.setAttribute("USER_DESIGNATION", userDetails.getDesignation());
						
						session.setAttribute("USER_LOGIN_DETAILS_ID", userDetails.getUser_login_details_id());
						
						if(!StringUtils.isEmpty(userDetails.getPasswordExpiredTime()) && Integer.parseInt(userDetails.getPasswordExpiredTime()) <= 0){
							model.setViewName("redirect:/reset-password");
							attributes.addFlashAttribute("message", passwordExpired);
						}
					}else if(!StringUtils.isEmpty(userDetails.getSingle_login_session_id()) && !userDetails.getSingle_login_session_id().equals(single_login_session_id)) {	
						/*model.addObject("error", alreadyLoggedInSomeOtherDeviceOrBrowser);
						session.setAttribute("user", userDetails);
						session.setAttribute("USER_ID", userDetails.getUser_id());
						session.setAttribute("USER_NAME", userDetails.getUser_name());
						session.setAttribute("USER_ROLE_NAME", userDetails.getUser_role_name_fk());
						session.setAttribute("USER_ROLE_CODE", userDetails.getUser_role_code());
						session.setAttribute("USER_TYPE", userDetails.getUser_type_fk());
						session.setAttribute("USER_DESIGNATION", userDetails.getDesignation());*/
						
						//model.setViewName(PageConstants2.alreadyLoggedIn);
						
						model.setViewName("redirect:/home");
						session.setAttribute("user", userDetails);
						session.setAttribute("USER_ID", userDetails.getUser_id());
						session.setAttribute("USER_NAME", userDetails.getUser_name());
						session.setAttribute("USER_ROLE_NAME", userDetails.getUser_role_name_fk());
						session.setAttribute("USER_ROLE_CODE", userDetails.getUser_role_code());
						session.setAttribute("USER_TYPE", userDetails.getUser_type_fk());
						session.setAttribute("USER_DESIGNATION", userDetails.getDesignation());
						
						session.setAttribute("USER_LOGIN_DETAILS_ID", userDetails.getUser_login_details_id());
						
						if(!StringUtils.isEmpty(userDetails.getPasswordExpiredTime()) && Integer.parseInt(userDetails.getPasswordExpiredTime()) <= 0){
							model.setViewName("redirect:/reset-password");
							attributes.addFlashAttribute("message", passwordExpired);
						}
						
						single_login_session_id = RandomGenerator.generateAlphaNumericRandom(45); 
						boolean flag = loginService.updateSingleLoginSessionId(single_login_session_id,userDetails.getUser_id());
						if(flag) {
							session.setAttribute("SINGLE_LOGIN_SESSION_ID", single_login_session_id);
						}
						
					}else if(StringUtils.isEmpty(userDetails.getSingle_login_session_id())) {
						model.setViewName("redirect:/home");
						
						session.setAttribute("user", userDetails);
						session.setAttribute("USER_ID", userDetails.getUser_id());
						session.setAttribute("USER_NAME", userDetails.getUser_name());
						session.setAttribute("USER_ROLE_NAME", userDetails.getUser_role_name_fk());
						session.setAttribute("USER_ROLE_CODE", userDetails.getUser_role_code());
						session.setAttribute("USER_TYPE", userDetails.getUser_type_fk());
						session.setAttribute("USER_DESIGNATION", userDetails.getDesignation());
						
						single_login_session_id = RandomGenerator.generateAlphaNumericRandom(45); 
						boolean flag = loginService.updateSingleLoginSessionId(single_login_session_id,userDetails.getUser_id());
						if(flag) {
							session.setAttribute("SINGLE_LOGIN_SESSION_ID", single_login_session_id);
						}
					}
				}else{
					model.addObject("message", invalidUserName);
					model.setViewName(PageConstants.login);
				}
			}else{
				model.setViewName(PageConstants.login);
			}
		}catch(NoKeyException e){
			logger.error("login : " + e.getMessage());
			model.addObject("message", e.getMessage());
			model.setViewName(PageConstants.login);
		}catch(SQLException e){
			logger.error("login : " + e.getMessage());
			model.addObject("message", commonError);
			model.setViewName(PageConstants.login);
		}
		return model;
	}
	
	/**
	 * This method logout() is used for logout the user and destroy the session
	 * @param session it will create/destroy the session for the user. 
	 * @return type of this method is view
	 */
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView logout(HttpSession session){
		ModelAndView model = new ModelAndView();		
		try {
			model.setViewName(PageConstants.login);
			String userId = (String)session.getAttribute("USER_ID");
			
			User userDetails = (User)session.getAttribute("user");
			
			boolean flag1 = loginService.addUserLogoutDateTime(userDetails);
			
			session.invalidate(); 
			
			boolean flag = loginService.logoutFromAllDevices(userDetails);
			
			model.addObject("success",logOutMessage);
		} catch (Exception e) {
			logger.error("resetPassword : " + e.getMessage());
			model.addObject("error", commonError);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/logout-from-all-devices", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView logoutFromAllDevices(@ModelAttribute User obj,HttpSession session){
		ModelAndView view = new ModelAndView(PageConstants.login);
		try {
			User userDetails = (User)session.getAttribute("user");
			
			boolean flag1 = loginService.addUserLogoutDateTime(userDetails);
			
			boolean flag = loginService.logoutFromAllDevices(obj);
			if(flag) {
				session.invalidate();	
				view.addObject("success",logOutMessage);
			}else {
				view.addObject("error", commonError);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			view.addObject("error", commonError);
		}
		return view;
	}
	
	
	/**
	 * When user click on reset password then this method is executed and view page is shown to the user.
	 * This resetPassword() method is ModelAndView type it is not taking any parameter.
	 * @return type of this method is model.
	 */
	@RequestMapping(value = "/reset-password", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView resetPassword(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.passwordReset);
			model.addObject("tabActive", "profile");
		}catch(Exception e){
			logger.error("resetPassword : " + e.getMessage());
			model.setViewName(PageConstants.passwordReset);
			model.addObject("message", commonError);
		}
		return model;
	}
	
	
	@RequestMapping(value = "/profile", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView profile(HttpSession session){
		ModelAndView model = new ModelAndView();
		
		try{
			String userId = (String) session.getAttribute("USER_ID");
			model.setViewName(PageConstants.profile);
			
			User userDetails = profileService.getUserProfile(userId);
			
			model.addObject("userDetails", userDetails);	
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("profile : " + e.getMessage());
			model.setViewName(PageConstants.profile);
			model.addObject("message", commonError);
		}
		return model;
	}
	
	@RequestMapping(value = "/update-profile", method = {RequestMethod.POST})
	public ModelAndView updateProfile(@ModelAttribute User user,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/profile");
			MultipartFile file = user.getUserImageFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.USER_IMAGE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				user.setUser_image(fileName);
			}			
			boolean flag =  profileService.updateProfile(user);
			if(flag) {
				attributes.addFlashAttribute("success", "Profile Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Profile is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Profile is failed. Try again.");
			logger.error("updateProfile : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method changePassowrd() is used for changing the login password, this method have three parameter.
	 * @param user is the User class type variable that will get and set the value in User model.
	 * @param session it will create/destroy the session for the user.
	 * @param attributes will show the flash message on the current request. 
	 * @return type of this method is model.
	 */
	@RequestMapping(value = "/change-password", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView changePassword(@ModelAttribute User user, HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			String userId = (String) session.getAttribute("USER_ID");
			user.setUser_id(userId);
			String temp = loginService.changePassword(user);
			if(temp.equals("true")) {
				session.invalidate();
				model.setViewName("redirect:/");
				attributes.addFlashAttribute("success", passwordResetSuccess);
			}else if(temp.equals("false")) {
				model.setViewName(PageConstants.passwordReset);
				model.addObject("error", wrongPasswordEntered);
			}else{
				model.setViewName(PageConstants.passwordReset);
				model.addObject("error", passwordResetFail);
			}
		}catch(Exception e){
			logger.error("resetPassword : " + e.getMessage());
			model.setViewName(PageConstants.passwordReset);
			model.addObject("error", commonError);
		}
		return model;
	}
	
	
	
	/**
	 * This method changeLoginBackground() is used for changing the background image of login panel.
	 * @param obj is object for the model class User.
	 * @return type of this method is view.
	 */
	@RequestMapping(value = "/changeLoginBackground", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView changeLoginBackground(@ModelAttribute User obj){
		ModelAndView view = new ModelAndView(PageConstants.login);
		try{
			view.setViewName("redirect:/login");	 
			MultipartFile uploadFile = obj.getFileName();
			if (null != uploadFile && !uploadFile.isEmpty()){
				String saveDirectory = CommonConstants.LOGIN_BACKGROUND_IMAGE;
                String fileName = uploadFile.getOriginalFilename();
                if (!StringUtils.isEmpty(fileName)) {
                	fileName = "login-background.jpg";
                	FileUploads.singleFileSaving(uploadFile,saveDirectory,fileName);
                }      
	        }
		}catch(Exception e){
			e.printStackTrace();
			logger.error("changeLoginBackground() : "+e.getMessage());
		}
		return view;
	}
}
