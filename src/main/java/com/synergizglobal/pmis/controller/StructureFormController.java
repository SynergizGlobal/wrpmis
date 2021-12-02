package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.StructureFormService;
import com.synergizglobal.pmis.Iservice.StructureService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.StructurePaginationObject;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.User;

@Controller
public class StructureFormController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(StructureFormController.class);

	@Autowired
	StructureFormService structureFormService;

	@Autowired
	StructureService structureService;
	
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
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/structure-form",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView structureForm(@ModelAttribute Structure obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.structureFormGrid);		
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("structure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorkStatusFilterListInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getWorkStatusList(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> workStatusList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			workStatusList = structureFormService.getWorkStatusList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusList : " + e.getMessage());
		}
		return workStatusList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getWorksListForFilter(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> worksList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			worksList = structureFormService.getWorksListForFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForFilter : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInStructure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getContractsListForFilter(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> contractsList = null;
		try {
			User uObj = (User) session.getAttribute("user"); 
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			contractsList = structureFormService.getContractsListForFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForFilter : " + e.getMessage());
		} 
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getStructuresList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getStructureList(@ModelAttribute Structure obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null;
		List<Structure> contractList = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
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

			 contractList = new ArrayList<Structure>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				contractList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			}

			//Search functionality: Returns filtered list based on search parameter
			//contractList = getListBasedOnSearchParameter(searchParameter,contractList);

		

			StructurePaginationObject personJsonObject = new StructurePaginationObject();
			int totalRecords = getTotalRecords(obj, searchParameter);
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(contractList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			contractList = new ArrayList<Structure>();
			logger.error("getStructureList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Structure obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = structureFormService.getTotalRecords(obj, searchParameter);
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
	public List<Structure> createPaginationData(int startIndex, int offset,Structure obj, String searchParameter,HttpSession session) {
		List<Structure> contractsGridList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractsGridList = structureFormService.getStructuresList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return contractsGridList;
	}
	
	@RequestMapping(value = "/add-structures-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addStructuresForm(@ModelAttribute Structure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditStructureForm);	
			model.addObject("action", "add");
			List<Structure> projectsList = structureService.getProjectsListForStructureForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Structure> worksList = structureService.getWorkListForStructureForm(obj);
			model.addObject("worksList", worksList);
			
			List<Structure> contractsList = structureService.getContractListForStructureFrom(obj);
			model.addObject("contractsList", contractsList);
			
			List<Structure> structuresList = structureService.getStructuresListForStructureFrom(obj);
			model.addObject("structuresList", structuresList);
			
			List<Structure> departmentsList = structureService.getDepartmentsListForStructureFrom(obj);
			model.addObject("departmentsList", departmentsList);
			
			List<Structure> responsiblePeopleList = structureService.getResponsiblePeopleListForStructureForm(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<Structure> workStatusList = structureService.getWorkStatusListForStructureForm(obj);
			model.addObject("workStatusList", workStatusList);
			
			List<Structure> unitsList = structureService.getUnitsListForStructureForm(obj);
			model.addObject("unitsList", unitsList);
			
			List<Structure> fileType = structureService.getFileTypeForStructureForm(obj);
			model.addObject("fileType", fileType);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
		
		}catch (Exception e) {
			logger.error("addStructuresForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/ajax/getContractsListForStructureFrom", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getContractsListForStructureFrom(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getContractListForStructureFrom(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractListForStructureFrom : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorksListForStructureForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Structure> getWorksListForStructureForm(@ModelAttribute Structure obj,HttpSession session) {
		List<Structure> objList = null;
		try {
			objList = structureService.getWorkListForStructureForm(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForStructureForm : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/get-structure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getStructuresForm(@ModelAttribute Structure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditStructureForm);	
			model.addObject("action", "edit");	
			List<Structure> projectsList = structureService.getProjectsListForStructureForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Structure> worksList = structureService.getWorkListForStructureForm(obj);
			model.addObject("worksList", worksList);
			
			List<Structure> contractsList = structureService.getContractListForStructureFrom(obj);
			model.addObject("contractsList", contractsList);
			
			List<Structure> structuresList = structureService.getStructuresListForStructureFrom(obj);
			model.addObject("structuresList", structuresList);
			
			List<Structure> departmentsList = structureService.getDepartmentsListForStructureFrom(obj);
			model.addObject("departmentsList", departmentsList);
		
			List<Structure> responsiblePeopleList = structureService.getResponsiblePeopleListForStructureForm(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<Structure> workStatusList = structureService.getWorkStatusListForStructureForm(obj);
			model.addObject("workStatusList", workStatusList);
			
			List<Structure> unitsList = structureService.getUnitsListForStructureForm(obj);
			model.addObject("unitsList", unitsList);
			
			List<Structure> fileType = structureService.getFileTypeForStructureForm(obj);
			model.addObject("fileType", fileType);
			
			List<String> generalStatusList = homeService.getGeneralStatusList();
			model.addObject("generalStatusList", generalStatusList);
			
			List<Structure> structureDetailsLocations = structureFormService.getStructureDetailsLocations(obj);
			model.addObject("structureDetailsLocations", structureDetailsLocations);
			
			List<Structure> structureDetailsTypes = structureFormService.getStructureDetailsTypes(obj);
			model.addObject("structureDetailsTypes", structureDetailsTypes);
			
			
			Structure structuresListDetails = structureFormService.getStructuresFormDetails(obj);
			model.addObject("structuresListDetails", structuresListDetails);
			
		}catch (Exception e) {
			logger.error("getStructuresForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/add-structures", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addStructureForm(@ModelAttribute Structure obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-form");
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));	
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));	
			obj.setRevised_completion(DateParser.parse(obj.getRevised_completion()));	
			obj.setCommissioning_date(DateParser.parse(obj.getCommissioning_date()));	
			obj.setActual_completion_date(DateParser.parse(obj.getActual_completion_date()));	
			boolean flag =  structureFormService.addStructureForm(obj);	
			if(flag) {
				attributes.addFlashAttribute("success", "Structure Added Succesfully."); 
			} else {
				attributes.addFlashAttribute("error","Adding Structure is failed. Try again.");
			}
			
		}catch (Exception e) {
			logger.error("addStructureForm : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/update-structure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateStructuresForm(@ModelAttribute Structure obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-form"); 
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));	
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));	
			obj.setRevised_completion(DateParser.parse(obj.getRevised_completion()));	
			obj.setCommissioning_date(DateParser.parse(obj.getCommissioning_date()));	
			obj.setActual_completion_date(DateParser.parse(obj.getActual_completion_date()));	
			boolean flag =  structureFormService.updateStructureForm(obj);	
			if(flag) {
				attributes.addFlashAttribute("success", "Structure Updated Succesfully."); 
			} else {
				attributes.addFlashAttribute("error","Updating Structure is failed. Try again.");
			}		
			
			
		}catch (Exception e) {
			logger.error("updateStructurseForm : " + e.getMessage());
		}
		return model;
	}	
}
