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

import com.synergizglobal.pmis.Iservice.DocumentService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
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
	
	
	@RequestMapping(value="/document",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView document(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.documentGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("document : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-document", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getDocumentList(@ModelAttribute Document obj) {
		List<Document> documentList = null;
		try {
			documentList = documentService.documentList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("documentList : " + e.getMessage());
		}
		return documentList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInDocument", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/ajax/getProjectPriorityFilterListInDocument", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/ajax/getDocumentTypesFilterListInDocument", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/ajax/getResponsibleForApprovalFilterListInDocument", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
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
		//	List<Document> financialYearList = documentService.getFinancialYearList();
			//model.addObject("financialYearList", financialYearList);
			
		}catch (Exception e) {
				logger.error("addDocumentForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-document", method = {RequestMethod.POST})
	public ModelAndView getDocument(@ModelAttribute Document obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDocumentForm);
			model.addObject("action", "edit");
			
			//Document documentDetails = documentService.getDocument(obj);
			//model.addObject("documentDetails", documentDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDocument : " + e.getMessage());
		}
		return model;
	 }
	
	
	@RequestMapping(value = "/export-document", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDocument(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Document dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.documentGrid);
		List<Document> dataList = new ArrayList<Document>();
		try {
			view.setViewName("redirect:/document");
			dataList =   documentService.documentList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Work");
	            headingRow.createCell((short)1).setCellValue("Contarct");
	         	headingRow.createCell((short)2).setCellValue("Priority");
	            headingRow.createCell((short)3).setCellValue("Document Type");
	            headingRow.createCell((short)4).setCellValue("Document Name");
	            headingRow.createCell((short)5).setCellValue("Responsible For Approval");

	            short rowNo = 1;
	            for (Document obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getWork_id_fk()+"-"+obj.getWork_short_name());
	                row.createCell((short)1).setCellValue(obj.getContract_id_fk()+"-"+ obj.getContract_short_name());
	                row.createCell((short)2).setCellValue(obj.getProject_priority_fk());
	                row.createCell((short)3).setCellValue(obj.getDocument_type_fk());
	                row.createCell((short)4).setCellValue(obj.getDocument_name());
	                row.createCell((short)5).setCellValue(obj.getResponsible_for_approval());
	          
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
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
