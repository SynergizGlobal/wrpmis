package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.apache.poi.ss.usermodel.DataFormatter;
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
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.DesignsPaginationObject;

@Controller
public class DesignController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DesignController.class);

	@Autowired
	ContractService contractservice;
	@Autowired
	SafetyService safetyService;
	@Autowired
	DesignService designService;
	@Autowired
	HomeService homeService;
	@Autowired
	WorkService workService;
	@Autowired
	IssueService issueService;
	@Autowired
	FormsHistoryDao formsHistoryDao;
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
	
	@RequestMapping(value="/design",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView design(@ModelAttribute Design obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.designGrid);
		
		try {
			/*List<Design> hodList = designService.getHodListFilter(obj);
			model.addObject("hodList", hodList);
			List<Design> departmentList = designService.getDepartmentListFilter(obj);
			model.addObject("departmentList", departmentList);
			List<Design> contractList = designService.getContractListFilter(obj);
			model.addObject("contractList", contractList);
			List<Design> structureTypeList = designService.getStructureListFilter(obj);
			model.addObject("structureTypeList", structureTypeList);
			List<Design> drawingTypeList = designService.getDrawingTypeListFilter(obj);
			model.addObject("drawingTypeList", drawingTypeList);*/
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("design : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getHodListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getHodListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getHodListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDepartmentListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDepartmentListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getWorksListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getWorksListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return design;
	}
	
	
	@RequestMapping(value = "/ajax/getContractListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getContractListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getContractListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getStructureListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getStructureListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getStructureListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getDrawingTypeListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDrawingTypeListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDrawingTypeListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDrawingTypeListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getDesigns", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDesigns(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDesigns(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesigns : " + e.getMessage());
		}
		return design;
	}
	
	
	@RequestMapping(value = "/ajax/getDesignsList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Design obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");

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

			List<Design> designsList = new ArrayList<Design>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				designsList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				designsList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//designsList = getListBasedOnSearchParameter(searchParameter,designsList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			DesignsPaginationObject personJsonObject = new DesignsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(designsList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Design obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = designService.getTotalRecords(obj, searchParameter);
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
	public List<Design> createPaginationData(int startIndex, int offset, Design obj, String searchParameter) {
		List<Design> earthWorkList = null;
		try {
			earthWorkList = designService.getDesignsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/get-design", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDesignNew(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.newAddEditDesign);
			model.addObject("action", "edit");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> componentList = designService.componentList();
			model.addObject("componentList", componentList);			

			List<Design> approvingRailway = designService.getApprovingRailwayList();
			model.addObject("approvingRailway", approvingRailway);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> approvalAuthority = designService.getApprovalAuthority();
			model.addObject("approvalAuthority", approvalAuthority);
			
			List<Design> stage = designService.getStage();
			model.addObject("stage", stage);
			
			List<Design> submitted = designService.getSubmitted();
			model.addObject("submitted", submitted);
			
			List<Design> structureId = designService.getStructureId();
			model.addObject("structureId", structureId);
			
			List<Design> submssionpurpose = designService.getSubmssionpurpose();
			model.addObject("submssionpurpose", submssionpurpose);
			
			List<Design> designFileType = designService.getDesignFileType();
			model.addObject("designFileType", designFileType);
		
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			Issue iObj = new Issue();
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList(iObj);	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);

			Design designDetails = designService.getDesignDetails(obj);
			model.addObject("designDetails", designDetails);
			 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesignNew : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/get-design/{design_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDesignNew(@ModelAttribute Design obj,@PathVariable("design_id") String design_id,HttpSession session,RedirectAttributes attributes ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.newAddEditDesign);
			model.addObject("action", "edit");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> approvingRailway = designService.getApprovingRailwayList();
			model.addObject("approvingRailway", approvingRailway);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> approvalAuthority = designService.getApprovalAuthority();
			model.addObject("approvalAuthority", approvalAuthority);
			
			List<Design> stage = designService.getStage();
			model.addObject("stage", stage);
			
			List<Design> submitted = designService.getSubmitted();
			model.addObject("submitted", submitted);
			
			List<Design> structureId = designService.getStructureId();
			model.addObject("structureId", structureId);
			
			List<Design> submssionpurpose = designService.getSubmssionpurpose();
			model.addObject("submssionpurpose", submssionpurpose);
			
			List<Design> designFileType = designService.getDesignFileType();
			model.addObject("designFileType", designFileType);
		
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			Issue iObj = new Issue();
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList(iObj);	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);

			Design designDetails = designService.getDesignDetails(obj);
			model.addObject("designDetails", designDetails);
			 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesignNew : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-design-old", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDesign(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDesign);
			model.addObject("action", "edit");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			Issue iObj = new Issue();
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList(iObj);	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);

			Design designDetails = designService.getDesignDetails(obj);
			model.addObject("designDetails", designDetails);
			 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesign : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-design-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDesignFormNew(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.newAddEditDesign);	
			model.addObject("action", "add");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Design> worksList = designService.getWorkListForDesignForm(obj);
			model.addObject("worksList", worksList);
			
			List<Design> contractsList = designService.getContractsListForDesignForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> componentList = designService.componentList();
			model.addObject("componentList", componentList);			
			
			List<Design> approvingRailway = designService.getApprovingRailwayList();
			model.addObject("approvingRailway", approvingRailway);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> approvalAuthority = designService.getApprovalAuthority();
			model.addObject("approvalAuthority", approvalAuthority);
			
			List<Design> structureId = designService.getStructureId();
			model.addObject("structureId", structureId);
			
			List<Design> stage = designService.getStage();
			model.addObject("stage", stage);
			
			List<Design> submitted = designService.getSubmitted();
			model.addObject("submitted", submitted);
			
			List<Design> submssionpurpose = designService.getSubmssionpurpose();
			model.addObject("submssionpurpose", submssionpurpose);
			
			List<Design> designFileType = designService.getDesignFileType();
			model.addObject("designFileType", designFileType);
		
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			Issue iObj = new Issue();
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList(iObj);	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);
			
		}catch (Exception e) {
			logger.error("addDesignFormNew : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-design-form-old", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDesignForm(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDesign);	
			model.addObject("action", "add");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Design> worksList = designService.getWorkListForDesignForm(obj);
			model.addObject("worksList", worksList);
			
			List<Design> contractsList = designService.getContractsListForDesignForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			Issue iObj = new Issue();
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList(iObj);	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);
			
		}catch (Exception e) {
			logger.error("addDesignForm : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/ajax/getProjectsListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getProjectsListForDesignForm(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getProjectsListForDesignForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getWorkListForDesignForm(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getWorkListForDesignForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getComponentsforDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getComponentsforDesign(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getComponentsforDesign(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getComponentsforDesign : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getStructureIdsforDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getStructureIdsforDesign(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getStructureIdsforDesign(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureIdsforDesign : " + e.getMessage());
		}
		return objsList;
	}	
	
	
	
	@RequestMapping(value = "/ajax/getContractsListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getContractsListForDesignForm(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getContractsListForDesignForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}	
	
	@RequestMapping(value = "/ajax/getDesignUploadsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDesignUploadsList(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getDesignUploadsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesignUploadsList : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getHodListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getHodList(@ModelAttribute Design obj,HttpSession session) {
		List<Design> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			
			dataList = designService.getHodList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getDyHodListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDyHodList(@ModelAttribute Design obj,HttpSession session) {
		List<Design> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			dataList = designService.getDyHodList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDyHodList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/add-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			model.setViewName("redirect:/design");
			
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));
			obj.setSubmitted_to_division(DateParser.parse(obj.getSubmitted_to_division()));
			obj.setSubmitted_to_hq(DateParser.parse(obj.getSubmitted_to_hq()));
			
			obj.setQuery_raised_by_division(DateParser.parse(obj.getQuery_raised_by_division()));
			obj.setQuery_replied_to_division(DateParser.parse(obj.getQuery_replied_to_division()));
			obj.setQuery_raised_by_hq(DateParser.parse(obj.getQuery_raised_by_hq()));
			obj.setQuery_replied_to_hq(DateParser.parse(obj.getQuery_replied_to_hq()));
			obj.setSubmitted_for_crs_sanction(DateParser.parse(obj.getSubmitted_for_crs_sanction()));
			obj.setQuery_raised_for_crs_sanction(DateParser.parse(obj.getQuery_raised_for_crs_sanction()));
			obj.setQuery_replied_for_crs_sanction(DateParser.parse(obj.getQuery_replied_for_crs_sanction()));
			obj.setCrs_sanction_approved(DateParser.parse(obj.getCrs_sanction_approved()));
			
			obj.setRequired_date(DateParser.parse(obj.getRequired_date()));
			obj.setSubmitted_date(DateParser.parse(obj.getSubmitted_date()));
			String designid =  designService.addDesign(obj);
			if(!StringUtils.isEmpty(designid)) {
				attributes.addFlashAttribute("success", "Design "+designid+" Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Design is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Design is failed. Try again.");
			logger.error("addDesign : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			model.setViewName("redirect:/design");
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));
			obj.setSubmitted_to_division(DateParser.parse(obj.getSubmitted_to_division()));
			obj.setSubmitted_to_hq(DateParser.parse(obj.getSubmitted_to_hq()));
			
			obj.setQuery_raised_by_division(DateParser.parse(obj.getQuery_raised_by_division()));
			obj.setQuery_replied_to_division(DateParser.parse(obj.getQuery_replied_to_division()));
			obj.setQuery_raised_by_hq(DateParser.parse(obj.getQuery_raised_by_hq()));
			obj.setQuery_replied_to_hq(DateParser.parse(obj.getQuery_replied_to_hq()));
			obj.setSubmitted_for_crs_sanction(DateParser.parse(obj.getSubmitted_for_crs_sanction()));
			obj.setQuery_raised_for_crs_sanction(DateParser.parse(obj.getQuery_raised_for_crs_sanction()));
			obj.setQuery_replied_for_crs_sanction(DateParser.parse(obj.getQuery_replied_for_crs_sanction()));
			obj.setCrs_sanction_approved(DateParser.parse(obj.getCrs_sanction_approved()));
			
			obj.setRequired_date(DateParser.parse(obj.getRequired_date()));
			obj.setSubmitted_date(DateParser.parse(obj.getSubmitted_date()));
			
			String designid =  designService.updateDesign(obj);
			if(!StringUtils.isEmpty(designid)) {
				attributes.addFlashAttribute("success", "Design "+designid+" Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Design is failed. Try again.");
			}

		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Design is failed. Try again.");
			logger.error("updateDesign : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-design", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDesign(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Design design,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.designGrid);
		List<Design> dataList = new ArrayList<Design>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/design");
			dataList = designService.getDesigns(design);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Design & Drawing"));
	            XSSFSheet sheet1 = workBook.createSheet(WorkbookUtil.createSafeSheetName("Drawing Revisions"));
	            
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        workBook.setSheetOrder(sheet1.getSheetName(), 1);
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 9;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,false,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        XSSFRow headingRowFirst = sheet.createRow(0);

	        	Cell cell1 = headingRowFirst.createCell(0);
		        cell1.setCellStyle(whiteStyle);
				cell1.setCellValue("Should be generated by the System");
				
	        	Cell cell2 = headingRowFirst.createCell(1);
		        cell2.setCellStyle(whiteStyle);
				cell2.setCellValue("Unique ID of the Work");
				
	        	Cell cell3 = headingRowFirst.createCell(2);
		        cell3.setCellStyle(whiteStyle);
				cell3.setCellValue("");
				
	        	Cell cell4 = headingRowFirst.createCell(3);
		        cell4.setCellStyle(whiteStyle);
				cell4.setCellValue("Department to which the design or drawing belongs to");
				
	        	Cell cell5 = headingRowFirst.createCell(4);
		        cell5.setCellStyle(whiteStyle);
				cell5.setCellValue("Dropdown from Reference Table");	
				
	        	Cell cell6 = headingRowFirst.createCell(5);
		        cell6.setCellStyle(whiteStyle);
				cell6.setCellValue("Dropdown from Reference Table");
				
	        	Cell cell7 = headingRowFirst.createCell(6);
		        cell7.setCellStyle(whiteStyle);
				cell7.setCellValue("");
				
	        	Cell cell8 = headingRowFirst.createCell(7);
		        cell8.setCellStyle(whiteStyle);
				cell8.setCellValue("");
				
	        	Cell cell81 = headingRowFirst.createCell(8);
		        cell81.setCellStyle(whiteStyle);
				cell81.setCellValue("");				
				
	        	Cell cell9 = headingRowFirst.createCell(9);
		        cell9.setCellStyle(whiteStyle);
				cell9.setCellValue("Drawing prepared By from reference table");
				
	        	Cell cell10 = headingRowFirst.createCell(10);
		        cell10.setCellStyle(whiteStyle);
				cell10.setCellValue("");
				
	        	Cell cell11 = headingRowFirst.createCell(11);
		        cell11.setCellStyle(whiteStyle);
				cell11.setCellValue("Contract ID of the consultant preparing the drawing (If prepared by MRVC or Contractor, leave blank)");
				
	        	Cell cell12 = headingRowFirst.createCell(12);
		        cell12.setCellStyle(whiteStyle);
				cell12.setCellValue("Proof Consultant Contract ID checking the design/ drg.(Leave blank if no proof checking) ");
				
	        	Cell cell13 = headingRowFirst.createCell(13);
		        cell13.setCellStyle(whiteStyle);
				cell13.setCellValue("Type of Drawing");
				
	        	Cell cell14 = headingRowFirst.createCell(14);
		        cell14.setCellStyle(whiteStyle);
				cell14.setCellValue("");
				
	        	Cell cell15 = headingRowFirst.createCell(15);
		        cell15.setCellStyle(whiteStyle);
				cell15.setCellValue("");
				
	        	Cell cell16 = headingRowFirst.createCell(16);
		        cell16.setCellStyle(whiteStyle);
				cell16.setCellValue("");
				
	        	Cell cell17 = headingRowFirst.createCell(17);
		        cell17.setCellStyle(whiteStyle);
				cell17.setCellValue("Title of the drawing");
				
	        	Cell cell18 = headingRowFirst.createCell(18);
		        cell18.setCellStyle(whiteStyle);
				cell18.setCellValue("Drawing No assigned by the Agency");
				
	        	Cell cell19 = headingRowFirst.createCell(19);
		        cell19.setCellStyle(whiteStyle);
				cell19.setCellValue("Drawing No assigned by MRVC");
				
	        	Cell cell20 = headingRowFirst.createCell(20);
	        	cell20.setCellStyle(whiteStyle);
	        	cell20.setCellValue("Drawing No assigned by the Division");
				
	        	Cell cell21 = headingRowFirst.createCell(21);
		        cell21.setCellStyle(whiteStyle);
				cell21.setCellValue("Drawing No assigned by the Head Quarters");
				
	        	Cell cell22 = headingRowFirst.createCell(22);
		        cell22.setCellStyle(whiteStyle);
				cell22.setCellValue("");
				
	        	Cell cell23 = headingRowFirst.createCell(23);
		        cell23.setCellStyle(whiteStyle);
				cell23.setCellValue("");
				
	        	Cell cell24 = headingRowFirst.createCell(24);
		        cell24.setCellStyle(whiteStyle);
				cell24.setCellValue("");
				
	        	Cell cell25 = headingRowFirst.createCell(25);
		        cell25.setCellStyle(whiteStyle);
				cell25.setCellValue("");
				
	        	Cell cell26 = headingRowFirst.createCell(26);
		        cell26.setCellStyle(whiteStyle);
				cell26.setCellValue("");
				
	        	Cell cell27 = headingRowFirst.createCell(27);
		        cell27.setCellStyle(whiteStyle);
				cell27.setCellValue("Any additional remarks");
				
								
				
		        
	            XSSFRow headingRow = sheet.createRow(1);
	        	String headerString = "PMIS Drawing No,Work ID,Approving Railway,Department,HOD,Dy HOD,Structure,Structure ID,Component,Prepared By,Contract ID,Consultant ,Proof Consultant ID,Drawing Type,Approval Authority,Required Date,GFC Release Date,Drawing Title,Agency Drawing No,MRVC Drawing No,Division Drawing No,HQ Drawing No,Stage,Submtted by,Submitted to,Purpose of Submission/Remarks,Submitted Date,Remarks";
	            String[] firstHeaderStringArr = headerString.split("\\,");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
	            short rowNo = 2;
	            for (Design obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                int c = 0;
	            
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDesign_seq_id());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_id_fk());
				    
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getApproving_railway());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDepartment_id_fk());
				
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getHod_designation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDy_hod_designation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStructure_type_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStructure_id_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getComponent());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPrepared_by_id_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_id_fk());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getConsultant_contract_id_fk()+" - "+obj.getConsult_contarct());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProof_consultant_contract_id_fk()+" - "+obj.getProof_consult_contarct());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDrawing_type_fk());

					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getApproval_authority_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRequired_date());
					
					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getGfc_released());
					
					
					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDrawing_title());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContractor_drawing_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getMrvc_drawing_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDivision_drawing_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getHq_drawing_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStage_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSubmitted_by());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSubmitted_to());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSubmission_purpose());
	                
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSubmitted_date());
	                
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
	                
	                
	                rowNo++;
	            }
	            
	            
	            XSSFRow headingRow1 = sheet1.createRow(0);
	        	String headerString1 = "PMIS Drawing No,Revision,Revision Date,Revision Status,Remarks";
	            String[] firstHeaderStringArr1 = headerString1.split("\\,");
	            
	            for (int i = 0; i < firstHeaderStringArr1.length; i++) {		        	
		        	Cell cell = headingRow1.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr1[i]);
				}
	            
	            short rowNo1 = 1;
	            for (Design obj : dataList) {
	                XSSFRow row = sheet1.createRow(rowNo1);
	                int c = 0;
	            
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDesign_seq_id());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRevision());
				    
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRevision_date());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRevision_status());
				
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());

	                rowNo1++;
	            }	            
	            
	            
	            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Design_"+dateFormat.format(date);
                
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
			logger.error("exportDesign : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
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
	
	@RequestMapping(value = "/upload-designs", method = {RequestMethod.POST})
	public ModelAndView uploadDesigns(@ModelAttribute Design design,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";String userId = null;
		try {
			 userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			design.setCreated_by_user_id_fk(userId);
			design.setUser_id(userId);
			design.setUser_name(userName);
			design.setDesignation(userDesignation);
			model.setViewName("redirect:/design");
			
			if(!StringUtils.isEmpty(design.getDesignFile())){
				MultipartFile multipartFile = design.getDesignFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet designsDrawingsSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = designsDrawingsSheet.getRow(1);
						//checking given file format
						if(headerRow != null)
						{
						    List<String> fileFormat = FileFormatModel.getDesignFileFormat();
							if(headerRow.getCell(0).getStringCellValue().trim().equals("PMIS Drawing No"))
							{
								int noOfColumns = headerRow.getLastCellNum();
								if(noOfColumns == fileFormat.size()){
									for (int i = 0; i < fileFormat.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
					                		attributes.addFlashAttribute("error",uploadformatError);
					                		msg = uploadformatError;
					                		design.setUploaded_by_user_id_fk(userId);
					                		design.setStatus("Fail");
					                		design.setRemarks(msg);
											boolean flag = designService.saveDesignDataUploadFile(design);
					                		return model;
					                	}
									}
								}else{
									attributes.addFlashAttribute("error",uploadformatError);
									msg = uploadformatError;
			                		design.setUploaded_by_user_id_fk(userId);
			                		design.setStatus("Fail");
			                		design.setRemarks(msg);
									boolean flag = designService.saveDesignDataUploadFile(design);
			                		return model;
								}
							}
							else
							{
								List<String> fileFormatDesign = FileFormatModel.getDesignFirstFileFormat();
								int noOfColumns = headerRow.getLastCellNum();
								if(noOfColumns == fileFormatDesign.size()){
									for (int i = 0; i < fileFormatDesign.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.equals(fileFormatDesign.get(i).trim()) && !columnName.contains(fileFormatDesign.get(i).trim())){
					                		attributes.addFlashAttribute("error",uploadformatError);
					                		msg = uploadformatError;
					                		design.setUploaded_by_user_id_fk(userId);
					                		design.setStatus("Fail");
					                		design.setRemarks(msg);
											boolean flag = designService.saveDesignDataUploadFile(design);
					                		return model;
					                	}
									}
								}else{
									attributes.addFlashAttribute("error",uploadformatError);
									msg = uploadformatError;
			                		design.setUploaded_by_user_id_fk(userId);
			                		design.setStatus("Fail");
			                		design.setRemarks(msg);
									boolean flag = designService.saveDesignDataUploadFile(design);
			                		return model;
								}								
								
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						int count =0;
						if(headerRow.getCell(0).getStringCellValue().trim().equals("PMIS Drawing No"))
						{
							count = uploadDesigns(design,userId,userName);
						}
						else
						{
							count = uploadDesignsFirst(design,userId,userName);
						}
						if(count > 0) {
							
							if(headerRow.getCell(0).getStringCellValue().trim().equals("PMIS Drawing No"))
							{
								attributes.addFlashAttribute("success", count + " Designs updated successfully.");	

							}
							else
							{
								attributes.addFlashAttribute("success", count + " Designs added successfully.");	
							}
							
							msg = count + " Designs added successfully.";
							
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(design.getCreated_by_user_id_fk());
							formHistory.setUser(design.getDesignation()+" - "+design.getUser_name());
							formHistory.setModule_name_fk("Design");
							formHistory.setForm_name("Upload Design");
							formHistory.setForm_action_type("Upload");
							formHistory.setForm_details( msg);
							formHistory.setWork(design.getWork_id_fk());
							//formHistory.setContract(obj.getContract_id_fk());
							
							boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
							/********************************************************************************/
						}else {
							attributes.addFlashAttribute("success"," No records found.");	
							msg = " No records found.";
						}
						design.setUploaded_by_user_id_fk(userId);
						design.setStatus("Success");
						design.setRemarks(msg);
						boolean flag = designService.saveDesignDataUploadFile(design);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");

				msg = "No file exists";
				design.setUploaded_by_user_id_fk(userId);
				design.setStatus("Fail");
				design.setRemarks(msg);
				boolean flag = designService.saveDesignDataUploadFile(design);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
			
			msg = "Something went wrong. Please try after some time";
			design.setUploaded_by_user_id_fk(userId);
			design.setStatus("Fail");
			design.setRemarks(msg);
			try {
				boolean flag = designService.saveDesignDataUploadFile(design);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveDesignDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	
	/**
	 * This method uploadDesigns() is called when user upload the file
	 * 
	 * @param obj is object for the model class User.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @return type of this method is count.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	public int uploadDesigns(Design obj, String userId, String userName) throws Exception {
		Design design = null;
		List<Design> designsList = new ArrayList<Design>();
		
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getDesignFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet designsDrawingsSheet = workbook.getSheetAt(0);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 2; i <= designsDrawingsSheet.getLastRowNum();i++){
						int v = designsDrawingsSheet.getLastRowNum();
						XSSFRow row = designsDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						design = new Design();
						String val = null;
						if(!StringUtils.isEmpty(row)) {
							
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDesign_seq_id(val);}
							
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { design.setWork_id_fk(val);}
							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { design.setApproving_railway(val);}
							
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDepartment_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHod(val);}	
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDy_hod(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStructure_type_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStructure_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { design.setComponent(val);}	
					
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { design.setPrepared_by_id_fk(val);}
							
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { design.setContract_id_fk(val);}									
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { design.setConsultant_contract_id_fk(val);}										
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { design.setProof_consultant_contract_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDrawing_type_fk(val);}
							

							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { design.setApproval_authority_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("M/dd/yy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								design.setRequired_date(val);}
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { design.setGfc_released(val);}	
														
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDrawing_title(val);}	
														
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { design.setContractor_drawing_no(val);}
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { design.setMrvc_drawing_no(val);}
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDivision_drawing_no(val);}								
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHq_drawing_no(val);}											
							
													
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStage_fk(val);}										
						
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmitted_by(val);}
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmitted_to(val);}
							
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmission_purpose(val);}
							
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { 
								/*if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("M/dd/yy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}*/
								design.setSubmitted_date(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) { 
								/*	if(val.contains("/")) {
										LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("M/dd/yy"));
										val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
									}*/
								design.setGfc_released(val);}
							
							val = formatter.formatCellValue(row.getCell(28)).trim();
							if(!StringUtils.isEmpty(val)) { design.setRemarks(val);}
							
							design.setGfc_released(DateParser.parse(design.getGfc_released()));
							design.setSubmitted_date(DateParser.parse(design.getSubmitted_date()));
							design.setRequired_date(DateParser.parse(design.getRequired_date()));
							
							/*design.setSubmited_to_proof_consultant_fk(DateParser.parse(design.getSubmited_to_proof_consultant_fk()));
							design.setApproval_by_proof_consultant_fk(DateParser.parse(design.getApproval_by_proof_consultant_fk()));
							design.setPlanned_start(DateParser.parse(design.getPlanned_start()));
							design.setPlanned_finish(DateParser.parse(design.getPlanned_finish()));
							design.setConsultant_submission(DateParser.parse(design.getConsultant_submission()));
							design.setMrvc_reviewed(DateParser.parse(design.getMrvc_reviewed()));
							design.setDivisional_approval(DateParser.parse(design.getDivisional_approval()));
							design.setHq_approval(DateParser.parse(design.getHq_approval()));
							design.setGfc_released(DateParser.parse(design.getGfc_released()));
							design.setAs_built_date(DateParser.parse(design.getAs_built_date()));
							
							design.setSubmitted_to_division(DateParser.parse(design.getSubmitted_to_division()));
							design.setSubmitted_to_hq(DateParser.parse(design.getSubmitted_to_hq()));
							design.setQuery_raised_by_division(DateParser.parse(design.getQuery_raised_by_division()));
							design.setQuery_replied_to_division(DateParser.parse(design.getQuery_replied_to_division()));
							design.setQuery_raised_by_hq(DateParser.parse(design.getQuery_raised_by_hq()));
							design.setQuery_replied_to_hq(DateParser.parse(design.getQuery_replied_to_hq()));
							design.setSubmitted_for_crs_sanction(DateParser.parse(design.getSubmitted_for_crs_sanction()));
							design.setQuery_raised_for_crs_sanction(DateParser.parse(design.getQuery_raised_for_crs_sanction()));
							design.setQuery_replied_for_crs_sanction(DateParser.parse(design.getQuery_replied_for_crs_sanction()));
							design.setCrs_sanction_approved(DateParser.parse(design.getCrs_sanction_approved()));*/
							
						}
						List<Design> pObjList = new ArrayList<Design>();
						
						if(!StringUtils.isEmpty(design.getMrvc_drawing_no())) {
							/*		XSSFSheet designRevisionsSheet = workbook.getSheetAt(2);
									for(int j = 2; j <= designRevisionsSheet.getLastRowNum();j++){
										XSSFRow row2 = designRevisionsSheet.getRow(j);
										// Sets the Read data to the model class
										Design pObj = new Design();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setMrvc_drawing_no(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRevision(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRevision_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRevision_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRemarks(val);}
											
											pObj.setRevision_date(DateParser.parse(pObj.getRevision_date()));
											
										}
										if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getMrvc_drawing_no())
												&& pObj.getMrvc_drawing_no().equals(design.getMrvc_drawing_no()))
										pObjList.add(pObj);
									}*/
							design.setDesignRevisions(pObjList);
						}
						
						boolean flag = design.checkNullOrEmpty();
						
						if(!flag) {
							designsList.add(design);
						}
					}
					
					if(!designsList.isEmpty() && designsList != null){
						//count  = designService.uploadDesigns(designsList);
						count  = designService.uploadDesignsNew(designsList);
					}
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadDesigns() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadDesigns() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
	public int uploadDesignsFirst(Design obj, String userId, String userName) throws Exception {
		Design design = null;
		List<Design> designsList = new ArrayList<Design>();
		
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getDesignFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet designsDrawingsSheet = workbook.getSheetAt(0);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 2; i <= designsDrawingsSheet.getLastRowNum();i++){
						int v = designsDrawingsSheet.getLastRowNum();
						XSSFRow row = designsDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						design = new Design();
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { design.setWork_id_fk(val);}

							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { design.setApproving_railway(val);}
							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDepartment_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHod(val);}	
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDy_hod(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStructure_type_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStructure_id_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { design.setComponent(val);}
							
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { design.setPrepared_by_id_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { design.setContract_id_fk(val);}							
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { design.setConsultant_contract_id_fk(val);}										
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { design.setProof_consultant_contract_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDrawing_type_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { design.setApproval_authority_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("M/dd/yy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								design.setRequired_date(val);}
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { design.setGfc_released(val);}	
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDrawing_title(val);}								
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { design.setContractor_drawing_no(val);}
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { design.setMrvc_drawing_no(val);}
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDivision_drawing_no(val);}								
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHq_drawing_no(val);}											
							
													
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStage_fk(val);}										
						
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmitted_by(val);}
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmitted_to(val);}
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmission_purpose(val);}
							
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { 
								/*if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("M/dd/yy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}*/
								design.setSubmitted_date(val);}
							
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { 
								/*	if(val.contains("/")) {
										LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("M/dd/yy"));
										val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
									}*/
								design.setGfc_released(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) { design.setRemarks(val);}
							
							design.setGfc_released(DateParser.parse(design.getGfc_released()));
							design.setSubmitted_date(DateParser.parse(design.getSubmitted_date()));
							design.setRequired_date(DateParser.parse(design.getRequired_date()));
							
							/*design.setSubmited_to_proof_consultant_fk(DateParser.parse(design.getSubmited_to_proof_consultant_fk()));
							design.setApproval_by_proof_consultant_fk(DateParser.parse(design.getApproval_by_proof_consultant_fk()));
							design.setPlanned_start(DateParser.parse(design.getPlanned_start()));
							design.setPlanned_finish(DateParser.parse(design.getPlanned_finish()));
							design.setConsultant_submission(DateParser.parse(design.getConsultant_submission()));
							design.setMrvc_reviewed(DateParser.parse(design.getMrvc_reviewed()));
							design.setDivisional_approval(DateParser.parse(design.getDivisional_approval()));
							design.setHq_approval(DateParser.parse(design.getHq_approval()));
							design.setGfc_released(DateParser.parse(design.getGfc_released()));
							design.setAs_built_date(DateParser.parse(design.getAs_built_date()));
							
							design.setSubmitted_to_division(DateParser.parse(design.getSubmitted_to_division()));
							design.setSubmitted_to_hq(DateParser.parse(design.getSubmitted_to_hq()));
							design.setQuery_raised_by_division(DateParser.parse(design.getQuery_raised_by_division()));
							design.setQuery_replied_to_division(DateParser.parse(design.getQuery_replied_to_division()));
							design.setQuery_raised_by_hq(DateParser.parse(design.getQuery_raised_by_hq()));
							design.setQuery_replied_to_hq(DateParser.parse(design.getQuery_replied_to_hq()));
							design.setSubmitted_for_crs_sanction(DateParser.parse(design.getSubmitted_for_crs_sanction()));
							design.setQuery_raised_for_crs_sanction(DateParser.parse(design.getQuery_raised_for_crs_sanction()));
							design.setQuery_replied_for_crs_sanction(DateParser.parse(design.getQuery_replied_for_crs_sanction()));
							design.setCrs_sanction_approved(DateParser.parse(design.getCrs_sanction_approved()));*/
							
						}
						List<Design> pObjList = new ArrayList<Design>();
						
						if(!StringUtils.isEmpty(design.getMrvc_drawing_no())) {
							/*		XSSFSheet designRevisionsSheet = workbook.getSheetAt(2);
									for(int j = 2; j <= designRevisionsSheet.getLastRowNum();j++){
										XSSFRow row2 = designRevisionsSheet.getRow(j);
										// Sets the Read data to the model class
										Design pObj = new Design();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setMrvc_drawing_no(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRevision(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRevision_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRevision_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { pObj.setRemarks(val);}
											
											pObj.setRevision_date(DateParser.parse(pObj.getRevision_date()));
											
										}
										if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getMrvc_drawing_no())
												&& pObj.getMrvc_drawing_no().equals(design.getMrvc_drawing_no()))
										pObjList.add(pObj);
									}*/
							design.setDesignRevisions(pObjList);
						}
						
						boolean flag = design.checkNullOrEmpty();
						
						if(!flag) {
							designsList.add(design);
						}
					}
					
					if(!designsList.isEmpty() && designsList != null){
						//count  = designService.uploadDesigns(designsList);
						count  = designService.uploadDesignsNew(designsList);
					}
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadDesigns() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadDesigns() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}	
	
	
}