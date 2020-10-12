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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.Iservice.ContractorService;
import com.synergizglobal.pmis.model.Contractor;


@Controller
public class ContractorController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ContractorController.class);

	@Autowired
	ContractorService contractorService;
	
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

	@RequestMapping(value="/contractor",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Contractor(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.contractorGrid);
		try {
			List<Contractor> contractorsList = contractorService.getContractorsList();
			model.addObject("contractorsList", contractorsList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Contractor : " + e.getMessage());
		}
		return model;
	}
		
	@RequestMapping(value = "/add-contractor-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractorForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditContractor);
			model.addObject("action", "add");
			List<Contractor> Specialization = contractorService.getContractorSpecialization();
			model.addObject("Specialization", Specialization);
		}catch (Exception e) {
				logger.info("Contractor : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getContractorForm(@ModelAttribute Contractor obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditContractor);
			model.addObject("action", "edit");
			Contractor contractorDetails = contractorService.getContractor(obj);
			model.addObject("contractorDetails", contractorDetails);
			List<Contractor> Specialization = contractorService.getContractorSpecialization();
			model.addObject("Specialization", Specialization);
		}catch (Exception e) {
				e.printStackTrace();
				logger.info("Contractor : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractor(@ModelAttribute Contractor obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor");
			boolean flag =  contractorService.addContractor(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding Contractor is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contractor is failed. Try again.");
			logger.info("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateContractor(@ModelAttribute Contractor obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor");
			boolean flag =  contractorService.updateContractor(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Contractor is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Contractor is failed. Try again.");
			logger.info("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteContractor(@ModelAttribute Contractor obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor");
			boolean flag =  contractorService.deleteContractorRow(obj);
		}catch (Exception e) {
			logger.info("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportContractor(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Contractor contractor,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.contractorGrid);
		List<Contractor> dataList = new ArrayList<Contractor>();
		try {
			view.setViewName("redirect:/contractor");
			dataList =  contractorService.getContractorsList();
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Contractor ID");
	            headingRow.createCell((short)1).setCellValue("Contractor Name");
	         	headingRow.createCell((short)2).setCellValue("Specialization");
	            headingRow.createCell((short)3).setCellValue("Address");
	            headingRow.createCell((short)4).setCellValue("Remarks");
	            short rowNo = 1;
	            for (Contractor obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getContractor_id());
	                row.createCell((short)1).setCellValue(obj.getContractor_name());
	                row.createCell((short)2).setCellValue(obj.getContractor_specilization_fk());
	                row.createCell((short)3).setCellValue(obj.getAddress());
	                row.createCell((short)4).setCellValue(obj.getRemarks());
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Contractor_"+dateFormat.format(date);
                
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

