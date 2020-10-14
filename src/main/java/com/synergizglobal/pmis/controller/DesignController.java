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

import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.Work;

@Controller
public class DesignController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DesignController.class);

	@Autowired
	ContractService contractservice;
	@Autowired
	SafetyService safetyService;
	@Autowired
	DesignService designService;
	@Autowired
	HomeService homeService;
	@Autowired
	WorkService workService;
	@Autowired
	IssueService issueService;
	
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
	@RequestMapping(value="/design",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView design(@ModelAttribute Contract obj,Design design,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.designGrid);
		
		try {
			List<User> hodList = contractservice.setHodList();
			model.addObject("hodList", hodList);
			List<Design> departmentList = designService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			List<Contract> contractList = contractservice.contractList(obj);
			model.addObject("contractList", contractList);
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("design : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getDesigns", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDesigns(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDesigns(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("getDesigns : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/get-design", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDesign(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDesign);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList();	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);

			Design designDetails = designService.getDesignDetails(obj);
			model.addObject("designDetails", designDetails);
			 
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("getDesign : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-design-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDesignForm(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDesign);	
			model.addObject("action", "add");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList();	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);
			
		}catch (Exception e) {
			logger.info("addDesignForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/add-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/design");
			
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));
			
			MultipartFile file = obj.getDesignFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.DESIGN_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}
			boolean flag =  designService.addDesign(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Design Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Design is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Design is failed. Try again.");
			logger.info("addDesign : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/design");
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));

			MultipartFile file = obj.getDesignFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.DESIGN_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}
			boolean flag =  designService.updateDesign(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Design Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Design is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Design is failed. Try again.");
			logger.info("updateDesign : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-design", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafety(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Design design,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.designGrid);
		List<Design> dataList = new ArrayList<Design>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/design");
			dataList = designService.getDesigns(design);  
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet();
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("Contract");
			            headingRow.createCell((short)1).setCellValue("Title");
			            headingRow.createCell((short)2).setCellValue("Structure");
			            headingRow.createCell((short)3).setCellValue("Drawing Type");
			            headingRow.createCell((short)5).setCellValue("Contractor Drawing No");
			            headingRow.createCell((short)6).setCellValue("MRVC Drawing No");
			            headingRow.createCell((short)7).setCellValue("Division Drawing No");
			            headingRow.createCell((short)8).setCellValue("HQ Drawing No");

			            short rowNo = 1;
			            for (Design obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getContract_id_fk());
			                row.createCell((short)1).setCellValue(obj.getDrawing_title());
			                row.createCell((short)2).setCellValue(obj.getStructure_type_fk());
			                row.createCell((short)3).setCellValue(obj.getDrawing_type_fk());
			                row.createCell((short)5).setCellValue(obj.getContractor_drawing_no());
			                row.createCell((short)6).setCellValue(obj.getMrvc_drawing_no());
			                row.createCell((short)7).setCellValue(obj.getDivision_drawing_no());
			                row.createCell((short)8).setCellValue(obj.getHq_drawing_no());
			                rowNo++;
			            }
		                
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Design_"+dateFormat.format(date);
		                
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
			logger.error("exportDesign : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
}