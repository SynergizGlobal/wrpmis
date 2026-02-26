package com.synergizglobal.wrpmis.controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.IssueService;
import com.synergizglobal.wrpmis.Iservice.ProjectWorkOverviewReportService;
import com.synergizglobal.wrpmis.Iservice.RiskService;
import com.synergizglobal.wrpmis.Iservice.SafetyService;
import com.synergizglobal.wrpmis.Iservice.UtilityShiftingService;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.Issue;
import com.synergizglobal.wrpmis.model.LandAcquisition;
import com.synergizglobal.wrpmis.model.Risk;
import com.synergizglobal.wrpmis.model.Safety;
import com.synergizglobal.wrpmis.model.User;
import com.synergizglobal.wrpmis.model.UtilityShifting;
import com.synergizglobal.wrpmis.model.Work;

@Controller
public class ProjectWorkOverviewReportController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ProjectWorkOverviewReportController.class);
	
	@Autowired
	ProjectWorkOverviewReportService projectWorkOverviewReportService;	
	
	@Autowired
	SafetyService safetyService;

	@Autowired
	RiskService riskService;
	
	@Autowired
	IssueService issueService;	

	@Autowired
	UtilityShiftingService utilityShiftingService;
	
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${record.dataexport.success}")
	public String dataExportSucess;
	
	@Value("${record.dataexport.invalid.directory}")
	public String dataExportInvalid;
	
	@Value("${record.dataexport.error}")
	public String dataExportError;
	
	@Value("${record.dataexport.nodata}")
	public String dataExportNoData;	
			
	@RequestMapping(value="/project-work-overview-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView ProjectWorkOverviewReport(@ModelAttribute Contract obj,HttpSession session) throws Exception{
		ModelAndView model = new ModelAndView(PageConstants.ProjectWorkOverviewReport);
		List<Contract> worksList = projectWorkOverviewReportService.getWorksFilterListInPOR(obj);
		List<Contract> projectsList = projectWorkOverviewReportService.getProjectsFilterListInPOR(obj);
		model.addObject("worksList", worksList);
		model.addObject("projectsList", projectsList);
		return model;
	}
	@RequestMapping(value = "/ajax/getPORWorksFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getWorksFilterListInPOR(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> worksFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksFilterList = projectWorkOverviewReportService.getWorksFilterListInPOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInPOR : " + e.getMessage());
		}
		return worksFilterList;
	}
	@RequestMapping(value = "/ajax/getPORProjectsFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectsFilterListInPOR(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> projectsFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			projectsFilterList = projectWorkOverviewReportService.getProjectsFilterListInPOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInPOR : " + e.getMessage());
		}
		return projectsFilterList;
	}
	@RequestMapping(value = "/ajax/getProjectWorkOverviewReportList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getProjectWorkOverviewReportList(@ModelAttribute Work obj,HttpSession session) {
		List<Work> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_id(uObj.getUser_id());
			contracts = projectWorkOverviewReportService.getProjectOverviewReportList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectOverviewReportList : " + e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getStructuralProgress", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getStructuralProgress(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = projectWorkOverviewReportService.getStructuralProgress(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectOverviewReportList : " + e.getMessage());
		}
		return contracts;
	}
	
	
	@RequestMapping(value = "/ajax/getIssueStatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssueStatus(@ModelAttribute Issue obj,HttpSession session) {
		List<Issue> issues = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			issues = issueService.getPendingIssues(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssuesList : " + e.getMessage());
		}
		return issues;
	}	

	@RequestMapping(value = "/ajax/getRisks", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRisks(@ModelAttribute Risk obj,HttpSession session) {
		List<Risk> riskList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation()); 
			
			riskList = riskService.getRisks(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRiskAssessmentList : " + e.getMessage());
		}
		return riskList;
	}
	
	@RequestMapping(value = "/ajax/getUtilityShifting", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session) {
		List<UtilityShifting> UtilityShiftingList = null;
		try {
			UtilityShiftingList = utilityShiftingService.getUtilityShiftingStatus(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getUtilityShiftingList : " + e.getMessage());
		}
		return UtilityShiftingList;
	}	
	
	@RequestMapping(value = "/ajax/getSafetyIncidents", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getSafetyIncidents(@ModelAttribute Safety obj,HttpSession session) {
		List<Safety> safety = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			safety = safetyService.getSafetyList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSafetyIncidents : " + e.getMessage());
		}
		return safety;
	}		
	@RequestMapping(value = "/ajax/getProjectPhotos", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectPhotos(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = projectWorkOverviewReportService.getProjectPhotos(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectPhotos : " + e.getMessage());
		}
		return contracts;
	}
	@RequestMapping(value = "/ajax/getListOfContracts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getListOfContracts(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = projectWorkOverviewReportService.getListOfContracts(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectPhotos : " + e.getMessage());
		}
		return contracts;
	}
	@RequestMapping(value = "/ajax/getProcurementStatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProcurementStatus(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = projectWorkOverviewReportService.getProcurementStatus(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectPhotos : " + e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getLandAcquisitionStatus", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getLandAcquisitionStatus(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = projectWorkOverviewReportService.getLandAcquisitionStatus(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectPhotos : " + e.getMessage());
		}
		return contracts;
	}	
	
}
