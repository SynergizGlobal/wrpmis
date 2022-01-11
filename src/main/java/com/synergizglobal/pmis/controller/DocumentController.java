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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.Iservice.DocumentService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.DesignsPaginationObject;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.DocumentsPaginationObject;
import com.synergizglobal.pmis.model.Training;

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
	DocumentService service;

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

	@RequestMapping(value = "/ajax/get-documents-list", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Document obj, HttpServletRequest request,
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

			List<Document> documentsList = new ArrayList<Document>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				documentsList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				documentsList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//documentsList = getListBasedOnSearchParameter(searchParameter,documentsList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			DocumentsPaginationObject personJsonObject = new DocumentsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(documentsList);

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
	public int getTotalRecords(Document obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = documentService.getTotalRecords(obj, searchParameter);
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
	public List<Document> createPaginationData(int startIndex, int offset, Document obj, String searchParameter) {
		List<Document> earthWorkList = null;
		try {
			earthWorkList = documentService.getDocumentsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
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
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInDocuments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getProjectsList(@ModelAttribute Document obj) {
		List<Document> projectsList = null;
		try {
			projectsList = documentService.getDocumentProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInDocuments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getWorksList(@ModelAttribute Document obj) {
		List<Document> worksList = null;
		try {
			worksList = documentService.getDocumentWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDocumentWorksList : " + e.getMessage());
		}
		return worksList;
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
	public ModelAndView addDocumentForm(@ModelAttribute Document obj){
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
			
			List<Document> projectsList = documentService.getProjectsListForDocumentForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Document> worksList = documentService.getWorkListForDocumentForm(obj);
			model.addObject("worksList", worksList);
			
			List<Document> contractsList = documentService.getContractsListForDocumentForm(obj);
			model.addObject("contractsList", contractsList);
			
		}catch (Exception e) {
				logger.error("addDocumentForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/getProjectsListForDocumentForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getProjectsListForDocumentForm(@ModelAttribute Document obj) {
		List<Document> objsList = null;
		try {
			objsList = documentService.getProjectsListForDocumentForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForDocumentForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForDocumentForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getWorkListForDocumentForm(@ModelAttribute Document obj) {
		List<Document> objsList = null;
		try {
			objsList = documentService.getWorkListForDocumentForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForDocumentForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForDocumentForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Document> getContractsListForDocumentForm(@ModelAttribute Document obj) {
		List<Document> objsList = null;
		try {
			objsList = documentService.getContractsListForDocumentForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDocumentForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/get-document", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDocument(@ModelAttribute Document obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDocumentForm);
			model.addObject("action", "edit");
			
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
	@RequestMapping(value = "/get-document/{document_no}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDocument(@ModelAttribute Document obj,@PathVariable("document_no") String document_no,HttpSession session,RedirectAttributes attributes ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDocumentForm);
			model.addObject("action", "edit");
			
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
		List<Document> revisionList = new ArrayList<Document>();
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
	                row.createCell((short)3).setCellValue(obj.getWork_id_fk()+"-"+obj.getWork_short_name());
	                row.createCell((short)4).setCellValue(obj.getContract_id_fk()+"-"+ obj.getContract_short_name());
	                row.createCell((short)5).setCellValue(obj.getDocument_type_fk());
	                row.createCell((short)6).setCellValue(obj.getDocument_name());
	                row.createCell((short)7).setCellValue(obj.getResponsible_for_approval());
	          
	                rowNo++;
	            }
	           
	            XSSFSheet revisionSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Revisions"));
		        workBook.setSheetOrder(revisionSheet.getSheetName(), 1);
		        XSSFRow headingRow1 = revisionSheet.createRow(0);
		        headingRow1.createCell((short)0).setCellValue("Revision ID");
		        headingRow1.createCell((short)1).setCellValue("Document No");
	            headingRow1.createCell((short)2).setCellValue("Revision No");
	         	headingRow1.createCell((short)3).setCellValue("Status");
	            headingRow1.createCell((short)4).setCellValue("Submission Date");
	            headingRow1.createCell((short)5).setCellValue("Approval Date");
	            headingRow1.createCell((short)6).setCellValue("Remark");
	            short rowNo2 = 1;
	        	for (Document revisions : dataList) { 
	        		String id = revisions.getDocument_no();
	        		revisionList = documentService.getRevisionsList(id);
		           
		            for (Document sObj : revisionList) {
		                XSSFRow row2 = revisionSheet.createRow(rowNo2);
		                row2.createCell((short)0).setCellValue(sObj.getId());
		                row2.createCell((short)1).setCellValue(sObj.getDocument_no_fk());
		                row2.createCell((short)2).setCellValue(sObj.getRevision_no());
		                row2.createCell((short)3).setCellValue(sObj.getStatus_fk());
		                row2.createCell((short)4).setCellValue(sObj.getSubmission_date());
		                row2.createCell((short)5).setCellValue(sObj.getApproval_date());
		                row2.createCell((short)6).setCellValue(sObj.getRemarks());
		          
		                rowNo2++;
		            }
	        	}
	        	for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
		            	//sheet.autoSizeColumn(columnIndex);
		        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < revisionList.size(); columnIndex++) {
	        		//revisionSheet.autoSizeColumn(columnIndex);
	        		revisionSheet.setColumnWidth(columnIndex, 25 * 200);
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
