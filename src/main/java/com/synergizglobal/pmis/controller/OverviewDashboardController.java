package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.OverviewDashboardService;
import com.synergizglobal.pmis.common.TableauTrustedTicket;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.OverviewDashboard;
import com.synergizglobal.pmis.model.TableauDashboard;

@Controller
public class OverviewDashboardController {
	Logger logger = Logger.getLogger(OverviewDashboardController.class);
	
	@Autowired
	OverviewDashboardService overviewDashboardService;
	
	@Value("${common.error.message}")
	public String commonError;

	@RequestMapping(value="overview-dashboard",method=RequestMethod.GET)
	public ModelAndView welcome(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			int ParentId=0;
				List<OverviewDashboard> forms = overviewDashboardService.getFormsList(ParentId);
				model.addObject("overviewDashboardForms", forms);
			    model.setViewName(PageConstants.overviewDashboard);
		} catch (Exception e) {
			logger.error("welcome : " + e.getMessage());
		}
		return model;
	}
	
	
	private String capitalize(final String line) {
	   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}	

	@RequestMapping(value = "/ajax/saveLeftNavData", method = {RequestMethod.POST})
	@ResponseBody
	public boolean insertLeaveResponsibility(@ModelAttribute OverviewDashboard overviewDashboard,HttpSession session,RedirectAttributes attributes) {	
		boolean flag=false;
		try{
			flag =  overviewDashboardService.saveLeftNavData(overviewDashboard);
		}catch (Exception e) 
		{
			attributes.addFlashAttribute("error","OverviewDashboard Updating LeftNavData is failed. Try again.");
			logger.error("OverviewDashboard LeftNavData : " + e.getMessage());
		}
		return flag;
	}	
	
	@RequestMapping(value = "/ajax/getLeftNavNodes", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OverviewDashboard> getLeftNavNodes(@ModelAttribute OverviewDashboard obj,HttpSession session) {
		List<OverviewDashboard> OverviewDashboard = null;
		try {
			int ParentId=0;
			OverviewDashboard = overviewDashboardService.getFormsList(ParentId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLeftNavNodes : " + e.getMessage());
		}
		return OverviewDashboard;
	}		
	
	@RequestMapping(value = "/ajax/GetURL", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TableauDashboard loadTableauView(@RequestParam("tableauDashboardName") String tableauDashboardName,HttpSession session){
		String user_Id = null;String userName = null;
		TableauDashboard obj=new TableauDashboard();
		String tableauUrlView="";
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			tableauDashboardName=tableauDashboardName.replaceAll("_", "&");
			tableauDashboardName=tableauDashboardName.replaceAll("--", " ");
			String pageurl=overviewDashboardService.getTableauUrl(tableauDashboardName);

			if(!StringUtils.isEmpty(pageurl)){
			
				String server_name = "Syntrack";
				if(pageurl.contains(".com/")) {
					server_name = "Syntrack";
				}else {
					server_name = "MRVC";
				}
				TableauTrustedTicket tObj = new TableauTrustedTicket();
				String trustedTokenId =  tObj.getTrustedTicket(server_name);
				CommonConstants cObj = new CommonConstants();
				String baseUrl = cObj.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
				String[] url = {};
				if(pageurl.contains(".com/")) {
					url = pageurl.split(".com/");
					baseUrl = cObj.BASE_URL_SYNTRACK.replace("{0}", trustedTokenId);
				}else {
					url = pageurl.split(":8000/");
					baseUrl = cObj.BASE_URL_MRVC.replace("{0}", trustedTokenId);
				}
				
				tableauUrlView = baseUrl + url[1]+CommonConstants.TABLEAU_PARAMS;
				
				obj.setTableauUrl(tableauUrlView.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("loadTableauView() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
		}
		return obj;
	}	
			
}
