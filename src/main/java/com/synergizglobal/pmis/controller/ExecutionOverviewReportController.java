package com.synergizglobal.pmis.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.synergizglobal.pmis.Iservice.ExecutionOverviewReportService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Controller
public class ExecutionOverviewReportController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ExecutionOverviewReportController.class);
	
	
	@Autowired
	ExecutionOverviewReportService executionOverviewReportService;		
		
	@RequestMapping(value="/execution-overview-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView ExecutionOverviewReport(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.ExecutionOverviewReport);
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInEOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getWorksFilterListInEOR(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> worksFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksFilterList = executionOverviewReportService.getWorksFilterListInEOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInEOR : " + e.getMessage());
		}
		return worksFilterList;
	}
	@RequestMapping(value = "/ajax/getDepartmentFilterListInEOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDepartmentFilterListInEOR(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> departmentFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			departmentFilterList = executionOverviewReportService.getDepartmentFilterListInEOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentFilterListInEOR : " + e.getMessage());
		}
		return departmentFilterList;
	}
	@RequestMapping(value = "/ajax/getContractIdFilterListInEOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getContractIdFilterListInEOR(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> contractFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractFilterList = executionOverviewReportService.getContractIdFilterListInEOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractIdFilterList : " + e.getMessage());
		}
		return contractFilterList;
	}
	
	@RequestMapping(value = "/ajax/getExecutionOverviewReportList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getExecutionOverviewReportList(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = executionOverviewReportService.getExecutionOverviewReportList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutionOverviewReportList : " + e.getMessage());
		}
		return contracts;
	}	
}
