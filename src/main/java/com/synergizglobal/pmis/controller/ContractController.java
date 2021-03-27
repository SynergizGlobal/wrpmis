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
import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.ContractPaginationObject;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.User;


@Controller
public class ContractController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ContractController.class);
	
	@Autowired
	ContractService contractService;
	
	@Autowired
	WorkService workService;
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	HomeService homeService;
	@Autowired
	ActivitiesService activitiesService;
	
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

	@RequestMapping(value="/contract",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Contract(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.contractGrid);
		try {
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getContracts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractList(@ModelAttribute Contract obj) {
		List<Contract> contractList = null;
		try {
			contractList = contractService.contractList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("contractList : " + e.getMessage());
		}
		return contractList;
	}
	
	@RequestMapping(value = "/ajax/get-contracts", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Contract obj, HttpServletRequest request,
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

			List<Contract> contractList = new ArrayList<Contract>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//contractList = getListBasedOnSearchParameter(searchParameter,contractList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ContractPaginationObject personJsonObject = new ContractPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(contractList);

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
	public int getTotalRecords(Contract obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = contractService.getTotalRecords(obj, searchParameter);
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
	public List<Contract> createPaginationData(int startIndex, int offset,Contract obj, String searchParameter) {
		List<Contract> earthWorkList = null;
		try {
			earthWorkList = contractService.getContractsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	
	@RequestMapping(value = "/ajax/getContractorsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractorsFilterList(@ModelAttribute Contract obj) {
		List<Contract> contractorsFilterList = null;  
		try {
			 contractorsFilterList = contractService.contractorsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterList : " + e.getMessage());
		}
		return contractorsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDepartmentsFilterList(@ModelAttribute Contract obj) {
		List<Contract> departmentFilterList = null;
		try {
			 departmentFilterList = contractService.departmentsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsFilterList : " + e.getMessage());
		}
		return departmentFilterList;
	}

	@RequestMapping(value = "/ajax/getWorksFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getWorksFilterList(@ModelAttribute Contract obj) {
		List<Contract> worksFilterList = null;
		try {
			worksFilterList = contractService.worksFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterList : " + e.getMessage());
		}
		return worksFilterList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectsFilterList(@ModelAttribute Contract obj) {
		List<Contract> projectsFilterList = null;
		try {
			projectsFilterList = contractService.getProjectsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsFilterList : " + e.getMessage());
		}
		return projectsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getDesignationsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDesignationsFilterList(@ModelAttribute Contract obj) {
		List<Contract> designationsFilterList = null;  
		try {
			designationsFilterList = contractService.getDesignationsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesignationsFilterList : " + e.getMessage());
		}
		return designationsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getDyHODDesignationsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDyHODDesignationsFilterList(@ModelAttribute Contract obj) {
		List<Contract> dyHODDesignationsFilterList = null;  
		try {
			dyHODDesignationsFilterList = contractService.getDyHODDesignationsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDyHODDesignationsFilterList : " + e.getMessage());
		}
		return dyHODDesignationsFilterList;
	}
	

	
	@RequestMapping(value = "/ajax/getStatusFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getStatusFilterListInContract(@ModelAttribute Contract obj) {
		List<Contract> dataList = null;  
		try {
			dataList = contractService.getStatusFilterListInContract(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusFilterListInContract : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDepartmentsList(@ModelAttribute Contract obj) {
		List<Contract> dataList = null;  
		try {
			dataList = contractService.getDepartmentsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/add-contract-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractForm(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addContractForm);	
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractors = contractService.getContractorsList();
			model.addObject("contractors", contractors);
			
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			
			List<Contract> contractList = contractService.contractList(obj);
			model.addObject("contractList", contractList);
			
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			
			List<Contract> contract_Statustype = contractService.getContractStatusType();
			model.addObject("contract_Statustype", contract_Statustype);
			
		}catch (Exception e) {
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectsListForContractForm(@ModelAttribute Contract obj) {
		List<Contract> objsList = null;
		try {
			objsList = contractService.getProjectsListForContractForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForContractForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getWorkListForContractForm(@ModelAttribute Contract obj) {
		List<Contract> objsList = null;
		try {
			objsList = contractService.getWorkListForContractForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForContractForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContract(@ModelAttribute  Contract contract,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract");
			contract.setDoc(DateParser.parse(contract.getDoc()));
			contract.setCa_date(DateParser.parse(contract.getCa_date()));
			contract.setDate_of_start(DateParser.parse(contract.getDate_of_start()));			
			contract.setLoa_date(DateParser.parse(contract.getLoa_date()));
			contract.setActual_completion_date(DateParser.parse(contract.getActual_completion_date()));		
			contract.setContract_closure_date(DateParser.parse(contract.getContract_closure_date()));
			contract.setCompletion_certificate_release(DateParser.parse(contract.getCompletion_certificate_release()));		
			contract.setFinal_takeover(DateParser.parse(contract.getFinal_takeover()));
			contract.setFinal_bill_release(DateParser.parse(contract.getFinal_bill_release()));
			contract.setDefect_liability_period(DateParser.parse(contract.getDefect_liability_period()));
			contract.setRetention_money_release(DateParser.parse(contract.getRetention_money_release()));
			contract.setPbg_release(DateParser.parse(contract.getPbg_release()));
			contract.setBg_date(DateParser.parse(contract.getBg_date()));
			contract.setRelease_date(DateParser.parse(contract.getRelease_date()));
		
			boolean flag =  contractService.addContract(contract);			
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Added Succesfully."); 
			} else {
				attributes.addFlashAttribute("error","Adding Contract is failed. Try again.");
			}
		 }catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contract is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getcontract(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.updateContractForm);
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractor = contractService.getContractorsList();
			model.addObject("contractor", contractor);
			
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			
			List<Contract> contract_Statustype = contractService.getContractStatusType();
			model.addObject("contract_Statustype", contract_Statustype);
			
			Contract contractDeatils = contractService.getContract(obj);
			model.addObject("contractDeatils", contractDeatils);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-contract/{contract_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getcontract(@ModelAttribute Contract obj,@PathVariable("contract_id") String contract_id ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.updateContractForm);
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			List<Contract> contractor = contractService.getContractorsList();
			model.addObject("contractor", contractor);
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			List<Contract> contract_Statustype = contractService.getContractStatusType();
			model.addObject("contract_Statustype", contract_Statustype);
			
			obj.setContract_id(contract_id);
			Contract contractDeatils = contractService.getContract(obj);
			model.addObject("contractDeatils", contractDeatils);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Contract contract,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract");

			contract.setDoc(DateParser.parse(contract.getDoc()));
			contract.setCa_date(DateParser.parse(contract.getCa_date()));
			contract.setDate_of_start(DateParser.parse(contract.getDate_of_start()));			
			contract.setLoa_date(DateParser.parse(contract.getLoa_date()));
			contract.setActual_completion_date(DateParser.parse(contract.getActual_completion_date()));		
			contract.setContract_closure_date(DateParser.parse(contract.getContract_closure_date()));
			contract.setCompletion_certificate_release(DateParser.parse(contract.getCompletion_certificate_release()));		
			contract.setFinal_takeover(DateParser.parse(contract.getFinal_takeover()));
			contract.setFinal_bill_release(DateParser.parse(contract.getFinal_bill_release()));
			contract.setDefect_liability_period(DateParser.parse(contract.getDefect_liability_period()));
			contract.setRetention_money_release(DateParser.parse(contract.getRetention_money_release()));
			contract.setPbg_release(DateParser.parse(contract.getPbg_release()));
			contract.setBg_date(DateParser.parse(contract.getBg_date()));
			contract.setRelease_date(DateParser.parse(contract.getRelease_date()));
		
			boolean flag =  contractService.updateContract(contract);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Contract is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Contract is failed. Try again.");
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/export-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafety(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Contract contract,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.contractGrid);
		List<Contract> dataList = new ArrayList<Contract>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/contract");
			dataList = contractService.contractList(contract);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Contract"));
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
	            String headerString = "Work^Contract ID^Contract Name^Contract Short Name^Contractor^Department^HOD^DY HOD^Contract Type^Scope of Contract"
	            		+ "^Tally Head^Estimated Cost^Awarded Cost^LOA Letter Number^LOA Date^CA NO^CA Date^Date of Start^DOC^"
	            		+ "Actual Completion Date^Completed Cost^Contract Closure Date^Completion Certificate Release^Final Takeover^Final Release^"
	            		+ "Contract Status^Defect Liability Period^Retention Money Release^PBG Release^Contract Closure^Bank Guarantee Requried^Insurance Requried";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
	            short rowNo = 1;
	            for (Contract obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                int c = 0;
	                
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_id());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue((!StringUtils.isEmpty(obj.getContractor_id_fk())?obj.getContractor_id_fk():"")+(!StringUtils.isEmpty(obj.getContractor_name())?" - "+obj.getContractor_name():""));
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDepartment_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDesignation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDy_hod_designation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_type_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getScope_of_contract());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTally_head());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getEstimated_cost());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAwarded_cost());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLoa_letter_number());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLoa_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCa_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCa_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDate_of_start());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDoc());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getActual_completion_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCompleted_cost());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_closure_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCompletion_certificate_release());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getFinal_takeover());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getFinal_bill_release());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_status_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDefect_liability_period());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRetention_money_release());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPbg_release());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_closure());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getBg_required());
	                
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getInsurance_required());
	                
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Contract_"+dateFormat.format(date);
                
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
			logger.error("exportContract : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
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
