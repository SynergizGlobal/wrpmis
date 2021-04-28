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
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.ProjectService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.ContractorPaginationObject;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.ProjectPaginationObject;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.Year;

@Controller
public class ProjectController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

	Logger logger = Logger.getLogger(ProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
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
	
	@RequestMapping(value="/project",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Project(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.project);
		try {
			List<Project> projectList = projectService.getProjectList();
			model.addObject("projectList", projectList);	
		}catch (Exception e) {
			e.printStackTrace();
			
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	/*@RequestMapping(value = "/ajax/get-projects", method = { RequestMethod.POST, RequestMethod.GET }) 
	public void getProjectsList(@ModelAttribute Project obj, HttpServletRequest request,
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

			List<Project> projectList = new ArrayList<Project>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				projectList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				projectList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//projectList = getListBasedOnSearchParameter(searchParameter,projectList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ProjectPaginationObject personJsonObject = new ProjectPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(projectList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getProjectsList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	/**
	public int getTotalRecords(Project obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = projectService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}/**

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	/**
	public List<Project> createPaginationData(int startIndex, int offset,Project obj, String searchParameter) {
		List<Project> objList = null;
		try {
			objList = projectService.getProjectsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}*/
	@RequestMapping(value = "/get-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getProjectDetails(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "edit");
			projectId= project.getProject_id();
			Project projectDetails = projectService.getProject(projectId, project);
			model.addObject("projectDetails", projectDetails);
			
			/*List <Project> fileNames = projectService.getFileNames(projectId);
			model.addObject("fileNames", fileNames);*/	
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
		}catch (Exception e) {
			logger.error("Project : " + e.getMessage());
		}
		return model;

	}
	
	@RequestMapping(value = "/update-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateProject(@ModelAttribute Project project,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			project.setCreated_by(userName);
			model.setViewName("redirect:/project");
			/*
			 * MultipartFile file = project.getProjectFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.PROJECT_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); project.setAttachment(fileName); }
			 */	
			boolean flag =  projectService.updateProject(project);
			if(flag == true) {
				attributes.addFlashAttribute("success", "Project Updated Succesfully.");

			}
			else {
				attributes.addFlashAttribute("error","Updating Project is failed. Try again.");

			}
		
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Project is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-project-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProjectForm(){
		ModelAndView model = new ModelAndView();
		
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "add");
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
		}catch (Exception e) {
				logger.error("Work : " + e.getMessage());
			}
			return model;
	 }
	
	@RequestMapping(value = "/add-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProject(@ModelAttribute  Project project,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try{
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			project.setCreated_by(userName);
			model.setViewName("redirect:/project");
			/*
			 * MultipartFile file = project.getProjectFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.PROJECT_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); project.setAttachment(fileName); }
			*/	
			
			boolean flag =  projectService.addProject(project);
			if(flag == true) {
				attributes.addFlashAttribute("success", "Project Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Project is failed. Try again.");

			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Project is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	
	}
	
	@RequestMapping(value = "/delete-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteProjectRow(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName("redirect:/project");
			projectId= project.getProject_id();
			boolean flag =  projectService.deleteProject(projectId,project);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	
	}
	
	
	@RequestMapping(value = "/export-project", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportProject(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Project project,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.project);
		List<Project> dataList = new ArrayList<Project>();
		List<Project> pinkBookList = new ArrayList<Project>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/project");
			dataList = projectService.getProjectList();
			pinkBookList = projectService.getProjectPinkBookList();
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Project"));
			            XSSFSheet pinkBookSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Project Pink Book"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
				        workBook.setSheetOrder(pinkBookSheet.getSheetName(), 1);
				        
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
			            String headerString = "Project ID^Project Name^Plan Head Number^Project Status^Benefits^Remarks";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            XSSFRow headingRow1 = pinkBookSheet.createRow(0);
			            String headerString1 = "Project ID^Financial Year^PB Item No";
			            
			            String[] secondHeaderStringArr = headerString1.split("\\^");
			            
			            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow1.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(secondHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (Project obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_name());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getPlan_head_number());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_status());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getBenefits());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
			                
			                rowNo++;
			            }
			            short rowNo2 = 1;
						
							 for (Project obj : pinkBookList) {
					                XSSFRow row = pinkBookSheet.createRow(rowNo2);
					                int b = 0;
					                
					                Cell cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getProject_id_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getFinancial_year_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getPb_item_no());
									
									rowNo2++;
							    }
					       
						 
			            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
			            for(int columnIndex = 0; columnIndex < secondHeaderStringArr.length; columnIndex++) {
			            	pinkBookSheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Project_"+dateFormat.format(date);
		                
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
			logger.error("exportProject : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
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
