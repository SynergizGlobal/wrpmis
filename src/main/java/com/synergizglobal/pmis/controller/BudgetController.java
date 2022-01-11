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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.BudgetPaginationObject;
import com.synergizglobal.pmis.model.TAFinancials;
import com.synergizglobal.pmis.model.TAFinancialsPaginationObject;

@Controller
public class BudgetController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(BudgetController.class);
	
	@Autowired
	WorkService workService;
	
	@Autowired
	BudgetService budgetService;
	

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
	
	
	@RequestMapping(value="/budget",method={RequestMethod.GET})
	public ModelAndView budget(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.budgetGrid);
		try {
			/*List<Budget> worksList = budgetService.getWorksList();
			model.addObject("worksList", worksList);
			List<Budget> financialYearsList = budgetService.getFinancialYearsList();
			model.addObject("financialYearsList", financialYearsList);
			List<Budget> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);*/
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("budget : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/get-budget", method = { RequestMethod.POST, RequestMethod.GET })
	public void getBudgetsList(@ModelAttribute Budget obj, HttpServletRequest request,
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

			List<Budget> budgetList = new ArrayList<Budget>();

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

			BudgetPaginationObject personJsonObject = new BudgetPaginationObject();
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
					"getBudgetsList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Budget obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = budgetService.getTotalRecords(obj, searchParameter);
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
	public List<Budget> createPaginationData(int startIndex, int offset, Budget obj, String searchParameter) {
		List<Budget> objList = null;
		try {
			objList = budgetService.getBudgetList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getWorksFilterListInBudget", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getWorksList(@ModelAttribute Budget obj) {
		List<Budget> worksList = null;
		try {
			worksList = budgetService.getBudgetWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInBudget", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getProjectsList(@ModelAttribute Budget obj) {
		List<Budget> projectsList = null;
		try {
			projectsList = budgetService.getBudgetProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getFinancialYearsFilterListInBudget", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getFinancialYearsList(@ModelAttribute Budget obj) {
		List<Budget> financialYearsList = null;
		try {
			financialYearsList = budgetService.getFinancialYearsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFinancialYearsList : " + e.getMessage());
		}
		return financialYearsList;
	}
	
	@RequestMapping(value = "/add-budget-form", method = {RequestMethod.GET})
	public ModelAndView addBudgetForm(@ModelAttribute Budget obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBudget);
			model.addObject("action", "add");
			List<Budget> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			
			List<Budget> projectsList = budgetService.getProjectsListForBudgetForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Budget> worksList = budgetService.getWorkListForBudgetForm(obj);
			model.addObject("worksList", worksList);
			
		}catch (Exception e) {
				logger.error("addBudgetForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForBudgetForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getProjectsListForBudgetForm(@ModelAttribute Budget obj) {
		List<Budget> objsList = null;
		try {
			objsList = budgetService.getProjectsListForBudgetForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForBudgetForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForBudgetForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getWorkListForBudgetForm(@ModelAttribute Budget obj) {
		List<Budget> objsList = null;
		try {
			objsList = budgetService.getWorkListForBudgetForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForBudgetForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/get-budget", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getBudgetForm(@ModelAttribute Budget budget ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBudget);
			model.addObject("action", "edit");
			
			List<Budget> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			
			Budget budgetDetails = budgetService.getBudget(budget);
			model.addObject("budgetDetails", budgetDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getBudget : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-budget/{budget_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getBudgetFormWithId(@ModelAttribute Budget budget,@PathVariable("budget_id") String budget_id ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBudget);
			model.addObject("action", "edit");
			
			List<Budget> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			
			Budget budgetDetails = budgetService.getBudget(budget);
			model.addObject("budgetDetails", budgetDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getBudgetFormWithId : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-budget", method = {RequestMethod.POST})
	public ModelAndView addBudget(@ModelAttribute Budget budget,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/budget");
			boolean flag =  budgetService.addBudget(budget);
			if(flag) {
				attributes.addFlashAttribute("success", "Budget Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Budget is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Budget is failed. Try again.");
			logger.error("addBudget : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-budget", method = {RequestMethod.POST})
	public ModelAndView updateBudget(@ModelAttribute Budget budget,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/budget");
			boolean flag =  budgetService.updateBudget(budget);
			if(flag) {
				attributes.addFlashAttribute("success", "Budget Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Budget is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Budget is failed. Try again.");
			logger.error("updateBudget : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-budget", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteBudget(@ModelAttribute Budget obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/budget");
			boolean flag =  budgetService.deleteBudget(obj);
		}catch (Exception e) {
			logger.error("deleteBudget : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/export-budget", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportBudget(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Budget budget,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.budgetGrid);
		List<Budget> dataList = new ArrayList<Budget>();
		try {
			view.setViewName("redirect:/budget");
			dataList =   budgetService.getBudgetExportList(budget);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet budgetSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Budget"));
		        workBook.setSheetOrder(budgetSheet.getSheetName(), 0);
		        XSSFRow headingRow = budgetSheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Budget ID");
	            headingRow.createCell((short)1).setCellValue("Work");
	            headingRow.createCell((short)2).setCellValue("Latest Financial Year");
	         	headingRow.createCell((short)3).setCellValue("Budget Estimate");
	            headingRow.createCell((short)4).setCellValue("Budget Grant");
	            headingRow.createCell((short)5).setCellValue("Reivised Estimate");
	            headingRow.createCell((short)6).setCellValue("Reivised Grant");
	            headingRow.createCell((short)7).setCellValue("Final Estimate");
	            headingRow.createCell((short)8).setCellValue("Final Grant");
	            headingRow.createCell((short)9).setCellValue("Remarks");

	            short rowNo = 1;
	            for (Budget obj : dataList) {
	                XSSFRow row = budgetSheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getBudget_id());
	                row.createCell((short)1).setCellValue(obj.getWork_id_fk());
	                row.createCell((short)2).setCellValue(obj.getFinancial_year_fk());
	                row.createCell((short)3).setCellValue(obj.getBudget_estimate());
	                row.createCell((short)4).setCellValue(obj.getBudget_grant());
	                row.createCell((short)5).setCellValue(obj.getRevised_estimate());
	                row.createCell((short)6).setCellValue(obj.getRevised_grant());
	                row.createCell((short)7).setCellValue(obj.getBudget_estimate());
	                row.createCell((short)8).setCellValue(obj.getFinal_grant());
	                row.createCell((short)9).setCellValue(obj.getRemarks());
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//Budgetsheet.autoSizeColumn(columnIndex);
	            	budgetSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Budget_"+dateFormat.format(date);
                
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
