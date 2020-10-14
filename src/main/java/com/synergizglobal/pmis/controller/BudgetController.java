package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

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
	
	
	@RequestMapping(value="/budget",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Contract(HttpSession session,@ModelAttribute Work obj){
		ModelAndView model = new ModelAndView(PageConstants.budgetGrid);
		try {
			List<Work> workList = workService.getWorkList(obj);
			model.addObject("workList", workList);
			List<Work> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getBudget", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Budget> getContractList(@ModelAttribute Budget obj) {
		List<Budget> budgetList = null;
		try {
			budgetList = budgetService.budgetList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("budgetList : " + e.getMessage());
		}
		return budgetList;
	}
	
	
	@RequestMapping(value = "/add-budget-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractorForm(@ModelAttribute Work obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBudget);
			model.addObject("action", "add");
			List<Work> workList = workService.getWorkList(obj);
			model.addObject("workList", workList);
			List<Work> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
		}catch (Exception e) {
				logger.error("Contractor : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-budget", method = {RequestMethod.POST})
	public ModelAndView getBudgetForm(@ModelAttribute Budget budget,Work obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBudget);
			model.addObject("action", "edit");
			List<Work> workList = workService.getWorkList(obj);
			model.addObject("workList", workList);
			List<Work> financialYearList = budgetService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
			Budget budgetDetails = budgetService.getBudget(budget);
			model.addObject("budgetDetails", budgetDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getBudget : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-budget", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addBudget(@ModelAttribute Budget budget,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/budget");
			 MultipartFile file = budget.getBudgetFile(); 
				if (null != file && !file.isEmpty()){
					String saveDirectory = CommonConstants.BUDGET_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
				}
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
			  MultipartFile file = budget.getBudgetFile(); 
				if (null != file && !file.isEmpty()){
					String saveDirectory = CommonConstants.BUDGET_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
				}
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
			dataList =   budgetService.budgetList(budget);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Work");
	            headingRow.createCell((short)1).setCellValue("Financial Year");
	         	headingRow.createCell((short)2).setCellValue("Budget Estimate");
	            headingRow.createCell((short)3).setCellValue("Budget Grant");
	            headingRow.createCell((short)4).setCellValue("Reivised Estimate");
	            headingRow.createCell((short)5).setCellValue("Reivised Grant");
	            headingRow.createCell((short)6).setCellValue("Final Estimate");
	            headingRow.createCell((short)7).setCellValue("Final Grant");

	            short rowNo = 1;
	            for (Budget obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getWork_id_fk());
	                row.createCell((short)1).setCellValue(obj.getFinancial_year_fk());
	                row.createCell((short)2).setCellValue(obj.getBudget_estimate());
	                row.createCell((short)3).setCellValue(obj.getBudget_grant());
	                row.createCell((short)4).setCellValue(obj.getRevised_estimate());
	                row.createCell((short)5).setCellValue(obj.getRevised_grant());
	                row.createCell((short)6).setCellValue(obj.getBudget_estimate());
	                row.createCell((short)7).setCellValue(obj.getFinal_grant());
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
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
