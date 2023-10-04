package com.synergizglobal.pmis.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.ModuleDashboardsService;
import com.synergizglobal.pmis.Iservice.OverviewDashboardService;
import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.common.UrlGenerator;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.OverviewDashboard;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;

@Controller
public class OverviewDashboardController {
	Logger logger = Logger.getLogger(OverviewDashboardController.class);
	
	@Autowired
	OverviewDashboardService overviewDashboardService;
	
	@Autowired
	ModuleDashboardsService moduleDashboardsService;	
	
	@Autowired
	HomeService service;	
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/overview-dashboard/{dashboardId}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView overviewDashboard(@PathVariable("dashboardId") String dashboardId,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.overviewDashboard);
			model.addObject("dashboardId", dashboardId);
			model.addObject("dashboard_type", "Modules");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardByWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorkDroneSurveyCount", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int getWorkDroneSurveyCount(String work_id) throws Exception {
		int cnt = 0;
		try {
			cnt = overviewDashboardService.getWorkDroneSurveyCount(work_id);
		} catch (SQLException e) {
			logger.error("getModulesCount : " + e.getMessage());
		}
		return cnt;
	}	
	
	
	@RequestMapping(value="/ta-dashboard",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView taDashboard(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.taDashboard);
		    model.addObject("dashboard_type", "Works");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("taDashboard : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/AIIBDisbursement/{work_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView taDashboard(@PathVariable("work_id") String work_id,HttpSession session,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.aIIBDisbursement);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("taDashboard : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/OtherWorks",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView otherWorks(HttpSession session,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.otherWorks);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("otherWorks : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/ajax/getAIIBDashboardURL", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OverviewDashboard getAIIBDashboardURL(@ModelAttribute OverviewDashboard dObj,HttpSession session){
		String user_Id = null;String userName = null;
		String tableauUrl = "";
		try{
			user_Id = (String) session.getAttribute("USER_ID"); userName = (String) session.getAttribute("USER_NAME");
		

			if(!StringUtils.isEmpty(dObj) && !StringUtils.isEmpty(dObj.getDashboard_url()) && !"structure-gallery-page".equals(dObj.getDashboard_url()) 
					 && !"wbs-tree".equals(dObj.getDashboard_url())){
				String dashboardUrl = dObj.getDashboard_url();
				String server_name = "MRVC";
				if(dashboardUrl.contains(".com/")) {
					server_name = "Syntrack";
				}else {
					server_name = "MRVC";
				}
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket(server_name);
				
				String[] url = {};

					url = dashboardUrl.split(":8000/");
					UrlGenerator ugObj = new UrlGenerator();
					String baseUrl = CommonConstants.BASE_URL_MRVC.replace("{0}", "203.153.40.44");
					baseUrl = baseUrl.replace("{1}", trustedTokenId);
					tableauUrl = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;
					
					
					
					String clientIpMap=tObj.getExternalIpAddress();
					
					String Str5[]=clientIpMap.split("\\.");
					String Concat=Str5[2]+'.'+Str5[3];

					 String Str[]=tObj.myPublicIp().split("___");
					 String ipnew=Str[4];
					 String Str1[]=ipnew.split(":");
					 String ipnew1=Str1[1];	
					 
					String Str6[]=ipnew1.split("\\.");
					String ConcatNew=Str6[0]+'.'+Str6[1]+'.'+Concat;
					String SMStr=Str6[0]+'.'+Str6[1];
					
					if(ConcatNew.compareTo("  203.153.39.186")==0)
					{
			

							tableauUrl =baseUrl +"/"+ url[1]+CommonConstants.TABLEAU_PARAMS;
												
					}
					else
					{
						String mainUrl[]=baseUrl.split("/");
						String weburl=mainUrl[2];
						if(weburl.compareTo("203.153.40.44:8000")==0)
						{
							weburl="203.153.40.44:8000";
						}
						else if(weburl.compareTo("pmis.mrvc.gov.in:8000")==0)
						{
							weburl="pmis.mrvc.gov.in:8000";
						}				

						tableauUrl =mainUrl[0]+"//"+weburl +"/"+ url[1]+CommonConstants.TABLEAU_PARAMS+"&:embed=y";
							
					}					
					
					
				}
			dObj.setDashboard_url(tableauUrl.toString());	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashboardURL() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return dObj;
	}	
	
	
	@RequestMapping(value = "/ajax/getTALeftNav", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboard> getTALeftNavNodes(@ModelAttribute OverviewDashboard obj,HttpSession session) {
		List<OverviewDashboard> overviewDashboard = null;
		try {
			String parentId = "0";
			obj.setParent_id(parentId);

			User uObj = (User) session.getAttribute("user");
 			obj.setUser_type_fk(uObj.getUser_type_fk());
 			obj.setUser_role_name_fk(uObj.getUser_role_name_fk());
			obj.setUser_id(uObj.getUser_id());
			obj.setDashboard_type("Works");
			
			overviewDashboard = moduleDashboardsService.getTALeftNavNodes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTALeftNav : " + e.getMessage());
		}
		return overviewDashboard;
	}	

	@RequestMapping(value="/work-overview-dashboard/{work_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView overviewDashboardByWork(@PathVariable("work_id") String work_id,HttpSession session,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.overviewDashboard);
			model.addObject("work_id", work_id);
			model.addObject("dashboard_type", "Works");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardByWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/work-overview-dashboard/{work_id}/{dashboardId}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView overviewDashboardListByWork(@PathVariable("work_id") String work_id,@PathVariable("dashboardId") String dashboardId,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.overviewDashboard);
			
			model.addObject("work_id", work_id);
			model.addObject("dashboardId", dashboardId);
			model.addObject("dashboard_type", "Works");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardListByWork : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/archive-overview-dashboard",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView archiveOverviewDashboard(HttpSession session,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.archiveDashboardsMenu);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardByWork : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/archive-overview-dashboard/{dashboardId}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView archiveOverviewDashboard(@PathVariable("dashboardId") String dashboardId,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.archiveDashboards);
			model.addObject("dashboardId", dashboardId);
			model.addObject("dashboard_type", "Modules");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardByWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/archive-work-overview-dashboard/{work_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView archiveOverviewDashboardByWork(@PathVariable("work_id") String work_id,HttpSession session,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.archiveDashboards);
			model.addObject("work_id", work_id);
			model.addObject("dashboard_type", "Works");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardByWork : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/ajax/getLeftNav", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboard> getLeftNavNodes(@ModelAttribute OverviewDashboard obj,HttpSession session) {
		List<OverviewDashboard> overviewDashboard = null;
		try {
			String parentId = "0";
			obj.setParent_id(parentId);

			User uObj = (User) session.getAttribute("user");
 			obj.setUser_type_fk(uObj.getUser_type_fk());
 			obj.setUser_role_name_fk(uObj.getUser_role_name_fk());
			obj.setUser_id(uObj.getUser_id());
			
			overviewDashboard = overviewDashboardService.getLeftNavNodes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLeftNavNodes : " + e.getMessage());
		}
		return overviewDashboard;
	}	
	
	@RequestMapping(value = "/ajax/getLeftNavArchiveModules", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TableauDashboard> getLeftNavArchiveModules(@ModelAttribute TableauDashboard obj,HttpSession session,HttpServletRequest request) {
		List<TableauDashboard> overviewDashboard = null;
		try {
			User userDetails = (User)request.getSession().getAttribute("user");
			String base = "web";
			String dashboardType = "Module";
			overviewDashboard = service.getDashboardsList(dashboardType,base,userDetails);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLeftNavArchiveModules : " + e.getMessage());
		}
		return overviewDashboard;
	}
	
	@RequestMapping(value = "/ajax/getLeftNavArchiveWorks", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TableauDashboard> getLeftNavArchiveWorks(@ModelAttribute TableauDashboard obj,HttpSession session,HttpServletRequest request) {
		List<TableauDashboard> overviewDashboard = null;
		try {
			User userDetails = (User)request.getSession().getAttribute("user");
			String base = "web";
			String dashboardType = "Project";
			overviewDashboard = service.getDashboardsList(dashboardType,base,userDetails);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLeftNavArchiveWorks : " + e.getMessage());
		}
		return overviewDashboard;
	}	
	
	@RequestMapping(value = "/ajax/getDashboardLeftMenuAccess", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean getDashboardLeftMenuAccess(String dashboard_id,String level,HttpSession session) throws Exception {
			boolean flag=false;
			OverviewDashboard obj = new OverviewDashboard();
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_name_fk(uObj.getUser_role_name_fk());
			obj.setUser_id(uObj.getUser_id());
			obj.setDashboard_id(dashboard_id);
			obj.setLevel(level);
			try {
				flag=overviewDashboardService.getDashboardLeftMenuAccess(obj);

			} catch (SQLException e) {
				logger.error("checkUserEmail : " + e.getMessage());
			}
			return flag;			
	
	}	
	
	@RequestMapping(value = "/ajax/getDashboardURL", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OverviewDashboard getDashboardURL(@ModelAttribute OverviewDashboard dObj,HttpSession session){
		String user_Id = null;String userName = null;
		OverviewDashboard obj = new OverviewDashboard();
		String tableauUrl = "";
		try{
			user_Id = (String) session.getAttribute("USER_ID"); userName = (String) session.getAttribute("USER_NAME");
			/*dashboardName = dashboardName.replaceAll("_", "&");
			dashboardName = dashboardName.replaceAll("--", " ");*/
			
			
			String dashboard_id = dObj.getDashboard_id();
			String work_id = dObj.getWork_id();
			String params = dObj.getParams();
			obj = overviewDashboardService.getTableauUrl(dashboard_id);

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_url()) && !"structure-gallery-page".equals(obj.getDashboard_url()) 
					 && !"wbs-tree".equals(obj.getDashboard_url())){
				String dashboardUrl = obj.getDashboard_url();
				if(!StringUtils.isEmpty(params)) {
					params = decodeURIComponent(params);
				}
				if(!StringUtils.isEmpty(work_id) && !StringUtils.isEmpty(params)) {
					params = params + "&"+obj.getSource_field_name()+"="+work_id;
				}else if(!StringUtils.isEmpty(work_id)){
					params = obj.getSource_field_name()+"="+work_id;
				}
				String server_name = "MRVC";
				if(dashboardUrl.contains(".com/")) {
					server_name = "Syntrack";
				}else {
					server_name = "MRVC";
				}
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				/*String trustedTokenId =  tObj.getTrustedTicket(server_name);
				//String baseUrl = CommonConstants.BASE_URL_SYNTRACK.replace("{0}", "infoviz.syntrackpro.com");
				//baseUrl = baseUrl.replace("{1}", trustedTokenId);
				String[] url = {};
				//if(dashboardUrl.contains(".com/")) {
				//	url = dashboardUrl.split(".com/");
				//	//baseUrl = CommonConstants.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
				//	baseUrl = CommonConstants.BASE_URL_SYNTRACK.replace("{0}", "infoviz.syntrackpro.com");
				//	baseUrl = baseUrl.replace("{1}", trustedTokenId);
				//}else {
					url = dashboardUrl.split(":8000/");
					//baseUrl = CommonConstants.BASE_URL_MRVC.replace("{0}", trustedTokenId);
					UrlGenerator ugObj = new UrlGenerator();
					String baseUrl = CommonConstants.BASE_URL_MRVC.replace("{0}", "203.153.40.44");
					baseUrl = baseUrl.replace("{1}", trustedTokenId);
				//}
					

					String clientIpMap=tObj.getExternalIpAddress();
					
					String Str5[]=clientIpMap.split("\\.");
					String Concat=Str5[2]+'.'+Str5[3];

					 String Str[]=tObj.myPublicIp().split("___");
					 String ipnew=Str[4];
					 String Str1[]=ipnew.split(":");
					 String ipnew1=Str1[1];	
					 
					String Str6[]=ipnew1.split("\\.");
					String ConcatNew=Str6[0]+'.'+Str6[1]+'.'+Concat;
					String SMStr=Str6[0]+'.'+Str6[1];
					System.out.println(ConcatNew);
					
					if(ConcatNew.compareTo("  203.153.39.186")==0)
					{
			
						if(!StringUtils.isEmpty(params)) {
							tableauUrl =  baseUrl +"/"+ url[1]+CommonConstants.TABLEAU_PARAMS+"&"+params;
						}else {
							tableauUrl =baseUrl +"/"+ url[1]+CommonConstants.TABLEAU_PARAMS;
						}						
					}
					else
					{
						String mainUrl[]=baseUrl.split("/");
						String weburl=mainUrl[2];
						if(weburl.compareTo("203.153.40.44:8000")==0)
						{
							weburl="203.153.40.44:8000";
						}
						else if(weburl.compareTo("pmis.mrvc.gov.in:8000")==0)
						{
							weburl="pmis.mrvc.gov.in:8000";
						}				
						if(!StringUtils.isEmpty(params)) {
							tableauUrl =  mainUrl[0]+"//"+weburl +"/"+ url[1]+CommonConstants.TABLEAU_PARAMS+"&"+params;
						}else {
							tableauUrl =mainUrl[0]+"//"+weburl +"/"+ url[1]+CommonConstants.TABLEAU_PARAMS;
						}						
					}*/
				
				String[] url = {};
				url = dashboardUrl.split(":8000/");
				String trustedTokenId =  tObj.getTrustedTicket(server_name);
				String baseUrl = CommonConstants.BASE_URL_MRVC.replace("{0}", trustedTokenId);
				String tableauUrl1 = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;

				
				obj.setDashboard_url(tableauUrl1.toString());	
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDashboardURL() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return obj;
	}	
	
	public static String decodeURIComponent(String s) {
	    String result = ""; 
	    try {
	      if(!StringUtils.isEmpty(s)) {
	    	  result = URLDecoder.decode(s, "UTF-8");
	      }
	    } catch (UnsupportedEncodingException e) {
	          result = s;  
	    } 
	    return result;
	}
	
	@RequestMapping(value = "/ajax/getFilters", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboard> getFilters(@ModelAttribute OverviewDashboard dObj,HttpSession session){
		List<OverviewDashboard> objList = null;
		try{
			objList = overviewDashboardService.getFilters(dObj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFilters() : "+e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getArchiveDates", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboard> getArchiveDates(@ModelAttribute OverviewDashboard dObj,HttpSession session){
		List<OverviewDashboard> objList = null;
		try{
			objList = overviewDashboardService.getArchiveDates(dObj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFilters() : "+e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value = "/ajax/getFilteredOptions", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboard> getFilteredOptions(@ModelAttribute OverviewDashboard dObj,HttpSession session){
		List<OverviewDashboard> objList = null;
		try{
			String params = dObj.getParams();
			if(!StringUtils.isEmpty(params)) {
				params = decodeURIComponent(params);
				dObj.setParams(params);
			}
			objList = overviewDashboardService.getFilteredOptions(dObj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFilteredOptions() : "+e.getMessage());
		}
		return objList;
	}
			
}
