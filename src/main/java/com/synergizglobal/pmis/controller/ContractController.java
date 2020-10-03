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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.Work;


@Controller
public class ContractController {
	Logger logger = Logger.getLogger(ContractController.class);
	
	@Autowired
	ContractService contractservice;
	
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
	public ModelAndView Work(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.contractGrid);
		try {
			List<Work> workList = workService.getworkList();
			model.addObject("workList", workList);
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			List<User> hodList = contractservice.setHodList();
			model.addObject("hodList", hodList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Work : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractList(@ModelAttribute Contract obj) {
		List<Contract> contractList = null;
		try {
		 contractList = contractservice.contractList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("getSafetyList : " + e.getMessage());
		}
		return contractList;
	}
	
	@RequestMapping(value = "/add-contract-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractForm(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addContractForm);	
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Work> workList = workService.getworkList();
			model.addObject("workList", workList);
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			List<User> hodList = contractservice.setHodList();
			model.addObject("hodList", hodList);
			/*
			 * List<Contract> contracts = stripChartService.getContractsList(null);
			 * model.addObject("contracts", contracts);
			 */
			List<Contract> contractor = contractservice.getContractorList();
			model.addObject("contractor", contractor);
			List<Contract> contract_type = contractservice.getContractTypeList();
			model.addObject("contract_type", contract_type);
			List<Contract> insurance_type = contractservice.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			List<Contract> contractList = contractservice.contractList(obj);
			model.addObject("contractList", contractList);
			List<BankGuarantee> bankGuaranteeTYpe = contractservice.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			List<Insurence> InsurenceType = contractservice.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			List<Contract> contract_Statustype = contractservice.getContractStatusType();
			model.addObject("contract_Statustype", contract_Statustype);
		}catch (Exception e) {
			logger.info("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-Contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContract(@ModelAttribute  Contract contract,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract");
			boolean flag =  contractservice.addContract(contract);
			
			  if(flag == true) {
				  MultipartFile file = contract.getContractFile();
					if (null != file && !file.isEmpty()){
						String saveDirectory = CommonConstants.CONTRACT_FILE_SAVING_PATH ;
						String fileName = file.getOriginalFilename();
						FileUploads.singleFileSaving(file, saveDirectory, fileName);}
					
				  attributes.addFlashAttribute("success", "Contract Added Succesfully."); 
			  } else {
			  attributes.addFlashAttribute("error","Adding Contract is failed. Try again.");
			  }
		 }catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contract is failed. Try again.");
			logger.info("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getcontract(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		String contractId = null;
		try{
			model.setViewName(PageConstants.updateContractForm);
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Work> workList = workService.getworkList();
			model.addObject("workList", workList);
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			List<User> hodList = contractservice.setHodList();
			model.addObject("hodList", hodList);
			/*
			 * List<Contract> contracts = stripChartService.getContractsList(null);
			 * model.addObject("contracts", contracts);
			 */
			List<Contract> contractor = contractservice.getContractorList();
			model.addObject("contractor", contractor);
			List<Contract> contract_type = contractservice.getContractTypeList();
			model.addObject("contract_type", contract_type);
			List<Contract> insurance_type = contractservice.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			List<Contract> contractList = contractservice.contractList(obj);
			model.addObject("contractList", contractList);
			List<BankGuarantee> bankGuaranteeTYpe = contractservice.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			List<Insurence> InsurenceType = contractservice.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			List<Contract> contract_Statustype = contractservice.getContractStatusType();
			model.addObject("contract_Statustype", contract_Statustype);
			contractId = obj.getContract_id();
			Contract contractDeatils = contractservice.getcontract(contractId, obj);
			model.addObject("contractDeatils", contractDeatils);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Contract contract,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contract");
			boolean flag =  contractservice.updateContract(contract);
			if(flag == true) {
				MultipartFile file = contract.getContractFile();
				if (null != file && !file.isEmpty()){
					String saveDirectory = CommonConstants.CONTRACT_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
				}				
				attributes.addFlashAttribute("success", "Contract Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Contract is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Contract is failed. Try again.");
			logger.info("Contract : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/export-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafety(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Contract contract,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.safetyGrid);
		List<Contract> dataList = new ArrayList<Contract>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/contract");
			dataList = contractservice.contractList(contract);  
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet();
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("Work");
			            headingRow.createCell((short)1).setCellValue("Contract ID");
			            headingRow.createCell((short)2).setCellValue("Contract Name");
			            headingRow.createCell((short)3).setCellValue("Contractor Name");
			            headingRow.createCell((short)5).setCellValue("Department");
			            headingRow.createCell((short)6).setCellValue("HOD");
			            headingRow.createCell((short)7).setCellValue("DY HOD");
			            short rowNo = 1;
			            for (Contract obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getWork_name());
			                row.createCell((short)1).setCellValue(obj.getContract_id());
			                row.createCell((short)2).setCellValue(obj.getContract_name());
			                row.createCell((short)3).setCellValue(obj.getContractor_id_fk());
			                row.createCell((short)5).setCellValue(obj.getDepartment_fk());
			                row.createCell((short)6).setCellValue(obj.getHod_user_id_fk());
			                row.createCell((short)7).setCellValue(obj.getDy_hod_user_id_fk());
			                rowNo++;
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
			logger.error("exportSafety : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
}
