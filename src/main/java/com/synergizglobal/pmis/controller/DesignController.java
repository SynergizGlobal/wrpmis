package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Project;

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
	
	@RequestMapping(value = "/get-design", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDesign(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDesign);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
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
			
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList();	
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
	public ModelAndView addDesignForm(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDesign);	
			model.addObject("action", "add");
			
			List<Project> projectsList = homeService.getProjectsList();
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
			
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList();	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);
			
		}catch (Exception e) {
			logger.error("addDesignForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/add-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/design");
			
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));
			
			boolean flag =  designService.addDesign(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Design Added Succesfully.");
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
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/design");
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));

			boolean flag =  designService.updateDesign(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Design Updated Succesfully.");
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
			            XSSFSheet sheet =workBook.createSheet(WorkbookUtil.createSafeSheetName("Design"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("Design ID");
			            headingRow.createCell((short)1).setCellValue("Work");
			            headingRow.createCell((short)2).setCellValue("Contract");
			            headingRow.createCell((short)3).setCellValue("HOD");
			            headingRow.createCell((short)4).setCellValue("Dy HOD");
			            headingRow.createCell((short)5).setCellValue("Prepared by ID");
			            headingRow.createCell((short)6).setCellValue("Consultant Contract ID");
			            headingRow.createCell((short)7).setCellValue("Proof Consultant Contract ID");
			            headingRow.createCell((short)8).setCellValue("Submited to Proof Consultant");
			            headingRow.createCell((short)9).setCellValue("Approval by Proof Consultant");
			            headingRow.createCell((short)10).setCellValue("Structure Type");
			            headingRow.createCell((short)11).setCellValue("Component");
			            headingRow.createCell((short)12).setCellValue("Title");
			            headingRow.createCell((short)13).setCellValue("Structure");
			            headingRow.createCell((short)14).setCellValue("Drawing Type");
			            headingRow.createCell((short)15).setCellValue("Contractor Drawing No");
			            headingRow.createCell((short)16).setCellValue("MRVC Drawing No");
			            headingRow.createCell((short)17).setCellValue("Division Drawing No");
			            headingRow.createCell((short)18).setCellValue("HQ Drawing No");
			            headingRow.createCell((short)19).setCellValue("Planned Start");
			            headingRow.createCell((short)20).setCellValue("Planned finish");
			            headingRow.createCell((short)21).setCellValue("Revision");
			            headingRow.createCell((short)22).setCellValue("Consultant Submission");
			            headingRow.createCell((short)23).setCellValue("MRVC Reviewed");
			            headingRow.createCell((short)24).setCellValue("Divisional Submission");
			            headingRow.createCell((short)25).setCellValue("Submitted to Division");
			            headingRow.createCell((short)26).setCellValue("Divisional Approval");
			            headingRow.createCell((short)27).setCellValue("HQ Submission");
			            headingRow.createCell((short)28).setCellValue("Submitted to HQ");
			            headingRow.createCell((short)29).setCellValue("HQ Approval");
			            headingRow.createCell((short)30).setCellValue("GFC Released");
			            headingRow.createCell((short)31).setCellValue("As Built Status");
			            headingRow.createCell((short)32).setCellValue("As Built Date");
			            headingRow.createCell((short)33).setCellValue("Remarks");

			            short rowNo = 1;
			            for (Design obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getDesign_id());
			                row.createCell((short)1).setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_name());
			                row.createCell((short)2).setCellValue(obj.getContract_id_fk()+" - "+ obj.getContract_name());
			                row.createCell((short)3).setCellValue(obj.getHod());
			                row.createCell((short)4).setCellValue(obj.getDy_hod());
			                row.createCell((short)5).setCellValue(obj.getPrepared_by_id_fk());
			                row.createCell((short)6).setCellValue(obj.getConsultant_contract_id_fk());
			                row.createCell((short)7).setCellValue(obj.getProof_consultant_contract_id_fk());
			                row.createCell((short)8).setCellValue(obj.getSubmited_to_proof_consultant_fk());
			                row.createCell((short)9).setCellValue(obj.getApproval_by_proof_consultant_fk());
			                row.createCell((short)10).setCellValue(obj.getStructure_type_fk());
			                row.createCell((short)11).setCellValue(obj.getComponent());
			                row.createCell((short)12).setCellValue(obj.getDrawing_title());
			                row.createCell((short)13).setCellValue(obj.getStructure_type_fk());
			                row.createCell((short)14).setCellValue(obj.getDrawing_type_fk());
			                row.createCell((short)15).setCellValue(obj.getContractor_drawing_no());
			                row.createCell((short)16).setCellValue(obj.getMrvc_drawing_no());
			                row.createCell((short)17).setCellValue(obj.getDivision_drawing_no());
			                row.createCell((short)18).setCellValue(obj.getHq_drawing_no());
			                row.createCell((short)19).setCellValue(obj.getPlanned_start());
			                row.createCell((short)20).setCellValue(obj.getPlanned_finish());
			                row.createCell((short)21).setCellValue(obj.getRevision());
			                row.createCell((short)22).setCellValue(obj.getConsultant_submission());
			                row.createCell((short)23).setCellValue(obj.getMrvc_reviewed());
			                row.createCell((short)24).setCellValue(obj.getDivisional_submission_fk());
			                row.createCell((short)25).setCellValue(obj.getSubmitted_to_division());
			                row.createCell((short)26).setCellValue(obj.getDivisional_approval());
			                row.createCell((short)27).setCellValue(obj.getHq_submission_fk());
			                row.createCell((short)28).setCellValue(obj.getSubmitted_to_hq());
			                row.createCell((short)29).setCellValue(obj.getHq_approval());
			                row.createCell((short)30).setCellValue(obj.getGfc_released());
			                row.createCell((short)31).setCellValue(obj.getAs_built_status());
			                row.createCell((short)32).setCellValue(obj.getAs_built_date());
			                row.createCell((short)33).setCellValue(obj.getRemarks());
			                rowNo++;
			            }
			            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
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
	
	
	@RequestMapping(value = "/upload-designs", method = {RequestMethod.POST})
	public ModelAndView uploadDesigns(@ModelAttribute Design design,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/design");
			
			if(!StringUtils.isEmpty(design.getDesignFile())){
				MultipartFile multipartFile = design.getDesignFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet designsDrawingsSheet = workbook.getSheetAt(1);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = designsDrawingsSheet.getRow(1);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getDesignFileFormat();;	
							int noOfColumns = headerRow.getLastCellNum();
							if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						
						int count = uploadDesigns(design,userId,userName);
						attributes.addFlashAttribute("success", count + " Designs added successfully.");	
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
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
					XSSFSheet designsDrawingsSheet = workbook.getSheetAt(1);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 2; i <= designsDrawingsSheet.getLastRowNum();i++){
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
							if(!StringUtils.isEmpty(val)) { design.setContract_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDepartment_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHod(val);}	
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDy_hod(val);}					
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { design.setPrepared_by_id_fk(val);}								
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { design.setConsultant_contract_id_fk(val);}										
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { design.setProof_consultant_contract_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmited_to_proof_consultant_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { design.setApproval_by_proof_consultant_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { design.setStructure_type_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { design.setComponent(val);}
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDrawing_type_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { design.setContractor_drawing_no(val);}
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { design.setMrvc_drawing_no(val);}
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDivision_drawing_no(val);}								
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHq_drawing_no(val);}											
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDrawing_title(val);}								
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { design.setPlanned_start(val);}										
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { design.setPlanned_finish(val);}
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { design.setRevision(val);}
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { design.setConsultant_submission(val);}
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { design.setMrvc_reviewed(val);}
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDivisional_submission_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmitted_to_division(val);}
							
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { design.setDivisional_approval(val);}
							
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHq_submission_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) { design.setSubmitted_to_hq(val);}
							
							val = formatter.formatCellValue(row.getCell(28)).trim();
							if(!StringUtils.isEmpty(val)) { design.setHq_approval(val);}
							
							val = formatter.formatCellValue(row.getCell(29)).trim();
							if(!StringUtils.isEmpty(val)) { design.setGfc_released(val);}
							
							val = formatter.formatCellValue(row.getCell(30)).trim();
							if(!StringUtils.isEmpty(val)) { design.setAs_built_status(val);}
							
							val = formatter.formatCellValue(row.getCell(31)).trim();
							if(!StringUtils.isEmpty(val)) { design.setAs_built_date(val);}
							
							val = formatter.formatCellValue(row.getCell(32)).trim();
							if(!StringUtils.isEmpty(val)) { design.setRemarks(val);}
							
							design.setSubmited_to_proof_consultant_fk(DateParser.parse(design.getSubmited_to_proof_consultant_fk()));
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
						}
						List<Design> pObjList = new ArrayList<Design>();
						
						if(!StringUtils.isEmpty(design.getMrvc_drawing_no())) {
							XSSFSheet designRevisionsSheet = workbook.getSheetAt(2);
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
							}
							design.setDesignRevisions(pObjList);
						}
						
						boolean flag = design.checkNullOrEmpty();
						
						if(!flag) {
							designsList.add(design);
						}
					}
					
					if(!designsList.isEmpty() && designsList != null){
						count  = designService.uploadDesigns(designsList);
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