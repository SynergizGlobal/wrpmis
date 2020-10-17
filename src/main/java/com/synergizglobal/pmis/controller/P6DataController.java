package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
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

import com.synergizglobal.pmis.Iservice.P6DataService;
import com.synergizglobal.pmis.Iservice.WorkContractModuleStatusService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.P6Data;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

@Controller
public class P6DataController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(P6DataController.class);

	@Autowired
	WorkContractModuleStatusService service;
	
	@Autowired
	P6DataService p6dataService;
	

	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/p6-data",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView P6Data(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.P6Data);
		try {
			List<WorkContractModuleStatus> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			List<P6Data> activityDataList = p6dataService.getActivityDataList();
			model.addObject("activityDataList", activityDataList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("P6Data : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-fob-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getFobList(@ModelAttribute P6Data obj) {
		List<P6Data> safetyEquipment = null;
		try {
			safetyEquipment = p6dataService.getFobList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("SafetyEquipment : " + e.getMessage());
		}
		return safetyEquipment;
	}
	
	@RequestMapping(value = "/upload-p6-baseline", method = {RequestMethod.POST})
	public ModelAndView uploadP6Baseline(@ModelAttribute P6Data p6data,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String fileName = null;
		String userId = null;String userName = null;
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		XSSFSheet uploadFilesSheet1 = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/p6-data");
			
			if(!StringUtils.isEmpty(p6data.getP6dataFile())){
				MultipartFile multipartFile = p6data.getP6dataFile();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0){					
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(2);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet.getRow(1);
							//checking given file format
							if(headerRow != null){
								List<String> fileFormat = FileFormatModel.getP6WbsFileFormat();;
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
							}
							uploadFilesSheet1 = workbook.getSheetAt(3);
							XSSFRow headerRow1 = uploadFilesSheet1.getRow(1);
							if(headerRow1 != null){
								List<String> fileFormat1 = FileFormatModel.getP6ActivitiesFileFormat();;	
								int noOfColumns1 = headerRow1.getLastCellNum();
								if(noOfColumns1 == fileFormat1.size()){
									for (int i = 0; i < fileFormat1.size();i++) {
										String columnName1 = headerRow1.getCell(i).getStringCellValue().trim();
										if(!columnName1.equals(fileFormat1.get(i).trim()) && !columnName1.contains(fileFormat1.get(i).trim())){
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
							
							int count = p6WbsBaselineUpload(p6data,userId,userName,workbook);
							count = p6ActivitiesUpload(p6data,userId,userName,workbook);
							attributes.addFlashAttribute("success", count + " P6DataFile added successfully.");	
						}
					}
					
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

	
	private int p6WbsBaselineUpload(P6Data obj, String userId, String userName,XSSFWorkbook workbook) throws Exception {
		P6Data p6data = null;
		List<P6Data> p6dataList = new ArrayList<P6Data>();
		
		//XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {	
			/*List<String> fileFormat = null;				
			fileFormat = FileFormatModel.getActivityFileFormat();*/
			
			MultipartFile excelfile = obj.getP6dataFile();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile){
				 String fileName = excelfile.getOriginalFilename();
				 String fileType = FilenameUtils.getExtension(fileName);
				 
				 if(excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					
				 
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						 List<P6Data> pObjList = new ArrayList<P6Data>();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(2);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
							
							for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								p6data = new P6Data();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									p6data.setContract_id_fk(formatter.formatCellValue(row.getCell(0)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									p6data.setFob_id_fk(formatter.formatCellValue(row.getCell(1)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									p6data.setP6_wbs_code(formatter.formatCellValue(row.getCell(2)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									p6data.setP6_wbs_name(formatter.formatCellValue(row.getCell(3)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									p6data.setP6_wbs_parent_code(formatter.formatCellValue(row.getCell(4)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									p6data.setP6_wbs_category_fk(formatter.formatCellValue(row.getCell(5)).trim());
								
								p6data.setData_date(DateParser.parse(obj.getData_date()));
								
								p6data.setP6_file_path(obj.getP6_file_path());
								
								pObjList.add(p6data);
								
								p6data.setP6_data(pObjList);
								
								if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(p6data.getContract_id_fk()) && !StringUtils.isEmpty(p6data.getFob_id_fk()) && !StringUtils.isEmpty(p6data.getP6_wbs_code())) {
									p6dataList.add(p6data);
								}
							}
							
							if(!p6dataList.isEmpty() && p6dataList != null){
								count  = p6dataService.p6WbsUpload(p6dataList,userName);
							}
						}
						workbook.close();
					}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("p6WbsUpload() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("p6WbsUpload() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
	private int p6ActivitiesUpload(P6Data pObj, String userId, String userName,XSSFWorkbook workbook) throws Exception {
		P6Data p6data = null;
		List<P6Data> p6dataList1 = new ArrayList<P6Data>();
		
		//XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {	
			/*List<String> fileFormat = null;				
			fileFormat = FileFormatModel.getActivityFileFormat();*/
			
			MultipartFile excelfile = pObj.getP6dataFile();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile){
				 String fileName = excelfile.getOriginalFilename();
				 String fileType = FilenameUtils.getExtension(fileName);
				 
				 if(excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					
				 
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						 List<P6Data> pObjList = new ArrayList<P6Data>();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(3);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
							
							for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								p6data = new P6Data();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									p6data.setP6_wbs_code_fk(formatter.formatCellValue(row.getCell(0)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									p6data.setP6_task_code(formatter.formatCellValue(row.getCell(1)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									p6data.setP6_activity_name(formatter.formatCellValue(row.getCell(2)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									p6data.setStatus_fk(formatter.formatCellValue(row.getCell(3)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									p6data.setBaseline_start(formatter.formatCellValue(row.getCell(4)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									p6data.setBaseline_finish(formatter.formatCellValue(row.getCell(5)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
									p6data.setStart(formatter.formatCellValue(row.getCell(6)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(7)).trim()))
									p6data.setFinish(formatter.formatCellValue(row.getCell(7)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(8)).trim()))
									p6data.setP6_float(formatter.formatCellValue(row.getCell(8)).trim());
								
								p6data.setBaseline_start(DateParser.parse(pObj.getBaseline_start()));
								p6data.setBaseline_finish(DateParser.parse(p6data.getBaseline_finish()));
								p6data.setStart(DateParser.parse(p6data.getStart()));
								p6data.setFinish(DateParser.parse(p6data.getFinish()));
								p6data.setData_date(DateParser.parse(pObj.getData_date()));
								p6data.setContract_id_fk(pObj.getContract_id_fk());
								p6data.setFob_id_fk(pObj.getFob_id_fk());
								
								pObjList.add(p6data);
								
								p6data.setP6_data(pObjList);
								
								if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(p6data.getP6_wbs_code_fk()) && !StringUtils.isEmpty(p6data.getP6_task_code())) {
									p6dataList1.add(p6data);
								}
							}
							
							if(!p6dataList1.isEmpty() && p6dataList1 != null){
								count  = p6dataService.p6ActivitiesUpload(p6dataList1,userName);
							}
						}
						workbook.close();
					}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("p6ActivitiesUpload() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("p6ActivitiesUpload() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}

	@RequestMapping(value = "/upload-p6-update", method = {RequestMethod.POST})
	public ModelAndView uploadP6Update(@ModelAttribute P6Data p6data,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String fileName = null;
		String userId = null;String userName = null;
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		XSSFSheet uploadFilesSheet1 = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/p6-data");
			
			if(!StringUtils.isEmpty(p6data.getP6dataFile())){
				MultipartFile multipartFile = p6data.getP6dataFile();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0){					
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(1);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet.getRow(1);
							//checking given file format
							if(headerRow != null){
								List<String> fileFormat = FileFormatModel.getP6UpdateFileFormat();;
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
							
							int count = p6WbsUpdateUpload(p6data,userId,userName,workbook);
							attributes.addFlashAttribute("success", count + " P6DataFile added successfully.");	
						}
					}
					
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

	private int p6WbsUpdateUpload(P6Data uObj, String userId, String userName,XSSFWorkbook workbook) throws Exception {
		P6Data p6data = null;
		List<P6Data> p6dataList1 = new ArrayList<P6Data>();
		
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {	
			/*List<String> fileFormat = null;				
			fileFormat = FileFormatModel.getActivityFileFormat();*/
			
			MultipartFile excelfile = uObj.getP6dataFile();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile){
				 String fileName = excelfile.getOriginalFilename();
				 String fileType = FilenameUtils.getExtension(fileName);
				 
				 if(excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					
				 
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						List<P6Data> pObjList = new ArrayList<P6Data>();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(1);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
							
							for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								p6data = new P6Data();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									p6data.setP6_wbs_code_fk(formatter.formatCellValue(row.getCell(0)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									p6data.setP6_task_code(formatter.formatCellValue(row.getCell(1)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									p6data.setP6_activity_name(formatter.formatCellValue(row.getCell(2)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									p6data.setStatus_fk(formatter.formatCellValue(row.getCell(3)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									p6data.setStart(formatter.formatCellValue(row.getCell(4)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									p6data.setFinish(formatter.formatCellValue(row.getCell(5)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
									p6data.setP6_float(formatter.formatCellValue(row.getCell(6)).trim());
								
								/*
								 * p6data.setStart(DateParser.parse(p6data.getStart()));
								 * p6data.setFinish(DateParser.parse(p6data.getFinish()));
								 */
								p6data.setData_date(DateParser.parse(uObj.getData_date()));
								p6data.setContract_id_fk(uObj.getContract_id_fk());
								p6data.setFob_id_fk(uObj.getFob_id_fk());
								p6data.setP6_file_path(uObj.getP6_file_path());
								
								pObjList.add(p6data);
								
								p6data.setP6_data(pObjList);
								
								if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(p6data.getP6_wbs_code_fk()) && !StringUtils.isEmpty(p6data.getP6_task_code())) {
									p6dataList1.add(p6data);
								}
							}
							
							if(!p6dataList1.isEmpty() && p6dataList1 != null){
								count  = p6dataService.p6ActivitiesUpdate(p6dataList1,userName);
							}
						}
						workbook.close();
					}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("p6ActivitiesUpload() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("p6ActivitiesUpload() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
}
