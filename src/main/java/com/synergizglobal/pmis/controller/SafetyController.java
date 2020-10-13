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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Safety;

@Controller
public class SafetyController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyController.class);	
	
	@Autowired
	SafetyService safetyService;
	
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
	
	@RequestMapping(value="/safety",method=RequestMethod.GET)
	public ModelAndView safety(@ModelAttribute Safety obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.safetyGrid);
			/*List<Safety> safety = safetyService.getSafetyList(obj);
			model.addObject("safety", safety);*/
			List<Safety> contracts = safetyService.getContractsListFromSafety();
			model.addObject("contracts", contracts);
			
			List<Safety> departments = safetyService.getDepartmentsListFromSafety();
			model.addObject("departments", departments);
			
			List<Safety> categorys = safetyService.getCategoryListFromSafety();
			model.addObject("categorys", categorys);
			
			List<Safety> statuses = safetyService.getStatusListFromSafety();
			model.addObject("statuses", statuses);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("safety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getSafetyList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getSafetyList(@ModelAttribute Safety obj) {
		List<Safety> safetyList = null;
		try {
			safetyList = safetyService.getSafetyList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getSafetyList : " + e.getMessage());
		}
		return safetyList;
	}
	
	
	@RequestMapping(value="/add-safety-form",method=RequestMethod.GET)
	public ModelAndView addSafetyForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addSafetyForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("addSafetyForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-safety",method=RequestMethod.POST)
	public ModelAndView addSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setClosure_date(DateParser.parse(obj.getClosure_date()));
			obj.setInvestigation_completed(DateParser.parse(obj.getInvestigation_completed()));			
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			
			MultipartFile file = obj.getSafetyFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}
			
			boolean flag = safetyService.addSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding safety is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.info("addSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateSafetyForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety/{safety_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafety(@ModelAttribute Safety obj,@PathVariable("safety_id") String safety_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateSafetyForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			obj.setSafety_id(safety_id);
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-safety",method=RequestMethod.POST)
	public ModelAndView updateSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setClosure_date(DateParser.parse(obj.getClosure_date()));
			obj.setInvestigation_completed(DateParser.parse(obj.getInvestigation_completed()));			
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			
			MultipartFile file = obj.getSafetyFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}
			
			boolean flag = safetyService.updateSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("updateSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-safety",method=RequestMethod.POST)
	public ModelAndView deleteSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			boolean flag = safetyService.deleteSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("deleteSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-safety", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafety(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Safety safety,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.safetyGrid);
		List<Safety> dataList = new ArrayList<Safety>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/safety");
			dataList = safetyService.getSafetyList(safety);  
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet();
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("Safety ID");
			            headingRow.createCell((short)1).setCellValue("Project ID");
			            headingRow.createCell((short)2).setCellValue("Work ID");
			            headingRow.createCell((short)3).setCellValue("Contract ID");
			            headingRow.createCell((short)5).setCellValue("Title");
			            headingRow.createCell((short)6).setCellValue("Date");
			            headingRow.createCell((short)7).setCellValue("Location");
			            headingRow.createCell((short)8).setCellValue("Reported By");
			            headingRow.createCell((short)9).setCellValue("Responsible Person");
			            headingRow.createCell((short)10).setCellValue("Department");
			            headingRow.createCell((short)11).setCellValue("Category");
			            headingRow.createCell((short)12).setCellValue("Status");
			            short rowNo = 1;
			            for (Safety obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getSafety_id());
			                row.createCell((short)1).setCellValue(obj.getProject_id_fk());
			                row.createCell((short)2).setCellValue(obj.getWork_id_fk());
			                row.createCell((short)3).setCellValue(obj.getContract_id_fk());
			                row.createCell((short)5).setCellValue(obj.getTitle());
			                row.createCell((short)6).setCellValue(obj.getDate());
			                row.createCell((short)7).setCellValue(obj.getLocation());
			                row.createCell((short)8).setCellValue(obj.getReported_by());
			                row.createCell((short)9).setCellValue(obj.getResponsible_person());
			                row.createCell((short)10).setCellValue(obj.getDepartment_fk());
			                row.createCell((short)11).setCellValue(obj.getCategory_fk());
			                row.createCell((short)12).setCellValue(obj.getStatus_fk());
			                
			                rowNo++;
			            }
		                
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Safety_"+dateFormat.format(date);
		                
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
			logger.error("exportSafety : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
}
