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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.UserService;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.User;

@Controller
public class UserController {
	Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
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
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ModelAndView users(@ModelAttribute User obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.usersGrid);
			
			List<User> roles = userService.getUserRoles();
			model.addObject("roles", roles);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> reportingToList = userService.getUserReportingToList();
			model.addObject("reportingToList", reportingToList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("users : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getUsersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUsersList(@ModelAttribute User obj) {
		List<User> users = null;
		try {
			users = userService.getUsersList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getUsersList : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value="/add-user-form",method=RequestMethod.GET)
	public ModelAndView addUserForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditUser);
			
			model.addObject("action", "add");
			
			List<User> roles = userService.getUserRoles();
			model.addObject("roles", roles);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> reportingToList = userService.getUsersList(null);
			model.addObject("reportingToList", reportingToList);
			
			List<User> userAccessTypes = userService.getUserAccessTypes(null);
			model.addObject("userAccessTypes", userAccessTypes);
			
		} catch (Exception e) {
			logger.info("addUserForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getContractsForUserAccessTypes", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getContractsForUserAccessTypes(@ModelAttribute User obj) {
		List<User> contracts = null;
		try {
			contracts = userService.getContractsForUserAccessTypes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getContractsForUserAccessTypes : " + e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsForUserAccessTypes", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getDepartmentsForUserAccessTypes(@ModelAttribute User obj) {
		List<User> departments = null;
		try {
			departments = userService.getDepartmentsForUserAccessTypes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getDepartmentsForUserAccessTypes : " + e.getMessage());
		}
		return departments;
	}
	
	@RequestMapping(value = "/ajax/getModulesForUserAccessTypes", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getModulesForUserAccessTypes(@ModelAttribute User obj) {
		List<User> modules = null;
		try {
			modules = userService.getModulesForUserAccessTypes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getModulesForUserAccessTypes : " + e.getMessage());
		}
		return modules;
	}
	
	@RequestMapping(value = "/ajax/getWorksForUserAccessTypes", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getWorksForUserAccessTypes(@ModelAttribute User obj) {
		List<User> works = null;
		try {
			works = userService.getWorksForUserAccessTypes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getWorksForUserAccessTypes : " + e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value="/add-user",method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/users");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			boolean flag = userService.addUser(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding user is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("addUser : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-user",method=RequestMethod.POST)
	public ModelAndView getUser(@ModelAttribute User obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditUser);
			
			model.addObject("action", "edit");
			
			List<User> roles = userService.getUserRoles();
			model.addObject("roles", roles);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> reportingToList = userService.getUsersList(null);
			model.addObject("reportingToList", reportingToList);
			
			List<User> userAccessTypes = userService.getUserAccessTypes(null);
			model.addObject("userAccessTypes", userAccessTypes);
			
			User user = userService.getUser(obj);			
			model.addObject("usrObj", user);
			
			List<User> contractsForAccess = userService.getContractsForUserAccessTypes(obj);
			model.addObject("contractsForAccess", contractsForAccess);
			
			List<User> departmentsForAccess = userService.getDepartmentsForUserAccessTypes(obj);
			model.addObject("departmentsForAccess", departmentsForAccess);
			
			List<User> modulesForAccess = userService.getModulesForUserAccessTypes(obj);
			model.addObject("modulesForAccess", modulesForAccess);
			
			List<User> worksForAccess = userService.getWorksForUserAccessTypes(obj);
			model.addObject("worksForAccess", worksForAccess);
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.info("getUser : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-user",method=RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/users");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			boolean flag = userService.updateUser(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating user is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.info("updateUser : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-user",method=RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute User obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/users");
			boolean flag = userService.deleteUser(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "User deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting user is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("deleteUser : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-users", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportUsers(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute User user,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants2.usersGrid);
		List<User> dataList = new ArrayList<User>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/users");
			dataList = userService.getUsersList(user);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet();
	            XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("User ID");
	            headingRow.createCell((short)1).setCellValue("User Name");
	            headingRow.createCell((short)2).setCellValue("Department");
	            headingRow.createCell((short)3).setCellValue("Reporting to");
	            headingRow.createCell((short)4).setCellValue("Role");
	            short rowNo = 1;
	            for (User obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getUser_id());
	                row.createCell((short)1).setCellValue(obj.getUser_name());
	                row.createCell((short)2).setCellValue(obj.getDepartment_fk());
	                row.createCell((short)3).setCellValue(obj.getReporting_to_name());
	                row.createCell((short)4).setCellValue(obj.getUser_role_name_fk());
	                
	                rowNo++;
	            }
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "User_"+dateFormat.format(date);
                
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
			logger.error("exportUsers : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
}
