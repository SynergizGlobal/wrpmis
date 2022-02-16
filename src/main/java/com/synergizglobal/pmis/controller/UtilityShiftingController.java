package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.UtilityShiftingService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.UtilityShiftingPaginationObject;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.User;



@Controller
public class UtilityShiftingController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityShiftingController.class);
	
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
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}	
	@RequestMapping(value = "/ajax/getLocationListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getLocationListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
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
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
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
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
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
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getUtilityStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value="/add-utility-shifting",method=RequestMethod.GET)
	public ModelAndView addUtilityShiftingForm(HttpSession session,@ModelAttribute UtilityShifting obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "add");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
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

			int totalRecords = getTotalRecords(obj, searchParameter);

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
	public int getTotalRecords(UtilityShifting obj, String searchParameter) {
		int totalRecords = 0;
		try {
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
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
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
		
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "edit");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
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
	
	@RequestMapping(value="/get-utility-shifting/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getUtilityShifting(@ModelAttribute UtilityShifting obj,@PathVariable("id") String id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
		
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "edit");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
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
	
	@RequestMapping(value = "/export-utility-shifting", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportUtilityShifting(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute UtilityShifting dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.utilityShifting);
		List<UtilityShifting> dataList = new ArrayList<UtilityShifting>();
		
		List<UtilityShifting> subList = new ArrayList<UtilityShifting>();
		
		try {
			view.setViewName("redirect:/utilityshifting");
			dataList =   utilityShiftingService.getUtilityShiftingList(dObj);
		   
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet RRSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Utility Shifting"));
				XSSFSheet subSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Progress Details"));
				
				
		        workBook.setSheetOrder(RRSheet.getSheetName(), 0);
				workBook.setSheetOrder(subSheet.getSheetName(), 1);
			
		        
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
		        
		        
		        
	            XSSFRow headingRow = RRSheet.createRow(0);
	            String headerString = "Project ^Work ^Contract ^Utility Shifting ID^Identification^Location Name^Reference Number^Chainage"
	            		+ "^Utility Description^Utility Type"
	            		+ "^Owner Name^Category^Execution Agency^Impacted Contract^Requirement stage^Planned Completion^Shifting Completed"
	            		+ "^Start Date^Scope^Completed^Unit^Status^Remarks";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            RRSheet.createFreezePane(0,1);
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				XSSFRow headingRow1 = subSheet.createRow(0);
	            String headerString1 = "Utility Shifting ID^Progress Date^Progress Of Work";
	            
	            String[] secondHeaderStringArr = headerString1.split("\\^");
	            subSheet.createFreezePane(0,1);
	            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow1.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(secondHeaderStringArr[i]);
				}
				
			
			 short rowNo5 = 1;
			 for (UtilityShifting rDetails : dataList) { 
				String utility_shifting_id = rDetails.getId();
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
					
	                XSSFRow row = RRSheet.createRow(rowNo);
	                int c = 0;
	               
	                Cell cell = row.createCell(c++);
	                cell.setCellStyle(sectionStyle);
					cell.setCellValue(/*obj.getProject_id_fk() + " - "+*/obj.getProject_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(/*obj.getWork_id_fk() + " - "+*/obj.getWork_short_name());
					
				    cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(/*obj.getContract_id_fk() + " - "+*/obj.getContract_short_name());
						
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_shifting_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIdentification());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLocation_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getReference_number());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLatitude());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_description());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getOwner_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_type_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getUtility_category_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getExecution_agency_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getImpacted_contract_id_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRequirement_stage_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRequirement_stage_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRequirement_stage_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStart_date());
					
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
					cell.setCellValue(obj.getShifting_status_fk());
				
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
					
	                rowNo++;
	            }
				
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		RRSheet.setColumnWidth(columnIndex, 25 * 200);
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
			// TODO: handle exception
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
}
