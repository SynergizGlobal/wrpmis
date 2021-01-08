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

import org.apache.commons.lang3.ArrayUtils;
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

import com.synergizglobal.pmis.Iservice.TrainingService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.Training;

@Controller
public class TrainingController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TrainingController.class);
	
	@Autowired
	TrainingService trainingService;


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
	
	
	@RequestMapping(value="/training",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView training(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.trainingGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("training : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-training", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingList(@ModelAttribute Training obj) {
		List<Training> trainingList = null;
		try {
			trainingList = trainingService.getTrainingList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingList : " + e.getMessage());
		}
		return trainingList;
	}
	

	@RequestMapping(value = "/ajax/getTrainingTypesFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingTypesList(@ModelAttribute Training obj) {
		List<Training> trainingTypesList = null;
		try {
			trainingTypesList = trainingService.getTrainingTypesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingTypesList : " + e.getMessage());
		}
		return trainingTypesList;
	}
	

	@RequestMapping(value = "/ajax/getTrainingCategorysFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingCategorysList(@ModelAttribute Training obj) {
		List<Training> trainingCategorysList = null;
		try {
			trainingCategorysList = trainingService.getTrainingCategorysList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingCategorysList : " + e.getMessage());
		}
		return trainingCategorysList;
	}
	

	@RequestMapping(value = "/ajax/getStatusFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getStatusList(@ModelAttribute Training obj) {
		List<Training> statusList = null;
		try {
			statusList = trainingService.getStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return statusList;
	}

	
	@RequestMapping(value = "/add-training-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addTrainingForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTrainingForm);
			model.addObject("action", "add");
			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);
			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);
			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);
			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);
			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			List<Training> usersList = trainingService.getUsersList();
			model.addObject("usersList", usersList);
			
		}catch (Exception e) {
				logger.error("addTrainingForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-training", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getTraining(@ModelAttribute Training obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTrainingForm);
			model.addObject("action", "edit");
			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);
			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);
			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);
			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);
			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			Training trainingDetails = trainingService.getTraining(obj);
			model.addObject("trainingDetails", trainingDetails);
			List<Training> usersList = trainingService.getUsersList();
			model.addObject("usersList", usersList);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getTraining : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-training", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTraining(@ModelAttribute Training obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/training");
			
			obj.setStart_time(DateParser.parseDateTime(obj.getStart_time()));
			obj.setEnd_time(DateParser.parseDateTime(obj.getEnd_time()));
			
			boolean flag =  trainingService.addTraining(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Training is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Training is failed. Try again.");
			logger.error("addTraining : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-training", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTraining(@ModelAttribute Training obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/training");
			obj.setStart_time(DateParser.parseDateTime(obj.getStart_time()));
			obj.setEnd_time(DateParser.parseDateTime(obj.getEnd_time()));

			boolean flag =  trainingService.updateTraining(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Training is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Training is failed. Try again.");
			logger.error("updateTraining : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/export-training", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportTraining(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Training dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.trainingGrid);
		List<Training> dataList = new ArrayList<Training>();
		List<Training> sessionsList = new ArrayList<Training>();
		List<Training> attendeesList = new ArrayList<Training>();
		try {
			view.setViewName("redirect:/training");
			dataList =   trainingService.getTrainingList(dObj);
			XSSFWorkbook  workBook = new XSSFWorkbook();
		   
			if(dataList != null && dataList.size() > 0){
				//XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet trainingSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Training"));
		        workBook.setSheetOrder(trainingSheet.getSheetName(), 0);
		        XSSFRow headingRow = trainingSheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Training ID");
	            headingRow.createCell((short)1).setCellValue("Training Type");
	         	headingRow.createCell((short)2).setCellValue("Category");
	            headingRow.createCell((short)3).setCellValue("Faculty Name");
	            headingRow.createCell((short)4).setCellValue("Designation");
	            headingRow.createCell((short)5).setCellValue("Title");
	            headingRow.createCell((short)6).setCellValue("Description");
	            headingRow.createCell((short)7).setCellValue("Training Center");
	            headingRow.createCell((short)8).setCellValue("Status");
	            headingRow.createCell((short)9).setCellValue("Remark");

	            short rowNo = 1;
	            for (Training obj : dataList) {
	                XSSFRow row = trainingSheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getTraining_id());
	                row.createCell((short)1).setCellValue(obj.getTraining_type_fk());
	                row.createCell((short)2).setCellValue(obj.getTraining_category_fk());
	                row.createCell((short)3).setCellValue(obj.getFaculty_name());
	                row.createCell((short)4).setCellValue(obj.getDesignation());
	                row.createCell((short)5).setCellValue(obj.getTitle());
	                row.createCell((short)6).setCellValue(obj.getDescription());
	                row.createCell((short)7).setCellValue(obj.getTraining_center());
	                row.createCell((short)8).setCellValue(obj.getStatus_fk());
	                row.createCell((short)9).setCellValue(obj.getRemarks());
	          
	                rowNo++;
	            }
		        XSSFSheet sessionsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Sessions"));
		        workBook.setSheetOrder(sessionsSheet.getSheetName(), 1);
		        XSSFRow headingRow1 = sessionsSheet.createRow(0);
		        headingRow1.createCell((short)0).setCellValue("Training Session ID");
		        headingRow1.createCell((short)1).setCellValue("Training ID");
	            headingRow1.createCell((short)2).setCellValue("Session No");
	         	headingRow1.createCell((short)3).setCellValue("Date");
	            headingRow1.createCell((short)4).setCellValue("Start Time");
	            headingRow1.createCell((short)5).setCellValue("End Time");
	            headingRow1.createCell((short)6).setCellValue("Remark");
	            
	            XSSFSheet attendeesSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Attendees"));
	            workBook.setSheetOrder(attendeesSheet.getSheetName(), 2);
		        XSSFRow headingRow2 = attendeesSheet.createRow(0);
		        headingRow2.createCell((short)0).setCellValue("Training Attendees ID");
		        headingRow2.createCell((short)1).setCellValue("Training Session ID");
		        headingRow2.createCell((short)2).setCellValue("Training ID");
		        headingRow2.createCell((short)3).setCellValue("Session No");
		        headingRow2.createCell((short)4).setCellValue("Department");
		        headingRow2.createCell((short)5).setCellValue("Name of attendee in the meenting");
		        headingRow2.createCell((short)6).setCellValue("HOD");
		        headingRow2.createCell((short)7).setCellValue("Mobile No");
		        headingRow2.createCell((short)8).setCellValue("Required (Yes / No)");
		        headingRow2.createCell((short)9).setCellValue("Participated (Yes/No)");
	            
	            short rowNo2 = 1;
	        	for (Training tariningSessions : dataList) { 
	        		String id = tariningSessions.getTraining_id();
	        		sessionsList = trainingService.getTrainingSessionsList(id);
		           
		            for (Training sObj : sessionsList) {
		                XSSFRow row2 = sessionsSheet.createRow(rowNo2);
		                row2.createCell((short)0).setCellValue(sObj.getTraining_session_id());
		                row2.createCell((short)1).setCellValue(sObj.getTraining_id());
		                row2.createCell((short)2).setCellValue(sObj.getSession_no());
		                row2.createCell((short)3).setCellValue(sObj.getDate());
		                row2.createCell((short)4).setCellValue(sObj.getStart_time());
		                row2.createCell((short)5).setCellValue(sObj.getEnd_time());
		                row2.createCell((short)6).setCellValue(sObj.getSession_remarks());
		          
		                rowNo2++;
		            }
	        	}
	        	short rowNo3 = 1;
		        	for (Training tariningAttendees : dataList) { 
		        		String trainingId = tariningAttendees.getTraining_id();
		        		attendeesList = trainingService.getTrainingAttendeesList(trainingId);
		        	
			            for (Training aObj : attendeesList) {
			                XSSFRow row3 = attendeesSheet.createRow(rowNo3);
			                row3.createCell((short)0).setCellValue(aObj.getTraining_attendees_id());
			                row3.createCell((short)1).setCellValue(aObj.getTraining_session_id());
			                row3.createCell((short)2).setCellValue(aObj.getTraining_id());
			                row3.createCell((short)3).setCellValue(aObj.getSession_no());
			                row3.createCell((short)4).setCellValue(aObj.getDepartment_fk());
			                row3.createCell((short)5).setCellValue(aObj.getAttendee());
			                row3.createCell((short)6).setCellValue(aObj.getHod_user_id_fk());
			                row3.createCell((short)7).setCellValue(aObj.getMobile_no());
			                row3.createCell((short)8).setCellValue(aObj.getRequired_fk());
			                row3.createCell((short)9).setCellValue(aObj.getParticipated_fk());
			          
			                rowNo3++;
			            }
		        	}
	        	for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
				     trainingSheet.autoSizeColumn(columnIndex);
	        		//trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < sessionsList.size(); columnIndex++) {
	        		sessionsSheet.autoSizeColumn(columnIndex);
	        		//trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < attendeesList.size(); columnIndex++) {
	        		attendeesSheet.autoSizeColumn(columnIndex);
	        		//trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Training_"+dateFormat.format(date);
                
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

	@RequestMapping(value = "/export-training-details", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportTrainingDetails(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Training dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.trainingGrid);
		List<Training> sessionsList = new ArrayList<Training>();
		List<Training> attendeesList = new ArrayList<Training>();
		try {
			view.setViewName("redirect:/training");
			String id = dObj.getTraining_id();
    		sessionsList = trainingService.getTrainingSessionsList(id);
			XSSFWorkbook  workBook = new XSSFWorkbook();
		   
			if(sessionsList != null && sessionsList.size() > 0){
				//XSSFWorkbook  workBook = new XSSFWorkbook ();
				 XSSFSheet sessionsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Sessions"));
			        workBook.setSheetOrder(sessionsSheet.getSheetName(), 0);
			        XSSFRow headingRow1 = sessionsSheet.createRow(0);
			        headingRow1.createCell((short)0).setCellValue("Training Session ID");
			        headingRow1.createCell((short)1).setCellValue("Training ID");
		            headingRow1.createCell((short)2).setCellValue("Session No");
		         	headingRow1.createCell((short)3).setCellValue("Date");
		            headingRow1.createCell((short)4).setCellValue("Start Time");
		            headingRow1.createCell((short)5).setCellValue("End Time");
		            headingRow1.createCell((short)6).setCellValue("Remark");

	            short rowNo = 1;
	            for (Training sObj : sessionsList) {
	                XSSFRow row = sessionsSheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(sObj.getTraining_session_id());
	                row.createCell((short)1).setCellValue(sObj.getTraining_id());
	                row.createCell((short)2).setCellValue(sObj.getSession_no());
	                row.createCell((short)3).setCellValue(sObj.getDate());
	                row.createCell((short)4).setCellValue(sObj.getStart_time());
	                row.createCell((short)5).setCellValue(sObj.getEnd_time());
	                row.createCell((short)6).setCellValue(sObj.getSession_remarks());
	          
	                rowNo++;
	            }
		       
	            XSSFSheet attendeesSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Attendees"));
	            workBook.setSheetOrder(attendeesSheet.getSheetName(), 1);
		        XSSFRow headingRow2 = attendeesSheet.createRow(0);
		        headingRow2.createCell((short)0).setCellValue("Training Attendees ID");
		        headingRow2.createCell((short)1).setCellValue("Training Session ID");
		        headingRow2.createCell((short)2).setCellValue("Training ID");
		        headingRow2.createCell((short)3).setCellValue("Session No");
		        headingRow2.createCell((short)4).setCellValue("Department");
		        headingRow2.createCell((short)5).setCellValue("Name of attendee in the meenting");
		        headingRow2.createCell((short)6).setCellValue("HOD");
		        headingRow2.createCell((short)7).setCellValue("Mobile No");
		        headingRow2.createCell((short)8).setCellValue("Required (Yes / No)");
		        headingRow2.createCell((short)9).setCellValue("Participated (Yes / No)");
	            
	        	short rowNo1 = 1;
		        	for (Training tariningAttendees : sessionsList) { 
		        		String trainingId = tariningAttendees.getTraining_id();
		        		attendeesList = trainingService.getTrainingAttendeesList(trainingId);
		        	
			            for (Training aObj : attendeesList) {
			                XSSFRow row3 = attendeesSheet.createRow(rowNo1);
			                row3.createCell((short)0).setCellValue(aObj.getTraining_attendees_id());
			                row3.createCell((short)1).setCellValue(aObj.getTraining_session_id());
			                row3.createCell((short)2).setCellValue(aObj.getTraining_id());
			                row3.createCell((short)3).setCellValue(aObj.getSession_no());
			                row3.createCell((short)4).setCellValue(aObj.getDepartment_fk());
			                row3.createCell((short)5).setCellValue(aObj.getAttendee());
			                row3.createCell((short)6).setCellValue(aObj.getHod_user_id_fk());
			                row3.createCell((short)7).setCellValue(aObj.getMobile_no());
			                row3.createCell((short)8).setCellValue(aObj.getRequired_fk());
			                row3.createCell((short)9).setCellValue(aObj.getParticipated_fk());
			          
			                rowNo1++;
			            }
		        	}
	        	for(int columnIndex = 0; columnIndex < sessionsList.size(); columnIndex++) {
	        		sessionsSheet.autoSizeColumn(columnIndex);
	        		//trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < attendeesList.size(); columnIndex++) {
	        		attendeesSheet.autoSizeColumn(columnIndex);
	        		//trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Training_"+dateFormat.format(date);
                
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
	
	
	@RequestMapping(value = "/upload-training", method = {RequestMethod.POST})
	public ModelAndView uploadTraining(@ModelAttribute Training training,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/training");
			
			if(!StringUtils.isEmpty(training.getTrainingFile())){
				MultipartFile multipartFile = training.getTrainingFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet trainingsSheet = workbook.getSheetAt(2);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = trainingsSheet.getRow(1);
						int list = trainingsSheet.getLastRowNum();
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getTrainingFileFormat();	
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
						
						int count = uploadTrainings(training,userId,userName);
					
							attributes.addFlashAttribute("success", count + " Trainings added successfully.");
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

	private int uploadTrainings(Training obj, String userId, String userName) throws Exception {
		Training training = null;
		List<Training> trainingsList = new ArrayList<Training>();
		Writer w = null;
		 int j = 0;
		 int k = 0;
		 int i = 0;
		int count = 0 ;
		try {	
			MultipartFile excelfile = obj.getTrainingFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet trainingsSheet = workbook.getSheetAt(2);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					int list = trainingsSheet.getLastRowNum();
					for( i = 2; i <= trainingsSheet.getLastRowNum();i++){
						XSSFRow row = trainingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						training = new Training();
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { training.setTraining_id(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { training.setTraining_type_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { training.setTraining_category_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { training.setFaculty_name(val);}	
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { training.setDesignation(val);}					
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { training.setTitle(val);}								
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { training.setDescription(val);}										
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { training.setTraining_center(val);}
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { training.setStatus_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { training.setRemarks(val);}
						
						}
						List<Training> sObjList = new ArrayList<Training>();
					
						XSSFSheet trainingSessionsSheet = workbook.getSheetAt(3);
						int list1 = trainingSessionsSheet.getLastRowNum();
						for( j = 2; j <= trainingSessionsSheet.getLastRowNum();j++){
							XSSFRow row2 = trainingSessionsSheet.getRow(j);
							// Sets the Read data to the model class
							Training sObj = new Training();
							if(!StringUtils.isEmpty(row2)) {
								val = formatter.formatCellValue(row2.getCell(0)).trim();
								if(!StringUtils.isEmpty(val)) { sObj.setTraining_id(val);}
								
								val = formatter.formatCellValue(row2.getCell(1)).trim();
								if(!StringUtils.isEmpty(val)) { sObj.setSession_no(val);}
								
								val = formatter.formatCellValue(row2.getCell(2)).trim();
								if(!StringUtils.isEmpty(val)) { sObj.setDate(val);}
								
								val = formatter.formatCellValue(row2.getCell(3)).trim();
								if(!StringUtils.isEmpty(val)) { sObj.setStart_time(val);}
								
								val = formatter.formatCellValue(row2.getCell(4)).trim();
								if(!StringUtils.isEmpty(val)) { sObj.setEnd_time(val);}
								
								val = formatter.formatCellValue(row2.getCell(5)).trim();
								if(!StringUtils.isEmpty(val)) { sObj.setRemarks(val);}
									
								if(!StringUtils.isEmpty(sObj.getDate())) {
									String date= (sObj.getDate() +" "+ sObj.getStart_time());
									String date2 = (sObj.getDate() +" "+ sObj.getEnd_time());
									
									DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
									DateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									Date startDate =null;
									startDate = df.parse(date);
									Date EndDate =null;
									EndDate = df.parse(date2);
									sObj.setStart_time((outputformat.format(startDate)));
									sObj.setEnd_time((outputformat.format(EndDate)));
								}
							  }
							  if(!StringUtils.isEmpty(sObj)&& !StringUtils.isEmpty(sObj.getTraining_id())
									&& sObj.getTraining_id().equals(training.getTraining_id())) 
								sObjList.add(sObj);
							    training.setTrainingSessions(sObjList);
		
								List<Training> aObjList = new ArrayList<Training>();
								XSSFSheet trainingAttendeesSheet = workbook.getSheetAt(4);
								int list2 = trainingAttendeesSheet.getLastRowNum();
								for( k = 1; k <= trainingAttendeesSheet.getLastRowNum();k++){
									XSSFRow row3 = trainingAttendeesSheet.getRow(k);
									Training aObj = new Training();
									if(!StringUtils.isEmpty(row3)) {
												
										val = formatter.formatCellValue(row3.getCell(0)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setTraining_id(val);}
										
										val = formatter.formatCellValue(row3.getCell(1)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setSession_no(val);}
										
										val = formatter.formatCellValue(row3.getCell(2)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setDepartment_fk(val);}
										
										val = formatter.formatCellValue(row3.getCell(3)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setAttendee(val);}
										
										val = formatter.formatCellValue(row3.getCell(4)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setHod_user_id_fk(val);}
										
										val = formatter.formatCellValue(row3.getCell(5)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setMobile_no(val);}
										
										val = formatter.formatCellValue(row3.getCell(6)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setRequired_fk(val);}
										
										val = formatter.formatCellValue(row3.getCell(7)).trim();
										if(!StringUtils.isEmpty(val)) { aObj.setParticipated_fk(val);}
								   }
								  if(!StringUtils.isEmpty(aObj) && !StringUtils.isEmpty(aObj.getTraining_id())
										&& aObj.getTraining_id().equals(training.getTraining_id()))
									aObjList.add(aObj);
							      }
							      training.setTrainingAttendees(aObjList);
							}
							boolean flag = training.checkNullOrEmpty();
						
							if(!flag) {
								trainingsList.add(training);
							}
						}
					
						if(!trainingsList.isEmpty() && trainingsList != null){
							count  = trainingService.uploadTraining(trainingsList);
						}
				  }
			   	  workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadTrainings() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadTrainings() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
}
