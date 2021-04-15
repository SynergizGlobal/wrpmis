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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.DeliverablesService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.DataGathering;
import com.synergizglobal.pmis.model.DeliverablesPaginationObject;
import com.synergizglobal.pmis.model.Deliverables;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.Deliverables;


@Controller
public class DeliverablesController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DeliverablesController.class);
	
	@Autowired
	DeliverablesService deliverablesService;

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
	
	@RequestMapping(value="/deliverables",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  deliverables(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.deliverablesGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("deliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-deliverables-list", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Deliverables obj, HttpServletRequest request,
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

			List<Deliverables> deliverablesList = new ArrayList<Deliverables>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				deliverablesList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				deliverablesList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//deliverablesList = getListBasedOnSearchParameter(searchParameter,deliverablesList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			DeliverablesPaginationObject personJsonObject = new DeliverablesPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(deliverablesList);

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
	public int getTotalRecords(Deliverables obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = deliverablesService.getTotalRecords(obj, searchParameter);
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
	public List<Deliverables> createPaginationData(int startIndex, int offset,Deliverables obj, String searchParameter) {
		List<Deliverables> objList = null;
		try {
			objList = deliverablesService.getDeliverablesList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getStatusFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getStatusList(@ModelAttribute Deliverables obj) {
		List<Deliverables> statusList = null;
		try {
			statusList = deliverablesService.getDeliverablesStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesStatusList : " + e.getMessage());
		}
		return statusList;
	}
	
	@RequestMapping(value = "/ajax/getProjectFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getProjectsList(@ModelAttribute Deliverables obj) {
		List<Deliverables> projectsList = null;
		try {
			projectsList = deliverablesService.getDeliverablesProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getWorksList(@ModelAttribute Deliverables obj) {
		List<Deliverables> worksList = null;
		try {
			worksList = deliverablesService.getDeliverablesWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContarctFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getContarctsList(@ModelAttribute Deliverables obj) {
		List<Deliverables> contractsList = null;
		try {
			contractsList = deliverablesService.getDeliverablesContarctsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesContarctsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/add-deliverables-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDeliverablesForm(@ModelAttribute Deliverables obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDeliverablesForm);
			model.addObject("action", "add");
			List<Deliverables> statusList = deliverablesService.getStatusList();
			model.addObject("statusList", statusList);
			
			List<Deliverables> deliverablesTypeList = deliverablesService.getDeliverableTypeList();
			model.addObject("deliverablesTypeList", deliverablesTypeList);
			
			List<Deliverables> priorityList = deliverablesService.getPriorityList();
			model.addObject("priorityList", priorityList);
			
			List<Deliverables> worksList = deliverablesService.getWorkListForDeliverablesForm(obj);
			model.addObject("worksList", worksList);
			
			List<Deliverables> contractsList = deliverablesService.getContractsListForDeliverablesForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
			model.addObject("projectsList", projectsList);
			
		}catch (Exception e) {
				logger.error("addDeliverablesForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/getProjectsListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getProjectsListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getWorkListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getWorkListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getContractsListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getContractsListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	
	@RequestMapping(value = "/get-deliverables", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDeliverablesForm(@ModelAttribute Deliverables obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDeliverablesForm);
			model.addObject("action", "edit");
			List<Deliverables> statusList = deliverablesService.getStatusList();
			model.addObject("statusList", statusList);
			List<Deliverables> deliverablesTypeList = deliverablesService.getDeliverableTypeList();
			model.addObject("deliverablesTypeList", deliverablesTypeList);
			List<Deliverables> priorityList = deliverablesService.getPriorityList();
			model.addObject("priorityList", priorityList);
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
			model.addObject("projectsList", projectsList);
			Deliverables deliverablesDetails = deliverablesService.getDeliverables(obj);
			model.addObject("deliverablesDetails", deliverablesDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDeliverables : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-deliverables", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDeliverables(@ModelAttribute Deliverables obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables");
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));
			obj.setStart_date(DateParser.parse(obj.getStart_date()));
			obj.setFinish_date(DateParser.parse(obj.getFinish_date()));
			
			boolean flag =  deliverablesService.addDeliverables(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverables Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Deliverables is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Deliverables is failed. Try again.");
			logger.error("addDeliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-deliverables", method = {RequestMethod.POST})
	public ModelAndView updateDeliverables(@ModelAttribute Deliverables obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables");
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));
			obj.setStart_date(DateParser.parse(obj.getStart_date()));
			obj.setFinish_date(DateParser.parse(obj.getFinish_date()));
			MultipartFile file = obj.getDeliverablesFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.DELIVERABLES_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}	
			boolean flag =  deliverablesService.updateDeliverables(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverables Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Deliverables is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Deliverables is failed. Try again.");
			logger.error("updateDeliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-deliverables", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteDeliverables(@ModelAttribute Deliverables obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables");
			boolean flag =  deliverablesService.deleteDeliverables(obj);
		}catch (Exception e) {
			logger.error("deleteDeliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-deliverables", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDeliverables(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Deliverables dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.deliverablesGrid);
		List<Deliverables> dataList = new ArrayList<Deliverables>();
		try {
			view.setViewName("redirect:/deliverables");
			dataList =  deliverablesService.getDeliverablesList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Deliverables"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
		        headingRow.createCell((short)0).setCellValue("ID");
		        headingRow.createCell((short)1).setCellValue("Project Priority");
		        headingRow.createCell((short)2).setCellValue("Project");
	            headingRow.createCell((short)3).setCellValue("Work");
	            headingRow.createCell((short)4).setCellValue("Contarct");
	            headingRow.createCell((short)5).setCellValue("Deliverable Type");
	            headingRow.createCell((short)6).setCellValue("Description");
	            headingRow.createCell((short)7).setCellValue("Target Date");
		        headingRow.createCell((short)8).setCellValue("Star Date");
		        headingRow.createCell((short)9).setCellValue("Finish Date");
	            headingRow.createCell((short)10).setCellValue("Status");
	            headingRow.createCell((short)11).setCellValue("Remarks");
	            short rowNo = 1;
	            for (Deliverables obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getId());
	                row.createCell((short)1).setCellValue(obj.getProject_priority_fk());
	                row.createCell((short)2).setCellValue(obj.getProject_id_fk() +" - "+ obj.getProject_name());
	                row.createCell((short)3).setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_name());
	                row.createCell((short)4).setCellValue(obj.getContract_id_fk()+" - "+ obj.getContract_name());
	                row.createCell((short)5).setCellValue(obj.getDeliverable_type_fk());
	                row.createCell((short)6).setCellValue(obj.getDeliverable_description());
	                row.createCell((short)7).setCellValue(obj.getTarget_date());
	                row.createCell((short)8).setCellValue(obj.getStart_date());
	                row.createCell((short)9).setCellValue(obj.getFinish_date());
	                row.createCell((short)10).setCellValue(obj.getStatus_fk());
	                row.createCell((short)11).setCellValue(obj.getRemarks());
	               
	          
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//sheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Deliverables_"+dateFormat.format(date);
                
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
