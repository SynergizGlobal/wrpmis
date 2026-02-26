package com.synergizglobal.wrpmis.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.wrpmis.Iservice.SourceOfFundService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Budget;
import com.synergizglobal.wrpmis.model.BudgetPaginationObject;
import com.synergizglobal.wrpmis.model.FOB;
import com.synergizglobal.wrpmis.model.SourceOfFund;
import com.synergizglobal.wrpmis.model.SourceOfFundPaginationObject;

@Controller
public class SourceOfFundController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SourceOfFundController.class);
	
	@Autowired
	SourceOfFundService sofService;
	
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
	
	@RequestMapping(value="/source-of-funds",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sourceOfFund(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.sourceOfFund);
		try {
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("sourceOfFund : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/get-funds", method = { RequestMethod.POST, RequestMethod.GET })
	public void getFundsList(@ModelAttribute SourceOfFund obj, HttpServletRequest request,
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

			List<SourceOfFund> budgetList = new ArrayList<SourceOfFund>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//budgetList = getListBasedOnSearchParameter(searchParameter,budgetList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			SourceOfFundPaginationObject personJsonObject = new SourceOfFundPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(budgetList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getFundsList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(SourceOfFund obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = sofService.getTotalRecords(obj, searchParameter);
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
	public List<SourceOfFund> createPaginationData(int startIndex, int offset, SourceOfFund obj, String searchParameter) {
		List<SourceOfFund> objList = null;
		try {
			objList = sofService.getSourceOfFundList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getSOFFilterListInFunds", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SourceOfFund> getSOFList(@ModelAttribute SourceOfFund obj) {
		List<SourceOfFund> sourceOfFundsList = null;
		try {
			sourceOfFundsList = sofService.getSOFList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSOFList : " + e.getMessage());
		}
		return sourceOfFundsList;
	}
	
	@RequestMapping(value = "/ajax/getRailwaysFilterListInFunds", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SourceOfFund> getRailwayList(@ModelAttribute SourceOfFund obj) {
		List<SourceOfFund> railwaysList = null;
		try {
			railwaysList = sofService.getRailwayList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRailwayList : " + e.getMessage());
		}
		return railwaysList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInFunds", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SourceOfFund> getFundWorksList(@ModelAttribute SourceOfFund obj) {
		List<SourceOfFund> worksList = null;
		try {
			worksList = sofService.getFundWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFundWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/add-fund-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addFundForm(@ModelAttribute SourceOfFund obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSourceOfFund);
			model.addObject("action", "add");
			
			List<SourceOfFund> projectsList = sofService.getProjectsListForSourceOfFundForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<SourceOfFund> worksList = sofService.getWorkListForSourceOfFundForm(obj);
			model.addObject("worksList", worksList);
			
			List<SourceOfFund> sourceOfFundList = sofService.getSourceOfFundList();
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<SourceOfFund> railwaysList = sofService.getRailwaysList();
			model.addObject("railwaysList", railwaysList);
			
			List<SourceOfFund> unitsList = sofService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
		}catch (Exception e) {
				logger.error("addFundForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForSourceOfFundForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SourceOfFund> getProjectsListForSourceOfFundForm(@ModelAttribute SourceOfFund obj) {
		List<SourceOfFund> objsList = null;
		try {
			objsList = sofService.getProjectsListForSourceOfFundForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForSourceOfFundForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForSourceOfFundForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SourceOfFund> getWorkListForSourceOfFundForm(@ModelAttribute SourceOfFund obj) {
		List<SourceOfFund> objsList = null;
		try {
			objsList = sofService.getWorkListForSourceOfFundForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForSourceOfFundForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/get-funds", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFunds(@ModelAttribute SourceOfFund obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSourceOfFund);
			model.addObject("action", "edit");
			
			List<SourceOfFund> sourceOfFundList = sofService.getSourceOfFundList();
			model.addObject("sourceOfFundList", sourceOfFundList);
			List<SourceOfFund> railwaysList = sofService.getRailwaysList();
			model.addObject("railwaysList", railwaysList);
			List<SourceOfFund> unitsList = sofService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			SourceOfFund fundDetails = sofService.getFunds(obj);
			model.addObject("fundDetails", fundDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getFunds : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-funds/{funds_id}",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFundsWithId(@ModelAttribute SourceOfFund obj,@PathVariable("funds_id") String funds_id ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSourceOfFund);
			model.addObject("action", "edit");
			
			List<SourceOfFund> sourceOfFundList = sofService.getSourceOfFundList();
			model.addObject("sourceOfFundList", sourceOfFundList);
			List<SourceOfFund> railwaysList = sofService.getRailwaysList();
			model.addObject("railwaysList", railwaysList);
			List<SourceOfFund> unitsList = sofService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			SourceOfFund fundDetails = sofService.getFunds(obj);
			model.addObject("fundDetails", fundDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getFundsWithId : " + e.getMessage());
		}
		return model;
	 }
	@RequestMapping(value = "/add-funds", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addFunds(@ModelAttribute SourceOfFund obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setFunding_date(DateParser.parse(obj.getFunding_date()));
			boolean flag =  sofService.addFunds(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Funds Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Funds is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Funds is failed. Try again.");
			logger.error("addFunds : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/update-funds", method = {RequestMethod.POST})
	public ModelAndView updateFunds(@ModelAttribute SourceOfFund obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setFunding_date(DateParser.parse(obj.getFunding_date()));
			obj.setFunding_date(DateParser.parse(obj.getFunding_date()));
			boolean flag =  sofService.updateFunds(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Funds Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Funds is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Funds is failed. Try again.");
			logger.error("updateFunds : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-funds", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteFunds(@ModelAttribute SourceOfFund obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			boolean flag =  sofService.deleteFunds(obj);
		}catch (Exception e) {
			logger.error("deleteFunds : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-funds", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportFunds(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute SourceOfFund sObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.sourceOfFund);
		List<SourceOfFund> dataList = new ArrayList<SourceOfFund>();
		try {
			view.setViewName("redirect:/source-of-funds");
			dataList =   sofService.fundsList(sObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Source_of_Funds"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
		        int i = 0;
	            headingRow.createCell((short)i++).setCellValue("Project");
	            headingRow.createCell((short)i++).setCellValue("Source of Fund");
	         	headingRow.createCell((short)i++).setCellValue("Railway");
	            headingRow.createCell((short)i++).setCellValue("Funding Date");
	            headingRow.createCell((short)i++).setCellValue("Fund Amount ");
	            headingRow.createCell((short)i++).setCellValue("Unit");
	            headingRow.createCell((short)i++).setCellValue("Ledger Account");
	            headingRow.createCell((short)i++).setCellValue("Bank Account");
	            headingRow.createCell((short)i++).setCellValue("Voucher Type");
	            headingRow.createCell((short)i++).setCellValue("Voucher No");
	            headingRow.createCell((short)i++).setCellValue("Narration");
	            headingRow.createCell((short)i++).setCellValue("Remarks");
	          
	            short rowNo = 1;
	            for (SourceOfFund obj : dataList) {
	            	  int j = 0;
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)j++).setCellValue(obj.getProject_id_fk() +" - "+obj.getProject_name());
	                row.createCell((short)j++).setCellValue(obj.getSource_of_funds_fk());
	                row.createCell((short)j++).setCellValue(obj.getSub_category_railway_id_fk());
	                row.createCell((short)j++).setCellValue(obj.getFunding_date());
	                row.createCell((short)j++).setCellValue(obj.getFund_amount());
	                row.createCell((short)j++).setCellValue(obj.getAmount_unit());
	                row.createCell((short)j++).setCellValue(obj.getLedger_account());
	                row.createCell((short)j++).setCellValue(obj.getBank_account());
	                row.createCell((short)j++).setCellValue(obj.getVoucher_type());
	                row.createCell((short)j++).setCellValue(obj.getVoucher_no());
	                row.createCell((short)j++).setCellValue(obj.getNarration());
	                row.createCell((short)j++).setCellValue(obj.getRemarks());
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//sheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Funds_"+dateFormat.format(date);
                
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
