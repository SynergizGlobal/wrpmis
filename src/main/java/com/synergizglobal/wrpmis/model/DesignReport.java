package com.synergizglobal.wrpmis.model;

public class DesignReport {
	private String name,work_id_fk, work_id,work_name,work_short_name,hod,dyhod,department_id_fk,department,department_name,contract_id_code;
	
	private String total_scope,total_drawings_approved,total_submitted_by_consultans,total_mrvc_reviewed,total_submitted_to_division,
	total_divisional_approval,total_submitted_to_hq,total_hq_approval,under_review_by_mrvc,under_review_by_division,under_review_by_hq,
	
	structure_type,structure,component,consultant,planned_approval_date,total_no_of_drawings,approval_by_division,
	approval_by_hq,approval_by_mrvc,approved_date,

	drawing_id,proof_consultant,prepared_by,drawing_type,approval_authority,required_date,drawing_name,mrvc_drawing_no,
	divisional_drawing_no,hq_drawing_no,current_stage,structure_type_fk,structure_id_fk,consult_contract,consultant_contract_id_fk,proof_consultant_contract_id_fk;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
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

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getDepartment_id_fk() {
		return department_id_fk;
	}

	public void setDepartment_id_fk(String department_id_fk) {
		this.department_id_fk = department_id_fk;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getContract_id_code() {
		return contract_id_code;
	}

	public void setContract_id_code(String contract_id_code) {
		this.contract_id_code = contract_id_code;
	}

	public String getTotal_scope() {
		return total_scope;
	}

	public void setTotal_scope(String total_scope) {
		this.total_scope = total_scope;
	}

	public String getTotal_drawings_approved() {
		return total_drawings_approved;
	}

	public void setTotal_drawings_approved(String total_drawings_approved) {
		this.total_drawings_approved = total_drawings_approved;
	}

	public String getTotal_submitted_by_consultans() {
		return total_submitted_by_consultans;
	}

	public void setTotal_submitted_by_consultans(String total_submitted_by_consultans) {
		this.total_submitted_by_consultans = total_submitted_by_consultans;
	}

	public String getTotal_mrvc_reviewed() {
		return total_mrvc_reviewed;
	}

	public void setTotal_mrvc_reviewed(String total_mrvc_reviewed) {
		this.total_mrvc_reviewed = total_mrvc_reviewed;
	}

	public String getTotal_submitted_to_division() {
		return total_submitted_to_division;
	}

	public void setTotal_submitted_to_division(String total_submitted_to_division) {
		this.total_submitted_to_division = total_submitted_to_division;
	}

	public String getTotal_divisional_approval() {
		return total_divisional_approval;
	}

	public void setTotal_divisional_approval(String total_divisional_approval) {
		this.total_divisional_approval = total_divisional_approval;
	}

	public String getTotal_submitted_to_hq() {
		return total_submitted_to_hq;
	}

	public void setTotal_submitted_to_hq(String total_submitted_to_hq) {
		this.total_submitted_to_hq = total_submitted_to_hq;
	}

	public String getTotal_hq_approval() {
		return total_hq_approval;
	}

	public void setTotal_hq_approval(String total_hq_approval) {
		this.total_hq_approval = total_hq_approval;
	}

	public String getUnder_review_by_mrvc() {
		return under_review_by_mrvc;
	}

	public void setUnder_review_by_mrvc(String under_review_by_mrvc) {
		this.under_review_by_mrvc = under_review_by_mrvc;
	}

	public String getUnder_review_by_division() {
		return under_review_by_division;
	}

	public void setUnder_review_by_division(String under_review_by_division) {
		this.under_review_by_division = under_review_by_division;
	}

	public String getUnder_review_by_hq() {
		return under_review_by_hq;
	}

	public void setUnder_review_by_hq(String under_review_by_hq) {
		this.under_review_by_hq = under_review_by_hq;
	}

	public String getDyhod() {
		return dyhod;
	}

	public void setDyhod(String dyhod) {
		this.dyhod = dyhod;
	}

	public String getStructure_type() {
		return structure_type;
	}

	public void setStructure_type(String structure_type) {
		this.structure_type = structure_type;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public String getPlanned_approval_date() {
		return planned_approval_date;
	}

	public void setPlanned_approval_date(String planned_approval_date) {
		this.planned_approval_date = planned_approval_date;
	}

	public String getTotal_no_of_drawings() {
		return total_no_of_drawings;
	}

	public void setTotal_no_of_drawings(String total_no_of_drawings) {
		this.total_no_of_drawings = total_no_of_drawings;
	}

	public String getApproval_by_division() {
		return approval_by_division;
	}

	public void setApproval_by_division(String approval_by_division) {
		this.approval_by_division = approval_by_division;
	}

	public String getApproval_by_hq() {
		return approval_by_hq;
	}

	public void setApproval_by_hq(String approval_by_hq) {
		this.approval_by_hq = approval_by_hq;
	}

	public String getApproval_by_mrvc() {
		return approval_by_mrvc;
	}

	public void setApproval_by_mrvc(String approval_by_mrvc) {
		this.approval_by_mrvc = approval_by_mrvc;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getDrawing_id() {
		return drawing_id;
	}

	public void setDrawing_id(String drawing_id) {
		this.drawing_id = drawing_id;
	}

	public String getProof_consultant() {
		return proof_consultant;
	}

	public void setProof_consultant(String proof_consultant) {
		this.proof_consultant = proof_consultant;
	}

	public String getPrepared_by() {
		return prepared_by;
	}

	public void setPrepared_by(String prepared_by) {
		this.prepared_by = prepared_by;
	}

	public String getDrawing_type() {
		return drawing_type;
	}

	public void setDrawing_type(String drawing_type) {
		this.drawing_type = drawing_type;
	}

	public String getApproval_authority() {
		return approval_authority;
	}

	public void setApproval_authority(String approval_authority) {
		this.approval_authority = approval_authority;
	}

	public String getRequired_date() {
		return required_date;
	}

	public void setRequired_date(String required_date) {
		this.required_date = required_date;
	}

	public String getDrawing_name() {
		return drawing_name;
	}

	public void setDrawing_name(String drawing_name) {
		this.drawing_name = drawing_name;
	}

	public String getMrvc_drawing_no() {
		return mrvc_drawing_no;
	}

	public void setMrvc_drawing_no(String mrvc_drawing_no) {
		this.mrvc_drawing_no = mrvc_drawing_no;
	}

	public String getDivisional_drawing_no() {
		return divisional_drawing_no;
	}

	public void setDivisional_drawing_no(String divisional_drawing_no) {
		this.divisional_drawing_no = divisional_drawing_no;
	}

	public String getHq_drawing_no() {
		return hq_drawing_no;
	}

	public void setHq_drawing_no(String hq_drawing_no) {
		this.hq_drawing_no = hq_drawing_no;
	}

	public String getCurrent_stage() {
		return current_stage;
	}

	public void setCurrent_stage(String current_stage) {
		this.current_stage = current_stage;
	}

	public String getStructure_type_fk() {
		return structure_type_fk;
	}

	public void setStructure_type_fk(String structure_type_fk) {
		this.structure_type_fk = structure_type_fk;
	}

	public String getStructure_id_fk() {
		return structure_id_fk;
	}

	public void setStructure_id_fk(String structure_id_fk) {
		this.structure_id_fk = structure_id_fk;
	}

	public String getConsult_contract() {
		return consult_contract;
	}

	public void setConsult_contract(String consult_contract) {
		this.consult_contract = consult_contract;
	}

	public String getConsultant_contract_id_fk() {
		return consultant_contract_id_fk;
	}

	public void setConsultant_contract_id_fk(String consultant_contract_id_fk) {
		this.consultant_contract_id_fk = consultant_contract_id_fk;
	}

	public String getProof_consultant_contract_id_fk() {
		return proof_consultant_contract_id_fk;
	}

	public void setProof_consultant_contract_id_fk(String proof_consultant_contract_id_fk) {
		this.proof_consultant_contract_id_fk = proof_consultant_contract_id_fk;
	}
	
}
