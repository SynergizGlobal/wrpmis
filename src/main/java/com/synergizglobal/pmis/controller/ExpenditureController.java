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

import com.synergizglobal.pmis.Iservice.ExpenditureService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Expenditure;

@Controller
public class ExpenditureController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ExpenditureController.class);
	
	@Autowired
	ExpenditureService expenditureService;
	
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
	
	@RequestMapping(value="/expenditure",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Expenditure(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.expenditure);
		try {
			List<Expenditure> worksList = expenditureService.getWorksList();
			model.addObject("worksList", worksList);
			List<Expenditure> contractsList = expenditureService.getContractsList();
			model.addObject("contractsList", contractsList);
			List<Expenditure> ledgerAccountsList = expenditureService.getLedgerAccountsList();
			model.addObject("ledgerAccountsList", ledgerAccountsList);
			List<Expenditure> contractorNamesList = expenditureService.getContractorNamesList();
			model.addObject("contractorNamesList", contractorNamesList);
			List<Expenditure> voucherTypesList = expenditureService.getVoucherTypesList();
			model.addObject("voucherTypesList", voucherTypesList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Expenditure : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/get-expenditure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getExpendituresList(@ModelAttribute Expenditure obj) {
		List<Expenditure> expenditureList = null;
		try {
			expenditureList = expenditureService.expendituresList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("expenditure : " + e.getMessage());
		}
		return expenditureList;
	}

	@RequestMapping(value = "/add-expenditure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addExpenditureForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditExpenditure);
			model.addObject("action", "add");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Expenditure> voucherList = expenditureService.getVoucherList();
			model.addObject("voucherList", voucherList);
		}catch (Exception e) {
				logger.error("Expenditure : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-expenditure", method = {RequestMethod.POST})
	public ModelAndView getExpenditure(@ModelAttribute Expenditure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditExpenditure);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Expenditure> voucherList = expenditureService.getVoucherList();
			model.addObject("voucherList", voucherList);
			Expenditure expenditureDetails = expenditureService.getExpenditure(obj);
			model.addObject("expenditureDetails", expenditureDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getExpenditure : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-expenditure", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addExpenditure(@ModelAttribute Expenditure obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/expenditure");
			obj.setDate(DateParser.parse(obj.getDate()));
			boolean flag =  expenditureService.addExpenditure(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Expenditure Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Expenditure is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Expenditure is failed. Try again.");
			logger.error("addExpenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-expenditure", method = {RequestMethod.POST})
	public ModelAndView updateExpenditure(@ModelAttribute Expenditure obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/expenditure");
			obj.setDate(DateParser.parse(obj.getDate()));
			boolean flag =  expenditureService.updateExpenditure(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Expenditure Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Expenditure is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Expenditure is failed. Try again.");
			logger.error("updateExpenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-expenditure", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteExpenditure(@ModelAttribute Expenditure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/expenditure");
			boolean flag =  expenditureService.deleteExpenditure(obj);
		}catch (Exception e) {
			logger.error("deleteExpenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-expenditure", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportExpenditure(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Expenditure obj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.expenditure);
		List<Expenditure> dataList = new ArrayList<Expenditure>();
		try {
			view.setViewName("redirect:/expenditure");
			dataList =  expenditureService.expendituresList(obj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Work");
	            headingRow.createCell((short)1).setCellValue("Contract");
	         	headingRow.createCell((short)2).setCellValue("Ledger Account");
	            headingRow.createCell((short)3).setCellValue("Contractor Name");
	            headingRow.createCell((short)4).setCellValue("Date");
	            headingRow.createCell((short)5).setCellValue("Vocher Type");
	           

	            short rowNo = 1;
	            for (Expenditure eObj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(eObj.getWork_id_fk());
	                row.createCell((short)1).setCellValue(eObj.getContract_id_fk());
	                row.createCell((short)2).setCellValue(eObj.getLedger_account());
	                row.createCell((short)3).setCellValue(eObj.getContract_name());
	                row.createCell((short)4).setCellValue(eObj.getDate());
	                row.createCell((short)5).setCellValue(eObj.getVoucher_type());
	             
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Expenditure_"+dateFormat.format(date);
                
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
			 //e.printStackTrace()
		}
	}

}
