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
import org.apache.poi.util.StringUtil;
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
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.ContractPaginationObject;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FOBPaginationObject;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Project;

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
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(FOB obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = fobService.getTotalRecords(obj, searchParameter);
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
	public List<FOB> createPaginationData(int startIndex, int offset,FOB obj, String searchParameter) {
		List<FOB> objList = null;
		try {
			objList = fobService.getFOBsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
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
			
			List<FOB> projectsList = fobService.getProjectsListForFOBForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<FOB> worksList = fobService.getWorkListForFOBForm(obj);
			model.addObject("worksList", worksList);
			
			List<FOB> contractsList = fobService.getContractsListForFOBForm(obj);
			model.addObject("contractsList", contractsList);
			
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
			MultipartFile file = obj.getFobFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.FOB_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}
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
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
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
			MultipartFile file = obj.getFobFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.FOB_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			} else if(!StringUtils.isEmpty(obj.getAttachment())){
				obj.setAttachment(obj.getAttachment());
			}
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
	
}
