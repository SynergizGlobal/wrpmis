package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.AlertsService;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.AlertsPaginationObject;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.BudgetPaginationObject;
import com.synergizglobal.pmis.model.User;

@Controller
public class AlertsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	public static Logger logger = Logger.getLogger(AlertsController.class);
	
	@Autowired
	AlertsService service;
	
	@Value("${common.error.message}")
	public String commonError;	
	
	@RequestMapping(value="/run-procedures",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView runProcedures(){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	boolean flag = service.callingStoredProcedures();
	    	System.out.println("flag : "+flag);
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("rubProcedures() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/generate-send-alerts-page",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateSendAlertsFromPage(){		
		 ModelAndView model = new ModelAndView(PageConstants2.generateSendAlerts);	    
	     try {
	    	 List<Alerts> alertTypes = service.getAlertTypesForGenerateSendAlerts();
	    	 model.addObject("alertTypes",alertTypes);
	    	 
	    	 List<Alerts> alertLevels = service.getAlertLevelsForGenerateSendAlerts();
	    	 model.addObject("alertLevels",alertLevels);
	    	 
	    	 List<Alerts> sendToList = service.getSendToListForGenerateSendAlerts();
	    	 model.addObject("sendToList",sendToList);
		 } catch (Exception e) {
			logger.error("generateSendAlertsFromPage() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/generate-alerts-manually",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAlertsManually(){		
		 ModelAndView model = new ModelAndView("redirect:/generate-send-alerts-page");	    
	     try {
	    	logger.error("generateAlertsManually : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsManually : "+flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-alerts-to-particulars",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendAlertsToParticulars(@ModelAttribute Alerts obj,RedirectAttributes attributes){		
		 ModelAndView model = new ModelAndView("redirect:/generate-send-alerts-page");	    
	     try {
	    	logger.error("sendAlertsToParticulars : start");
			
		    boolean flag = service.sendAlertsToParticulars(obj);
		    logger.error("sendAlertsToParticulars >> Sending mails : "+ flag); 
		    
		    if(flag) {
		    	attributes.addFlashAttribute("success", "Alerts sent successfully.");
		    }else {
		    	attributes.addFlashAttribute("error", "Sending alerts failed.");
		    }
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendAlertsToParticulars() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-contract-alerts-to-all",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendContractAlertsToAllByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/generate-send-alerts-page");	    
	     try {
	    	   Date date = new Date();
			   Calendar cal = Calendar.getInstance();
	           cal.setTime(date); // don't forget this if date is arbitrary
	             
	           SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
	           SimpleDateFormat dayOfWeekTextFormat = new SimpleDateFormat("EEEE");
	           String dayOfWeekText = dayOfWeekTextFormat.format(date).toUpperCase();
	           //int month = cal.get(Calendar.MONTH); // 0 being January
	           
				/*String alert_type = null;
				if(dayOfWeekText.equals("MONDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_BANK_GUARANTEE;
				}else if(dayOfWeekText.equals("TUESDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_INSURANCE;
				}else if(dayOfWeekText.equals("WEDNESDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_CONTRACT_PERIOD;
				}else if(dayOfWeekText.equals("THURSDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_CONTRACT_VALUE;
				}else if(dayOfWeekText.equals("FRIDAY")) {
				   alert_type = CommonConstants2.ALERT_TYPE_ISSUE;
				}*/
	           
	           String alert_type = null;
	           //if(dayOfWeekText.equals("WEDNESDAY")) {
	        	   alert_type = CommonConstants2.ALERT_TYPE_CONTRACT;
	        	   boolean flag = service.sendEMailNotificationWithContractAlerts(alert_type);
				   logger.error("sendContractAlertsToAllByManual >> Sent mails : "+ flag);
	           //}
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendContractAlertsToAllByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-issue-alerts-to-all",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendIssueAlertsToAllByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/generate-send-alerts-page");	    
	     try {
	    	   Date date = new Date();
			   Calendar cal = Calendar.getInstance();
	           cal.setTime(date); // don't forget this if date is arbitrary
	             
	           SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
	           SimpleDateFormat dayOfWeekTextFormat = new SimpleDateFormat("EEEE");
	           String dayOfWeekText = dayOfWeekTextFormat.format(date).toUpperCase();
	           //int month = cal.get(Calendar.MONTH); // 0 being January
	           String alert_type = null;
	           //if(dayOfWeekText.equals("FRIDAY")) {
	        	   alert_type = CommonConstants2.ALERT_TYPE_ISSUE;
	        	   logger.error("sendIssueAlertsToAllByManual : start");
			       boolean flag = service.sendEMailNotificationWithIssueAlerts(alert_type);
			       logger.error("sendIssueAlertsToAllByManual >> Sending mails : "+ flag); 
	           //}
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendAlertsToAllByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-risk-alerts-to-all",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendEMailNotificationWithRiskAlertsByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/generate-send-alerts-page");	    
	     try {
	    	logger.error("sendEMailNotificationWithRiskAlertsByManual : start");
		    
		    boolean flag = service.sendEMailNotificationWithRiskAlerts();
			logger.error("sendEMailNotificationWithRiskAlertsByManual >> Sent mails : "+ flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendEMailNotificationWithRiskAlertsByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-alerts-to-it-admins",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendEMailNotificationAlertsToITAdminsByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/generate-send-alerts-page");	    
	     try {
	    	logger.error("sendEMailNotificationAlertsToITAdminsByManual : start");
	    	 
			boolean flag = service.sendEMailNotificationAlertsToITAdmins();
			logger.error("sendEMailNotificationAlertsToITAdminsByManual >> Sent mails : "+ flag); 
			//System.out.println("Sending mails : "+ flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendEMailNotificationAlertsToITAdminsByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	/********************************************************************************************/
	
	@RequestMapping(value="/get-alerts",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getAlertsList(HttpSession session){		
		 ModelAndView model = new ModelAndView(PageConstants2.alertsGrid);	    
	     try {
	    	 //String user_Id = (String) session.getAttribute("USER_ID");
	    	 //String userName = (String) session.getAttribute("USER_NAME");
	    	 User uObj = (User) session.getAttribute("user");
	    	 model.addObject("user_id", uObj.getUser_id());
	    	 model.addObject("email_id", uObj.getEmail_id());
	    	 model.addObject("user_role_name", uObj.getUser_role_name_fk());
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("getAlerts() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/get-alerts/{alert_id}",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getAlertsList(@PathVariable("alert_id") String alert_id,HttpSession session){		
		 ModelAndView model = new ModelAndView(PageConstants2.alertsGrid);	    
	     try {
	    	 //String user_Id = (String) session.getAttribute("USER_ID");
	    	 //String userName = (String) session.getAttribute("USER_NAME");
	    	 User uObj = (User) session.getAttribute("user");
	    	 model.addObject("user_id", uObj.getUser_id());
	    	 model.addObject("email_id", uObj.getEmail_id());
	    	 model.addObject("user_role_name", uObj.getUser_role_name_fk());	    	 
	    	 model.addObject("alert_id", alert_id);
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("getAlerts() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value = "/ajax/getAlerts", method = { RequestMethod.POST, RequestMethod.GET })
	public void getAlertsList(@ModelAttribute Alerts obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null,user_role_name=null,user_role_code = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setUser_id(userId);obj.setUser_role_code(user_role_code);
			pw = response.getWriter();
			//Fetch the page number from client
			Integer pageNumber = 0;
			Integer pageDisplayLength = 0;
			if (null != request.getParameter("iDisplayStart")) {
				pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			}
			//Fetch search parameter
			String searchParameter = request.getParameter("sSearch");

			//Fetch Page display length
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			List<Alerts> alertList = new ArrayList<Alerts>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				alertList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				alertList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//alertList = getListBasedOnSearchParameter(searchParameter,alertList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			AlertsPaginationObject personJsonObject = new AlertsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(alertList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getAlertsList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Alerts obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = service.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	public List<Alerts> createPaginationData(int startIndex, int offset, Alerts obj, String searchParameter) {
		List<Alerts> objList = null;
		try {
			objList = service.getAlertsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getContractorsFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getContractorsFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getContractorsFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getContractsFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getContractsFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getHODFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getHODFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getHODFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getWorksFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getWorksFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getAlertTypesFilterListInAlerts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Alerts> getAlertTypesFilterListInAlerts(@ModelAttribute Alerts obj) {
		List<Alerts> objsList = null;  
		try {
			objsList = service.getAlertTypesFilterListInAlerts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAlertTypesFilterListInAlerts : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-alert-remarks",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addAlertRemarks(@ModelAttribute Alerts obj,HttpSession session,RedirectAttributes attributes){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	 String user_Id = (String) session.getAttribute("USER_ID");
	    	 //String userName = (String) session.getAttribute("USER_NAME");
	    	 obj.setUser_id_fk(user_Id);
	    	 boolean flag = service.addAlertRemarks(obj);
	    	 if(flag) {
	    		 attributes.addFlashAttribute("success", "Remarks addedd successfully.");
	    	 }else {
	    		 attributes.addFlashAttribute("error", "Adding remarks failed.");
	    	 }
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("addAlertRemarks() : "+e.getMessage());
		 }
	     return model;
	}
	
}
