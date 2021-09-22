package com.synergizglobal.pmis.webview.controller;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.ContractPaginationObject;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.User;

@Controller
@RequestMapping("/mobileappwebview")
public class WebViewContractController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebViewContractController.class);
	
	@Autowired
	ContractService contractService;
	
	@Autowired
	WorkService workService;
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	HomeService homeService;
	@Autowired
	ActivitiesService activitiesService;
	
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

	@RequestMapping(value="/contract",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Contract(HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.contractGrid);
		try {
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getContracts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contractList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractList = contractService.contractList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("contractList : " + e.getMessage());
		}
		return contractList;
	}
	
	@RequestMapping(value = "/ajax/get-contracts", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Contract obj, HttpServletRequest request,
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

			List<Contract> contractList = new ArrayList<Contract>();

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

		

			ContractPaginationObject personJsonObject = new ContractPaginationObject();
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
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Contract obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = contractService.getTotalRecords(obj, searchParameter);
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
	public List<Contract> createPaginationData(int startIndex, int offset,Contract obj, String searchParameter,HttpSession session) {
		List<Contract> contractsGridList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractsGridList = contractService.getContractsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return contractsGridList;
	}
	
	
	@RequestMapping(value = "/ajax/getContractorsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractorsFilterList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contractorsFilterList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractorsFilterList = contractService.contractorsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterList : " + e.getMessage());
		}
		return contractorsFilterList;
	}

	@RequestMapping(value = "/ajax/getWorksFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getWorksFilterList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> worksFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksFilterList = contractService.worksFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterList : " + e.getMessage());
		}
		return worksFilterList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectsFilterList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> projectsFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			projectsFilterList = contractService.getProjectsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsFilterList : " + e.getMessage());
		}
		return projectsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getDesignationsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDesignationsFilterList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> designationsFilterList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			designationsFilterList = contractService.getDesignationsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesignationsFilterList : " + e.getMessage());
		}
		return designationsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getDyHODDesignationsFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDyHODDesignationsFilterList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> dyHODDesignationsFilterList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			dyHODDesignationsFilterList = contractService.getDyHODDesignationsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDyHODDesignationsFilterList : " + e.getMessage());
		}
		return dyHODDesignationsFilterList;
	}
	

	@RequestMapping(value = "/ajax/getContractStatusFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractStatusFilterListInContract(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			dataList = contractService.getContractStatusFilterListInContract(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractStatusFilterListInContract : " + e.getMessage());
		}
		return dataList;
	}
	
	
	@RequestMapping(value = "/ajax/getStatusFilterListInContract", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getStatusFilterListInContract(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			dataList = contractService.getStatusFilterListInContract(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusFilterListInContract : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDepartmentsList(@ModelAttribute Contract obj) {
		List<Contract> dataList = null;  
		try {
			dataList = contractService.getDepartmentsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getHodList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getHodList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			
			dataList = contractService.getHodList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getDyHodList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDyHodList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> dataList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			dataList = contractService.getDyHodList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDyHodList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getExecutivesListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getExecutivesListForContractForm(@ModelAttribute Contract obj) {
		List<Contract> dataList = null;  
		try {
			dataList = contractService.getExecutivesListForContractForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutivesListForContractForm : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/ajax/getContractStatusLIstFormContractFom", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractStatusLIstFormContractFom(@ModelAttribute Contract obj) {
		List<Contract> dataList = null;  
		try {
			dataList = contractService.getContractStatusType(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractStatusLIstFormContractFom : " + e.getMessage());
		}
		return dataList;
	}
	
	
	@RequestMapping(value = "/addcontract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContract(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addContractForm);	
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> contractFileTypeList = contractService.getContractFileTypeList(obj);
			model.addObject("contractFileTypeList", contractFileTypeList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractors = contractService.getContractorsList();
			model.addObject("contractors", contractors);
			
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			
			List<Contract> contractList = contractService.contractList(obj);
			model.addObject("contractList", contractList);
			
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			
			List<Contract> contract_Statustype = contractService.getContractStatusType(obj);
			model.addObject("contract_Statustype", contract_Statustype);
			
		}catch (Exception e) {
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}	
	
	
	
	@RequestMapping(value = "/add-contract-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractForm(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addContractForm);	
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> contractFileTypeList = contractService.getContractFileTypeList(obj);
			model.addObject("contractFileTypeList", contractFileTypeList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractors = contractService.getContractorsList();
			model.addObject("contractors", contractors);
			
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			
			List<Contract> contractList = contractService.contractList(obj);
			model.addObject("contractList", contractList);
			
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			
			List<Contract> contract_Statustype = contractService.getContractStatusType(obj);
			model.addObject("contract_Statustype", contract_Statustype);
			
			List<Contract> contract_Status = contractService.getContractStatus();
			model.addObject("contract_Status", contract_Status);
			
			List<Contract> responsiblePeopleList = contractService.getResponsiblePeopleList(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<Contract> unitsList = contractService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
		}catch (Exception e) {
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectsListForContractForm(@ModelAttribute Contract obj) {
		List<Contract> objsList = null;
		try {
			objsList = contractService.getProjectsListForContractForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForContractForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForContractForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getWorkListForContractForm(@ModelAttribute Contract obj) {
		List<Contract> objsList = null;
		try {
			objsList = contractService.getWorkListForContractForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForContractForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContract(@ModelAttribute  Contract contract,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mobileappwebview/contract");
			contract.setDoc(DateParser.parse(contract.getDoc()));
			contract.setCa_date(DateParser.parse(contract.getCa_date()));
			contract.setDate_of_start(DateParser.parse(contract.getDate_of_start()));			
			contract.setLoa_date(DateParser.parse(contract.getLoa_date()));
			contract.setActual_completion_date(DateParser.parse(contract.getActual_completion_date()));		
			contract.setContract_closure_date(DateParser.parse(contract.getContract_closure_date()));
			contract.setCompletion_certificate_release(DateParser.parse(contract.getCompletion_certificate_release()));		
			contract.setFinal_takeover(DateParser.parse(contract.getFinal_takeover()));
			contract.setFinal_bill_release(DateParser.parse(contract.getFinal_bill_release()));
			contract.setDefect_liability_period(DateParser.parse(contract.getDefect_liability_period()));
			contract.setRetention_money_release(DateParser.parse(contract.getRetention_money_release()));
			contract.setPbg_release(DateParser.parse(contract.getPbg_release()));
			contract.setBg_date(DateParser.parse(contract.getBg_date()));
			contract.setRelease_date(DateParser.parse(contract.getRelease_date()));
			
			contract.setContract_status("Open");
			contract.setContract_status_fk("Not Started");
		
			boolean flag =  contractService.addContract(contract);			
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Added Succesfully."); 
			} else {
				attributes.addFlashAttribute("error","Adding Contract is failed. Try again.");
			}
		 }catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contract is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getcontract(@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.editContractForm);
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> contractFileTypeList = contractService.getContractFileTypeList(obj);
			model.addObject("contractFileTypeList", contractFileTypeList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractor = contractService.getContractorsList();
			model.addObject("contractor", contractor);
			
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			
			List<Contract> responsiblePeopleList = contractService.getResponsiblePeopleList(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<Contract> unitsList = contractService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<Contract> contract_Status = contractService.getContractStatus();
			model.addObject("contract_Status", contract_Status);
			
			Contract contractDeatils = contractService.getContract(obj);
			model.addObject("contractDeatils", contractDeatils);
			
			obj.setContract_status(contractDeatils.getStatus());
			List<Contract> contract_Statustype = contractService.getContractStatusType(obj);
			model.addObject("contract_Statustype", contract_Statustype);
			
			model.addObject("gotoTab", obj.getTab_name());
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-contract/{contract_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getcontract(@ModelAttribute Contract obj,@PathVariable("contract_id") String contract_id ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.editContractForm);
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> contractFileTypeList = contractService.getContractFileTypeList(obj);
			model.addObject("contractFileTypeList", contractFileTypeList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractor = contractService.getContractorsList();
			model.addObject("contractor", contractor);
			 
			List<Contract> contract_type = contractService.getContractTypeList();
			model.addObject("contract_type", contract_type);
			
			List<Contract> insurance_type = contractService.getInsurenceTypeList();
			model.addObject("insurance_type", insurance_type);
			
			List<BankGuarantee> bankGuaranteeTYpe = contractService.bankGuarantee();
			model.addObject("bankGuaranteeTYpe", bankGuaranteeTYpe);
			
			List<Insurence> InsurenceType = contractService.insurenceType();
			model.addObject("InsurenceType", InsurenceType);
			
			List<Contract> contract_Status = contractService.getContractStatus();
			model.addObject("contract_Status", contract_Status);
			
			List<Contract> unitsList = contractService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			obj.setContract_id(contract_id);
			Contract contractDeatils = contractService.getContract(obj);
			model.addObject("contractDeatils", contractDeatils);
			
			obj.setContract_status(contractDeatils.getStatus());
			List<Contract> contract_Statustype = contractService.getContractStatusType(obj);
			model.addObject("contract_Statustype", contract_Statustype);
			
			model.addObject("gotoTab", obj.getTab_name());
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contract", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Contract contract,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mobileappwebview/contract");

			contract.setDoc(DateParser.parse(contract.getDoc()));
			contract.setCa_date(DateParser.parse(contract.getCa_date()));
			contract.setDate_of_start(DateParser.parse(contract.getDate_of_start()));			
			contract.setLoa_date(DateParser.parse(contract.getLoa_date()));
			contract.setActual_completion_date(DateParser.parse(contract.getActual_completion_date()));		
			contract.setContract_closure_date(DateParser.parse(contract.getContract_closure_date()));
			contract.setCompletion_certificate_release(DateParser.parse(contract.getCompletion_certificate_release()));		
			contract.setFinal_takeover(DateParser.parse(contract.getFinal_takeover()));
			contract.setFinal_bill_release(DateParser.parse(contract.getFinal_bill_release()));
			contract.setDefect_liability_period(DateParser.parse(contract.getDefect_liability_period()));
			contract.setRetention_money_release(DateParser.parse(contract.getRetention_money_release()));
			contract.setPbg_release(DateParser.parse(contract.getPbg_release()));
			contract.setBg_date(DateParser.parse(contract.getBg_date()));
			contract.setRelease_date(DateParser.parse(contract.getRelease_date()));
			contract.setTarget_doc(DateParser.parse(contract.getTarget_doc()));
			
			boolean flag =  contractService.updateContract(contract);
			if(flag) {
				attributes.addFlashAttribute("success", "Contract Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Contract is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Contract is failed. Try again.");
			logger.error("Contract : " + e.getMessage());
		}
		return model;
	}
	
	
}
