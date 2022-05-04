package com.synergizglobal.pmis.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.FormsAccessService;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.Iservice.ProfileService;
import com.synergizglobal.pmis.Iservice.UserService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.EncryptDecrypt;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.exceptions.NotEnabledTestEnv;
import com.synergizglobal.pmis.model.Form;
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
	
	@Autowired
	UserService  userService;	
	@Autowired
	FormsAccessService  formsAccessService;	
	
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
				userDetails = loginService.validateUser(user,single_login_session_id);
				if(!StringUtils.isEmpty(userDetails)) {
					if(!StringUtils.isEmpty(user.getCurrent_url())) {
						model.setViewName("redirect:"+user.getCurrent_url());
					}else {
						model.setViewName("redirect:/home");
					}
					session.setAttribute("user", userDetails);
					session.setAttribute("USER_ID", userDetails.getUser_id());
					session.setAttribute("USER_NAME", userDetails.getUser_name());
					session.setAttribute("USER_ROLE_NAME", userDetails.getUser_role_name_fk());
					session.setAttribute("USER_ROLE_CODE", userDetails.getUser_role_code());
					session.setAttribute("USER_TYPE", userDetails.getUser_type_fk());
					session.setAttribute("USER_DESIGNATION", userDetails.getDesignation());
					session.setAttribute("IS_TEST_ENV_ENABLED", userDetails.getIs_test_env_enabled());
					
					session.setAttribute("USER_LOGIN_DETAILS_ID", userDetails.getUser_login_details_id());

					session.setAttribute("SINGLE_LOGIN_SESSION_ID", userDetails.getSingle_login_session_id());
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
		}catch(NotEnabledTestEnv e){
			logger.error("login : " + e.getMessage());
			model.addObject("message", e.getMessage());
			model.setViewName(PageConstants.login);
		}catch(SQLException e){
			logger.error("login : " + e.getMessage());
			model.addObject("message", commonError);
			model.setViewName(PageConstants.login);
		}catch(Exception e){
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
			
			boolean flag = loginService.addUserLogoutDateTime(userDetails);
			
			session.invalidate(); 
			
			flag = loginService.logoutFromAllDevices(userDetails);
			
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
	
	@RequestMapping(value = "/ajax/getPastLeaves", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getPastLeaves(@ModelAttribute User obj,HttpSession session) {
		List<User> PastLeaves = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			PastLeaves = profileService.getPastLeaves(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getPastLeaves : " + e.getMessage());
		}
		return PastLeaves;
	}	
	
	@RequestMapping(value = "/ajax/getResponsiblePersonUsers", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getResponsiblePersonUsers(@ModelAttribute User obj,HttpSession session) {
		List<User> usersList = null;
		try {
			
			List<User> users=userService.getUsersList(obj);
			for(User UserData : users) { 
				   if(UserData.getUser_id().equals(obj.getUser_id())) 
				   { 
						obj.setUser_type_fk(UserData.getUser_type_fk());
						obj.setDepartment_fk(UserData.getDepartment_fk());
						obj.setUser_role_code(UserData.getUser_role_code());
						obj.setUser_role_code(UserData.getUser_id());	
				   }
				}

			
			usersList = userService.getResponsiblePersonUsers(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonUsers : " + e.getMessage());
		}
		return usersList;
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
	
	
	@RequestMapping(value = "/forgot-password", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView forgotPassword(@ModelAttribute User user, RedirectAttributes attributes) throws Exception{
		User userDetails = null;
		ModelAndView model = new ModelAndView();
		try{
			
			if(!StringUtils.isEmpty(user.getUser_id()) && !StringUtils.isEmpty(user.getNewPassword())){
				if(!StringUtils.isEmpty(user)) 
				{
					String temp = loginService.resetPassword(user);
					model.setViewName("redirect:/login");
					attributes.addFlashAttribute("success", "Your password changed successfully..");
				}else
				{
					model.addObject("message", invalidUserName);
					model.setViewName(PageConstants.forgotPassword);
				}
			}else{
				model.setViewName(PageConstants.forgotPassword);
			}
		}catch(NoKeyException e){
			logger.error("forgot password : " + e.getMessage());
			model.addObject("message", e.getMessage());
			model.setViewName(PageConstants.forgotPassword);
		}catch(SQLException e){
			logger.error("forgot password : " + e.getMessage());
			model.addObject("message", commonError);
			model.setViewName(PageConstants.forgotPassword);
		}
		return model;
	}

	
	
	@RequestMapping(value = "/profile", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView profile(HttpSession session,@ModelAttribute User obj){
		ModelAndView model = new ModelAndView();
		
		try{
			User uObj = (User) session.getAttribute("user");
			String userId = (String) session.getAttribute("USER_ID");
			String userType = (String) session.getAttribute("USER_TYPE");
			
			obj.setUser_id(userId);
			obj.setUser_type_fk(userType);
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			model.setViewName(PageConstants.profile);
			
			User userDetails = profileService.getUserProfile(userId);
			
			List<Form> modulesList = formsAccessService.getAllModules();
			List<User> usersList = userService.getAllUsersList(obj);
			
			model.addObject("modulesList", modulesList);
			model.addObject("usersList", usersList);
			
			model.addObject("userDetails", userDetails);	
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("profile : " + e.getMessage());
			model.setViewName(PageConstants.profile);
			model.addObject("message", commonError);
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/insertLeaveResponsibility", method = {RequestMethod.POST})
	@ResponseBody
	public boolean insertLeaveResponsibility(@ModelAttribute User user,HttpSession session,RedirectAttributes attributes) {	
		boolean flag=false;
		try{
			
			if (StringUtils.isEmpty(user.getUser_id())) 
			{
				String userId = (String) session.getAttribute("USER_ID");
				user.setUser_id(userId);
			}
			user.setCreated_by_user_id_fk((String) session.getAttribute("USER_ID"));
			user.setFrom_date(DateParser.parse(user.getFrom_date()));	
			user.setTo_date(DateParser.parse(user.getTo_date()));	
			flag =  profileService.insertLeaveResponsibility(user);
		}catch (Exception e) 
		{
			attributes.addFlashAttribute("error","Updating Leave Responsibility is failed. Try again.");
			logger.error("Leave Responsibility : " + e.getMessage());
		}
		return flag;
	}
	
	@RequestMapping(value = "/ajax/checkLeaveResponsibility", method = {RequestMethod.POST})
	@ResponseBody
	public boolean checkLeaveResponsibility(@ModelAttribute User user,HttpSession session,RedirectAttributes attributes) {	
		boolean flag=false;
		try{
			String userId = (String) session.getAttribute("USER_ID");
			user.setUser_id(userId);
			user.setFrom_date(DateParser.parse(user.getFrom_date()));	
			user.setTo_date(DateParser.parse(user.getTo_date()));	
			flag =  profileService.checkLeaveResponsibility(user);
		}catch (Exception e) 
		{
			attributes.addFlashAttribute("error","Updating Leave Responsibility is failed. Try again.");
			logger.error("Leave Responsibility : " + e.getMessage());
		}
		return flag;
	}
	
	@RequestMapping(value = "/ajax/deleteLeaveResponsibility", method = {RequestMethod.POST})
	@ResponseBody
	public boolean deleteLeaveResponsibility(@ModelAttribute User user,HttpSession session,RedirectAttributes attributes) {	
		boolean flag=false;
		try{
			String userId = (String) session.getAttribute("USER_ID");
			user.setUser_id(userId);
			user.setFrom_date(DateParser.parse(user.getFrom_date()));	
			user.setTo_date(DateParser.parse(user.getTo_date()));	
			flag =  profileService.deleteLeaveResponsibility(user);
		}catch (Exception e) 
		{
			attributes.addFlashAttribute("error","Delete Leave Responsibility is failed. Try again.");
			logger.error("Delete Leave Responsibility : " + e.getMessage());
		}
		return flag;
	}	
	
	@RequestMapping(value="/auto-responsibility",method=RequestMethod.GET)
	public void autoResponsibility(){
		try 
		{
	        boolean flag1 = profileService.generateAutoResponsibility();
	        boolean flag2 = profileService.generateRevertAutoResponsibility();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("autoResponsibility : " + e.getMessage());
		}
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
	
	@RequestMapping(value = "/ajax/sendOTPtomail", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public static int sendOTPtomail(int OTP,HttpSession session) {
		User loginuser = (User)session.getAttribute("user");
		String recipients=loginuser.getEmail_id();
		if (!StringUtils.isEmpty(recipients)) {
			EMailSender emailSender = new EMailSender();
			emailSender.sendOTPEmail(recipients,OTP);
		}
		return 1;
	}
	
	@RequestMapping(value = "/ajax/sendOTPtomailforResetPassword", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public static int sendOTPtomailforResetPassword(int OTP,String Email) {
		String recipients=Email;
		if (!StringUtils.isEmpty(recipients)) {
			EMailSender emailSender = new EMailSender();
			emailSender.sendOTPEmail(recipients,OTP);
		}
		return 1;
	}		
	
	@RequestMapping(value = "/ajax/checkLoggedInUserEmail", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public static boolean checkLoggedInUserEmail(HttpSession session) 
	{
		User loginuser = (User)session.getAttribute("user");
		if (StringUtils.isEmpty(loginuser.getEmail_id())) 
		{
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/ajax/checkUserId", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean checkUserId(String user_id) {
		boolean flag = false;
		try {
			flag = loginService.checkUserId(user_id);
		} catch (SQLException e) {
			logger.error("checkUserId : " + e.getMessage());
		}
		return flag;
	}
	
	@RequestMapping(value = "/ajax/getModulesCount", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int getModulesCount() throws Exception {
		int cnt = 0;
		try {
			cnt = profileService.getModulesCount();
		} catch (SQLException e) {
			logger.error("getModulesCount : " + e.getMessage());
		}
		return cnt;
	}	
	
	@RequestMapping(value = "/ajax/checkUserEmail", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean checkUserEmail(String email_id) {
		boolean flag = false;
		try {
			flag = loginService.checkUserEmail(email_id);
		} catch (SQLException e) {
			logger.error("checkUserEmail : " + e.getMessage());
		}
		return flag;
	}	
	
	
	@RequestMapping(value = "/ajax/checkLoggedInUserPassword", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean checkLoggedInUserPassword(String password,HttpSession session){
		boolean process=false;
		try {
			User loginuser = (User)session.getAttribute("user");
			if (!StringUtils.isEmpty(loginuser.getPassword())) {
				String decryptPwd = EncryptDecrypt.decryptX(loginuser.getPassword());
				if(!StringUtils.isEmpty(password) && password.equals(decryptPwd)) {
					process = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("checkLoggedInUserPassword() : "+e.getMessage());
		}		
		return process;
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
	
	/*@RequestMapping(value = "/encrypt-user-passwords", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int encryptUserPasswords(){
		int count = 0;
		try{
			count = loginService.encryptUserPasswords();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("encryptUserPasswords() : "+e.getMessage());
		}
		return count;
	}*/
	
	@RequestMapping(value = "/encrypt-user-passwords", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView encryptUserPasswords(){
		ModelAndView model = new ModelAndView("redirect:/home");
		int count = 0;
		try{
			count = loginService.encryptUserPasswords();
			logger.error("encryptUserPasswords() >> success count : "+count);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("encryptUserPasswords() : "+e.getMessage());
		}
		return model;
	}
}
