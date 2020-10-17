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
import com.synergizglobal.pmis.Iservice.SourceOfFundService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Work;

@Controller
public class SourceOfFundController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SourceOfFundController.class);
	
	@Autowired
	SourceOfFundService sofService;
	
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
	
	@RequestMapping(value="/source-of-funds",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sourceOfFund(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.sourceOfFund);
		try {
			List<Work> workList = workService.getWorkList(null);
			model.addObject("workList", workList);
			List<SourceOfFund> sourceOfFundList = sofService.getSourceOfFundList();
			model.addObject("sourceOfFundList", sourceOfFundList);
			List<SourceOfFund> railwayList = sofService.getRailwayListList();
			model.addObject("railwayList", railwayList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("SourceOfFund : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-fund-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addFundForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSourceOfFund);
			model.addObject("action", "add");
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<SourceOfFund> sourceOfFundList = sofService.getSourceOfFundList();
			model.addObject("sourceOfFundList", sourceOfFundList);
			List<SourceOfFund> railwayList = sofService.getRailwayListList();
			model.addObject("railwayList", railwayList);
		}catch (Exception e) {
				logger.error("SourceOfFund : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/get-funds", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SourceOfFund> getFundsList(@ModelAttribute SourceOfFund obj) {
		List<SourceOfFund> fundsList = null;
		try {
			fundsList = sofService.fundsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("fundsList : " + e.getMessage());
		}
		return fundsList;
	}
	
	@RequestMapping(value = "/get-funds", method = {RequestMethod.POST})
	public ModelAndView getFundsForm(@ModelAttribute SourceOfFund obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSourceOfFund);
			model.addObject("action", "edit");
			List<Project> projectsList = budgetService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<SourceOfFund> sourceOfFundList = sofService.getSourceOfFundList();
			model.addObject("sourceOfFundList", sourceOfFundList);
			List<SourceOfFund> railwayList = sofService.getRailwayListList();
			model.addObject("railwayList", railwayList);
			SourceOfFund fundDetails = sofService.getFunds(obj);
			model.addObject("fundDetails", fundDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getBudget : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-funds", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addFunds(@ModelAttribute SourceOfFund obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			 MultipartFile file = obj.getFundFile(); 
				if (null != file && !file.isEmpty()){
					String saveDirectory = CommonConstants.FUND_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
				}
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
	public ModelAndView updateFunds(@ModelAttribute SourceOfFund obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/source-of-funds");
			  MultipartFile file = obj.getFundFile(); 
				if (null != file && !file.isEmpty()){
					String saveDirectory = CommonConstants.FUND_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
				}
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
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Work");
	            headingRow.createCell((short)1).setCellValue("Source of Fund");
	         	headingRow.createCell((short)2).setCellValue("Railway");
	            headingRow.createCell((short)3).setCellValue("Funding Date");
	            headingRow.createCell((short)4).setCellValue("Fund Amount ");
	            headingRow.createCell((short)5).setCellValue("Ledger Account");
	          

	            short rowNo = 1;
	            for (SourceOfFund obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getWork_id_fk());
	                row.createCell((short)1).setCellValue(obj.getSource_of_funds_fk());
	                row.createCell((short)2).setCellValue(obj.getSub_category_railway_id_fk());
	                row.createCell((short)3).setCellValue(obj.getFunding_date());
	                row.createCell((short)4).setCellValue(obj.getFund_amount());
	                row.createCell((short)5).setCellValue(obj.getLedger_account());
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
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
