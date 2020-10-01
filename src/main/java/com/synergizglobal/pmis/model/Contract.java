package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class Contract {
	private String contract_id,Contract_ID,work_id_fk,contract_name,contract_type_fk,strip_chart_type_fk,scope_of_contract,contractor_id_fk,department_fk,
	hod_user_id_fk,dy_hod_user_id_fk,tally_head,estimated_cost,awarded_cost,loa_letter_number,loa_date,ca_no,ca_date,date_of_start,doc,
	actual_completion_date,completed_cost,contract_closure_date,weight,remarks,work_name,contractor_name,insurance_type,project_id_fk,
	bg_type_fk,issuing_bank,bank_address,bg_number,bg_value,valid_upto, insurance_type_fk, issuing_agency, agency_address, insurance_number, insurance_value
	,contract_milestones_id,milestone_name, milestone_date, actual_date, revision,
	contract_revision_id, revision_number, revision_date, revised_amount, revised_doc;

	private String[] bg_type_fks,issuing_banks,bank_addresss,bg_numbers,bg_values,bg_valid_uptos,remarkss,
	 insurance_type_fks, issuing_agencys, agency_addresss, insurance_numbers, insurance_values,insurence_valid_uptos,insurence_remarks,
	 contract_milestones_ids, milestone_names, milestone_dates, actual_dates, revisions,mile_remarks,
	 contract_revision_ids, revision_numbers, revision_dates, revised_amounts, revised_docs,revision_remarks;
	
	private MultipartFile contractFile;

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

	public String getValid_upto() {
		return valid_upto;
	}

	public void setValid_upto(String valid_upto) {
		this.valid_upto = valid_upto;
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
	
}
