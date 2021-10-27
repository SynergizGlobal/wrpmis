package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.UserService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.User;

@Controller
public class UserController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	
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
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ModelAndView users(@ModelAttribute User obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.usersGrid);
			
			/*List<User> roles = userService.getUserRoles();
			model.addObject("roles", roles);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> reportingToList = userService.getUserReportingToList();
			model.addObject("reportingToList", reportingToList);*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("users : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getUserTypesFilterInUser", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUserTypesFilterInUser(@ModelAttribute User obj) {
		List<User> users = null;
		try {
			users = userService.getUserTypesFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserTypesFilterInUser : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value = "/ajax/getUserRolesFilterInUser", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUserRolesFilterInUser(@ModelAttribute User obj) {
		List<User> users = null;
		try {
			users = userService.getUserRolesFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserRolesFilterInUser : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value = "/ajax/getUserDepartmentsFilterInUser", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUserDepartmentsFilterInUser(@ModelAttribute User obj) {
		List<User> users = null;
		try {
			users = userService.getUserDepartmentsFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserDepartmentsFilterInUser : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value = "/ajax/getUserReportingToListFilterInUser", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUserReportingToListFilterInUser(@ModelAttribute User obj) {
		List<User> users = null;
		try {
			users = userService.getUserReportingToListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserReportingToListFilterInUser : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value = "/ajax/getUsersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUsersList(@ModelAttribute User obj) {
		List<User> users = null;
		//List<User> usersExport = null;
		try {
			users = userService.getUsersList(obj);
			//usersExport = userService.getUsersExportList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUsersList : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value = "/ajax/checkPMISKeyAvailability", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User checkPMISKeyAvailability(@ModelAttribute User obj) {
		String pmis_key = null;
		User dObj = new User();
		try {
			pmis_key = userService.checkPMISKeyAvailability(obj);
			dObj.setKeyAvailability(pmis_key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("checkPMISKeyAvailability : " + e.getMessage());
		}
		return dObj;
	}
	
	@RequestMapping(value = "/ajax/getUserReportingToList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUserReportingToList(@ModelAttribute User obj) {
		List<User> reportingToList = null;
		try {
			reportingToList = userService.getUserReportingToList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserReportingToList : " + e.getMessage());
		}
		return reportingToList;
	}
	
	@RequestMapping(value="/add-user-form",method=RequestMethod.GET)
	public ModelAndView addUserForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditUser);
			
			model.addObject("action", "add");
			
			List<User> roles = userService.getUserRoles();
			model.addObject("roles", roles);
			
			List<User> types = userService.getUserTypes();
			model.addObject("types", types);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> reportingToList = userService.getUserReportingToList(null);
			model.addObject("reportingToList", reportingToList);
			
			List<User> userAccessTypes = userService.getUserAccessTypes(null);
			model.addObject("userAccessTypes", userAccessTypes);
			
			List<User> pmisKeys = userService.getPmisKeys();
			model.addObject("pmisKeys", pmisKeys);
			
		} catch (Exception e) {
			logger.error("addUserForm : " + e.getMessage());
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
			logger.error("getContractsForUserAccessTypes : " + e.getMessage());
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
			logger.error("getDepartmentsForUserAccessTypes : " + e.getMessage());
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
			logger.error("getModulesForUserAccessTypes : " + e.getMessage());
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
			logger.error("getWorksForUserAccessTypes : " + e.getMessage());
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
			
			String fileDirectory = CommonConstants2.USER_IMAGE_SAVING_PATH ;
			MultipartFile file = obj.getFileName();
			if (null != file && !file.isEmpty()){
				String fileName = file.getOriginalFilename();
				obj.setUser_image(fileName);
			}
			String userId = userService.addUser(obj);
			if(!StringUtils.isEmpty(userId)) {
				if (null != file && !file.isEmpty()){
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, fileDirectory, fileName);
				}
				attributes.addFlashAttribute("success", "User added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding user is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addUser : " + e.getMessage());
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
			
			List<User> types = userService.getUserTypes();
			model.addObject("types", types);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> userAccessTypes = userService.getUserAccessTypes(null);
			model.addObject("userAccessTypes", userAccessTypes);
			
			List<User> pmisKeys = userService.getPmisKeys();
			model.addObject("pmisKeys", pmisKeys);
			
			List<User> contractsForAccess = userService.getContractsForUserAccessTypes(obj);
			model.addObject("contractsForAccess", contractsForAccess);
			
			List<User> departmentsForAccess = userService.getDepartmentsForUserAccessTypes(obj);
			model.addObject("departmentsForAccess", departmentsForAccess);
			
			List<User> modulesForAccess = userService.getModulesForUserAccessTypes(obj);
			model.addObject("modulesForAccess", modulesForAccess);
			
			List<User> worksForAccess = userService.getWorksForUserAccessTypes(obj);
			model.addObject("worksForAccess", worksForAccess);
			
			User user = userService.getUser(obj);			
			model.addObject("usrObj", user);
			
			List<User> reportingToList = userService.getUserReportingToList(user);
			model.addObject("reportingToList", reportingToList);
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("getUser : " + e.getMessage());
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
			String fileDirectory = CommonConstants2.USER_IMAGE_SAVING_PATH ;
			MultipartFile file = obj.getFileName();
			if (null != file && !file.isEmpty()){
				String fileName = file.getOriginalFilename();
				obj.setUser_image(fileName);
			}
			
			boolean flag = userService.updateUser(obj);
			if(flag) {
				if (null != file && !file.isEmpty()){
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, fileDirectory, fileName);
				}
				attributes.addFlashAttribute("success", "User updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating user is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateUser : " + e.getMessage());
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
			logger.error("deleteUser : " + e.getMessage());
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
			dataList = userService.getUsersExportList(user);  
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("User"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Calibri";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Calibri";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
	            XSSFRow headingRow = sheet.createRow(0);
	            String headerString = "User ID^User Name^Designation^Department^Reporting to^Email-Id^Mobile Number^User Type^Role";
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(yellowStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            short rowNo = 1;
	            for (User obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getUser_id());
	                row.createCell((short)1).setCellValue(obj.getUser_name());
	                row.createCell((short)2).setCellValue(obj.getDesignation());
	                row.createCell((short)3).setCellValue(obj.getDepartment_name());
	                row.createCell((short)4).setCellValue(obj.getReporting_to_designation());
	                
	                row.createCell((short)5).setCellValue(obj.getEmail_id());
	                row.createCell((short)6).setCellValue(obj.getMobile_number());
	                row.createCell((short)7).setCellValue(obj.getUser_type_fk());
	                row.createCell((short)8).setCellValue(obj.getUser_role_name_fk());
	                
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
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
	private CellStyle cellFormating(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
		CellStyle style = workBook.createCellStyle();
		//Setting Background color  
		//style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (style instanceof XSSFCellStyle) {
		   XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle)style;
		   xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		//style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);
		
		Font font = workBook.createFont();
        //font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontHeightInPoints((short)fontSize);  
        font.setFontName(fontName);  //"Times New Roman"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
	
	@RequestMapping(value = "/upload-users", method = {RequestMethod.POST})
	public ModelAndView uploadUsers(@ModelAttribute User user,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String fileName = null;
		String userId = null;String userName = null;
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/users");
			
			if(!StringUtils.isEmpty(user.getFileName())){
				MultipartFile multipartFile = user.getFileName();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0){					
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							int sheetNo =0;
							if(sheetsCount >1) {sheetNo =1;}
							uploadFilesSheet = workbook.getSheetAt(sheetNo);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet.getRow(0);
							//checking given file format
							if(headerRow != null){
								List<String> fileFormat = FileFormatModel.getUserFileFormat();;	
								int noOfColumns = headerRow.getLastCellNum();
								int noOfrOWs = uploadFilesSheet.getLastRowNum();
								if(noOfColumns == fileFormat.size()){
									for (int i = 0; i < fileFormat.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
					                		attributes.addFlashAttribute("error",uploadformatError);
					                		return model;
					                	}
									}
								}else{
									attributes.addFlashAttribute("error",uploadformatError);
			                		return model;
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
		                		return model;
							}
							String errorRows = "";int rowNo = 1,errorNo = 0;
							for(int i = 1; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								DataFormatter formatter = new DataFormatter(); 
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
								user.setReporting_to_id_srfk(formatter.formatCellValue(row.getCell(4)).trim());	
								List<User> error_rows = userService.getReportingToUserId(user.getReporting_to_id_srfk());
								rowNo++;
								if(error_rows.size() > 1) {
									errorRows = errorRows+ ","+rowNo;
									errorNo++;
								}
							}
							int count = uploadUsers(user,userId,userName,workbook,sheetNo);
							attributes.addFlashAttribute("success", count + " Users added successfully.");	
							if(errorNo >0) {
								errorRows = String.join(",",Arrays.asList(errorRows.split(",")).stream().distinct().collect(Collectors.toList()));
								errorRows  = errorRows.replaceAll("(^(\\s*?\\,+)+\\s?)", "");
								errorRows = "<br><span style='color:red;'>Error occurs at "+ errorRows + " Rows, Conflict with same Designation for multiple Reporting to Users </span> ";
								attributes.addFlashAttribute("error",errorRows);	
							}
						}
					}
					
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method uploadUsers() is called when user upload the file
	 * 
	 * @param obj is object for the model class User.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @param sheetNo 
	 * @return type of this method is count.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	public int uploadUsers(User obj, String userId, String userName, XSSFWorkbook workbook, int sheetNo) throws Exception {
		User user = null;
		List<User> usersList = new ArrayList<User>();
		
		//XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {	
			/*List<String> fileFormat = null;				
			fileFormat = FileFormatModel.getActivityFileFormat();*/
			
			MultipartFile excelfile = obj.getFileName();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile){
				 String fileName = excelfile.getOriginalFilename();
				 String fileType = FilenameUtils.getExtension(fileName);
				 
				 if(excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(sheetNo);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
							
							for(int i = 1; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								user = new User();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									user.setUser_id(formatter.formatCellValue(row.getCell(0)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									user.setUser_name(formatter.formatCellValue(row.getCell(1)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									user.setDesignation(formatter.formatCellValue(row.getCell(2)).trim());	
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									user.setDepartment_name(formatter.formatCellValue(row.getCell(3)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									user.setReporting_to_id_srfk(formatter.formatCellValue(row.getCell(4)).trim());	
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									user.setEmail_id(formatter.formatCellValue(row.getCell(5)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
									user.setMobile_number(formatter.formatCellValue(row.getCell(6)).trim());
								

								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(7)).trim()))
									user.setUser_type_fk(formatter.formatCellValue(row.getCell(7)).trim());	
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(8)).trim()))
									user.setUser_role_name_fk(formatter.formatCellValue(row.getCell(8)).trim());	
						

								/*List<User> pObjList = new ArrayList<User>();
								if(!StringUtils.isEmpty(user.getDesignation())) {
									XSSFSheet uploadFilesSheet2 = workbook.getSheetAt(3);
									for(int j = 2; j<= uploadFilesSheet2.getLastRowNum();j++){
										XSSFRow row2 = uploadFilesSheet2.getRow(j);
										// Sets the Read data to the model class
										DataFormatter formatter2 = new DataFormatter(); //creating formatter using the default locale
										// Cell cell = row.getCell(0);
										// String j_username = formatter.formatCellValue(row.getCell(0));
										
										User pObj = new User();
										
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(0)).trim()))
											pObj.setDesignation(formatter2.formatCellValue(row2.getCell(0)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(1)).trim()))
											pObj.setUser_access_type(formatter2.formatCellValue(row2.getCell(1)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(2)).trim()))
											pObj.setAccess_value(formatter2.formatCellValue(row2.getCell(2)).trim());								
										
										if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getDesignation()) 
												&& !StringUtils.isEmpty(pObj.getUser_access_type())
												&& !StringUtils.isEmpty(pObj.getAccess_value())
												&& pObj.getDesignation().equals(user.getDesignation()))
											pObjList.add(pObj);
									}
									user.setUserPermissions(pObjList);
								}
								*/
								
								if(!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getUser_name()) && !StringUtils.isEmpty(user.getDepartment_name())) {
									usersList.add(user);
								}
							}
							
							if(!usersList.isEmpty() && usersList != null){
								count  = userService.uploadUsers(usersList);
							}
						}
						workbook.close();
					}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadUsers() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadUsers() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
}
