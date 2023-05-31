package com.synergizglobal.pmis.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.mail.iap.ResponseHandler;
import com.synergizglobal.pmis.Iservice.StructureGalleryPageService;
import com.synergizglobal.pmis.Iservice.UAVService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.UAVPaginationObject;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.P6Data;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.UAV;
import com.synergizglobal.pmis.model.User;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UAVController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UAVController.class);

	
	@Autowired
	UAVService UAVService;
	
	@Autowired
	StructureGalleryPageService service;	
	

	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value = "/upload_video", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadVideoFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			  File uploads = new File(CommonConstants2.DRONE_SURVEY_SAVING_PATH);
			  
			  String multipartContentType = "multipart/form-data";
			  String fieldname = "file";
			  Part filePart = request.getPart(fieldname);
			  
			  // Create path components to save the file.
			  Map < Object, Object > responseData;
			  final PrintWriter writer = response.getWriter();
			  String linkName = null;
			  
			  try {
			     // Check content type.
			     if (request.getContentType() == null ||
			         request.getContentType().toLowerCase().indexOf(multipartContentType) == -1) {
			  
			         throw new Exception("Invalid contentType. It must be " + multipartContentType);
			     }
			  
			     // Get file Part based on field name and also file extension.
			     String type = filePart.getContentType();
			     type = type.substring(type.lastIndexOf("/") + 1);
			  
			     // Generate random name.
			     String extension = type;
			     extension = (extension != null && extension != "") ? "." + extension : extension;
			     String name = UUID.randomUUID().toString() + extension;
			  
			     // Get absolute server path.
			     String absoluteServerPath = uploads + name;
			  
			     // Create link.
			     String path = request.getHeader("referer");
			     linkName = path + "files/" + name;
			  
			     // Validate file.
			     String mimeType = filePart.getContentType();
			     String[] allowedExts = new String[] {
			         "mp4",
			         "webm",
			         "ogg"
			     };
			     String[] allowedMimeTypes = new String[] {
			         "video/mp4",
			         "video/webm",
			         "video/ogg"
			     };
			  
			     if (!ArrayUtils.contains(allowedExts, FilenameUtils.getExtension(absoluteServerPath)) ||
			         !ArrayUtils.contains(allowedMimeTypes, mimeType.toLowerCase())) {
			  
			         // Delete the uploaded file.
			         File file1 = new File(absoluteServerPath);
			  
			         if (file1.exists()) {
			             file1.delete();
			         }
			  
			         throw new Exception("File does not meet the validation.");
			     }
			  
			     // Save the file on server.
			     File file2 = new File(uploads, name);
			  
			     try (InputStream input = filePart.getInputStream()) {
			         Files.copy(input, file2.toPath());
			     } catch (Exception e) {
			      writer.println("<br/> ERROR: " + e);
			     }
			  
			  } catch (Exception e) {
			     e.printStackTrace();
			     writer.println("You either did not specify a file to upload or are " +
			         "trying to upload a file to a protected or nonexistent " +
			         "location.");
			     writer.println("<br/> ERROR: " + e.getMessage());
			     responseData = new HashMap < Object, Object > ();
			     responseData.put("error", e.toString());
			  
			  } finally {
			     responseData = new HashMap < Object, Object > ();
			     responseData.put("link", linkName);
			  
			     // Send response data.
			     String jsonResponseData = new Gson().toJson(responseData);
			     response.setContentType("application/json");
			     response.setCharacterEncoding("UTF-8");
			     response.getWriter().write(jsonResponseData);
			  }
			return null;
			  }
			  
	
	@RequestMapping(value="/add-uav",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView UAV(@ModelAttribute UAV obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.addEditUAV);
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<UAV> worksList = UAVService.getWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<UAV> projectsList = UAVService.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("UAV : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInUAV", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UAV> getWorksFilterListInUAV(@ModelAttribute UAV obj) {
		List<UAV> worksList = null;
		try {
			worksList = UAVService.getWorksFilterListInUAV(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInUAV : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getSurveyDateVideoSpecifications", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UAV> getSurveyDateVideoSpecifications(@ModelAttribute UAV obj) {
		List<UAV> worksList = null;
		try {
			worksList = UAVService.getSurveyDateVideoSpecifications(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSurveyDateVideoSpecifications : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getStructureSurvey_Date", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UAV> getStructureSurvey_Date(@ModelAttribute UAV obj) {
		List<UAV> worksList = null;
		try {
			worksList = UAVService.getStructureSurvey_Date(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureSurvey_Date : " + e.getMessage());
		}
		return worksList;
	}	
	
	@RequestMapping(value = "/ajax/getStructuresFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UAV> getStructuresFilterList(@ModelAttribute UAV obj) {
		List<UAV> worksList = null;
		try {
			worksList = UAVService.getStructuresFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructuresFilterList : " + e.getMessage());
		}
		return worksList;
	}	
	
	
	@RequestMapping(value = "/ajax/getSurveyDatesFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UAV> getSurveyDatesFilterList(@ModelAttribute UAV obj) {
		List<UAV> worksList = null;
		try {
			worksList = UAVService.getSurveyDatesFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSurveyDatesFilterList : " + e.getMessage());
		}
		return worksList;
	}	
	
	
	
	@RequestMapping(value="/uav",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView UAV(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.uav);
		try {
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("UAV : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/work-drone-survey",method={RequestMethod.GET})
	public ModelAndView DroneSurvey(HttpSession session,@ModelAttribute Structure obj){
		ModelAndView model = new ModelAndView(PageConstants.droneSurvey);
		try {
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("DroneSurvey : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value="/work-drone-survey/{work_id}",method={RequestMethod.GET})
	public ModelAndView DroneSurveyWithWork(HttpSession session,@ModelAttribute UAV obj,@PathVariable("work_id") String work_id){
		ModelAndView model = new ModelAndView(PageConstants.droneSurvey);
		try {
			
			Structure objWorkShortName=new Structure();
			objWorkShortName.setWork_id(obj.getWork_id());
			List<UAV> UavVideoDataStructure = UAVService.getUavVideoDataStructure(obj);
			model.addObject("UavVideoDataStructure", UavVideoDataStructure);
		    model.setViewName(PageConstants.droneSurvey);
			model.addObject("work_id", work_id);
			Structure work = service.getWorkShortName(objWorkShortName);
			model.addObject("work_short_name", work.getWork_short_name());
			model.addObject("dashboard_type", "Works");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("DroneSurveyWithWork : " + e.getMessage());
		}
		return model;
	}	
	

	@RequestMapping(value = "/upload-uavmp4", method = {RequestMethod.POST})
	public ModelAndView uploadUavmp4(@ModelAttribute UAV uav,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			uav.setCreated_by_user_id_fk(user_Id);
			uav.setUser_id(user_Id);
			uav.setUser_name(userName);
			uav.setDesignation(userDesignation);
			model.setViewName("redirect:/uav");
			
			if(uav.getMp4FileUpload().getSize()>0)
			{
			
				if(!StringUtils.isEmpty(uav.getMp4FileUpload()))
				{
					int cnt=UAVService.uploadMP4Data(uav);
					attributes.addFlashAttribute("success", "Uploaded successfully");
				}
			}
			if(uav.getSrtFileUpload().getSize()>0)
			{			
				if(!StringUtils.isEmpty(uav.getSrtFileUpload()))
				{
					int cnt=UAVService.uploadSRTData(uav);
					attributes.addFlashAttribute("success", "Uploaded successfully");
				}
			}
			 if(uav.getAnnotationFileUpload().getSize()>0)
			 {			
				if(!StringUtils.isEmpty(uav.getAnnotationFileUpload()))
				{
					int cnt=UAVService.uploadAnnotationData(uav);
					attributes.addFlashAttribute("success", "Uploaded successfully");
				}
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  
	public ModelAndView uploadMP4(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {  
	    	response.setContentType("video/mp4");
	  
	        // creating path components for saving the file  
	        final String path = request.getParameter("destination");  
	        final Part filePart = request.getPart("file");  
	        final String fileName = getFileName(filePart);  
	          
	        // declare instances of OutputStream, InputStream, and PrintWriter classes  
	        OutputStream otpStream = null;  
	        InputStream iptStream = null;  
	        final PrintWriter writer = response.getWriter();  
	          
	        // try section handles the code for storing file into the specified location  
	        try {  
	            // initialize instances of OutputStream and InputStream classes  
	            otpStream = new FileOutputStream(new File(path + File.separator + fileName));  
	            iptStream = filePart.getInputStream();  
	  
	            int read = 0;  
	              
	            // initialize bytes array for storing file data  
	            final byte[] bytes = new byte[1024];  
	              
	            // use while loop to read data from the file using iptStream and write into  the desination folder using writer and otpStream  
	            while ((read = iptStream.read(bytes)) != -1) {  
	                otpStream.write(bytes, 0, read);  
	            }  
	            writer.println("New file " + fileName + " created at " + path);  
	            
	        }  
	        // catch section handles the errors   
	        catch (FileNotFoundException fne){  
	            writer.println("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location.");  
	            writer.println("<br/> ERROR: " + fne.getMessage());  
	        }  
	        // finally section will close all the open classes  
	        finally {  
	            if (otpStream != null) {  
	                otpStream.close();  
	            }  
	            if (iptStream != null) {  
	                iptStream.close();  
	            }  
	            if (writer != null) {  
	                writer.close();  
	            }  
	        }
			return null;  
	    }  
	    // getFileName() method to get the file name from the part  
	    private String getFileName(final Part part) {  
	        // get header(content-disposition) from the part  
	        final String partHeader = part.getHeader("content-disposition");  
	        
	          
	        // code to get file name from the header  
	        for (String content : part.getHeader("content-disposition").split(";")) {  
	            if (content.trim().startsWith("filename")) {  
	                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");  
	            }  
	        }  
	        // it will return null when it doesn't get file name in the header   
	        return null;  
	    }  
	 	
	
	@RequestMapping(value = "/upload-uav", method = {RequestMethod.POST})
	public ModelAndView uploadUAVFile(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file)throws ServletException, IOException {
		
		String saveDirectory = CommonConstants2.DRONE_SURVEY_SAVING_PATH ;
		String fileName = file.getOriginalFilename();
		FileUploads.singleFileSaving(file, saveDirectory, fileName);
		
	  File uploads = new File(CommonConstants2.DRONE_SURVEY_SAVING_PATH);
	  
	  String multipartContentType = "multipart/form-data";
	  String fieldname = "file";
	  javax.servlet.http.Part filePart = request.getPart(fieldname);
	  
	  // Create path components to save the file.
	  Map < Object, Object > responseData;
	  final PrintWriter writer = response.getWriter();
	  String linkName = null;
	  
	  try {
	     // Check content type.
	     if (request.getContentType() == null ||
	         request.getContentType().toLowerCase().indexOf(multipartContentType) == -1) {
	  
	         throw new Exception("Invalid contentType. It must be " + multipartContentType);
	     }
	  
	     // Get file Part based on field name and also file extension.
	     String type = filePart.getContentType();
	     type = type.substring(type.lastIndexOf("/") + 1);
	  
	     // Generate random name.
	     String extension = type;
	     extension = (extension != null && extension != "") ? "." + extension : extension;
	     String name = UUID.randomUUID().toString() + extension;
	  
	     // Get absolute server path.
	     String absoluteServerPath = uploads + name;
	  
	     // Create link.
	     String path = request.getHeader("referer");
	     linkName = path + "files/" + name;
	  
	     // Validate file.
	     String mimeType = filePart.getContentType();
	     String[] allowedExts = new String[] {
	         "mp4"
	     };
	     String[] allowedMimeTypes = new String[] {
	         "video/mp4"
	     };
	  
	     if (!ArrayUtils.contains(allowedExts, FilenameUtils.getExtension(absoluteServerPath)) ||
	         !ArrayUtils.contains(allowedMimeTypes, mimeType.toLowerCase())) {
	  
	         // Delete the uploaded file.
	         File file1 = new File(absoluteServerPath);
	  
	         if (file1.exists()) {
	             file1.delete();
	         }
	  
	         throw new Exception("File does not meet the validation.");
	     }
	  
	     // Save the file on server.
	     File file1 = new File(uploads, name);
	  
	     try (InputStream input = filePart.getInputStream()) {
	         Files.copy(input, file1.toPath());
	     } catch (Exception e) {
	      writer.println("<br/> ERROR: " + e);
	     }
	  
	  } catch (Exception e) {
	     e.printStackTrace();
	     writer.println("You either did not specify a file to upload or are " +
	         "trying to upload a file to a protected or nonexistent " +
	         "location.");
	     writer.println("<br/> ERROR: " + e.getMessage());
	     responseData = new HashMap < Object, Object > ();
	     responseData.put("error", e.toString());
	  
	  } finally {
	     responseData = new HashMap < Object, Object > ();
	     responseData.put("link", linkName);
	  
	     // Send response data.
	     String jsonResponseData = new Gson().toJson(responseData);
	     response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonResponseData);
	  }
	return null;
	  }
	  

	@RequestMapping(value = "/get-uav", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getBudgetForm(@ModelAttribute UAV uav,HttpSession session ){
		ModelAndView model = new ModelAndView();
		try{
			
			User uObj = (User) session.getAttribute("user");
			uav.setUser_type_fk(uObj.getUser_type_fk());
			uav.setUser_role_code(uObj.getUser_role_code());
			uav.setUser_id(uObj.getUser_id());
			
			
			model.setViewName(PageConstants.addEditUAV);
			model.addObject("action", "edit");
			
			List<UAV> worksList = UAVService.getWorksList(uav);
			model.addObject("worksList", worksList);
			
			List<UAV> projectsList = UAVService.getProjectsList(uav);
			model.addObject("projectsList", projectsList);

			UAV uavDetails = UAVService.getUav(uav);
			model.addObject("uavDetails", uavDetails);
			
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getUAV : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-uav/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getBudgetFormWithId(@ModelAttribute UAV uav,@PathVariable("id") String id,HttpSession session ){
		ModelAndView model = new ModelAndView();
		try{
			
			User uObj = (User) session.getAttribute("user");
			uav.setUser_type_fk(uObj.getUser_type_fk());
			uav.setUser_role_code(uObj.getUser_role_code());
			uav.setUser_id(uObj.getUser_id());
			
			
			model.setViewName(PageConstants.addEditUAV);
			model.addObject("action", "edit");
			
			List<UAV> worksList = UAVService.getWorksList(uav);
			model.addObject("worksList", worksList);
			
			List<UAV> projectsList = UAVService.getProjectsList(uav);
			model.addObject("projectsList", projectsList);
			
			UAV uavDetails = UAVService.getUav(uav);
			model.addObject("uavDetails", uavDetails);			
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getUAV : " + e.getMessage());
		}
		return model;
	 }	
	
	@RequestMapping(value = "/ajax/get-uav", method = { RequestMethod.POST, RequestMethod.GET })
	public void getUAVList(@ModelAttribute UAV obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");

			pw = response.getWriter();
			//Fetch the page number from client
			Integer pageNumber = 0;
			Integer pageDisplayLength = 0;
			if (null != request.getParameter("iDisplayStart")) {
				pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			}
			//Fetch search parameter
			String searchParameter = request.getParameter("sSearch");

			//Fetch Page display length
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			List<UAV> uavList = new ArrayList<UAV>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				uavList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				uavList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//uavList = getListBasedOnSearchParameter(searchParameter,uavList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			UAVPaginationObject personJsonObject = new UAVPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(uavList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getUAVList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}	

	public int getTotalRecords(UAV obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = UAVService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	public List<UAV> createPaginationData(int startIndex, int offset,UAV obj, String searchParameter) {
		List<UAV> earthWorkList = null;
		try {
			earthWorkList = UAVService.getUAVList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}

}
