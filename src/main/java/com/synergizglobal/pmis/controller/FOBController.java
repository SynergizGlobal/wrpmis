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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.FOBService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Project;

@Controller
public class FOBController {
	Logger logger = Logger.getLogger(FOBController.class);	
	
	@Autowired
	FOBService fobService;
	
	@Autowired
	StripChartService stripChartService;
	
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
			List<FOB> contracts = fobService.contractListFromFOB();
			model.addObject("contracts", contracts);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
		} catch (Exception e) {
			logger.info("fob : " + e.getMessage());
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
			logger.info("getFOBList : " + e.getMessage());
		}
		return fobs;
	}
	
	@RequestMapping(value="/add-fob-form",method=RequestMethod.GET)
	public ModelAndView addFOBForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditFob);
			
			model.addObject("action", "add");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
		} catch (Exception e) {
			logger.info("addFOBForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-fob",method=RequestMethod.POST)
	public ModelAndView addFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/fob");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			if(!StringUtils.isEmpty(obj.getTarget_date())){
				Date convertedDate = sdf.parse(obj.getTarget_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setTarget_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getConstruction_start_date())){
				Date convertedDate = sdf.parse(obj.getConstruction_start_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setConstruction_start_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getCommissioning_date())){
				Date convertedDate = sdf.parse(obj.getCommissioning_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setCommissioning_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getActual_completion_date())){
				Date convertedDate = sdf.parse(obj.getActual_completion_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setActual_completion_date(currentDate);
			}
			
			boolean flag = fobService.addFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding fob is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("addFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-fob",method=RequestMethod.POST)
	public ModelAndView getFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditFob);
			
			model.addObject("action", "edit");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
			FOB fob = fobService.getFOB(obj);
			model.addObject("fob", fob);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.info("getFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-fob",method=RequestMethod.POST)
	public ModelAndView updateFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/fob");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			if(!StringUtils.isEmpty(obj.getTarget_date())){
				Date convertedDate = sdf.parse(obj.getTarget_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setTarget_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getConstruction_start_date())){
				Date convertedDate = sdf.parse(obj.getConstruction_start_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setConstruction_start_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getCommissioning_date())){
				Date convertedDate = sdf.parse(obj.getCommissioning_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setCommissioning_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getActual_completion_date())){
				Date convertedDate = sdf.parse(obj.getActual_completion_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setActual_completion_date(currentDate);
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
			logger.info("updateFOB : " + e.getMessage());
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
			logger.info("deleteFOB : " + e.getMessage());
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
	            XSSFSheet sheet = workBook.createSheet();
	            XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("FOB ID");
	            headingRow.createCell((short)1).setCellValue("Work ID");
	            headingRow.createCell((short)2).setCellValue("Contract ID");
	            headingRow.createCell((short)3).setCellValue("FOB Name");
	            headingRow.createCell((short)4).setCellValue("Work Status");
	            short rowNo = 1;
	            for (FOB obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getFob_id());
	                row.createCell((short)1).setCellValue(obj.getWork_id_fk());
	                row.createCell((short)2).setCellValue(obj.getContract_id_fk());
	                row.createCell((short)3).setCellValue(obj.getFob_name());
	                row.createCell((short)4).setCellValue(obj.getWork_status_fk());
	                
	                rowNo++;
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
