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

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Issue;

@Controller
public class IssueController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(IssueController.class);	
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	StripChartService stripChartService;
	
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
	
	@RequestMapping(value="/issues",method=RequestMethod.GET)
	public ModelAndView issues(@ModelAttribute Issue obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.issuesGrid);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("issues : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getWorksListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getWorksListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getContractsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getDepartmentsListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getCategoryListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getCategoryListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getResponsiblePersonsListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getResponsiblePersonsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonsListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusListFilterInIssue", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getStatusListFilterInIssue(@ModelAttribute Issue obj) {
		List<Issue> objList = null;
		try {
			objList = issueService.getStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusListFilterInIssue : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesList(@ModelAttribute Issue obj) {
		List<Issue> issues = null;
		try {
			issues = issueService.getIssuesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getIssuesList : " + e.getMessage());
		}
		return issues;
	}
	
	@RequestMapping(value="/add-issue-form",method=RequestMethod.GET)
	public ModelAndView addIssueForm(HttpSession session,@ModelAttribute Issue obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addIssueForm);
			
			List<Issue> projectsList = issueService.getProjectsListForIssueForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Issue> worksList = issueService.getWorkListForIssueForm(obj);
			model.addObject("worksList", worksList);
			
			List<Issue> contractsList = issueService.getContractsListForIssueForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
		} catch (Exception e) {
			logger.error("addIssueForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getProjectsListForIssueForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getProjectsListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getWorkListForIssueForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getWorkListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForIssuesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListForIssueForm(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getContractsListForIssueForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForIssueForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-issue",method=RequestMethod.POST)
	public ModelAndView addIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/issues");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));			
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			
			boolean flag = issueService.addIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding issue is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("addIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-issue",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateIssueForm);
			
			List<Issue> projectsList = issueService.getProjectsListForIssueForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Issue> worksList = issueService.getWorkListForIssueForm(obj);
			model.addObject("worksList", worksList);
			
			List<Issue> contractsList = issueService.getContractsListForIssueForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Issue> railwayList = issueService.getRailwayList();
			model.addObject("railwayList", railwayList);
			
			Issue issue = issueService.getIssue(obj);
			model.addObject("issue", issue);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-issue/{issue_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getIssueByIssueId(@ModelAttribute Issue obj,@PathVariable("issue_id") String issue_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateIssueForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Issue> issuesStatusList = issueService.getIssuesStatusList();
			model.addObject("issuesStatusList", issuesStatusList);
			
			List<Issue> issuesPriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuesPriorityList", issuesPriorityList);
			
			List<Issue> issuesCategoryList = issueService.getIssuesCategoryList();
			model.addObject("issuesCategoryList", issuesCategoryList);
			
			List<Issue> departmentList = issueService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			obj.setIssue_id(issue_id);
			Issue issue = issueService.getIssue(obj);
			model.addObject("issue", issue);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-issue",method=RequestMethod.POST)
	public ModelAndView updateIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/issues");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setResolved_date(DateParser.parse(obj.getResolved_date()));
			obj.setEscalation_date(DateParser.parse(obj.getEscalation_date()));
			
			boolean flag = issueService.updateIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating issue is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-issue",method=RequestMethod.POST)
	public ModelAndView deleteIssue(@ModelAttribute Issue obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/issues");
			boolean flag = issueService.deleteIssue(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Issue deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting issue is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("deleteIssue : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-issues", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportIssues(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Issue issue,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.issuesGrid);
		List<Issue> dataList = new ArrayList<Issue>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/issues");
			dataList = issueService.getIssuesList(issue);  
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet =  workBook.createSheet(WorkbookUtil.createSafeSheetName("Issues"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("Issue ID");
			            headingRow.createCell((short)1).setCellValue("Project ID");
			            headingRow.createCell((short)2).setCellValue("Work ID");
			            headingRow.createCell((short)3).setCellValue("Contract ID");
			            headingRow.createCell((short)4).setCellValue("Activity");
			            headingRow.createCell((short)5).setCellValue("Title");
			            headingRow.createCell((short)6).setCellValue("Description");
			            headingRow.createCell((short)7).setCellValue("Date");
			            headingRow.createCell((short)8).setCellValue("Location");
			            headingRow.createCell((short)9).setCellValue("Latitude");
			            headingRow.createCell((short)10).setCellValue("Longitude");
			            headingRow.createCell((short)11).setCellValue("Reported By");
			            headingRow.createCell((short)12).setCellValue("Responsible Person");
			            headingRow.createCell((short)13).setCellValue("Department");
			            headingRow.createCell((short)14).setCellValue("Issue Category");
			            headingRow.createCell((short)15).setCellValue("Issue Status");
			            headingRow.createCell((short)16).setCellValue("Zonal Railway");
			            headingRow.createCell((short)17).setCellValue("Priority");
			            headingRow.createCell((short)18).setCellValue("Corrective Measure");
			            headingRow.createCell((short)19).setCellValue("Resolved Date");
			            headingRow.createCell((short)20).setCellValue("Escalated to");
			            headingRow.createCell((short)21).setCellValue("remarks");
			            short rowNo = 1;
			            for (Issue obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getIssue_id());
			                row.createCell((short)1).setCellValue(obj.getProject_id_fk());
			                row.createCell((short)2).setCellValue(obj.getWork_id_fk());
			                row.createCell((short)3).setCellValue(obj.getContract_id_fk());
			                row.createCell((short)4).setCellValue(obj.getActivity());
			                row.createCell((short)5).setCellValue(obj.getTitle());
			                row.createCell((short)6).setCellValue(obj.getDescription());
			                row.createCell((short)7).setCellValue(obj.getDate());
			                row.createCell((short)8).setCellValue(obj.getLocation());
			                row.createCell((short)9).setCellValue(obj.getLatitude());
			                row.createCell((short)10).setCellValue(obj.getLongitude());
			                row.createCell((short)11).setCellValue(obj.getReported_by());
			                row.createCell((short)12).setCellValue(obj.getResponsible_person());
			                row.createCell((short)13).setCellValue(obj.getDepartment_name());
			                row.createCell((short)14).setCellValue(obj.getCategory_fk());
			                row.createCell((short)15).setCellValue(obj.getStatus_fk());
			                row.createCell((short)16).setCellValue(obj.getZonal_railway_fk());
			                row.createCell((short)17).setCellValue(obj.getPriority_fk());
			                row.createCell((short)18).setCellValue(obj.getCorrective_measure());
			                row.createCell((short)19).setCellValue(obj.getResolved_date());
			                row.createCell((short)20).setCellValue(obj.getEscalated_to());
			                row.createCell((short)21).setCellValue(obj.getRemarks());
			                
			                rowNo++;
			            }
			            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Issues_"+dateFormat.format(date);
		                
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
			logger.error("exportIssues : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
}
