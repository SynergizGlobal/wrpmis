
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.ProjectPaginationObject;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.WorkPaginationObject;
import com.synergizglobal.pmis.model.Year;

@Controller
public class WorkController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkController.class);
	
	@Autowired
	WorkService workService;
	
	@Autowired
	HomeService homeService;
	
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
	
	
	@RequestMapping(value="/work",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Work(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.work);
		try {
			List<Work> workList = workService.getWorkList(null);
			model.addObject("workList", workList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/get-WorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksList(@ModelAttribute Work obj) {
		List<Work> worksList = null;
		try {
			worksList = workService.getWorksList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInWork", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getProjectsList(@ModelAttribute Work obj) {
		List<Work> projectsList = null;
		try {
			projectsList = workService.getWorktProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	/**
	@RequestMapping(value = "/ajax/get-works", method = { RequestMethod.POST, RequestMethod.GET }) 
	public void getWorksList(@ModelAttribute Work obj, HttpServletRequest request,
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

			List<Work> workList = new ArrayList<Work>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				workList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				workList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//workList = getListBasedOnSearchParameter(searchParameter,workList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			WorkPaginationObject personJsonObject = new WorkPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(workList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}
*/
	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 *//**
	public int getTotalRecords(Work obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = workService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}*/

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 *//**
	public List<Work> createPaginationData(int startIndex, int offset,Work obj, String searchParameter) {
		List<Work> objList = null;
		try {
			objList = workService.getWorksList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	*/
	@RequestMapping(value = "/get-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkDetails(@ModelAttribute Work work,Railway rail){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "edit");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);
			
			workId= work.getWork_id();
			Work workDetails = workService.getWork(workId, work);
			model.addObject("workDetails", workDetails);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWorkForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "add");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);

		}catch (Exception e) {
				logger.error("Work : " + e.getMessage());
			}
			return model;
	 }
	
	
	@RequestMapping(value = "/update-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			/*
			 * MultipartFile file = work.getWorkFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.WORK_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); work.setAttachment(fileName); }
			 */			
			boolean flag =  workService.updateWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWork(@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			/*MultipartFile file = work.getWorkFile();
			
			 * if (null != file && !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.WORK_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); work.setAttachment(fileName); }
			 */
			boolean flag =  workService.addWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			logger.error("addWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteRow", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteRow(@ModelAttribute Work work){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName("redirect:/work");
			workId = work.getWork_id();
			boolean flag =  workService.deleteRow(workId,work);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	
	}
	
	/*
	@RequestMapping(value = "/export-work", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportWork(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.work);
		List<Work> dataList = new ArrayList<Work>();
		try {
			view.setViewName("redirect:/work");
			dataList = workService.getWorkList(work); 
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Work"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Project ID");
	            headingRow.createCell((short)1).setCellValue("Project Name");
	         	headingRow.createCell((short)2).setCellValue("Work ID");
	            headingRow.createCell((short)3).setCellValue("Work Name");
	            headingRow.createCell((short)4).setCellValue("Work Short Name");
	            headingRow.createCell((short)5).setCellValue("Sanctioned Year");
	            headingRow.createCell((short)6).setCellValue("Railway Agency");
	            headingRow.createCell((short)7).setCellValue("Executed By");
	            headingRow.createCell((short)8).setCellValue("Sanctioned Estimated Cost");
	            headingRow.createCell((short)9).setCellValue("Sanctioned Completion Cost");
	            headingRow.createCell((short)10).setCellValue("Completeion Period Months");
	            headingRow.createCell((short)11).setCellValue("Anticipated Cost");
	            headingRow.createCell((short)12).setCellValue("Year of Completion");
	            headingRow.createCell((short)13).setCellValue("Projected Completion");
	            headingRow.createCell((short)14).setCellValue("Completion Cost");
	            headingRow.createCell((short)15).setCellValue("Remarks");
	            short rowNo = 1;
	            for (Work obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getProject_id_fk());
	                row.createCell((short)1).setCellValue(obj.getProject_name());
	                row.createCell((short)2).setCellValue(obj.getWork_id());
	                row.createCell((short)3).setCellValue(obj.getWork_name());
	                row.createCell((short)4).setCellValue(obj.getWork_short_name());
	                row.createCell((short)5).setCellValue(obj.getSanctioned_year_fk());
	                row.createCell((short)6).setCellValue(obj.getRailway());
	                row.createCell((short)7).setCellValue(obj.getExecuted_by());
	                row.createCell((short)8).setCellValue(obj.getSanctioned_estimated_cost());
	                row.createCell((short)9).setCellValue(obj.getSanctioned_completion_cost());
	                row.createCell((short)10).setCellValue(obj.getCompleteion_period_months());
	                row.createCell((short)11).setCellValue(obj.getAnticipated_cost());
	                row.createCell((short)12).setCellValue(obj.getYear_of_completion());
	                row.createCell((short)13).setCellValue(obj.getProjected_completion());
	                row.createCell((short)14).setCellValue(obj.getCompletion_cost());
	                row.createCell((short)15).setCellValue(obj.getRemarks());
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//Budgetsheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Work_"+dateFormat.format(date);
                
	            try{
	                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
	                workBook.write(fos);
	                fos.flush();*/
	           /* 	
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
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);			
		}
		
	}
*/
	@RequestMapping(value = "/export-work", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportWork(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.work);
		List<Work> dataList = new ArrayList<Work>();
		List<Work> revisionList = new ArrayList<Work>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/work");
			dataList = workService.getWorksList(work); 
			revisionList = workService.getWorkRevisionsList();
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Work"));
			            XSSFSheet revisonSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Revision Details"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
				        workBook.setSheetOrder(revisonSheet.getSheetName(), 1);
				        
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
			            String headerString = "Project ID^Project Name^Work ID^Work Short Name^Sanctioned Year^Railway Agency^Executed By^Sanctioned Estimated Cost^Sanctioned Completion Cost^Completeion Period Months^"
			            		+ "Anticipated Cost^Projected Completion Date^Completion Cost^Year of Completion^Remarks";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            XSSFRow headingRow1 = revisonSheet.createRow(0);
			            String headerString1 = "Work ID^Financial Year^PB Item No^Latest Revised Cost^Year Of Revision^Revision Number";
			            
			            String[] secondHeaderStringArr = headerString1.split("\\^");
			            
			            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow1.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(secondHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (Work obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_id_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_name());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSanctioned_year_fk());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRailway());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getExecuted_by());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSanctioned_estimated_cost());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSanctioned_completion_cost());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCompleteion_period_months());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getAnticipated_cost());
						
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProjected_completion());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCompletion_cost());

							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getYear_of_completion());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
						
			                rowNo++;
			            }
			            short rowNo2 = 1;
						
							 for (Work obj : revisionList) {
					                XSSFRow row = revisonSheet.createRow(rowNo2);
					                int b = 0;
					                
					                Cell cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getWork_id_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getFinancial_year());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getPink_book_item_number());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getLatest_revised_cost());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getYear_of_revision());
									
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getRevision_number());
									
									rowNo2++;
							    }
					       
						 
			            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
			            for(int columnIndex = 0; columnIndex < secondHeaderStringArr.length; columnIndex++) {
			            	revisonSheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Work_"+dateFormat.format(date);
		                
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
			logger.error("exportWork : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
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
	
	
	
	
	
	
	
	
	