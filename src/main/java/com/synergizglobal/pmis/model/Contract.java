package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Contract {
	private String contract_id,contract_id_fk,Contract_ID,work_id_fk,contract_name,contract_short_name,contract_type_fk,project_name,strip_chart_type_fk,scope_of_contract,contractor_id_fk,department_fk,department_name,contract_id_code,
	hod_user_id_fk,dy_hod_user_id_fk,designation,user_name,tally_head,estimated_cost,awarded_cost,loa_letter_number,loa_date,ca_no,ca_date,date_of_start,doc,completion_certificate_release,
	final_takeover,final_bill_release,defect_liability_period,retention_money_release,pbg_release,contract_closure,contract_status_fk,bg_required,insurance_required,
	actual_completion_date,completed_cost,contract_closure_date,weight,remarks,work_name,contractor_name,insurance_type,project_id_fk,
	bg_type_fk,issuing_bank,bank_address,bg_number,bg_value,bg_valid_upto, insurance_type_fk, issuing_agency, agency_address, insurance_number, insurance_value,insurence_remark,insurence_valid_upto
	,contract_milestones_id,milestone_name, milestone_date, actual_date, revision,mile_remark,milestone_id,status,released_fk,
	contract_revision_id, revision_number, revision_date, revised_amount, revised_doc,revision_remark,work_short_name,bank_status,insurance_status,revision_status
	,code, bg_date, release_date,project_id,work_id,hod_designation,dy_hod_designation,insurance_valid_upto,date,cumulative_expenditure,insurance_valid_till,pbg_valid_till,
	payment_made,actual_physical_progress,actual_financial_progress,hod_user_id,dy_hod_user_id,user_type_fk,user_id,reporting_to_id_srfk,user_role_code,contract_file_type_fk,contract_file_type,contract_file_id,
	responsible_people_id_fk,hod_name,dy_hod_name,PhysicalProgress;
	
	private String contract_documents_id,name,attachment,contract_key_personnel_id,mobile_no,email_id,id, department_id_fk, executive_user_id_fk;

	private String[] bg_type_fks,issuing_banks,bank_addresss,bg_numbers,bg_values,bg_valid_uptos,remarkss,bank_revisions,bankStatus,insuranceStatus,codes, bg_dates, release_dates,released_fks,
	 insurance_type_fks, issuing_agencys, agency_addresss, insurance_numbers, insurance_values,insurence_valid_uptos,insurence_remarks,insurance_revisions,revision_statuss,
	 contract_milestones_ids, milestone_names, milestone_dates, actual_dates, revisions,mile_remarks,
	 contract_revision_ids, revision_numbers, revision_dates, revised_amounts,hod_designations, revised_docs,revision_remarks,contract_file_type_fks,contract_file_ids,contract_file_types;
	
	private String contractor_id, contractor_specilization_fk, address,
	primary_contact_name, phone_number, pan_number, gst_number, bank_name, account_number, ifsc_code,target_doc;
	
	private MultipartFile contractFile;
	
	private String[] contractDocumentNames,contractDocumentFileNames,milestone_ids,contractKeyPersonnelNames,contractKeyPersonnelMobileNos,contractKeyPersonnelEmailIds,contractKeyPersonnelDesignations
	,ids, contract_id_fks, department_id_fks, executive_user_id_fks,department_fks,responsible_people_id_fks;
	private int[] filecounts;
	private MultipartFile[] contractDocumentFiles;
	
	private List<Contract> bankGauranree;
	private List<Contract> insurence;
	private List<Contract> milestones;
	private List<Contract> contract_revision;
	private List<Contract> contractDocuments;
	private List<Contract> contractKeyPersonnels;
	private List<Contract> responsiblePeopleList;
	private List<Contract> departmentList;
	private List<Contract> executivesList;
	
	public String[] getHod_designations() {
		return hod_designations;
	}

	public void setHod_designations(String[] hod_designations) {
		this.hod_designations = hod_designations;
	}

	public List<Contract> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Contract> departmentList) {
		this.departmentList = departmentList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment_id_fk() {
		return department_id_fk;
	}

	public void setDepartment_id_fk(String department_id_fk) {
		this.department_id_fk = department_id_fk;
	}

	public String getExecutive_user_id_fk() {
		return executive_user_id_fk;
	}

	public void setExecutive_user_id_fk(String executive_user_id_fk) {
		this.executive_user_id_fk = executive_user_id_fk;
	}

	public String[] getDepartment_fks() {
		return department_fks;
	}

	public void setDepartment_fks(String[] department_fks) {
		this.department_fks = department_fks;
	}

	public String[] getResponsible_people_id_fks() {
		return responsible_people_id_fks;
	}

	public void setResponsible_people_id_fks(String[] responsible_people_id_fks) {
		this.responsible_people_id_fks = responsible_people_id_fks;
	}

	public int[] getFilecounts() {
		return filecounts;
	}

	public void setFilecounts(int[] filecounts) {
		this.filecounts = filecounts;
	}

	
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getContract_id_fks() {
		return contract_id_fks;
	}

	public void setContract_id_fks(String[] contract_id_fks) {
		this.contract_id_fks = contract_id_fks;
	}

	public String[] getDepartment_id_fks() {
		return department_id_fks;
	}

	public void setDepartment_id_fks(String[] department_id_fks) {
		this.department_id_fks = department_id_fks;
	}

	public String[] getExecutive_user_id_fks() {
		return executive_user_id_fks;
	}

	public void setExecutive_user_id_fks(String[] executive_user_id_fks) {
		this.executive_user_id_fks = executive_user_id_fks;
	}

	public String getContract_file_type_fk() {
		return contract_file_type_fk;
	}

	public void setContract_file_type_fk(String contract_file_type_fk) {
		this.contract_file_type_fk = contract_file_type_fk;
	}

	public String getContract_file_type() {
		return contract_file_type;
	}

	public void setContract_file_type(String contract_file_type) {
		this.contract_file_type = contract_file_type;
	}

	public String[] getContract_file_type_fks() {
		return contract_file_type_fks;
	}

	public void setContract_file_type_fks(String[] contract_file_type_fks) {
		this.contract_file_type_fks = contract_file_type_fks;
	}


	public String[] getContract_file_types() {
		return contract_file_types;
	}

	public void setContract_file_types(String[] contract_file_types) {
		this.contract_file_types = contract_file_types;
	}

	public String getContract_file_id() {
		return contract_file_id;
	}

	public void setContract_file_id(String contract_file_id) {
		this.contract_file_id = contract_file_id;
	}


	public String[] getContract_file_ids() {
		return contract_file_ids;
	}

	public void setContract_file_ids(String[] contract_file_ids) {
		this.contract_file_ids = contract_file_ids;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getReporting_to_id_srfk() {
		return reporting_to_id_srfk;
	}

	public void setReporting_to_id_srfk(String reporting_to_id_srfk) {
		this.reporting_to_id_srfk = reporting_to_id_srfk;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_type_fk() {
		return user_type_fk;
	}

	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}

	public String getPayment_made() {
		return payment_made;
	}

	public void setPayment_made(String payment_made) {
		this.payment_made = payment_made;
	}

	public String getActual_physical_progress() {
		return actual_physical_progress;
	}

	public void setActual_physical_progress(String actual_physical_progress) {
		this.actual_physical_progress = actual_physical_progress;
	}

	public String getActual_financial_progress() {
		return actual_financial_progress;
	}

	public void setActual_financial_progress(String actual_financial_progress) {
		this.actual_financial_progress = actual_financial_progress;
	}

	public String getContractor_id() {
		return contractor_id;
	}

	public void setContractor_id(String contractor_id) {
		this.contractor_id = contractor_id;
	}

	public String getContractor_specilization_fk() {
		return contractor_specilization_fk;
	}

	public void setContractor_specilization_fk(String contractor_specilization_fk) {
		this.contractor_specilization_fk = contractor_specilization_fk;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrimary_contact_name() {
		return primary_contact_name;
	}

	public void setPrimary_contact_name(String primary_contact_name) {
		this.primary_contact_name = primary_contact_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPan_number() {
		return pan_number;
	}

	public void setPan_number(String pan_number) {
		this.pan_number = pan_number;
	}

	public String getGst_number() {
		return gst_number;
	}

	public void setGst_number(String gst_number) {
		this.gst_number = gst_number;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getDy_hod_designation() {
		return dy_hod_designation;
	}

	public void setDy_hod_designation(String dy_hod_designation) {
		this.dy_hod_designation = dy_hod_designation;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getRevision_status() {
		return revision_status;
	}

	public void setRevision_status(String revision_status) {
		this.revision_status = revision_status;
	}

	public String[] getRevision_statuss() {
		return revision_statuss;
	}

	public void setRevision_statuss(String[] revision_statuss) {
		this.revision_statuss = revision_statuss;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String[] getReleased_fks() {
		return released_fks;
	}

	public void setReleased_fks(String[] released_fks) {
		this.released_fks = released_fks;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String[] getBg_dates() {
		return bg_dates;
	}

	public void setBg_dates(String[] bg_dates) {
		this.bg_dates = bg_dates;
	}

	public String[] getRelease_dates() {
		return release_dates;
	}

	public void setRelease_dates(String[] release_dates) {
		this.release_dates = release_dates;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBg_date() {
		return bg_date;
	}

	public void setBg_date(String bg_date) {
		this.bg_date = bg_date;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getReleased_fk() {
		return released_fk;
	}

	public void setReleased_fk(String released_fk) {
		this.released_fk = released_fk;
	}

	public String getBank_status() {
		return bank_status;
	}

	public void setBank_status(String bank_status) {
		this.bank_status = bank_status;
	}

	public String getInsurance_status() {
		return insurance_status;
	}

	public void setInsurance_status(String insurance_status) {
		this.insurance_status = insurance_status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String[] bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String[] getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(String[] insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public String getContract_documents_id() {
		return contract_documents_id;
	}

	public String[] getMilestone_ids() {
		return milestone_ids;
	}

	public void setMilestone_ids(String[] milestone_ids) {
		this.milestone_ids = milestone_ids;
	}

	public String getMilestone_id() {
		return milestone_id;
	}

	public void setMilestone_id(String milestone_id) {
		this.milestone_id = milestone_id;
	}

	public void setContract_documents_id(String contract_documents_id) {
		this.contract_documents_id = contract_documents_id;
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

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getContract_key_personnel_id() {
		return contract_key_personnel_id;
	}

	public void setContract_key_personnel_id(String contract_key_personnel_id) {
		this.contract_key_personnel_id = contract_key_personnel_id;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String[] getContractDocumentNames() {
		return contractDocumentNames;
	}

	public void setContractDocumentNames(String[] contractDocumentNames) {
		this.contractDocumentNames = contractDocumentNames;
	}

	public String[] getContractKeyPersonnelNames() {
		return contractKeyPersonnelNames;
	}

	public void setContractKeyPersonnelNames(String[] contractKeyPersonnelNames) {
		this.contractKeyPersonnelNames = contractKeyPersonnelNames;
	}

	public String[] getContractKeyPersonnelMobileNos() {
		return contractKeyPersonnelMobileNos;
	}

	public void setContractKeyPersonnelMobileNos(String[] contractKeyPersonnelMobileNos) {
		this.contractKeyPersonnelMobileNos = contractKeyPersonnelMobileNos;
	}

	public String[] getContractKeyPersonnelEmailIds() {
		return contractKeyPersonnelEmailIds;
	}

	public void setContractKeyPersonnelEmailIds(String[] contractKeyPersonnelEmailIds) {
		this.contractKeyPersonnelEmailIds = contractKeyPersonnelEmailIds;
	}

	public MultipartFile[] getContractDocumentFiles() {
		return contractDocumentFiles;
	}

	public void setContractDocumentFiles(MultipartFile[] contractDocumentFiles) {
		this.contractDocumentFiles = contractDocumentFiles;
	}

	public List<Contract> getContractDocuments() {
		return contractDocuments;
	}

	public void setContractDocuments(List<Contract> contractDocuments) {
		this.contractDocuments = contractDocuments;
	}

	public List<Contract> getContractKeyPersonnels() {
		return contractKeyPersonnels;
	}

	public void setContractKeyPersonnels(List<Contract> contractKeyPersonnels) {
		this.contractKeyPersonnels = contractKeyPersonnels;
	}

	public String getContract_id_code() {
		return contract_id_code;
	}

	public void setContract_id_code(String contract_id_code) {
		this.contract_id_code = contract_id_code;
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

	public String getRevision_remark() {
		return revision_remark;
	}

	public void setRevision_remark(String revision_remark) {
		this.revision_remark = revision_remark;
	}

	public String getInsurence_remark() {
		return insurence_remark;
	}

	public void setInsurence_remark(String insurence_remark) {
		this.insurence_remark = insurence_remark;
	}

	public String getInsurence_valid_upto() {
		return insurence_valid_upto;
	}

	public void setInsurence_valid_upto(String insurence_valid_upto) {
		this.insurence_valid_upto = insurence_valid_upto;
	}

	public String getMile_remark() {
		return mile_remark;
	}

	public void setMile_remark(String mile_remark) {
		this.mile_remark = mile_remark;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getContract_status_fk() {
		return contract_status_fk;
	}

	public void setContract_status_fk(String contract_status_fk) {
		this.contract_status_fk = contract_status_fk;
	}

	public String getBg_required() {
		return bg_required;
	}

	public void setBg_required(String bg_required) {
		this.bg_required = bg_required;
	}

	public String getInsurance_required() {
		return insurance_required;
	}

	public void setInsurance_required(String insurance_required) {
		this.insurance_required = insurance_required;
	}

	public String getContract_closure() {
		return contract_closure;
	}

	public void setContract_closure(String contract_closure) {
		this.contract_closure = contract_closure;
	}

	public String getPbg_release() {
		return pbg_release;
	}

	public void setPbg_release(String pbg_release) {
		this.pbg_release = pbg_release;
	}

	public String getRetention_money_release() {
		return retention_money_release;
	}

	public void setRetention_money_release(String retention_money_release) {
		this.retention_money_release = retention_money_release;
	}

	public String getDefect_liability_period() {
		return defect_liability_period;
	}

	public void setDefect_liability_period(String defect_liability_period) {
		this.defect_liability_period = defect_liability_period;
	}

	public String getFinal_takeover() {
		return final_takeover;
	}

	public String getFinal_bill_release() {
		return final_bill_release;
	}

	public void setFinal_bill_release(String final_bill_release) {
		this.final_bill_release = final_bill_release;
	}

	public void setFinal_takeover(String final_takeover) {
		this.final_takeover = final_takeover;
	}

	public String getCompletion_certificate_release() {
		return completion_certificate_release;
	}

	public void setCompletion_certificate_release(String completion_certificate_release) {
		this.completion_certificate_release = completion_certificate_release;
	}
	public List<Contract> getContract_revision() {
		return contract_revision;
	}

	public void setContract_revision(List<Contract> contract_revision) {
		this.contract_revision = contract_revision;
	}

	public List<Contract> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Contract> milestones) {
		this.milestones = milestones;
	}

	public List<Contract> getInsurence() {
		return insurence;
	}

	public void setInsurence(List<Contract> insurence) {
		this.insurence = insurence;
	}

	public List<Contract> getBankGauranree() {
		return bankGauranree;
	}

	public void setBankGauranree(List<Contract> bankGauranree) {
		this.bankGauranree = bankGauranree;
	}

	public MultipartFile getContractFile() {
		return contractFile;
	}

	public void setContractFile(MultipartFile contractFile) {
		this.contractFile = contractFile;
	}

	public String[] getMile_remarks() {
		return mile_remarks;
	}

	public void setMile_remarks(String[] mile_remarks) {
		this.mile_remarks = mile_remarks;
	}

	public String[] getRevision_remarks() {
		return revision_remarks;
	}

	public void setRevision_remarks(String[] revision_remarks) {
		this.revision_remarks = revision_remarks;
	}

	public String[] getInsurence_valid_uptos() {
		return insurence_valid_uptos;
	}

	public void setInsurence_valid_uptos(String[] insurence_valid_uptos) {
		this.insurence_valid_uptos = insurence_valid_uptos;
	}

	public String[] getInsurence_remarks() {
		return insurence_remarks;
	}

	public void setInsurence_remarks(String[] insurence_remarks) {
		this.insurence_remarks = insurence_remarks;
	}

	public String[] getRevised_docs() {
		return revised_docs;
	}

	public void setRevised_docs(String[] revised_docs) {
		this.revised_docs = revised_docs;
	}

	public String getContract_revision_id() {
		return contract_revision_id;
	}

	public void setContract_revision_id(String contract_revision_id) {
		this.contract_revision_id = contract_revision_id;
	}

	public String getRevision_number() {
		return revision_number;
	}

	public void setRevision_number(String revision_number) {
		this.revision_number = revision_number;
	}

	public String getRevision_date() {
		return revision_date;
	}

	public void setRevision_date(String revision_date) {
		this.revision_date = revision_date;
	}

	public String getRevised_amount() {
		return revised_amount;
	}

	public void setRevised_amount(String revised_amount) {
		this.revised_amount = revised_amount;
	}

	public String getRevised_doc() {
		return revised_doc;
	}

	public void setRevised_doc(String revised_doc) {
		this.revised_doc = revised_doc;
	}

	public String[] getContract_revision_ids() {
		return contract_revision_ids;
	}

	public void setContract_revision_ids(String[] contract_revision_ids) {
		this.contract_revision_ids = contract_revision_ids;
	}

	public String[] getRevision_numbers() {
		return revision_numbers;
	}

	public void setRevision_numbers(String[] revision_numbers) {
		this.revision_numbers = revision_numbers;
	}

	public String[] getRevision_dates() {
		return revision_dates;
	}

	public void setRevision_dates(String[] revision_dates) {
		this.revision_dates = revision_dates;
	}

	public String[] getRevised_amounts() {
		return revised_amounts;
	}

	public void setRevised_amounts(String[] revised_amounts) {
		this.revised_amounts = revised_amounts;
	}

	public String getContract_milestones_id() {
		return contract_milestones_id;
	}

	public void setContract_milestones_id(String contract_milestones_id) {
		this.contract_milestones_id = contract_milestones_id;
	}

	public String getMilestone_name() {
		return milestone_name;
	}

	public void setMilestone_name(String milestone_name) {
		this.milestone_name = milestone_name;
	}

	public String getMilestone_date() {
		return milestone_date;
	}

	public void setMilestone_date(String milestone_date) {
		this.milestone_date = milestone_date;
	}

	public String getActual_date() {
		return actual_date;
	}

	public void setActual_date(String actual_date) {
		this.actual_date = actual_date;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String[] getContract_milestones_ids() {
		return contract_milestones_ids;
	}

	public void setContract_milestones_ids(String[] contract_milestones_ids) {
		this.contract_milestones_ids = contract_milestones_ids;
	}

	public String[] getMilestone_names() {
		return milestone_names;
	}

	public void setMilestone_names(String[] milestone_names) {
		this.milestone_names = milestone_names;
	}

	public String[] getMilestone_dates() {
		return milestone_dates;
	}

	public void setMilestone_dates(String[] milestone_dates) {
		this.milestone_dates = milestone_dates;
	}

	public String[] getActual_dates() {
		return actual_dates;
	}

	public String[] getBg_valid_uptos() {
		return bg_valid_uptos;
	}

	public void setBg_valid_uptos(String[] bg_valid_uptos) {
		this.bg_valid_uptos = bg_valid_uptos;
	}

	public void setActual_dates(String[] actual_dates) {
		this.actual_dates = actual_dates;
	}

	public String[] getRevisions() {
		return revisions;
	}

	public void setRevisions(String[] revisions) {
		this.revisions = revisions;
	}

	public String getInsurance_type_fk() {
		return insurance_type_fk;
	}

	public void setInsurance_type_fk(String insurance_type_fk) {
		this.insurance_type_fk = insurance_type_fk;
	}

	public String getIssuing_agency() {
		return issuing_agency;
	}

	public void setIssuing_agency(String issuing_agency) {
		this.issuing_agency = issuing_agency;
	}

	public String getAgency_address() {
		return agency_address;
	}

	public void setAgency_address(String agency_address) {
		this.agency_address = agency_address;
	}

	public String getInsurance_number() {
		return insurance_number;
	}

	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}

	public String getInsurance_value() {
		return insurance_value;
	}

	public void setInsurance_value(String insurance_value) {
		this.insurance_value = insurance_value;
	}

	public String[] getInsurance_type_fks() {
		return insurance_type_fks;
	}

	public void setInsurance_type_fks(String[] insurance_type_fks) {
		this.insurance_type_fks = insurance_type_fks;
	}

	public String[] getIssuing_agencys() {
		return issuing_agencys;
	}

	public void setIssuing_agencys(String[] issuing_agencys) {
		this.issuing_agencys = issuing_agencys;
	}

	public String[] getAgency_addresss() {
		return agency_addresss;
	}

	public void setAgency_addresss(String[] agency_addresss) {
		this.agency_addresss = agency_addresss;
	}

	public String[] getInsurance_numbers() {
		return insurance_numbers;
	}

	public void setInsurance_numbers(String[] insurance_numbers) {
		this.insurance_numbers = insurance_numbers;
	}

	public String[] getInsurance_values() {
		return insurance_values;
	}

	public void setInsurance_values(String[] insurance_values) {
		this.insurance_values = insurance_values;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
	}

	public String getBg_type_fk() {
		return bg_type_fk;
	}

	public void setBg_type_fk(String bg_type_fk) {
		this.bg_type_fk = bg_type_fk;
	}

	public String getIssuing_bank() {
		return issuing_bank;
	}

	public void setIssuing_bank(String issuing_bank) {
		this.issuing_bank = issuing_bank;
	}

	public String getBank_address() {
		return bank_address;
	}

	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	public String getBg_number() {
		return bg_number;
	}

	public void setBg_number(String bg_number) {
		this.bg_number = bg_number;
	}

	public String getBg_value() {
		return bg_value;
	}

	public void setBg_value(String bg_value) {
		this.bg_value = bg_value;
	}


	public String getBg_valid_upto() {
		return bg_valid_upto;
	}

	public void setBg_valid_upto(String bg_valid_upto) {
		this.bg_valid_upto = bg_valid_upto;
	}

	public String[] getBg_type_fks() {
		return bg_type_fks;
	}

	public void setBg_type_fks(String[] bg_type_fks) {
		this.bg_type_fks = bg_type_fks;
	}

	public String[] getIssuing_banks() {
		return issuing_banks;
	}

	public void setIssuing_banks(String[] issuing_banks) {
		this.issuing_banks = issuing_banks;
	}

	public String[] getBank_addresss() {
		return bank_addresss;
	}

	public void setBank_addresss(String[] bank_addresss) {
		this.bank_addresss = bank_addresss;
	}

	public String[] getBg_numbers() {
		return bg_numbers;
	}

	public void setBg_numbers(String[] bg_numbers) {
		this.bg_numbers = bg_numbers;
	}

	public String[] getBg_values() {
		return bg_values;
	}

	public void setBg_values(String[] bg_values) {
		this.bg_values = bg_values;
	}

	
	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
	}

	public String getInsurance_type() {
		return insurance_type;
	}

	public void setInsurance_type(String insurance_type) {
		this.insurance_type = insurance_type;
	}

	public String getContract_id() {
		return contract_id;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getContract_type_fk() {
		return contract_type_fk;
	}

	public void setContract_type_fk(String contract_type_fk) {
		this.contract_type_fk = contract_type_fk;
	}

	public String getStrip_chart_type_fk() {
		return strip_chart_type_fk;
	}

	public void setStrip_chart_type_fk(String strip_chart_type_fk) {
		this.strip_chart_type_fk = strip_chart_type_fk;
	}

	public String getScope_of_contract() {
		return scope_of_contract;
	}

	public void setScope_of_contract(String scope_of_contract) {
		this.scope_of_contract = scope_of_contract;
	}

	public String getContractor_id_fk() {
		return contractor_id_fk;
	}

	public void setContractor_id_fk(String contractor_id_fk) {
		this.contractor_id_fk = contractor_id_fk;
	}

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

	public String getHod_user_id_fk() {
		return hod_user_id_fk;
	}

	public void setHod_user_id_fk(String hod_user_id_fk) {
		this.hod_user_id_fk = hod_user_id_fk;
	}

	public String getDy_hod_user_id_fk() {
		return dy_hod_user_id_fk;
	}

	public void setDy_hod_user_id_fk(String dy_hod_user_id_fk) {
		this.dy_hod_user_id_fk = dy_hod_user_id_fk;
	}

	public String getTally_head() {
		return tally_head;
	}

	public void setTally_head(String tally_head) {
		this.tally_head = tally_head;
	}

	public String getEstimated_cost() {
		return estimated_cost;
	}

	public void setEstimated_cost(String estimated_cost) {
		this.estimated_cost = estimated_cost;
	}

	public String getAwarded_cost() {
		return awarded_cost;
	}

	public void setAwarded_cost(String awarded_cost) {
		this.awarded_cost = awarded_cost;
	}

	public String getLoa_letter_number() {
		return loa_letter_number;
	}

	public void setLoa_letter_number(String loa_letter_number) {
		this.loa_letter_number = loa_letter_number;
	}

	public String getLoa_date() {
		return loa_date;
	}

	public void setLoa_date(String loa_date) {
		this.loa_date = loa_date;
	}

	public String getCa_no() {
		return ca_no;
	}

	public void setCa_no(String ca_no) {
		this.ca_no = ca_no;
	}

	public String getCa_date() {
		return ca_date;
	}

	public void setCa_date(String ca_date) {
		this.ca_date = ca_date;
	}

	public String getDate_of_start() {
		return date_of_start;
	}

	public void setDate_of_start(String date_of_start) {
		this.date_of_start = date_of_start;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getActual_completion_date() {
		return actual_completion_date;
	}

	public void setActual_completion_date(String actual_completion_date) {
		this.actual_completion_date = actual_completion_date;
	}

	public String getCompleted_cost() {
		return completed_cost;
	}

	public void setCompleted_cost(String completed_cost) {
		this.completed_cost = completed_cost;
	}

	public String getContract_closure_date() {
		return contract_closure_date;
	}

	public void setContract_closure_date(String contract_closure_date) {
		this.contract_closure_date = contract_closure_date;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getContract_ID() {
		return Contract_ID;
	}

	public void setContract_ID(String contract_ID) {
		Contract_ID = contract_ID;
	}

	public String[] getContractDocumentFileNames() {
		return contractDocumentFileNames;
	}

	public void setContractDocumentFileNames(String[] contractDocumentFileNames) {
		this.contractDocumentFileNames = contractDocumentFileNames;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String[] getBank_revisions() {
		return bank_revisions;
	}

	public void setBank_revisions(String[] bank_revisions) {
		this.bank_revisions = bank_revisions;
	}

	public String[] getInsurance_revisions() {
		return insurance_revisions;
	}

	public void setInsurance_revisions(String[] insurance_revisions) {
		this.insurance_revisions = insurance_revisions;
	}

	public String[] getContractKeyPersonnelDesignations() {
		return contractKeyPersonnelDesignations;
	}

	public void setContractKeyPersonnelDesignations(String[] contractKeyPersonnelDesignations) {
		this.contractKeyPersonnelDesignations = contractKeyPersonnelDesignations;
	}

	public String getHod_designation() {
		return hod_designation;
	}

	public void setHod_designation(String hod_designation) {
		this.hod_designation = hod_designation;
	}

	public String getInsurance_valid_upto() {
		return insurance_valid_upto;
	}

	public void setInsurance_valid_upto(String insurance_valid_upto) {
		this.insurance_valid_upto = insurance_valid_upto;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCumulative_expenditure() {
		return cumulative_expenditure;
	}

	public void setCumulative_expenditure(String cumulative_expenditure) {
		this.cumulative_expenditure = cumulative_expenditure;
	}

	public String getInsurance_valid_till() {
		return insurance_valid_till;
	}

	public void setInsurance_valid_till(String insurance_valid_till) {
		this.insurance_valid_till = insurance_valid_till;
	}

	public String getPbg_valid_till() {
		return pbg_valid_till;
	}

	public void setPbg_valid_till(String pbg_valid_till) {
		this.pbg_valid_till = pbg_valid_till;
	}

	public String getHod_user_id() {
		return hod_user_id;
	}

	public void setHod_user_id(String hod_user_id) {
		this.hod_user_id = hod_user_id;
	}

	public String getDy_hod_user_id() {
		return dy_hod_user_id;
	}

	public void setDy_hod_user_id(String dy_hod_user_id) {
		this.dy_hod_user_id = dy_hod_user_id;
	}

	public String getResponsible_people_id_fk() {
		return responsible_people_id_fk;
	}

	public void setResponsible_people_id_fk(String responsible_people_id_fk) {
		this.responsible_people_id_fk = responsible_people_id_fk;
	}

	public List<Contract> getResponsiblePeopleList() {
		return responsiblePeopleList;
	}

	public void setResponsiblePeopleList(List<Contract> responsiblePeopleList) {
		this.responsiblePeopleList = responsiblePeopleList;
	}

	public String getHod_name() {
		return hod_name;
	}

	public void setHod_name(String hod_name) {
		this.hod_name = hod_name;
	}

	public String getDy_hod_name() {
		return dy_hod_name;
	}

	public void setDy_hod_name(String dy_hod_name) {
		this.dy_hod_name = dy_hod_name;
	}

	public List<Contract> getExecutivesList() {
		return executivesList;
	}

	public void setExecutivesList(List<Contract> executivesList) {
		this.executivesList = executivesList;
	}

	public String getPhysicalProgress() {
		return PhysicalProgress;
	}

	public void setPhysicalProgress(String physicalProgress) {
		PhysicalProgress = physicalProgress;
	}

	public String getTarget_doc() {
		return target_doc;
	}

	public void setTarget_doc(String target_doc) {
		this.target_doc = target_doc;
	}
	
}
