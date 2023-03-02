package com.synergizglobal.pmis.RESTful;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.Iservice.TableauDashboardService;
import com.synergizglobal.pmis.Iservice.WebDocumentsService;
import com.synergizglobal.pmis.Iservice.WebLinksService;
import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.WebDocuments;
import com.synergizglobal.pmis.model.WebLinks;

@CrossOrigin(origins="*" ,maxAge = 3600)
@RestController
@RequestMapping("/mobileapp")
public class RESTfullController {
	Logger logger = Logger.getLogger(RESTfullController.class);
	
	@Autowired
	LoginService loginService;
	
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	WebDocumentsService webDocumentsService;
	
	@Autowired
	WebLinksService webLinksService;
	
	@Autowired
	TableauDashboardService tableauDashboardService;
	
	@Autowired
	AlertsService alertsService;
	
	@Value("${Login.Form.Invalid}")
	public String invalidUserName;
	
	@Value("${Login.Form.Valid.User.pwd}")
	public String validUserNamePassword;
	
	@Value("${common.error.message}")
	public String commonError;
	
	
	@Value("${message.password.reset.fail}")
	public String passwordResetFail;
	
	@Value("${message.password.reset.success}")
	public String passwordResetSuccess;
	
	@Value("${message.wrong.password.entered}")
	public String wrongPasswordEntered;
	
	/**
	 * App Login method
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/app-login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response appLogin(@RequestBody() User user){
		Response response = new Response();
		User userDetails = null;
		try{
			
			if(!StringUtils.isEmpty(user.getUser_id()) && !StringUtils.isEmpty(user.getPassword())){
				userDetails = loginService.validateUser(user,null);
				if(!StringUtils.isEmpty(userDetails)){ 
					response.setSuccess(true);
					response.setResult(userDetails);
				}else{
					response.setSuccess(false);
					response.setError(invalidUserName);
				}
			}else{
				response.setSuccess(false);
				response.setError(validUserNamePassword);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("appLogin() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	

	
	
	
	/**
	 * changePassword
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response changePassword(@RequestBody User user){
		Response response = new Response();
		try{			
			String temp = loginService.changePassword(user);
			if(temp.equals("true")) {
				response.setSuccess(true);
				response.setResult(passwordResetSuccess);
			}else if(temp.equals("false")) {
				response.setSuccess(false);
				response.setError(wrongPasswordEntered);
			}else{
				response.setSuccess(false);
				response.setError(commonError);
			}			
		}catch(Exception e){
			logger.error("changePassword : " + e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	/**
	 * Tableau dashboards list for menu
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/TableauDashboardsModulesList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getTableauDashboardsModulesList(@RequestBody User user){
		Response response = new Response();
		List<TableauDashboard> modulesList = null;
		try{
			
			String dashboardType = "Module";
			String base = "mobile";
			modulesList = homeService.getDashboardsList(dashboardType,base,user);
			
			response.setSuccess(true);
			response.setResult(modulesList);
		}catch(Exception e){
			logger.error("getTableauDashboardsModulesList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	
	/**
	 * Tableau dashboards list for menu
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/TableauDashboardsProjectsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getTableauDashboardsProjectsList(@RequestBody User user){
		Response response = new Response();
		List<TableauDashboard> projectsList = null;
		try{
			String dashboardType = "Project";
			String base = "mobile";
			projectsList = homeService.getDashboardsList(dashboardType,base,user);
			
			response.setSuccess(true);
			response.setResult(projectsList);
		}catch(Exception e){
			logger.error("getTableauDashboardsProjectsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	
	/**
	 * single method for all tableau urls
	 * @param param1
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/InfoViz/{infovizId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response tableauView(@PathVariable(value = "infovizId") String infovizId){
		Response response = new Response();
		TableauDashboard infoviz = null;
		try{			
			infoviz = tableauDashboardService.getTableauUrlForMobile(infovizId);
			if(!StringUtils.isEmpty(infoviz)){	
				if(!StringUtils.isEmpty(infoviz.getTableauUrl())){
					String server_name = "Syntrack";
					if(infoviz.getTableauUrl().contains(".com/")) {
						server_name = "Syntrack";
					}else {
						server_name = "MRVC";
					}
					TableauTrustedTicket tObj = new TableauTrustedTicket();
					String trustedTokenId =  tObj.getTrustedTicket(server_name);
					CommonConstants cObj = new CommonConstants();
					String baseUrl = cObj.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
					String[] url = {};
					if(infoviz.getTableauUrl().contains(".com/")) {
						url = infoviz.getTableauUrl().split(".com/");
						baseUrl = cObj.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
					}else {
						url = infoviz.getTableauUrl().split(":8000/");
						baseUrl = cObj.BASE_URL_MRVC.replace("{0}", trustedTokenId);
					}
					
					String tableauUrl = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;
					infoviz.setTableauUrl(tableauUrl);
					infoviz.setTableauTrustedToken(trustedTokenId);
				}
				
				response.setSuccess(true);
				response.setResult(infoviz);				
			}else{
				response.setSuccess(false);
				response.setError(commonError);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractCategory() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	/**
	 * getting Forms List for side menu
	 * @return
	 */
	@RequestMapping(value = "/getFormsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getFormsList(@RequestBody User user){
		Response response = new Response();
		List<Forms> forms = null;
		try{
			String base = "mobile";
			forms = homeService.getFormsList(base,user);
			response.setSuccess(true);
			response.setResult(forms);
		}catch(Exception e){
			logger.error("getFormsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	/**
	 * getting Reports List for side menu
	 * @return
	 */
	@RequestMapping(value = "/getReportFormsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getReportsList(@RequestBody User user){
		Response response = new Response();
		List<Forms> forms = null;
		try{
			String base = "mobile";
			forms = homeService.getReportFormsList(base,user);
			response.setSuccess(true);
			response.setResult(forms);
		}catch(Exception e){
			logger.error("getReportFormsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	@RequestMapping(value = "/getWebDocumentFormsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getWebDocumentFormsList(){
		Response response = new Response();
		List<WebDocuments> webDocumentTypes = null;
		try{
			webDocumentTypes = webDocumentsService.getWebDocumentTypes(null);
			response.setSuccess(true);
			response.setResult(webDocumentTypes);
		}catch(Exception e){
			logger.error("getWebDocumentFormsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	@RequestMapping(value = "/getQuickLinksList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getQuickLinksList(){
		Response response = new Response();
		List<WebLinks> webLinksList = null;
		try{
			webLinksList = webLinksService.getWebLinks(null);
			response.setSuccess(true);
			response.setResult(webLinksList);
		}catch(Exception e){
			logger.error("getQuickLinksList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	@RequestMapping(value = "/getAlertsCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getAlertsCount(@RequestBody Alerts obj){
		Response response = new Response();
		int count = 0;
		Alerts aObj = new Alerts();
		try{
			count = alertsService.getAlertsCount(obj);
			aObj.setCount(String.valueOf(count));
			response.setSuccess(true);
			response.setResult(aObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAlertsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	@RequestMapping(value = "/getAlertsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getAlertsList(){
		Response response = new Response();
		Map<String,List<Alerts>> alertsList = null;
		try{
			Alerts obj = new Alerts();
			alertsList = alertsService.getAlertsForHeaderNotifications(obj);
			response.setSuccess(true);
			response.setResult(alertsList);
		}catch(Exception e){
			logger.error("getAlertsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	/***********************************************************************************************************************/
	
}
