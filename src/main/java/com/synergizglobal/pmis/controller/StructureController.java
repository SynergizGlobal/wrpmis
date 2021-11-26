package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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
import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.StructureService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.StructurePaginationObject;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.User;

@Controller
public class StructureController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(StructureController.class);

	@Autowired
	StructureService structureService;

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
	
	@RequestMapping(value="/structure",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView structure(@ModelAttribute Structure obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.structureGrid);		
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("structure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getProjectsListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getProjectsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getWorksListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getContractsListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructuresListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getDepartmentsListFilterInStructure(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInStructure : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getContractListForStructureFrom", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getContractListForStructureFrom(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getContractListForStructureFrom(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractListForStructureFrom : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForStructureForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getWorkListForStructureForm(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getWorkListForStructureForm(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForStructureForm : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStructureUploadsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getStructureUploadsList(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getStructureUploadsList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureUploadsList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStructureList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getStructureList(@ModelAttribute Structure obj, HttpServletRequest request,
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

			List<Structure> contractList = new ArrayList<Structure>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			}

			//Search functionality: Returns filtered list based on search parameter
			//contractList = getListBasedOnSearchParameter(searchParameter,contractList);

		

			StructurePaginationObject personJsonObject = new StructurePaginationObject();
			int totalRecords = getTotalRecords(obj, searchParameter);
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
					"getStructureList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Structure obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = structureService.getTotalRecords(obj, searchParameter);
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
	public List<Structure> createPaginationData(int startIndex, int offset,Structure obj, String searchParameter,HttpSession session) {
		List<Structure> contractsGridList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractsGridList = structureService.getStructuresList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return contractsGridList;
	}
	
	
	@RequestMapping(value = "/add-structure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addStructureForm(@ModelAttribute Structure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditStructure);	
			model.addObject("action", "add");
			List<Structure> projectsList = structureService.getProjectsListForStructureForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Structure> worksList = structureService.getWorkListForStructureForm(obj);
			model.addObject("worksList", worksList);
			
			List<Structure> contractsList = structureService.getContractListForStructureFrom(obj);
			model.addObject("contractsList", contractsList);
			
			List<Structure> structuresList = structureService.getStructuresListForStructureFrom(obj);
			model.addObject("structuresList", structuresList);
			
			List<Structure> departmentsList = structureService.getDepartmentsListForStructureFrom(obj);
			model.addObject("departmentsList", departmentsList);
			
			List<Structure> responsiblePeopleList = structureService.getResponsiblePeopleListForStructureForm(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<Structure> workStatusList = structureService.getWorkStatusListForStructureForm(obj);
			model.addObject("workStatusList", workStatusList);
			
			List<Structure> unitsList = structureService.getUnitsListForStructureForm(obj);
			model.addObject("unitsList", unitsList);
			
			List<Structure> fileType = structureService.getFileTypeForStructureForm(obj);
			model.addObject("fileType", fileType);
			
			
		}catch (Exception e) {
			logger.error("addStructureForm : " + e.getMessage());
		}
		return model;
	}	
	@RequestMapping(value = "/update-structure", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateStructureForm(@ModelAttribute Structure obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure");
			boolean flag =  structureService.updateStructure(obj);	
			if(flag) {
				attributes.addFlashAttribute("success", "Structures Updated Succesfully."); 
			} else {
				attributes.addFlashAttribute("error","Updating Structures is failed. Try again.");
			}		
			
			
		}catch (Exception e) {
			logger.error("updateStructureForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/add-structure", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addStructure(@ModelAttribute Structure obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure");
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));	
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));	
			obj.setRevised_completion(DateParser.parse(obj.getRevised_completion()));	
			boolean flag =  structureService.addStructure(obj);	
			if(flag) {
				attributes.addFlashAttribute("success", "Structures Added Succesfully."); 
			} else {
				attributes.addFlashAttribute("error","Adding Structures is failed. Try again.");
			}
			
		}catch (Exception e) {
			logger.error("addStructure : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/get-structure", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getStructureForm(@ModelAttribute Structure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditStructure);	
			model.addObject("action", "edit");	
			List<Structure> projectsList = structureService.getProjectsListForStructureForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Structure> worksList = structureService.getWorkListForStructureForm(obj);
			model.addObject("worksList", worksList);
			
			List<Structure> contractsList = structureService.getContractListForStructureFrom(obj);
			model.addObject("contractsList", contractsList);
			
			List<Structure> structuresList = structureService.getStructuresListForStructureFrom(obj);
			model.addObject("structuresList", structuresList);
			
			List<Structure> departmentsList = structureService.getDepartmentsListForStructureFrom(obj);
			model.addObject("departmentsList", departmentsList);
		
			List<Structure> responsiblePeopleList = structureService.getResponsiblePeopleListForStructureForm(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<Structure> workStatusList = structureService.getWorkStatusListForStructureForm(obj);
			model.addObject("workStatusList", workStatusList);
			
			List<Structure> unitsList = structureService.getUnitsListForStructureForm(obj);
			model.addObject("unitsList", unitsList);
			
			List<Structure> fileType = structureService.getFileTypeForStructureForm(obj);
			model.addObject("fileType", fileType);
			
			Structure structuresListDetails = structureService.getStructuresListDetails(obj);
			model.addObject("structuresListDetails", structuresListDetails);
			
		}catch (Exception e) {
			logger.error("getStructuresForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/upload-structures", method = {RequestMethod.POST})
	public ModelAndView uploadStructures(@ModelAttribute Structure structure,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		String msg = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/structure");
			
			if(!StringUtils.isEmpty(structure.getStructureFile())){
				MultipartFile multipartFile = structure.getStructureFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet structuresDrawingsSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = structuresDrawingsSheet.getRow(0);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getStructureFileFormat();;	
							int noOfColumns = headerRow.getLastCellNum();
							if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		msg = uploadformatError;
				                		structure.setUploaded_by_user_id_fk(userId);
				                		structure.setStatus("Fail");
				                		structure.setRemarks(msg);
										boolean flag = structureService.saveStructureDataUploadFile(structure);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
								msg = uploadformatError;
		                		structure.setUploaded_by_user_id_fk(userId);
		                		structure.setStatus("Fail");
		                		structure.setRemarks(msg);
								boolean flag = structureService.saveStructureDataUploadFile(structure);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						
						int count = uploadStructures(structure,userId,userName);
						if(count != 0) {
							attributes.addFlashAttribute("success", count + " Structures added successfully.");	
							msg = count + " Structures added successfully.";
							structure.setUploaded_by_user_id_fk(userId);
							structure.setStatus("Success");
							structure.setRemarks(msg);
							boolean flag = structureService.saveStructureDataUploadFile(structure);
						}else {
							attributes.addFlashAttribute("success"," Empty Sheet, No reords found.");	
						}
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");

				msg = "No file exists";
				structure.setUploaded_by_user_id_fk(userId);
				structure.setStatus("Fail");
				structure.setRemarks(msg);
				boolean flag = structureService.saveStructureDataUploadFile(structure);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
			
			msg = "Something went wrong. Please try after some time";
			structure.setUploaded_by_user_id_fk(userId);
			structure.setStatus("Fail");
			structure.setRemarks(msg);
			try {
				boolean flag = structureService.saveStructureDataUploadFile(structure);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveStructureDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	
	/**
	 * This method uploadStructures() is called when user upload the file
	 * 
	 * @param obj is object for the model class User.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @return type of this method is count.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	public int uploadStructures(Structure obj, String userId, String userName) throws Exception {
		Structure structure = null;
		List<Structure> structuresList = new ArrayList<Structure>();
		
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getStructureFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet structuresDrawingsSheet = workbook.getSheetAt(0);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 1; i <= structuresDrawingsSheet.getLastRowNum();i++){
						XSSFRow row = structuresDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						structure = new Structure();
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { structure.setWork_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { structure.setContract_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { structure.setDepartment_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { structure.setStructure_type_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { structure.setStructure(val);}					
							
						}
						List<Structure> pObjList = new ArrayList<Structure>();
			
						boolean flag = structure.checkNullOrEmpty();
						
						if(!flag) {
							structuresList.add(structure);
						}
					}
					
					if(!structuresList.isEmpty() && structuresList != null){
						count  = structureService.uploadStructures(structuresList);
					}
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadStructures() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadStructures() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
	
	@RequestMapping(value = "/export-structure", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportStructure(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Structure structure,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.structureGrid);
		List<Structure> dataList = new ArrayList<Structure>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/structure");
			dataList = structureService.getStructureExportList(structure);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Structure"));
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
	            String headerString = "Work^Contract^Department^Structure Type^Structure";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
	            short rowNo = 1;
	            for (Structure obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                int c = 0;
	              
					Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_short_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_id_fk()+" - "+ obj.getContract_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDepartment_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStructure_type_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStructure());
					
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Structure_"+dateFormat.format(date);
                
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
			logger.error("exportStructure : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
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