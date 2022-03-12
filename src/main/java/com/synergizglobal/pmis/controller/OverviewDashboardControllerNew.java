package com.synergizglobal.pmis.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

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

import com.synergizglobal.pmis.Iservice.OverviewDashboardServiceNew;
import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.OverviewDashboardNew;

@Controller
public class OverviewDashboardControllerNew {
	Logger logger = Logger.getLogger(OverviewDashboardControllerNew.class);
	
	@Autowired
	OverviewDashboardServiceNew overviewDashboardService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/work-overview-dashboard/{work_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView overviewDashboardByWork(@PathVariable("work_id") String work_id,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		    model.setViewName(PageConstants.overviewDashboardNew);
			
			model.addObject("work_id", work_id);
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
		    model.setViewName(PageConstants.overviewDashboardNew);
			
			model.addObject("work_id", work_id);
			model.addObject("dashboardId", dashboardId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("overviewDashboardListByWork : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/ajax/getLeftNav", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboardNew> getLeftNavNodes(@ModelAttribute OverviewDashboardNew obj,HttpSession session) {
		List<OverviewDashboardNew> overviewDashboard = null;
		try {
			String parentId = "0";
			obj.setParent_id(parentId);
			overviewDashboard = overviewDashboardService.getLeftNavNodes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLeftNavNodes : " + e.getMessage());
		}
		return overviewDashboard;
	}		
	
	@RequestMapping(value = "/ajax/getDashboardURL", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OverviewDashboardNew getDashboardURL(@ModelAttribute OverviewDashboardNew dObj,HttpSession session){
		String user_Id = null;String userName = null;
		OverviewDashboardNew obj = new OverviewDashboardNew();
		String tableauUrl = "";
		try{
			user_Id = (String) session.getAttribute("USER_ID"); userName = (String) session.getAttribute("USER_NAME");
			/*dashboardName = dashboardName.replaceAll("_", "&");
			dashboardName = dashboardName.replaceAll("--", " ");*/
			
			String dashboard_id = dObj.getDashboard_id();
			String work_id = dObj.getWork_id();
			String params = dObj.getParams();
			obj = overviewDashboardService.getTableauUrl(dashboard_id);

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_url())){
				String dashboardUrl = obj.getDashboard_url();
				if(!StringUtils.isEmpty(params)) {
					params = decodeURIComponent(params);
				}
				if(!StringUtils.isEmpty(work_id) && !StringUtils.isEmpty(params)) {
					params = params + "&"+obj.getSource_field_name()+"="+work_id;
				}else if(!StringUtils.isEmpty(work_id)){
					params = obj.getSource_field_name()+"="+work_id;
				}
				String server_name = "Syntrack";
				if(dashboardUrl.contains(".com/")) {
					server_name = "Syntrack";
				}else {
					server_name = "MRVC";
				}
				/*TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket(server_name);
				CommonConstants cObj = new CommonConstants();
				String baseUrl = cObj.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
				String[] url = {};
				if(dashboardUrl.contains(".com/")) {
					url = dashboardUrl.split(".com/");
					baseUrl = cObj.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
				}else {
					url = dashboardUrl.split(":8000/");
					baseUrl = cObj.BASE_URL_MRVC.replace("{0}", trustedTokenId);
				}
				
				if(!StringUtils.isEmpty(params)) {
					tableauUrl = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS+"&"+params;
				}else {
					tableauUrl = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;
				}*/
				logger.error("getDashboardURL() : URL : "+tableauUrl);
				obj.setDashboard_url(tableauUrl.toString());	
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
	public List<OverviewDashboardNew> getFilters(@ModelAttribute OverviewDashboardNew dObj,HttpSession session){
		List<OverviewDashboardNew> objList = null;
		try{
			objList = overviewDashboardService.getFilters(dObj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFilters() : "+e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getFilteredOptions", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboardNew> getFilteredOptions(@ModelAttribute OverviewDashboardNew dObj,HttpSession session){
		List<OverviewDashboardNew> objList = null;
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
