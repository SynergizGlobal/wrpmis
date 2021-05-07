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
import org.apache.poi.util.StringUtil;
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
import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.FOBService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.ContractPaginationObject;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FOBPaginationObject;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class FOBController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FOBController.class);	
	
	@Autowired
	FOBService fobService;
	
	@Autowired
	ActivitiesService activitiesService;
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	ContractService contractService;
	
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
	
	@RequestMapping(value="/fob",method=RequestMethod.GET)
	public ModelAndView fob(@ModelAttribute FOB obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.fobGrid);
		} catch (Exception e) {
			logger.error("fob : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getFOBList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getFOBList(@ModelAttribute FOB obj) {
		List<FOB> fobs = null;
		try {
			fobs = fobService.getFOBList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFOBList : " + e.getMessage());
		}
		return fobs;
	}
	/**
	@RequestMapping(value = "/ajax/getFOBList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getFOBList(@ModelAttribute FOB obj, HttpServletRequest request,
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

			List<FOB> contractList = new ArrayList<FOB>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//contractList = getListBasedOnSearchParameter(searchParameter,contractList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			FOBPaginationObject personJsonObject = new FOBPaginationObject();
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
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}*/

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 *//**
	public int getTotalRecords(FOB obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = fobService.getTotalRecords(obj, searchParameter);
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
	public List<FOB> createPaginationData(int startIndex, int offset,FOB obj, String searchParameter) {
		List<FOB> objList = null;
		try {
			objList = fobService.getFOBsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	*/
	
	@RequestMapping(value = "/ajax/getWorkStatusFilterListInFOB", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getWorkStatusList(@ModelAttribute FOB obj) {
		List<FOB> workStatusList = null;
		try {
			workStatusList = fobService.getWorkStatusList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusList : " + e.getMessage());
		}
		return workStatusList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInFOB", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getContractsList(@ModelAttribute FOB obj) {
		List<FOB> contractsList = null;
		try {
			contractsList = fobService.getContractsList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value="/add-fob-form",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addFOBForm(@ModelAttribute FOB obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditFob);
			
			model.addObject("action", "add");
			
			List<FOB> fobIdCheck = fobService.getFobIdCheck(obj);
			model.addObject("fobIdCheck",fobIdCheck);
			
			List<FOB> projectsList = fobService.getProjectsListForFOBForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<FOB> fobFileTypesList = fobService.getFobFileTypesList(obj);
			model.addObject("fobFileTypesList", fobFileTypesList);
			
			List<FOB> worksList = fobService.getWorkListForFOBForm(obj);
			model.addObject("worksList", worksList);
			
			List<FOB> contractsList = fobService.getContractsListForFOBForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<FOB> fobDetailsList = fobService.getFobDetailsList(obj);
			model.addObject("fobDetailsList", fobDetailsList);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
		} catch (Exception e) {
			logger.error("addFOBForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getProjectsListForFOBForm(@ModelAttribute FOB obj) {
		List<FOB> objsList = null;
		try {
			objsList = fobService.getProjectsListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForFOBForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getWorkListForFOBForm(@ModelAttribute FOB obj) {
		List<FOB> objsList = null;
		try {
			objsList = fobService.getWorkListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForFOBForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getContractsListForFOBForm(@ModelAttribute FOB obj) {
		List<FOB> objsList = null;
		try {
			objsList = fobService.getContractsListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForFOBForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-fob",method=RequestMethod.POST)
	public ModelAndView addFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/fob");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));			
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));			
			obj.setCommissioning_date(DateParser.parse(obj.getCommissioning_date()));			
			obj.setActual_completion_date(DateParser.parse(obj.getActual_completion_date()));
		
			boolean flag = fobService.addFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding fob is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-fob",method=RequestMethod.POST)
	public ModelAndView getFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditFob);
			
			model.addObject("action", "edit");
			
			List<FOB> projectsList = fobService.getProjectsListForFOBForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<FOB> fobFileTypesList = fobService.getFobFileTypesList(obj);
			model.addObject("fobFileTypesList", fobFileTypesList);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
			List<FOB> fobDetailsList = fobService.getFobDetailsList(obj);
			model.addObject("fobDetailsList", fobDetailsList);
			
			FOB fob = fobService.getFOB(obj);
			model.addObject("fob", fob);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-fob",method=RequestMethod.POST)
	public ModelAndView updateFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/fob");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));			
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));			
			obj.setCommissioning_date(DateParser.parse(obj.getCommissioning_date()));			
			obj.setActual_completion_date(DateParser.parse(obj.getActual_completion_date()));
		
			boolean flag = fobService.updateFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating fob is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-fob",method=RequestMethod.POST)
	public ModelAndView deleteFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/fob");
			boolean flag = fobService.deleteFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting fob is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("deleteFOB : " + e.getMessage());
		}
		return model;
	}
/*	
	@RequestMapping(value = "/export-fobs", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportFOBs(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute FOB fob,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants2.fobGrid);
		List<FOB> dataList = new ArrayList<FOB>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/fob");
			dataList = fobService.getFOBList(fob);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("FOB"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
	            XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("FOB ID");
	            headingRow.createCell((short)1).setCellValue("Work ID");
	            headingRow.createCell((short)2).setCellValue("Contract ID");
	            headingRow.createCell((short)3).setCellValue("FOB Name");
	            headingRow.createCell((short)4).setCellValue("Work Status");
	            headingRow.createCell((short)5).setCellValue("Date of Approval");
	            headingRow.createCell((short)6).setCellValue("Target Date");
	            headingRow.createCell((short)7).setCellValue("Construction Start Date");
	            headingRow.createCell((short)8).setCellValue("Revised Completion");
	            headingRow.createCell((short)9).setCellValue("Actual Completion Date");
	            headingRow.createCell((short)10).setCellValue("Commissioning Date");
	            headingRow.createCell((short)11).setCellValue("Estimated Cost");
	            headingRow.createCell((short)12).setCellValue("Last Sanctioned Cost");
	            headingRow.createCell((short)13).setCellValue("Completion Cost");
	            headingRow.createCell((short)14).setCellValue("Latitude");
	            headingRow.createCell((short)15).setCellValue("Longitude");
	            headingRow.createCell((short)16).setCellValue("Remarks");
	            short rowNo = 1;
	            for (FOB obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getFob_id());
	                row.createCell((short)1).setCellValue(obj.getWork_id_fk());
	                row.createCell((short)2).setCellValue(obj.getContract_id_fk());
	                row.createCell((short)3).setCellValue(obj.getFob_name());
	                row.createCell((short)4).setCellValue(obj.getWork_status_fk());
	                row.createCell((short)5).setCellValue(obj.getDate_of_approval());
	                row.createCell((short)6).setCellValue(obj.getTarget_date());
	                row.createCell((short)7).setCellValue(obj.getConstruction_start_date());
	                row.createCell((short)8).setCellValue(obj.getRevised_completion());
	                row.createCell((short)9).setCellValue(obj.getActual_completion_date());
	                row.createCell((short)10).setCellValue(obj.getCommissioning_date());
	                row.createCell((short)11).setCellValue(obj.getEstimated_cost());
	                row.createCell((short)12).setCellValue(obj.getLast_sanctioned_cost());
	                row.createCell((short)13).setCellValue(obj.getCompletion_cost());
	                row.createCell((short)14).setCellValue(obj.getLatitude());
	                row.createCell((short)15).setCellValue(obj.getLongitude());
	                row.createCell((short)16).setCellValue(obj.getRemarks());
	                
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "FOB_"+dateFormat.format(date);
                
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
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportFOBs : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}*/
	
	@RequestMapping(value = "/export-fobs", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportFOBs(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute FOB fob,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants2.fobGrid);
		List<FOB> dataList = new ArrayList<FOB>();
		List<FOB> revisionList = new ArrayList<FOB>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/fob");
			dataList = fobService.getFOBList(fob); 
			revisionList = fobService.getFOBDetails(fob);
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("FOB"));
			            XSSFSheet revisonSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("FOB Details"));
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
			            String headerString = "FOB ID^Work ID^Contract ID^FOB Name^Work Status^Date of Approval^Target Date^Construction Start Date^Revised Completion^Actual Completion Date^"
			            		+ "Commissioning Date^Estimated Cost^Last Sanctioned Cost^Completion Cost^Latitude^Longitude^Remarks";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            XSSFRow headingRow1 = revisonSheet.createRow(0);
			            String headerString1 = "FOB ID^Detail Name^Value";
			            
			            String[] secondHeaderStringArr = headerString1.split("\\^");
			            
			            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow1.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(secondHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (FOB obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getFob_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_id_fk());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getContract_id_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getFob_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_status_fk());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDate_of_approval());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getTarget_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getConstruction_start_date());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRevised_completion());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getActual_completion_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCommissioning_date());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getEstimated_cost());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLast_sanctioned_cost());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCompletion_cost());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLatitude());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getLongitude());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
						
			                rowNo++;
			            }
			            short rowNo2 = 1;
						
							 for (FOB obj : revisionList) {
					                XSSFRow row = revisonSheet.createRow(rowNo2);
					                int b = 0;
					                
					                Cell cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getFob_id_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getDetail_name());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getValue());
									
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
		                String fileName = "FOB_"+dateFormat.format(date);
		                
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
			logger.error("exportFOBs : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
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
