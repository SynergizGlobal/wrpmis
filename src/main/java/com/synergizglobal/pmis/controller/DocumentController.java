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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.Iservice.DocumentService;
import com.synergizglobal.pmis.Iservice.SafetyEquipmentService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Document;

@Controller
public class DocumentController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DocumentController.class);
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	SafetyEquipmentService service;

	@Autowired
	DesignService designService;

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
	
	
	@RequestMapping(value="/documents",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView documents(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.documentGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("documents : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-documents-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getDocumentsList(@ModelAttribute Document obj) {
		List<Document> documentList = null;
		try {
			documentList = documentService.getDocumentsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentsList : " + e.getMessage());
		}
		return documentList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInDocuments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getContractsList(@ModelAttribute Document obj) {
		List<Document> contractsList = null;
		try {
			contractsList = documentService.getDocumentContractsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentContractsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getProjectPriorityFilterListInDocuments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document>getProjectPriorityList(@ModelAttribute Document obj) {
		List<Document> projectPriorityList = null;
		try {
			projectPriorityList = documentService.getDocumentProjectPriorityList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentprojectPriorityList : " + e.getMessage());
		}
		return projectPriorityList;
	}
	
	@RequestMapping(value = "/ajax/getDocumentTypesFilterListInDocuments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getDocumentTypesList(@ModelAttribute Document obj) {
		List<Document> documentTypesList = null;
		try {
			documentTypesList = documentService.getDocumentTypesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentTypesList : " + e.getMessage());
		}
		return documentTypesList;
	}
	
	@RequestMapping(value = "/ajax/getResponsibleForApprovalFilterListInDocuments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getResponsibleForApprovalList(@ModelAttribute Document obj) {
		List<Document> responsibleForApprovalList = null;
		try {
			responsibleForApprovalList = documentService.getDocumentResponsibleForApprovalList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentResponsibleForApprovalList : " + e.getMessage());
		}
		return responsibleForApprovalList;
	}
	
	@RequestMapping(value = "/add-document-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDocumentForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDocumentForm);
			model.addObject("action", "add");
			List<Document> statusList = documentService.getStatusList();
			model.addObject("statusList", statusList);
			List<Document> documentTypeList = documentService.getDocumentTypeList();
			model.addObject("documentTypeList", documentTypeList);
			List<Document> priorityList = documentService.getPriorityList();
			model.addObject("priorityList", priorityList);
			List<Document> userList = documentService.getUserList();
			model.addObject("userList", userList);
			List<Document> projectsList = documentService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
		}catch (Exception e) {
				logger.error("addDocumentForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-document", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDocument(@ModelAttribute Document obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDocumentForm);
			model.addObject("action", "edit");
			List<Document> projectsList = documentService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Document> statusList = documentService.getStatusList();
			model.addObject("statusList", statusList);
			List<Document> documentTypeList = documentService.getDocumentTypeList();
			model.addObject("documentTypeList", documentTypeList);
			List<Document> priorityList = documentService.getPriorityList();
			model.addObject("priorityList", priorityList);
			List<Document> userList = documentService.getUserList();
			model.addObject("userList", userList);
			Document documentDetails = documentService.getDocument(obj);
			model.addObject("documentDetails", documentDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDocument : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-document", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDocument(@ModelAttribute Document obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/documents");
			obj.setSubmission_date(DateParser.parse(obj.getSubmission_date()));
			obj.setApproval_date(DateParser.parse(obj.getApproval_date()));
			boolean flag =  documentService.addDocument(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Document Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding Document is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Document is failed. Try again.");
			logger.error("addDocument : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-document", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateDocument(@ModelAttribute Document obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/documents");
			obj.setSubmission_date(DateParser.parse(obj.getSubmission_date()));
			obj.setApproval_date(DateParser.parse(obj.getApproval_date()));
			boolean flag =  documentService.updateDocument(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Document Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Document is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Document is failed. Try again.");
			logger.error("updateDocument : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/delete-document", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteDocument(@ModelAttribute Document obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/documents");
			boolean flag =  documentService.deleteDocument(obj);
		}catch (Exception e) {
			logger.error("deleteDocument : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/export-document", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDocument(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Document dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.documentGrid);
		List<Document> dataList = new ArrayList<Document>();
		try {
			view.setViewName("redirect:/documents");
			dataList =   documentService.getDocumentsList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet =  workBook.createSheet(WorkbookUtil.createSafeSheetName("Documents"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
		        headingRow.createCell((short)0).setCellValue("Document No");
		        headingRow.createCell((short)1).setCellValue("Project Priority");
		        headingRow.createCell((short)2).setCellValue("Project");
	            headingRow.createCell((short)3).setCellValue("Work");
	            headingRow.createCell((short)4).setCellValue("Contarct");
	            headingRow.createCell((short)5).setCellValue("Document Type");
	            headingRow.createCell((short)6).setCellValue("Document Name");
	            headingRow.createCell((short)7).setCellValue("Responsible For Approval");

	            short rowNo = 1;
	            for (Document obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getDocument_no());
	                row.createCell((short)1).setCellValue(obj.getProject_priority_fk());
	                row.createCell((short)2).setCellValue(obj.getProject_id_fk() +" - "+ obj.getProject_name());
	                row.createCell((short)3).setCellValue(obj.getWork_id_fk()+"-"+obj.getWork_name());
	                row.createCell((short)4).setCellValue(obj.getContract_id_fk()+"-"+ obj.getContract_name());
	                row.createCell((short)5).setCellValue(obj.getDocument_type_fk());
	                row.createCell((short)6).setCellValue(obj.getDocument_name());
	                row.createCell((short)7).setCellValue(obj.getResponsible_for_approval());
	          
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//sheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Document_"+dateFormat.format(date);
                
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

	
	
}
