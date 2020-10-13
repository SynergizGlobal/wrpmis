
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

@Controller
public class WorkController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkController.class);
	
	@Autowired
	WorkService workService;
	
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
	
	
	@RequestMapping(value="/work",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Work(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.work);
		try {
			List<Work> workList = workService.getWorkList(null);
			model.addObject("workList", workList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/get-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkDetails(@ModelAttribute Work work,Railway rail){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			workId= work.getWork_id();
			Work workDeatils = workService.getWork(workId, work);
			model.addObject("workDeatils", workDeatils);
		}catch (Exception e) {
			logger.info("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-Work-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWorkForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "add");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);

		}catch (Exception e) {
				logger.info("Work : " + e.getMessage());
			}
			return model;
	 }
	
	
	@RequestMapping(value = "/update-Work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			MultipartFile file = work.getWorkFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.WORK_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				work.setAttachment(fileName);
			}				
			boolean flag =  workService.updateWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			logger.info("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-Work", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWork(@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			MultipartFile file = work.getWorkFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.WORK_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				work.setAttachment(fileName);
			}
			boolean flag =  workService.addWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			logger.info("addWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteRow", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteRow(@ModelAttribute Work work){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName("redirect:/work");
			workId = work.getWork_id();
			boolean flag =  workService.deleteRow(workId,work);
		}catch (Exception e) {
			logger.info("Work : " + e.getMessage());
		}
		return model;
	
	}
	
	
	@RequestMapping(value = "/export-work", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportWork(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.work);
		List<Work> dataList = new ArrayList<Work>();
		try {
			view.setViewName("redirect:/work");
			dataList = workService.getWorkList(work); 
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Project ID");
	            headingRow.createCell((short)1).setCellValue("Project Name");
	         	headingRow.createCell((short)2).setCellValue("Work ID");
	            headingRow.createCell((short)3).setCellValue("Work Name");
	            headingRow.createCell((short)4).setCellValue("Sanctioned Year");
	            headingRow.createCell((short)5).setCellValue("Railway Agency");
	            headingRow.createCell((short)6).setCellValue("Executed By");
	            headingRow.createCell((short)7).setCellValue("Remarks");
	            short rowNo = 1;
	            for (Work obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getProject_id_fk());
	                row.createCell((short)1).setCellValue(obj.getProject_name());
	                row.createCell((short)2).setCellValue(obj.getWork_id());
	                row.createCell((short)3).setCellValue(obj.getWork_name());
	                row.createCell((short)4).setCellValue(obj.getSanctioned_year());
	                row.createCell((short)5).setCellValue(obj.getRailway_name());
	                row.createCell((short)6).setCellValue(obj.getExecuted_by_id_fk());
	                row.createCell((short)7).setCellValue(obj.getRemarks());
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Work_"+dateFormat.format(date);
                
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
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);			
		}
		
	}

}
	
	
	
	
	
	
	
	
	