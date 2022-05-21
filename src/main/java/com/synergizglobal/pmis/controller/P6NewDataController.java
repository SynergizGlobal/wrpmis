package com.synergizglobal.pmis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.synergizglobal.pmis.Iservice.P6NewDataService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.P6Data;

@Controller
public class P6NewDataController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(P6NewDataController.class);

	
	@Autowired
	P6NewDataService p6newdataService;
	

	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/p6-new-data",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView P6Data(@ModelAttribute P6Data obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.P6NewData);
		try {
			List<P6Data> worksList = p6newdataService.getWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<P6Data> contractsList = p6newdataService.getContractsList(obj);
			model.addObject("contractsList", contractsList);
			
			List<P6Data> fobList = p6newdataService.getFobList(obj);
			model.addObject("fobList", fobList);
			
			/*List<P6Data> contractsListFilter = p6newdataService.getContractsListFilter(obj);
			model.addObject("contractsListFilter", contractsListFilter);
			
			List<P6Data> fobListFilter = p6newdataService.getFobListFilter(obj);
			model.addObject("fobListFilter", fobListFilter);
			
			List<P6Data> uploadTypes = p6newdataService.getUploadTypesFilter(obj);
			model.addObject("uploadTypes", uploadTypes);
			
			List<P6Data> statusList = p6newdataService.getStatusListFilter(obj);
			model.addObject("statusList", statusList);*/
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("P6Data : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getContractListInP6New", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getContractListInP6New(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getContractsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractListInP6New : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getContractsListFilterInP6New", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getContractsListFilterInP6(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getContractsListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInP6 : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getFobListFilterInP6New", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getFobListFilterInP6(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getFobListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFobListFilterInP6 : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getUploadTypesFilterInP6New", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getUploadTypesFilterInP6(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getUploadTypesFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getUploadTypesFilterInP6 : " + e.getMessage());
		}
		return objList;
	}
	

	
	@RequestMapping(value = "/ajax/getStatusListFilterInP6New", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getStatusListFilterInP6(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getStatusListFilter(obj);
		}catch (Exception e) { 
			e.printStackTrace();
			logger.error("getStatusListFilterInP6 : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getP6NewActivityData", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getP6NewActivityData(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getActivityDataList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getP6NewActivityData : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getFobListInP6New", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<P6Data> getFobListInP6(@ModelAttribute P6Data obj) {
		List<P6Data> objList = null;
		try {
			objList = p6newdataService.getFobList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFobListInP6 : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/upload-p6-new-data", method = {RequestMethod.POST})
	public ModelAndView uploadP6Baseline(@ModelAttribute P6Data p6data,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		XSSFSheet uploadFilesSheet1 = null;
		String fob_mismatch = null;
		List<P6Data> wbsList = new ArrayList<P6Data>();
		List<P6Data> activitiesList = new ArrayList<P6Data>();
		try {
			model.setViewName("redirect:/p6-new-data");
			
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			p6data.setCreated_by_user_id_fk(userId);
			p6data.setUser_name(userName);
			p6data.setDesignation(userDesignation);
			
			MultipartFile multipartFile = p6data.getP6dataFile();
			// Creates a workbook object from the uploaded excelfile
			if (null != multipartFile && multipartFile.getSize() > 0){
				String fileName = multipartFile.getOriginalFilename();
				
				workbook = new XSSFWorkbook(multipartFile.getInputStream());
				// Creates a worksheet object representing the first sheet
				if(workbook != null) {
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						uploadFilesSheet = workbook.getSheetAt(0);
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
						uploadFilesSheet1 = workbook.getSheetAt(1);
						XSSFRow headerRow1 = uploadFilesSheet1.getRow(1);
						if(headerRow1 != null){
							List<String> fileFormat1 = FileFormatModel.getP6ActivitiesFileFormat();	
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
						
						p6data.setP6_file_path(fileName);
						int i= 2;
						wbsList = baselineWBSUpload(p6data,workbook,fob_mismatch);
						activitiesList = baselineActivitiesUpload(p6data,workbook);
						
						uploadFilesSheet = workbook.getSheetAt(0);
						XSSFRow row = uploadFilesSheet.getRow(i);
						String contarct = null;
						DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
						if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
							contarct = formatter.formatCellValue(row.getCell(0)).trim();
						if(!p6data.getContract_id_fk().equalsIgnoreCase(contarct)) {
							fob_mismatch = "selected Contract ID and WBS Code Mismatch at sheet (1) row [A3].";
						}
						if(wbsList.size() == 0 && StringUtils.isEmpty(fob_mismatch)){
							fob_mismatch = "Sheet is empty.";
						}
						if(!StringUtils.isEmpty(p6data.getFob_id_fk()))
						{
							for(P6Data list : wbsList) {
								
								if(!StringUtils.isEmpty(list.getFob_id_fk()) && !list.getFob_id_fk().equals(p6data.getFob_id_fk()) && !StringUtils.isEmpty(list.getP6_wbs_code())) {
									fob_mismatch = " FOB selected from the dropdown and on the P6 File do not match. at Row no(s) " + (i+1);
									break;
								}
								i++;
							}
						}
					}
					
					workbook.close();
					
					
					String saveDirectory = CommonConstants2.P6_FILE_SAVING_PATH ;
					FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
				}
				
			}
			
			p6data.setUploaded_by_user_id_fk(userId);
			p6data.setData_date(DateParser.parse(p6data.getData_date()));
			p6data.setUpload_type("Baseline");
			if(StringUtils.isEmpty(fob_mismatch)){
				String counts = p6newdataService.uploadP6WBSActivities(wbsList,activitiesList,p6data);
				if(counts.contains("Error")) {
					attributes.addFlashAttribute("error", "WBS Parent code doesnt exist in sheet (1) at row [A"+counts.replace("Error", "") +"].");	
				}else {
					attributes.addFlashAttribute("success", "Data date updated and "+ counts + " WBS , Activities records added successfully.");	
				}
			}else {
				attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("uploadP6Baseline() : "+e.getMessage());
		}
		return model;
	}

	
	private List<P6Data> baselineWBSUpload(P6Data obj,XSSFWorkbook workbook, String fob_mismatch) throws Exception {
		P6Data p6data = null;
		List<P6Data> wbsList = new ArrayList<P6Data>();
		XSSFSheet uploadFilesSheet = null;
		try {	
			int sheetsCount = workbook.getNumberOfSheets();
			if(sheetsCount > 0) {
				uploadFilesSheet = workbook.getSheetAt(0);
				for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
					XSSFRow row = uploadFilesSheet.getRow(i);
					// Sets the Read data to the model class
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					// Cell cell = row.getCell(0);
					// String j_username = formatter.formatCellValue(row.getCell(0));
					
					p6data = new P6Data();
					/*if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
						p6data.setContract_id_fk(formatter.formatCellValue(row.getCell(0)).trim());*/
					/*if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
						p6data.setFob_id_fk(formatter.formatCellValue(row.getCell(1)).trim());*/
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
						p6data.setP6_wbs_code(formatter.formatCellValue(row.getCell(0)).trim());
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
						p6data.setP6_wbs_name(formatter.formatCellValue(row.getCell(1)).trim());
					/*if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
						p6data.setP6_wbs_parent_code(formatter.formatCellValue(row.getCell(3)).trim());*/
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
						p6data.setP6_wbs_category_fk(formatter.formatCellValue(row.getCell(2)).trim());
					
					
					if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(obj.getContract_id_fk()) && !StringUtils.isEmpty(p6data.getP6_wbs_code())) {
						wbsList.add(p6data);
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("baselineWBSUpload() : "+e.getMessage());
			throw new Exception(e);	
		}		
		return wbsList;
	}
	
	private List<P6Data> baselineActivitiesUpload(P6Data pObj,XSSFWorkbook workbook) throws Exception {
		P6Data p6data = null;
		List<P6Data> pObjList = new ArrayList<P6Data>();
		XSSFSheet uploadFilesSheet = null;
		try {		
			int sheetsCount = workbook.getNumberOfSheets();
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
						p6data.setP6_task_code(formatter.formatCellValue(row.getCell(0)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
						p6data.setStatus_fk(formatter.formatCellValue(row.getCell(1)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
						p6data.setP6_wbs_code_fk(formatter.formatCellValue(row.getCell(2)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
						p6data.setP6_activity_name(formatter.formatCellValue(row.getCell(3)).trim());
					
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
					
					p6data.setBaseline_start(DateParser.parse(p6data.getBaseline_start()));
					p6data.setBaseline_finish(DateParser.parse(p6data.getBaseline_finish()));
					p6data.setStart(DateParser.parse(p6data.getStart()));
					p6data.setFinish(DateParser.parse(p6data.getFinish()));
					
					
					if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(p6data.getP6_wbs_code_fk()) && !StringUtils.isEmpty(p6data.getP6_task_code())) {
						pObjList.add(p6data);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("baselineActivitiesUpload() : "+e.getMessage());
			throw new Exception(e);	
		}
		
		return pObjList;
	}

	@RequestMapping(value = "/revised-p6-new-activities", method = {RequestMethod.POST})
	public ModelAndView revisedP6NewActivities(@ModelAttribute P6Data p6data,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		XSSFWorkbook workbook = null;
		String fob_mismatch = null;
		XSSFSheet uploadFilesSheet = null;
		List<P6Data> activitiesList = new ArrayList<P6Data>();
		try {
			model.setViewName("redirect:/p6-new-data");
			
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			p6data.setCreated_by_user_id_fk(userId);
			p6data.setUser_name(userName);
			p6data.setDesignation(userDesignation);
			p6data.setIsRevised("Yes");
			if(!StringUtils.isEmpty(p6data.getP6dataFile())){
				MultipartFile multipartFile = p6data.getP6dataFile();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0){					
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null) {
						String fileName = multipartFile.getOriginalFilename();
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(0);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet.getRow(0);
							//checking given file format
							if(headerRow != null){
								List<String> fileFormat = FileFormatModel.getP6RevisedFileFormat();
								int noOfColumns = headerRow.getLastCellNum();
								if(noOfColumns == fileFormat.size()){
									for (int i = 0; i < fileFormat.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.contains(fileFormat.get(i).trim())){
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
							
							p6data.setP6_file_path(fileName);
							int i= 2;
							activitiesList = revisedP6NewActivities(p6data,workbook);
							uploadFilesSheet = workbook.getSheetAt(0);
							XSSFRow row = uploadFilesSheet.getRow(i);
							String contarct = null;
							DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
							if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim())) {
								contarct = formatter.formatCellValue(row.getCell(2)).trim();
								if(contarct.contains(".")) {
									contarct = contarct.split("\\.")[0];
								}
							}
							/*if(!p6data.getContract_id_fk().equalsIgnoreCase(contarct)) {
								fob_mismatch = "selected Contract ID and WBS Code Mismatch at sheet (1) row [A3].";
							}*/
							if(activitiesList.size() == 0 ){
								fob_mismatch = "Sheet is empty.";
							}
							if(!StringUtils.isEmpty(p6data.getFob_id_fk()))
							{
								for(P6Data list : activitiesList) {
									
									if(!StringUtils.isEmpty(list.getFob_id_fk()) && !list.getFob_id_fk().equals(p6data.getFob_id_fk()) && !StringUtils.isEmpty(list.getP6_task_code())) {
										fob_mismatch = " FOB selected from the dropdown and on the P6 File do not match. at Row no(s) " + (i+1);
										break;
									}
									i++;
								}
							}
						}
						
						workbook.close();
						
						String saveDirectory = CommonConstants2.P6_FILE_SAVING_PATH ;
						FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
					}
					
				}
			}
			p6data.setUploaded_by_user_id_fk(userId);
			p6data.setData_date(DateParser.parse(p6data.getData_date()));
			p6data.setUpload_type("Update");
			if(StringUtils.isEmpty(fob_mismatch)){
				int count  = 0;
				try {
					count  = p6newdataService.updateP6Activities(activitiesList,p6data);
					String lineErr = Integer.toString(count);
					if(count <= 0) {
						fob_mismatch = "WBS Code or Activity ID Missmatch.  ";
						attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
					}else{
						attributes.addFlashAttribute("success", "Data date updated and "+ count + " Activities updated successfully.");	
					}
				}catch(Exception e) {
					String lineErr = e.getMessage();
					if(lineErr.contains("Cannot add or update a child row")) {
						if(lineErr.contains("p6_activity.p6_wbs_code_fk")) {
							fob_mismatch = "Incorrect <b>WBS Code</b>, No such value in records, Please check and try again.  ";
							attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
						}else if(lineErr.contains("p6_activity.status_fk")){
							
							fob_mismatch = "Incorrect data for column <b>Activity Status</b>, Please check and try again.  ";
							attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
						}
					}else if(lineErr.contains("Incorrect integer value")){
						
						fob_mismatch = "Incorrect data for column <b>Total Float</b>, Please check and try again.  ";
						attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
					}
					
				}
				
			}else {
				attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateP6NewActivities() : "+e.getMessage());
		}
		return model;
	}

	private List<P6Data> revisedP6NewActivities(P6Data uObj,XSSFWorkbook workbook) throws Exception {
		P6Data p6data = null;
		List<P6Data> p6dataList = new ArrayList<P6Data>();		
		XSSFSheet uploadFilesSheet = null;
		try {				
			int sheetsCount = workbook.getNumberOfSheets();
			if(sheetsCount > 0) {
				uploadFilesSheet = workbook.getSheetAt(0);	
				for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
					XSSFRow row = uploadFilesSheet.getRow(i);
					// Sets the Read data to the model class
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					
					p6data = new P6Data();					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
						p6data.setP6_task_code(formatter.formatCellValue(row.getCell(0)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
						p6data.setStatus_fk(formatter.formatCellValue(row.getCell(1)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
						p6data.setP6_wbs_code_fk(formatter.formatCellValue(row.getCell(2)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
						p6data.setP6_activity_name(formatter.formatCellValue(row.getCell(3)).trim());
					
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
					
					p6data.setBaseline_start(DateParser.parse(p6data.getBaseline_start()));
					p6data.setBaseline_finish(DateParser.parse(p6data.getBaseline_finish()));
					p6data.setStart(DateParser.parse(p6data.getStart()));
					p6data.setFinish(DateParser.parse(p6data.getFinish()));
					
					if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(p6data.getP6_wbs_code_fk()) && !StringUtils.isEmpty(p6data.getP6_task_code())) {
						p6dataList.add(p6data);
					}
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateP6NewActivities() : "+e.getMessage());
			throw new Exception(e);	
		}		
		return p6dataList;
	}
	
	@RequestMapping(value = "/update-p6-new-activities", method = {RequestMethod.POST})
	public ModelAndView updateP6NewActivities(@ModelAttribute P6Data p6data,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		XSSFWorkbook workbook = null;
		String fob_mismatch = null;
		XSSFSheet uploadFilesSheet = null;
		List<P6Data> activitiesList = new ArrayList<P6Data>();
		try {
			model.setViewName("redirect:/p6-new-data");
			
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			p6data.setCreated_by_user_id_fk(userId);
			p6data.setUser_name(userName);
			p6data.setDesignation(userDesignation);
			p6data.setIsRevised("No");
			if(!StringUtils.isEmpty(p6data.getP6dataFile())){
				MultipartFile multipartFile = p6data.getP6dataFile();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0){					
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null) {
						String fileName = multipartFile.getOriginalFilename();
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(0);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet.getRow(0);
							//checking given file format
							if(headerRow != null){
								List<String> fileFormat = FileFormatModel.getP6UpdateFileFormat();
								int noOfColumns = headerRow.getLastCellNum();
								if(noOfColumns == fileFormat.size()){
									for (int i = 0; i < fileFormat.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.contains(fileFormat.get(i).trim())){
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
							
							p6data.setP6_file_path(fileName);
							int i= 2;
							activitiesList = updateP6NewActivities(p6data,workbook);
							uploadFilesSheet = workbook.getSheetAt(0);
							XSSFRow row = uploadFilesSheet.getRow(i);
							String contarct = null;
							DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
							if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim())) {
								contarct = formatter.formatCellValue(row.getCell(2)).trim();
								if(contarct.contains(".")) {
									contarct = contarct.split("\\.")[0];
								}
							}
							/*if(!p6data.getContract_id_fk().equalsIgnoreCase(contarct)) {
								fob_mismatch = "selected Contract ID and WBS Code Mismatch at sheet (1) row [A3].";
							}*/
							if(activitiesList.size() == 0 ){
								fob_mismatch = "Sheet is empty.";
							}
							if(!StringUtils.isEmpty(p6data.getFob_id_fk()))
							{
								for(P6Data list : activitiesList) {
									
									if(!StringUtils.isEmpty(list.getFob_id_fk()) && !list.getFob_id_fk().equals(p6data.getFob_id_fk()) && !StringUtils.isEmpty(list.getP6_task_code())) {
										fob_mismatch = " FOB selected from the dropdown and on the P6 File do not match. at Row no(s) " + (i+1);
										break;
									}
									i++;
								}
							}
						}
						
						workbook.close();
						
						String saveDirectory = CommonConstants2.P6_FILE_SAVING_PATH ;
						FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
					}
					
				}
			}
			p6data.setUploaded_by_user_id_fk(userId);
			p6data.setData_date(DateParser.parse(p6data.getData_date()));
			p6data.setUpload_type("Update");
			if(StringUtils.isEmpty(fob_mismatch)){
				int count  = 0;
				try {
					count  = p6newdataService.updateP6Activities(activitiesList,p6data);
					String lineErr = Integer.toString(count);
					if(count <= 0) {
						fob_mismatch = "WBS Code or Activity ID Missmatch.  ";
						attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
					}else{
						attributes.addFlashAttribute("success", "Data date updated and "+ count + " Activities updated successfully.");	
					}
				}catch(Exception e) {
					String lineErr = e.getMessage();
					if(lineErr.contains("Cannot add or update a child row")) {
						if(lineErr.contains("p6_activity.p6_wbs_code_fk")) {
							fob_mismatch = "Incorrect <b>WBS Code</b>, No such value in records, Please check and try again.  ";
							attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
						}else if(lineErr.contains("p6_activity.status_fk")){
							
							fob_mismatch = "Incorrect data for column <b>Activity Status</b>, Please check and try again.  ";
							attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
						}
					}else if(lineErr.contains("Incorrect integer value")){
						
						fob_mismatch = "Incorrect data for column <b>Total Float</b>, Please check and try again.  ";
						attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
					}
					
				}
				
			}else {
				attributes.addFlashAttribute("error", "<br><span style='color:red;'>" + fob_mismatch + "</span> ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateP6NewActivities() : "+e.getMessage());
		}
		return model;
	}

	private List<P6Data> updateP6NewActivities(P6Data uObj,XSSFWorkbook workbook) throws Exception {
		P6Data p6data = null;
		List<P6Data> p6dataList = new ArrayList<P6Data>();		
		XSSFSheet uploadFilesSheet = null;
		try {				
			int sheetsCount = workbook.getNumberOfSheets();
			if(sheetsCount > 0) {
				uploadFilesSheet = workbook.getSheetAt(0);	
				for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
					XSSFRow row = uploadFilesSheet.getRow(i);
					// Sets the Read data to the model class
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					
					p6data = new P6Data();					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
						p6data.setP6_task_code(formatter.formatCellValue(row.getCell(0)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
						p6data.setStatus_fk(formatter.formatCellValue(row.getCell(1)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
						p6data.setP6_wbs_code_fk(formatter.formatCellValue(row.getCell(2)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
						p6data.setP6_activity_name(formatter.formatCellValue(row.getCell(3)).trim());
				
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
						p6data.setStart(formatter.formatCellValue(row.getCell(4)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
						p6data.setFinish(formatter.formatCellValue(row.getCell(5)).trim());
					
					if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
						p6data.setP6_float(formatter.formatCellValue(row.getCell(6)).trim());
					
					p6data.setStart(DateParser.parse(p6data.getStart()));
					p6data.setFinish(DateParser.parse(p6data.getFinish()));
					
					if(!StringUtils.isEmpty(p6data) && !StringUtils.isEmpty(p6data.getP6_wbs_code_fk()) && !StringUtils.isEmpty(p6data.getP6_task_code())) {
						p6dataList.add(p6data);
					}
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateP6NewActivities() : "+e.getMessage());
			throw new Exception(e);	
		}		
		return p6dataList;
	}
}
