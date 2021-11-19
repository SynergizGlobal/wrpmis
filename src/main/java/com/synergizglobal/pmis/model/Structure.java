package com.synergizglobal.pmis.model;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Structure {
	private String structure_id,work_id_fk,contract_id_fk,department_fk,structure_type_fk,structure,structure_count,
	work_name,work_short_name,user_type_fk,user_id,user_role_code,contract_name,contract_short_name,department_name,project_id_fk,project_name,structure_type,
	id, uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on;
	private int [] subRowsLengths;
	private MultipartFile structureFile;
	
	private String [] structure_type_fks,structures;
	private List<Structure> structureList;
	private List<Structure> structureSubList;
	

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
