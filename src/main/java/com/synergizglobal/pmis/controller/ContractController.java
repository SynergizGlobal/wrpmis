package com.synergizglobal.pmis.controller;

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
import org.apache.poi.ss.util.WorkbookUtil;
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

import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
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
	StripChartService stripChartService;
	
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
	
	@RequestMapping(value = "/ajax/getDepartmentsListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDepartmentsList(@ModelAttribute Contract obj) {
		List<Contract> dataList = null;  
		try {
			dataList = contractService.getgetDepartmentsList(obj);
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
			
			List<Contract> departmentfilterList = contractService.getgetDepartmentsList(obj);
			model.addObject("departmentfilterList", departmentfilterList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.setDyHodList();
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
			
			List<User> dyHodList = contractService.setDyHodList();
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
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("Work");
			            headingRow.createCell((short)1).setCellValue("Contract ID");
			            headingRow.createCell((short)2).setCellValue("Contract Name");
			            headingRow.createCell((short)3).setCellValue("Contract Short Name");
			            headingRow.createCell((short)4).setCellValue("Contractor");
			            headingRow.createCell((short)5).setCellValue("Department");
			            headingRow.createCell((short)6).setCellValue("HOD");
			            headingRow.createCell((short)7).setCellValue("DY HOD");
			            headingRow.createCell((short)8).setCellValue("Contract Type");
			            headingRow.createCell((short)9).setCellValue("Scope of Contract");
			            headingRow.createCell((short)10).setCellValue("Tally Head");
			            headingRow.createCell((short)11).setCellValue("Estimated Cost");
			            headingRow.createCell((short)12).setCellValue("Awarded Cost");
			            headingRow.createCell((short)13).setCellValue("LOA Letter Number");
			            headingRow.createCell((short)14).setCellValue("LOA Date");
			            headingRow.createCell((short)15).setCellValue("CA NO");
			            headingRow.createCell((short)16).setCellValue("CA Date");
			            headingRow.createCell((short)17).setCellValue("Date of Start");
			            headingRow.createCell((short)18).setCellValue("DOC");
			            headingRow.createCell((short)19).setCellValue("Actual Completion Date");
			            headingRow.createCell((short)20).setCellValue("Completed Cost");
			            headingRow.createCell((short)21).setCellValue("Contract Closure Date");
			            headingRow.createCell((short)22).setCellValue("Completion Certificate Release");
			            headingRow.createCell((short)23).setCellValue("Final Takeover");
			            headingRow.createCell((short)24).setCellValue("Final Release");
			            headingRow.createCell((short)25).setCellValue("Contract Status");
			            headingRow.createCell((short)26).setCellValue("Defect Liability Period");
			            headingRow.createCell((short)27).setCellValue("Retention Money Release");
			            headingRow.createCell((short)28).setCellValue("PBG Release");
			            headingRow.createCell((short)29).setCellValue("Contract Closure");
			            headingRow.createCell((short)30).setCellValue("Bank Guarantee Requried");
			            headingRow.createCell((short)31).setCellValue("Insurance Requried");
			            short rowNo = 1;
			            for (Contract obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_name());
			                row.createCell((short)1).setCellValue(obj.getContract_id());
			                row.createCell((short)2).setCellValue(obj.getContract_name());
			                row.createCell((short)3).setCellValue(obj.getContract_short_name());
			                row.createCell((short)4).setCellValue((!StringUtils.isEmpty(obj.getContractor_id_fk())?obj.getContractor_id_fk():"")+(!StringUtils.isEmpty(obj.getContractor_name())?" - "+obj.getContractor_name():""));
			                row.createCell((short)5).setCellValue(obj.getDepartment_name());
			                row.createCell((short)6).setCellValue(obj.getDesignation());
			                row.createCell((short)7).setCellValue(obj.getDy_hod_designation());
			                row.createCell((short)8).setCellValue(obj.getContract_type_fk());
			                row.createCell((short)9).setCellValue(obj.getScope_of_contract());
			                row.createCell((short)10).setCellValue(obj.getTally_head());
			                row.createCell((short)11).setCellValue(obj.getEstimated_cost());
			                row.createCell((short)12).setCellValue(obj.getAwarded_cost());
			                row.createCell((short)13).setCellValue(obj.getLoa_letter_number());
			                row.createCell((short)14).setCellValue(obj.getLoa_date());
			                row.createCell((short)15).setCellValue(obj.getCa_no());
			                row.createCell((short)16).setCellValue(obj.getCa_date());
			                row.createCell((short)17).setCellValue(obj.getDate_of_start());
			                row.createCell((short)18).setCellValue(obj.getDoc());
			                row.createCell((short)19).setCellValue(obj.getActual_completion_date());
			                row.createCell((short)20).setCellValue(obj.getCompleted_cost());
			                row.createCell((short)21).setCellValue(obj.getContract_closure_date());
			                row.createCell((short)22).setCellValue(obj.getCompletion_certificate_release());
			                row.createCell((short)23).setCellValue(obj.getFinal_takeover());
			                row.createCell((short)24).setCellValue(obj.getFinal_bill_release());
			                row.createCell((short)25).setCellValue(obj.getContract_status_fk());
			                row.createCell((short)26).setCellValue(obj.getDefect_liability_period());
			                row.createCell((short)27).setCellValue(obj.getRetention_money_release());
			                row.createCell((short)28).setCellValue(obj.getPbg_release());
			                row.createCell((short)29).setCellValue(obj.getContract_closure());
			                row.createCell((short)30).setCellValue(obj.getBg_required());
			                row.createCell((short)31).setCellValue(obj.getInsurance_required());
			                rowNo++;
			            }
			            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
			            	//Budgetsheet.autoSizeColumn(columnIndex);
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
	
}
