package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Iservice.UtilityShiftingService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.UtilityShiftingPaginationObject;



@Controller
public class UtilityShiftingController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityShiftingController.class);
	@Autowired
	FormsHistoryDao formsHistoryDao;
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
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/utilityshifting",method=RequestMethod.GET)
	public ModelAndView users(@ModelAttribute UtilityShifting obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.utilityShifting);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getWorksListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objList = utilityShiftingService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getWorkListForUtilityForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getWorkListForUtilityForm(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objsList = utilityShiftingService.getWorkListForUtilityShifting(obj);

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForUtilityForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getImpactedContractsListForUSForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getImpactedContractsListForUSForm(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objsList = utilityShiftingService.getImpactedContractsListForUtilityShifting(obj);

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getImpactedContractsListForUtilityShifting : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getLocationListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getLocationListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = utilityShiftingService.getLocationListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getUtilityCategoryListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getUtilityCategoryListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objList = utilityShiftingService.getUtilityCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getUtilityTypeListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getUtilityTypeListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = utilityShiftingService.getUtilityTypeListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getStatusListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objList = utilityShiftingService.getUtilityStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getHodListForUtilityShifting", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getHodListForUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session) {
		List<UtilityShifting> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			dataList = utilityShiftingService.getHodListForUtilityShifting(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodListForUtilityShifting : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getReqStageListForUSForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getReqStageListForUSForm(@ModelAttribute UtilityShifting obj,HttpSession session) {
		List<UtilityShifting> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			dataList = utilityShiftingService.getReqStageList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getReqStageListForUSForm : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getImpactedElementListForUSForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getImpactedElementListForUSForm(@ModelAttribute UtilityShifting obj,HttpSession session) {
		List<UtilityShifting> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			dataList = utilityShiftingService.getImpactedElementList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getImpactedElementListForUSForm : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value="/add-utility-shifting",method=RequestMethod.GET)
	public ModelAndView addUtilityShiftingForm(HttpSession session,@ModelAttribute UtilityShifting obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "add");
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			
			List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(obj);
			model.addObject("contractsList", contractsList);
			
			
			List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(obj);
			model.addObject("utilityTypeList", utilityTypeList);
			
			List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(obj);
			model.addObject("utilityCategoryList", utilityCategoryList);
			
			List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(obj);
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
			
			List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(obj);
			model.addObject("impactedContractList", impactedContractList);
			
			List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(obj);
			model.addObject("requirementStageList", requirementStageList);
			
			List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(obj);
			model.addObject("unitList", unitList);
			
			List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(obj);
			model.addObject("utilityshiftingfiletypeList", utilityshiftingfiletypeList);			
			
			List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(obj);
			model.addObject("statusList", statusList);	
			
			List<UtilityShifting> utilityHODList = utilityShiftingService.getHodListForUtilityShifting(obj);
			model.addObject("utilityHODList", utilityHODList);	
			
			List<UtilityShifting> impactedContractsList = utilityShiftingService.getImpactedContractsListForUtilityShifting(obj);
			model.addObject("impactedContractsList", impactedContractsList);
			
			List<UtilityShifting> reqStageList = utilityShiftingService.getReqStageList(obj);
			model.addObject("reqStageList", reqStageList);
			
			List<UtilityShifting> impactedElementList = utilityShiftingService.getImpactedElementList(obj);
			model.addObject("impactedElementList", impactedElementList);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addUtilityShiftingForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/addUtilityShifting",method=RequestMethod.POST)
	public ModelAndView addUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		
		try {
			model.setViewName("redirect:/utilityshifting");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			User user = (User)session.getAttribute("user");
			
			obj.setStart_date(DateParser.parse(obj.getStart_date()));			
			obj.setShifting_completion_date(DateParser.parse(obj.getShifting_completion_date()));
			obj.setPlanned_completion_date(DateParser.parse(obj.getPlanned_completion_date()));
			obj.setIdentification(DateParser.parse(obj.getIdentification()));
			
			
			boolean flag = utilityShiftingService.addUtilityShifting(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Shifting added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding Utility Shifting is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addUtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getUtilityShiftingList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getUtilityShiftingList(@ModelAttribute UtilityShifting obj, HttpServletRequest request,
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

			List<UtilityShifting> utilityShiftingList = new ArrayList<UtilityShifting>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				utilityShiftingList = createPaginationData(session,startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				utilityShiftingList = createPaginationData(session,startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//UtilityShiftingList = getListBasedOnSearchParameter(searchParameter,UtilityShiftingList);

			int totalRecords = getTotalRecords(obj, searchParameter, session);

			UtilityShiftingPaginationObject personJsonObject = new UtilityShiftingPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(utilityShiftingList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getUtilityShiftingList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(UtilityShifting obj, String searchParameter,HttpSession session) {
		int totalRecords = 0;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			totalRecords = utilityShiftingService.getTotalRecords(obj, searchParameter);
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
	public List<UtilityShifting> createPaginationData(HttpSession session, int startIndex, int offset, UtilityShifting obj, String searchParameter) {
		List<UtilityShifting> objList = null;
		try {

			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objList = utilityShiftingService.getUtilityShiftingList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value="/get-utility-shifting",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
		
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "edit");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			

			
			List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(obj);
			model.addObject("contractsList", contractsList);
			
			
			List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(obj);
			model.addObject("utilityTypeList", utilityTypeList);
			
			List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(obj);
			model.addObject("utilityCategoryList", utilityCategoryList);
			
			List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(obj);
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
			
			List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(obj);
			model.addObject("impactedContractList", impactedContractList);
			
			List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(obj);
			model.addObject("requirementStageList", requirementStageList);
			
			List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(obj);
			model.addObject("unitList", unitList);
			
			List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(obj);
			model.addObject("utilityshiftingfiletypeList", utilityshiftingfiletypeList);			
			
			List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(obj);
			model.addObject("statusList", statusList);	
			
			List<UtilityShifting> utilityHODList = utilityShiftingService.getHodListForUtilityShifting(obj);
			model.addObject("utilityHODList", utilityHODList);	
			
			List<UtilityShifting> impactedContractsList = utilityShiftingService.getImpactedContractsListForUtilityShifting(obj);
			model.addObject("impactedContractsList", impactedContractsList);
			
			List<UtilityShifting> reqStageList = utilityShiftingService.getReqStageList(obj);
			model.addObject("reqStageList", reqStageList);
			
			List<UtilityShifting> impactedElementList = utilityShiftingService.getImpactedElementList(obj);
			model.addObject("impactedElementList", impactedElementList);
			
			UtilityShifting utilityShifting = utilityShiftingService.getUtilityShifting(obj);
			model.addObject("utilityShifting", utilityShifting);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getUtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-utility-shifting/{utility_shifting_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getUtilityShifting(@ModelAttribute UtilityShifting obj,@PathVariable("utility_shifting_id") String id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
		
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "edit");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			

						
			
			List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(obj);
			model.addObject("contractsList", contractsList);
			
			
			List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(obj);
			model.addObject("utilityTypeList", utilityTypeList);
			
			List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(obj);
			model.addObject("utilityCategoryList", utilityCategoryList);
			
			List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(obj);
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
			
			List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(obj);
			model.addObject("impactedContractList", impactedContractList);
			
			List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(obj);
			model.addObject("requirementStageList", requirementStageList);
			
			List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(obj);
			model.addObject("unitList", unitList);
			
			List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(obj);
			model.addObject("utilityshiftingfiletypeList", utilityshiftingfiletypeList);			
			
			List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(obj);
			model.addObject("statusList", statusList);	
			
			UtilityShifting utilityShifting = utilityShiftingService.getUtilityShifting(obj);
			model.addObject("utilityShifting", utilityShifting);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getUtilityShifting : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/updateUtilityShifting",method=RequestMethod.POST)
	public ModelAndView updateUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/utilityshifting");


			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			User user = (User)session.getAttribute("user");
			
	
			obj.setStart_date(DateParser.parse(obj.getStart_date()));			
			obj.setShifting_completion_date(DateParser.parse(obj.getShifting_completion_date()));
			obj.setPlanned_completion_date(DateParser.parse(obj.getPlanned_completion_date()));
			obj.setIdentification(DateParser.parse(obj.getIdentification()));
			obj.setCreated_by_user_id_fk(user_Id);
			
			boolean flag = utilityShiftingService.updateUtilityShifting(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "UtilityShifting updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating UtilityShifting is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateUtilityShifting : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getUtilityShiftingUploadsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getUtilityShiftingUploadsList(@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objsList = null;
		try {
			objsList = utilityShiftingService.getUtilityShiftingUploadsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getUtilityShiftingUploadsList : " + e.getMessage());
		}
		return objsList;
	}
	@RequestMapping(value = "/export-utility-shifting", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportUtilityShifting(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute UtilityShifting dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.utilityShifting);
		List<UtilityShifting> dataList = new ArrayList<UtilityShifting>();
		
		List<UtilityShifting> subList = new ArrayList<UtilityShifting>();
		
		try {
			view.setViewName("redirect:/utilityshifting");
			User uObj = (User) session.getAttribute("user");
			dObj.setUser_type_fk(uObj.getUser_type_fk());
			dObj.setUser_role_code(uObj.getUser_role_code());
			dObj.setUser_id(uObj.getUser_id());
			dataList =   utilityShiftingService.getUtilityShiftingList(dObj);
			
						
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet utilityShiftingSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Utility Shifting"));
				XSSFSheet subSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Progress Details"));
				XSSFSheet refSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Reference Data"));
				
				
		        workBook.setSheetOrder(utilityShiftingSheet.getSheetName(), 0);
				workBook.setSheetOrder(subSheet.getSheetName(), 1);
				workBook.setSheetOrder(refSheet.getSheetName(), 2);
			
		        
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
		        
		        
		        
	            XSSFRow headingRow = utilityShiftingSheet.createRow(0);
	            String headerString = "Utility ID ^Project ^Work ^Execution Agency ^HOD ^Utility Type ^Utility Description ^Location ^Custodian ^Identification Date ^Reference No. "
	            		+ "^Chainage ^Executed By ^Impacted Contract  ^Requirement stage "
	            		+ "^Impacted Element ^Affected Structures  ^Target Date ^Scope ^Completed ^Unit ^Start Date ^Status ^Completetion Date ^Remarks";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            utilityShiftingSheet.createFreezePane(0,1);
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				XSSFRow headingRow1 = subSheet.createRow(0);
	            String headerString1 = "Utility ID ^Progress Date ^Progress Of Work";
	            
	            String[] secondHeaderStringArr = headerString1.split("\\^");
	            subSheet.createFreezePane(0,1);
	            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow1.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(secondHeaderStringArr[i]);
				}
				
			
				 short rowNo5 = 1;
				 for (UtilityShifting rDetails : dataList) { 
					String utility_shifting_id = rDetails.getUtility_shifting_id();
					subList = utilityShiftingService.getRDetailsList(utility_shifting_id);
						
					 for (UtilityShifting obj : subList) {
			                XSSFRow row = subSheet.createRow(rowNo5);
			                int b = 0;
			                
			                Cell cell1 = row.createCell(b++);
							cell1.setCellStyle(sectionStyle);
							cell1.setCellValue(obj.getUtility_shifting_id());
							
			                cell1 = row.createCell(b++);
							cell1.setCellStyle(sectionStyle);
							cell1.setCellValue(obj.getProgress_date());
							
			                cell1 = row.createCell(b++);
							cell1.setCellStyle(sectionStyle);
							cell1.setCellValue(obj.getProgress_of_work());
							
			               
							rowNo5++;
					    }
			       }
				 short rowNo = 1;
		         for (UtilityShifting obj : dataList) {
					
	                XSSFRow row = utilityShiftingSheet.createRow(rowNo);
	                int c = 0;
	               
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_shifting_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProject_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getExecution_agency_fk());
					
					/*
					 * String hod = ""; if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {hod =
					 * obj.getHod_user_id_fk() +" - "+obj.getUser_name();}
					 */
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDesignation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_type_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_description());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLocation_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCustodian());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIdentification());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getReference_number());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getChainage());	
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getExecuted_by());
					
				    cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRequirement_stage_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getImpacted_element());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAffected_structures());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPlanned_completion_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getScope());									
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCompleted());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUnit_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStart_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getShifting_status_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getShifting_completion_date());
									
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
					
	                rowNo++;
	            }
		         
		        /*************************************************************/ 
		         
		        List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(null);
				List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(null);
				List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(null);
				List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(null);
				List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(null);
				List<UtilityShifting> utilityHODList = utilityShiftingService.getHodListForUtilityShifting(null);
				List<UtilityShifting> impactedContractsList = utilityShiftingService.getImpactedContractsListForUtilityShifting(null);
				List<UtilityShifting> reqStageList = utilityShiftingService.getReqStageList(null);
				List<UtilityShifting> impactedElementList = utilityShiftingService.getImpactedElementList(null);
				
				/*List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(null);
				List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(null);
				List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(null);
				List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(null);
				List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(null);
				List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(null);
				*/
					
		        XSSFRow headerRow = refSheet.createRow(0);
	            refSheet.createFreezePane(0,1);
	            
	            int b = 1;	
	            Cell cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Project Name");
				int rowNoRef = 1;			
				XSSFRow row = null;
				for (UtilityShifting obj : projectsList) {
	                row = refSheet.createRow(rowNoRef++);	                	                
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProject_name());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Work Name");			
				int p = 1;
				for (UtilityShifting obj : worksList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_short_name());
					
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Execution Agencies");	
				p = 1;
				for (UtilityShifting obj : utilityExecutionAgencyList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getExecution_agency_fk());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("HOD");	
				p = 1;
				for (UtilityShifting obj : utilityHODList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDesignation());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Utility Type");	
				p = 1;
				for (UtilityShifting obj : utilityTypeList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_type_fk());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Impacted Contract");	
				p = 1;
				for (UtilityShifting obj : impactedContractsList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_short_name());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Requirement stage ");	
				p = 1;
				for (UtilityShifting obj : reqStageList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRequirement_stage_fk());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Impacted Element");	
				p = 1;
				for (UtilityShifting obj : impactedElementList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getImpacted_element());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				b = b+2;
				cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Status");	
				p = 1;
				for (UtilityShifting obj : statusList) {
					row = refSheet.getRow(p++);
					if(row == null) {
		                row = refSheet.createRow(rowNoRef++);
					}
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getShifting_status_fk());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				/*************************************************************/
				
				
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		utilityShiftingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 38; columnIndex++) {
	        		subSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	            Date date = new Date();
	            String fileName = "Utility_Shifting_"+dateFormat.format(date);
	            
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
		}catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@RequestMapping(value = "/upload-utility-shifting", method = {RequestMethod.POST})
	public ModelAndView uploadUtilityShifting(@ModelAttribute UtilityShifting obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";String userId = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(userId);
			obj.setUser_id(userId);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			model.setViewName("redirect:/utilityshifting");
			
			if(!StringUtils.isEmpty(obj.getUtilityFile())){
				MultipartFile multipartFile = obj.getUtilityFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet laSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = laSheet.getRow(0);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getUtilityShiftingFileFormat();	
							int columnSize = fileFormat.size();
							int noOfColumns = headerRow.getLastCellNum();
							String columnName = headerRow.getCell(0).getStringCellValue().trim();
							if(!columnName.equalsIgnoreCase(fileFormat.get(0).trim()) &&  columnName.equals("Project")) {
								columnSize = columnSize - 1;
							}
							if(noOfColumns == columnSize){
								boolean tempFlag = false;
								for (int i = 0; i < columnSize;i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(i == 0 && "Utility ID".equalsIgnoreCase(fileFormat.get(i).trim()) && columnName.equals("Project") && !columnName.equals(fileFormat.get(i).trim())) {
										tempFlag = true;
									}
									if(tempFlag) {i++;}
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		msg = uploadformatError;
				                		obj.setUploaded_by_user_id_fk(userId);
				                		obj.setStatus("Fail");
				                		obj.setRemarks(msg);
										boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
								msg = uploadformatError;
		                		obj.setUploaded_by_user_id_fk(userId);
		                		obj.setStatus("Fail");
		                		obj.setRemarks(msg);
								boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
							msg = uploadformatError;
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
							boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
	                		return model;
						}
						String[]  result = uploadUtilityShifting(obj,userId,userName);
						String errMsg = result[0];String actualVal = "";
						int count = 0,row = 0,sheet = 0,subRow = 0;
						List<String> fileFormat = FileFormatModel.getUtilityShiftingFileFormat();	
						if(!StringUtils.isEmpty(result[1])){count = Integer.parseInt(result[1]);}
						if(!StringUtils.isEmpty(result[2])){row = Integer.parseInt(result[2]);}
						if(!StringUtils.isEmpty(result[3])){sheet = Integer.parseInt(result[3]);}
						if(!StringUtils.isEmpty(result[4])){subRow = Integer.parseInt(result[4]);}
						if(!StringUtils.isEmpty(errMsg)){
							if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Duplicate entry")) {
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;<b>Work and Utility Shifting Id Mismatch at row: ("+row+")</b> please check and Upload again.</span>");
								msg = "Work and Utility Shifting Id Mismatch at row: "+row;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Data truncated")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row; 
									String error = "Data truncated";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								} 
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect Value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Cannot add or update a child row")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Cannot add or update a child row";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect Value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect date value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Incorrect date value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect date value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect date value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect integer value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row; 
									String error = "Incorrect integer value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect integer value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect integer value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect decimal value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Incorrect decimal value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect decimal value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect decimal value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Data too long for column")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Data too long for column";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Data too long for value in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect decimal value identified in Sheet: "+sheet+" at row: "+actualVal;
							}
						
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
							boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
	                		return model;
						}
						
						if(count > 0) {
							attributes.addFlashAttribute("success","<i class='fa fa-check'></i>&nbsp;"+ count + "<span style='color:green;'> records Uploaded successfully.</span>");	
							msg = count + " records Uploaded successfully.";
							
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
							formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
							formHistory.setModule_name_fk("Utility Shifting");
							formHistory.setForm_name("Upload Utility Shifting");
							formHistory.setForm_action_type("Upload");
							formHistory.setForm_details( msg);
							formHistory.setWork(obj.getWork_id_fk());
							//formHistory.setContract(obj.getContract_id_fk());
							
							boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
							/********************************************************************************/
						}else {
							attributes.addFlashAttribute("success"," No records found.");	
							msg = " No records found.";
						}
                		obj.setUploaded_by_user_id_fk(userId);
                		obj.setStatus("Success");
                		obj.setRemarks(msg);
						boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				msg = "No file exists";
				obj.setUploaded_by_user_id_fk(userId);
        		obj.setStatus("Fail");
        		obj.setRemarks(msg);
				boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
			msg = "Something went wrong. Please try after some time";
			obj.setUploaded_by_user_id_fk(userId);
    		obj.setStatus("Fail");
    		obj.setRemarks(msg);
			try {
				boolean flag = utilityShiftingService.saveUSDataUploadFile(obj);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveDesignDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	private  String[]  uploadUtilityShifting(UtilityShifting obj, String userId, String userName) throws Exception {
		UtilityShifting us = null;
		List<UtilityShifting> ussList = new ArrayList<UtilityShifting>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getUtilityFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					
					XSSFSheet laSheet = workbook.getSheetAt(0);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 1; i <= laSheet.getLastRowNum();i++){
						int v = laSheet.getLastRowNum();
						XSSFRow headerRow = laSheet.getRow(0);
						String columnName = headerRow.getCell(0).getStringCellValue();
						XSSFRow row = laSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						us = new UtilityShifting();
						String val = null;
						if(!StringUtils.isEmpty(row)) {		
							int p = 0;
							if("Utility ID".equalsIgnoreCase(columnName.trim())) {
								val = formatter.formatCellValue(row.getCell(p++)).trim();
								if(!StringUtils.isEmpty(val)) { us.setUtility_shifting_id(val);}
							}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setProject_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setWork_short_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setExecution_agency_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setDesignation(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setUtility_type_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setUtility_description(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setLocation_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setCustodian(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setIdentification(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setReference_number(val);}							
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setChainage(val);}							
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setExecuted_by(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setContract_short_name(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setRequirement_stage_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setImpacted_element(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) {us.setAffected_structures(val);}	
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setPlanned_completion_date(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(p-1));
								}
								us.setScope(val);
							}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) {
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(p-1));
								}
								us.setCompleted(val);
							}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) {us.setUnit_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setStart_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) {us.setShifting_status_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { us.setShifting_completion_date(val);}
						
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) {us.setRemarks(val);}								
						
							us.setStart_date(DateParser.parse(us.getStart_date()));
							us.setShifting_completion_date(DateParser.parse(us.getShifting_completion_date()));
							us.setCreated_by_user_id_fk(userId);
							us.setPlanned_completion_date(DateParser.parse(us.getPlanned_completion_date()));
							us.setIdentification(DateParser.parse(us.getIdentification()));
				
						List<UtilityShifting> pObjList = new ArrayList<UtilityShifting>();
						XSSFSheet laComercialDetailsSheet = workbook.getSheetAt(1);
						XSSFRow comDetails = laComercialDetailsSheet.getRow(1);
					
						if(comDetails != null){
							for(int j = 1; j <= laComercialDetailsSheet.getLastRowNum();j++){
								XSSFRow row2 = laComercialDetailsSheet.getRow(j);
								UtilityShifting pObj = new UtilityShifting();
								if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(us.getUtility_shifting_id())) {
									val = formatter.formatCellValue(row2.getCell(0)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setUtility_shifting_id(val);}
									
									val = formatter.formatCellValue(row2.getCell(1)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setProgress_date(val);}
									
									val = formatter.formatCellValue(row2.getCell(2)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setProgress_of_work(val);}	
									
									pObj.setProgress_date(DateParser.parse(pObj.getProgress_date()));
								
								}
								if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(us.getUtility_shifting_id())) {
									pObjList.add(pObj);
								}
										
							}
							us.setProcessList(pObjList);
						}
				
						boolean flag = us.checkNullOrEmpty();
						if(!flag && !StringUtils.isEmpty(us.getWork_short_name())) {
							ussList.add(us);
						}
					}
					if(!ussList.isEmpty() && ussList != null){
						String[] arr  = utilityShiftingService.uploadUtilityShiftingData(ussList,us);
						result[0] = arr[0];
						result[1] = arr[1];
						result[2] = arr[2];
						result[3] = arr[3];
						result[4] = arr[4];
					}
					
				}
				workbook.close();
			}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadUtilityShifting() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRUtilityShifting() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return result;
	}
	private String getCellDataType(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup
		//workbook.setForceFormulaRecalculation(true);
		try {
			CellType type = cell.getCellType();
			if (!StringUtils.isEmpty(cell)) {
			    switch (type) {
			        case BOOLEAN:
			            val = String.valueOf(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			        	val = String.valueOf(cell.getNumericCellValue());
			        	if(val.contains("E")){
			        		val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        	}
			       
			            break;
			        case STRING:
			        	try {  
			        		val = cell.getStringCellValue();
			        		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			        		Number number = format.parse(val);
			        		int d = number.intValue();
			        		val = String.valueOf(d);
			        		if(val.contains("E")){
			        			val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        		}
			        	  } catch(NumberFormatException e){  
			        		  val = cell.getStringCellValue();
			        	  }  
			            
			            break;
			        case BLANK:
			        	val = cell.getStringCellValue();
			            break;
			        case ERROR:
			            val = cell.getStringCellValue();
			            break;
			        case _NONE:
			            val = cell.getStringCellValue();
			            break;
					default:
						break;
			    }
			}else if (!StringUtils.isEmpty(cell)) {
				DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
				val = formatter.formatCellValue(cell).trim();
			}
		}catch(Exception e) {
			try {
				 val = cell.getStringCellValue();
			}catch(Exception e1) {
				val = String.valueOf(cell.getNumericCellValue());
			}
			
		}
	
		return val;
	}
}
