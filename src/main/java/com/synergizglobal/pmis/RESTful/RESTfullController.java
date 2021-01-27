package com.synergizglobal.pmis.RESTful;

import java.util.List;

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

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.LoginService;
import com.synergizglobal.pmis.Iservice.TableauDashboardService;
import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;

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
	TableauDashboardService tableauDashboardService;
	
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
				userDetails = loginService.validateUser(user);
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
			modulesList = homeService.getDashboardsList(dashboardType);
			
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
			projectsList = homeService.getDashboardsList(dashboardType);
			
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
					String[] url = {};
					if(infoviz.getTableauUrl().contains(".com/")) {
						url = infoviz.getTableauUrl().split(".com/");
					}else {
						url = infoviz.getTableauUrl().split(":8000/");
					}
					String trustedTokenId =  TableauTrustedTicket.getTrustedTicket();
					String baseUrl = CommonConstants.BASE_URL.replace("{0}", trustedTokenId);
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
	public Response getFormsList(){
		Response response = new Response();
		List<Forms> forms = null;
		try{
			String base = "mobile";
			forms = homeService.getFormsList(base);
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
	public Response getReportsList(){
		Response response = new Response();
		List<Forms> forms = null;
		try{
			String base = "mobile";
			forms = homeService.getReportFormsList(base);
			response.setSuccess(true);
			response.setResult(forms);
		}catch(Exception e){
			logger.error("getReportFormsList() : "+e.getMessage());
			response.setSuccess(false);
			response.setError(commonError);
		}
		return response;
	}
	
	/***********************************************************************************************************************/
	
}
