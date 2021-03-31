package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	
	
	//@Scheduled(cron = "0 0/3 * * * *")	//  = every minute.
	//@Scheduled(cron = "0 50 10 * * *")	//  = every day 2:10 am.
	//@Scheduled(cron = "0 10 16 * * *")	//  = every day 4:10 pm.
	//@Scheduled(cron = "${cron.expression.generate.alerts}")
	public void generateAlertsByCronJob(){		
	     String message = "Method executed every day. Current time is :: "+ new Date();
	     
	     logger.error("generateAlertsByCronJob : "+message);	    
	     try {
	    	 
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAlertsByCronJob : "+flag);
			
			flag = service.sendAlertsToRajivRavi();
			logger.error("generateAlertsByCronJob >> Sending mails : "+ flag); 
			
			//Calling stored procedures
			flag = service.callingStoredProcedures();
			logger.error("generateAlertsByCronJob >> Run Procedures : "+ flag);
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByCronJob() : "+e.getMessage());
		 }
	}
	
	//@Scheduled(cron = "0 25 15 * * MON")
	//@Scheduled(cron = "${cron.expression.sending.alert.mails}")
	public void sendNotificationAlertMailsToAllByCronJob(){		
	     String message = "Method executed every Monday. Current time is :: "+ new Date();
	     
	     logger.error("sendNotificationAlertMailsToAllByCronJob : "+message);	    
	     try {
	    	boolean flag = service.sendNotificationAlertMails();
		    logger.error("sendNotificationAlertMailsToAllByCronJob >> Sending mails : "+ flag);
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendNotificationAlertMailsToAllByCronJob() : "+e.getMessage());
		 }
	}
	
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
	
	@RequestMapping(value="/generate-and-send-alerts-to-all",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAndSendAlertsToAllByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	logger.error("generateAndSendAlertsToAllByManual : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAndSendAlertsToAllByManual : "+flag);
			
		    flag = service.sendNotificationAlertMails();
		    logger.error("generateAndSendAlertsToAllByManual >> Sending mails : "+ flag); 
		    //System.out.println("Sending mails : "+ flag); 
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAndSendAlertsToAllByManual() : "+e.getMessage());
		 }
	     return model;
	}
	

	
	@RequestMapping(value="/generate-and-send-alerts-rajiv-ravi",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAndSendAlertsToRajivRaviByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	logger.error("generateAndSendAlertsToRajivRaviByManual : start");
	    	//System.out.println("Start "+ new Date());
            boolean flag = service.generateAterts();
            //System.out.println("End "+ new Date());
	    	logger.error("generateAndSendAlertsToRajivRaviByManual : "+flag);
	    	 
			flag = service.sendAlertsToRajivRavi();
			logger.error("generateAndSendAlertsToRajivRaviByManual >> Sending mails : "+ flag); 
			//System.out.println("Sending mails : "+ flag); 
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("generateAlertsByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/send-alerts-rajiv-ravi",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendAlertsToRajivRaviByManual(){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
	     try {
	    	logger.error("sendAlertsToRajivRaviByManual : start");
	    	 
			boolean flag = service.sendAlertsToRajivRavi();
			logger.error("sendAlertsToRajivRaviByManual >> Sending mails : "+ flag); 
			//System.out.println("Sending mails : "+ flag); 
			
		 } catch (Exception e) {
			 e.printStackTrace();
			logger.error("sendAlertsToRajivRaviByManual() : "+e.getMessage());
		 }
	     return model;
	}
	
	@RequestMapping(value="/generate-alerts-manually",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateAlertsManually(){		
		 ModelAndView model = new ModelAndView("redirect:/get-alerts");	    
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
	public void getAlertssList(@ModelAttribute Alerts obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null,user_role_name=null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			obj.setUser_id(userId);obj.setUser_role_name(user_role_name);
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

			List<Alerts> budgetList = new ArrayList<Alerts>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//budgetList = getListBasedOnSearchParameter(searchParameter,budgetList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			AlertsPaginationObject personJsonObject = new AlertsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(budgetList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"objList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
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
