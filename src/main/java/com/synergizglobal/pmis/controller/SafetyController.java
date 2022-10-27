package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.SafetyPaginationObject;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.AlertsPaginationObject;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Safety;

@Controller
public class SafetyController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyController.class);	
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	HomeService homeService;
	
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
	
	@RequestMapping(value="/safety",method=RequestMethod.GET)
	public ModelAndView safety(@ModelAttribute Safety obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.safetyGrid);
			/*List<Safety> safety = safetyService.getSafetyList(obj);
			model.addObject("safety", safety);*/
			/*List<Safety> contracts = safetyService.getContractsListFilter(obj);
			model.addObject("contracts", contracts);
			
			List<Safety> departments = safetyService.getDepartmentsListFilter(obj);
			model.addObject("departments", departments);
			
			List<Safety> categorys = safetyService.getCategoryListFilter(obj);
			model.addObject("categorys", categorys);
			
			List<Safety> statuses = safetyService.getStatusListFilter(obj);
			model.addObject("statuses", statuses);*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("safety : " + e.getMessage());
		}
		return model;
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
	
	@RequestMapping(value = "/ajax/getWorksListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getWorksListFilterInSafety(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			
			objList = safetyService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getContractsListFilterInSafety(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			
			objList = safetyService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getDepartmentsListFilterInSafety(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			objList = safetyService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getCategoryListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getCategoryListFilterInSafety(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			objList = safetyService.getCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getStatusListFilterInSafety(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			objList = safetyService.getStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getHODListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getHODListFilterInSafety(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			objsList = safetyService.getHODListFilterInSafety(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODListFilterInSafety : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getResponsiblePersonsListForSafetyForm(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getResponsiblePersonsListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonsListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getNominatedAuthorityListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getNominatedAuthorityListForSafetyForm(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getNominatedAuthorityListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getNominatedAuthorityListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}	
	
	@RequestMapping(value = "/ajax/getSafetyList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getSafetysList(@ModelAttribute Safety obj, HttpServletRequest request,
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
			
			obj.setUser_id(userId);
			obj.setUser_role_code(user_role_code);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				obj.setDepartment_fk(decodeURIComponent(obj.getDepartment_fk()));
			}
			
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

			List<Safety> safetyList = new ArrayList<Safety>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				safetyList = createPaginationData(session,startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				safetyList = createPaginationData(session,startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//safetyList = getListBasedOnSearchParameter(searchParameter,safetyList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			SafetyPaginationObject personJsonObject = new SafetyPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(safetyList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getSafetysList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Safety obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = safetyService.getTotalRecords(obj, searchParameter);
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
	public List<Safety> createPaginationData(HttpSession session, int startIndex, int offset, Safety obj, String searchParameter) {
		List<Safety> objList = null;
		try {
			objList = safetyService.getSafetyList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value="/add-safety-form",method=RequestMethod.GET)
	public ModelAndView addSafetyForm(HttpSession session,@ModelAttribute Safety obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addSafetyForm);
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);			
			
			List<Safety> projectsList = safetyService.getProjectsListForSafetyForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Safety> worksList = safetyService.getWorkListForSafetyForm(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
			List<Safety> contractsList = safetyService.getContractsListForSafetyForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Safety> hodList = safetyService.getHODListForSafetyForm(obj);
			model.addObject("hodList", hodList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Safety> unitsList = safetyService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addSafetyForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getProjectsListForSafetyForm(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			String user_Id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);				
			objsList = safetyService.getProjectsListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getWorkListForSafetyForm(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			String user_Id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);			
			objsList = safetyService.getWorkListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getContractsListForSafetyForm(HttpSession session,@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			String user_Id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);	
			objsList = safetyService.getContractsListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-safety",method=RequestMethod.POST)
	public ModelAndView addSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		
		try {
			model.setViewName("redirect:/safety");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			User user = (User)session.getAttribute("user");
			if(!StringUtils.isEmpty(user)) {
				obj.setReported_by(userName);
				obj.setReported_by_email_id(user.getEmail_id());
			}			
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setClosure_date(DateParser.parse(obj.getClosure_date()));
			obj.setInvestigation_completed(DateParser.parse(obj.getInvestigation_completed()));			
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			
			String safetyid = safetyService.addSafety(obj);
			if(!StringUtils.isEmpty(safetyid)) {
				attributes.addFlashAttribute("success", "Safety "+safetyid+" Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error", "Adding safety is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateSafetyForm);
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);			
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Safety> unitsList = safetyService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<Safety> usersList = safetyService.getUersList(obj);
			model.addObject("usersList", usersList);
			
			List<Safety> hodList = safetyService.getHODListForSafetyForm(obj);
			model.addObject("hodList", hodList);
			
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety/{safety_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafety(@ModelAttribute Safety obj,@PathVariable("safety_id") String safety_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateSafetyForm);
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);			
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Safety> unitsList = safetyService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<Safety> hodList = safetyService.getHODListForSafetyForm(obj);
			model.addObject("hodList", hodList);
			
			obj.setSafety_id(safety_id);
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-safety",method=RequestMethod.POST)
	public ModelAndView updateSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");

			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			String user_role_code = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setUser_id(user_Id);
			obj.setUser_role_code(user_role_code);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			/*User user = (User)session.getAttribute("user");
			if(!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getEmail_id())) {
				obj.setReported_by_email_id(user.getEmail_id());
			}*/
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setClosure_date(DateParser.parse(obj.getClosure_date()));
			obj.setInvestigation_completed(DateParser.parse(obj.getInvestigation_completed()));			
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			obj.setCreated_by_user_id_fk(user_Id);

			
			String safetyid = safetyService.updateSafety(obj);
			if(!StringUtils.isEmpty(safetyid)) {
				attributes.addFlashAttribute("success", "Safety "+safetyid+" Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error", "Updating safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-safety",method=RequestMethod.POST)
	public ModelAndView deleteSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			boolean flag = safetyService.deleteSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("deleteSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-safety", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafety(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Safety safety,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.safetyGrid);
		List<Safety> dataList = new ArrayList<Safety>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/safety");
			
			User uObj = (User) session.getAttribute("user");
			safety.setUser_id(uObj.getUser_id());
			safety.setUser_role_code(uObj.getUser_role_code());			
			
			dataList = safetyService.getSafetyList(safety);  
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Safety"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
				        
				        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
				        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
				        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
				        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
				        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
				        
				        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
				        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        
				        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        
				        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
				        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        
				        
				        
			            XSSFRow headingRow = sheet.createRow(0);
			            String headerString = "Safety ID^Work^Contract^Short Description^Description^Date of Incident^Category^Impact^Root Cause^Safety Incident (Yes/No)^Status^Location^Latitude^Longitude^Reported By^Responsible Person^Committee Required^Name of Committee members^Nominated Authority^LTI Hours^Equipment Impact^People Impact^Work Impact^Corrective Measure Short Term^Corrective Measure long Term^Investigation Completion Date^Compensation (Rs.)^Payment Date^Remarks";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (Safety obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
			                
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSafety_seq_id());			                
			                
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_id_fk()+'-'+obj.getWork_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getContract_id_fk()+'-'+obj.getContract_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getTitle());							
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDescription());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDate());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCategory_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getImpact_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRoot_cause_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSafety_incident());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getStatus_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLocation());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLatitude());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLongitude());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getReported_by());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getResponsible_person());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCommittee_required_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCommitte_members());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getNominated_authority());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLti_hours());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getEquipment_impact());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getPeople_impact());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_impact());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCorrective_measure_short_term());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCorrective_measure_long_term());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getInvestigation_completed());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCompensation());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getPayment_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
			                
			                rowNo++;
			            }
			            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Safety_"+dateFormat.format(date);
		                
			            try{
			                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
			                workBook.write(fos);
			                fos.flush();*/
			            	
			               response.setContentType("application/.csv");
			 			   response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			 			   response.setContentType("application/vnd.ms-excel");
			 			   // add response header
			 			   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".xlsx");
			 			   
			 			    //copies all bytes from a file to an output stream
			 			   workBook.write(response.getOutputStream()); // Write workbook to response.
				           workBook.close();
			 			    //flushes output stream
			 			    response.getOutputStream().flush();
			            	
			                
			                attributes.addFlashAttribute("success",dataExportSucess);
			            	//response.setContentType("application/vnd.ms-excel");
			            	//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
			            	//XSSFWorkbook  workbook = new XSSFWorkbook ();
			            	// ...
			            	// Now populate workbook the usual way.
			            	// ...
			            	//workbook.write(response.getOutputStream()); // Write workbook to response.
			            	//workbook.close();
			            }catch(FileNotFoundException e){
			                //e.printStackTrace();
			                attributes.addFlashAttribute("error",dataExportInvalid);
			            }catch(IOException e){
			                //e.printStackTrace();
			                attributes.addFlashAttribute("error",dataExportError);
			            }
	         }else{
	        	 attributes.addFlashAttribute("error",dataExportNoData);
	         }
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportSafety : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
	private CellStyle cellFormating(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
		CellStyle style = workBook.createCellStyle();
		//Setting Background color  
		//style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (style instanceof XSSFCellStyle) {
		   XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle)style;
		   xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		//style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);
		
		Font font = workBook.createFont();
        //font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontHeightInPoints((short)fontSize);  
        font.setFontName(fontName);  //"Times New Roman"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
	
}
