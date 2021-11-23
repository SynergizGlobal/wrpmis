package com.synergizglobal.pmis.model;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Structure {
	private String structure_id,work_id_fk,contract_id_fk,department_fk,structure_type_fk,structure,structure_count,
		work_name,work_short_name,user_type_fk,user_id,user_role_code,contract_name,contract_short_name,department_name,project_id_fk,project_name,structure_type,
		id, uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on,contract_id,responsible_people_id_fk,user_name,designation,work_status_fk, unit, value,structure_file_type
		,structure_id_fk, structure_detail,name,attachment,structure_file_type_fk,created_date,structure_value,target_date,estimated_cost,estimated_cost_units,construction_start_date,revised_completion;
	private int [] subRowsLengths;
	
	private MultipartFile structureFile;
	private MultipartFile[] structureFiles;
	
	private String [] structure_type_fks,structures,responsible_people_id_fks,contracts_id_fk,ids,structure_values,structure_file_type_fks,names,structureFiless
		,structure_details,work_status_fks,target_dates,estimated_costs,estimated_cost_unitss,construction_start_dates,revised_completions,remarkss,contracts
		,structure_file_types,structureDocumentNames,structureFileNames,structure_file_ids,structure_detailss;
	
	private List<Structure> structureList;
	private List<Structure> structureSubList;
	private List<Structure> structureSubList2;
	private List<Structure> executivesList;
	private List<Structure> documentsList;
	private List<Structure> structureDetailsList;
	private List<Structure> contractsLists;
	private List<Structure> responsiblePeopleLists;
	

	public List<Structure> getContractsLists() {
		return contractsLists;
	}

	public void setContractsLists(List<Structure> contractsLists) {
		this.contractsLists = contractsLists;
	}

	public List<Structure> getResponsiblePeopleLists() {
		return responsiblePeopleLists;
	}

	public void setResponsiblePeopleLists(List<Structure> responsiblePeopleLists) {
		this.responsiblePeopleLists = responsiblePeopleLists;
	}

	public List<Structure> getStructureSubList2() {
		return structureSubList2;
	}

	public void setStructureSubList2(List<Structure> structureSubList2) {
		this.structureSubList2 = structureSubList2;
	}

	public String[] getStructureFiless() {
		return structureFiless;
	}

	public void setStructureFiless(String[] structureFiless) {
		this.structureFiless = structureFiless;
	}

	public String[] getStructure_detailss() {
		return structure_detailss;
	}

	public void setStructure_detailss(String[] structure_detailss) {
		this.structure_detailss = structure_detailss;
	}

	public MultipartFile[] getStructureFiles() {
		return structureFiles;
	}

	public void setStructureFiles(MultipartFile[] structureFiles) {
		this.structureFiles = structureFiles;
	}

	public String[] getStructure_file_types() {
		return structure_file_types;
	}

	public void setStructure_file_types(String[] structure_file_types) {
		this.structure_file_types = structure_file_types;
	}

	public String[] getStructureDocumentNames() {
		return structureDocumentNames;
	}

	public void setStructureDocumentNames(String[] structureDocumentNames) {
		this.structureDocumentNames = structureDocumentNames;
	}

	public String[] getStructureFileNames() {
		return structureFileNames;
	}

	public void setStructureFileNames(String[] structureFileNames) {
		this.structureFileNames = structureFileNames;
	}

	public String[] getStructure_file_ids() {
		return structure_file_ids;
	}

	public void setStructure_file_ids(String[] structure_file_ids) {
		this.structure_file_ids = structure_file_ids;
	}

	public String[] getContracts() {
		return contracts;
	}

	public void setContracts(String[] contracts) {
		this.contracts = contracts;
	}

	public String getTarget_date() {
		return target_date;
	}

	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}

	public String getEstimated_cost() {
		return estimated_cost;
	}

	public void setEstimated_cost(String estimated_cost) {
		this.estimated_cost = estimated_cost;
	}

	public String getEstimated_cost_units() {
		return estimated_cost_units;
	}

	public void setEstimated_cost_units(String estimated_cost_units) {
		this.estimated_cost_units = estimated_cost_units;
	}

	public String getConstruction_start_date() {
		return construction_start_date;
	}

	public void setConstruction_start_date(String construction_start_date) {
		this.construction_start_date = construction_start_date;
	}

	public String getRevised_completion() {
		return revised_completion;
	}

	public void setRevised_completion(String revised_completion) {
		this.revised_completion = revised_completion;
	}

	public String[] getWork_status_fks() {
		return work_status_fks;
	}

	public void setWork_status_fks(String[] work_status_fks) {
		this.work_status_fks = work_status_fks;
	}

	public String[] getTarget_dates() {
		return target_dates;
	}

	public void setTarget_dates(String[] target_dates) {
		this.target_dates = target_dates;
	}

	public String[] getEstimated_costs() {
		return estimated_costs;
	}

	public void setEstimated_costs(String[] estimated_costs) {
		this.estimated_costs = estimated_costs;
	}

	public String[] getEstimated_cost_unitss() {
		return estimated_cost_unitss;
	}

	public void setEstimated_cost_unitss(String[] estimated_cost_unitss) {
		this.estimated_cost_unitss = estimated_cost_unitss;
	}

	public String[] getConstruction_start_dates() {
		return construction_start_dates;
	}

	public void setConstruction_start_dates(String[] construction_start_dates) {
		this.construction_start_dates = construction_start_dates;
	}

	public String[] getRevised_completions() {
		return revised_completions;
	}

	public void setRevised_completions(String[] revised_completions) {
		this.revised_completions = revised_completions;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
	}

	public String getStructure_file_type() {
		return structure_file_type;
	}

	public void setStructure_file_type(String structure_file_type) {
		this.structure_file_type = structure_file_type;
	}

	public String getStructure_id_fk() {
		return structure_id_fk;
	}

	public void setStructure_id_fk(String structure_id_fk) {
		this.structure_id_fk = structure_id_fk;
	}

	public String getStructure_detail() {
		return structure_detail;
	}

	public void setStructure_detail(String structure_detail) {
		this.structure_detail = structure_detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getStructure_file_type_fk() {
		return structure_file_type_fk;
	}

	public void setStructure_file_type_fk(String structure_file_type_fk) {
		this.structure_file_type_fk = structure_file_type_fk;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getStructure_value() {
		return structure_value;
	}

	public void setStructure_value(String structure_value) {
		this.structure_value = structure_value;
	}

	public String[] getStructure_values() {
		return structure_values;
	}

	public void setStructure_values(String[] structure_values) {
		this.structure_values = structure_values;
	}

	public String[] getStructure_file_type_fks() {
		return structure_file_type_fks;
	}

	public void setStructure_file_type_fks(String[] structure_file_type_fks) {
		this.structure_file_type_fks = structure_file_type_fks;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getStructure_details() {
		return structure_details;
	}

	public void setStructure_details(String[] structure_details) {
		this.structure_details = structure_details;
	}

	public List<Structure> getExecutivesList() {
		return executivesList;
	}

	public void setExecutivesList(List<Structure> executivesList) {
		this.executivesList = executivesList;
	}

	public List<Structure> getDocumentsList() {
		return documentsList;
	}

	public void setDocumentsList(List<Structure> documentsList) {
		this.documentsList = documentsList;
	}

	public List<Structure> getStructureDetailsList() {
		return structureDetailsList;
	}

	public void setStructureDetailsList(List<Structure> structureDetailsList) {
		this.structureDetailsList = structureDetailsList;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getWork_status_fk() {
		return work_status_fk;
	}

	public void setWork_status_fk(String work_status_fk) {
		this.work_status_fk = work_status_fk;
	}

	public String[] getResponsible_people_id_fks() {
		return responsible_people_id_fks;
	}

	public void setResponsible_people_id_fks(String[] responsible_people_id_fks) {
		this.responsible_people_id_fks = responsible_people_id_fks;
	}

	public String[] getContracts_id_fk() {
		return contracts_id_fk;
	}

	public void setContracts_id_fk(String[] contracts_id_fk) {
		this.contracts_id_fk = contracts_id_fk;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getResponsible_people_id_fk() {
		return responsible_people_id_fk;
	}

	public void setResponsible_people_id_fk(String responsible_people_id_fk) {
		this.responsible_people_id_fk = responsible_people_id_fk;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int[] getSubRowsLengths() {
		return subRowsLengths;
	}

	public void setSubRowsLengths(int[] subRowsLengths) {
		this.subRowsLengths = subRowsLengths;
	}

	public List<Structure> getStructureSubList() {
		return structureSubList;
	}

	public void setStructureSubList(List<Structure> structureSubList) {
		this.structureSubList = structureSubList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUploaded_file() {
		return uploaded_file;
	}

	public void setUploaded_file(String uploaded_file) {
		this.uploaded_file = uploaded_file;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getUploaded_on() {
		return uploaded_on;
	}

	public void setUploaded_on(String uploaded_on) {
		this.uploaded_on = uploaded_on;
	}

	public MultipartFile getStructureFile() {
		return structureFile;
	}

	public void setStructureFile(MultipartFile structureFile) {
		this.structureFile = structureFile;
	}

	public String getStructure_type() {
		return structure_type;
	}

	public String getStructure_count() {
		return structure_count;
	}

	public void setStructure_count(String structure_count) {
		this.structure_count = structure_count;
	}

	public void setStructure_type(String structure_type) {
		this.structure_type = structure_type;
	}

	public String[] getStructure_type_fks() {
		return structure_type_fks;
	}

	public void setStructure_type_fks(String[] structure_type_fks) {
		this.structure_type_fks = structure_type_fks;
	}

	public String[] getStructures() {
		return structures;
	}

	public void setStructures(String[] structures) {
		this.structures = structures;
	}

	public List<Structure> getStructureList() {
		return structureList;
	}

	public void setStructureList(List<Structure> structureList) {
		this.structureList = structureList;
	}

	public String getUser_type_fk() {
		return user_type_fk;
	}

	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getStructure_id() {
		return structure_id;
	}

	public void setStructure_id(String structure_id) {
		this.structure_id = structure_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

	public String getStructure_type_fk() {
		return structure_type_fk;
	}

	public void setStructure_type_fk(String structure_type_fk) {
		this.structure_type_fk = structure_type_fk;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public boolean checkNullOrEmpty() {
		boolean flag = true;
		try {
			for (Field f : getClass().getDeclaredFields())
		        if (!StringUtils.isEmpty(f.get(this)))
		        	flag = false;
		} catch (Exception e) {
			
		}
	    
	    return flag; 
	}
	
	
}
