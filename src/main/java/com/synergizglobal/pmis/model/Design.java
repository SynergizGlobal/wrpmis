package com.synergizglobal.pmis.model;

public class Design {
	
	private String design_id, contract_id_fk, department_id_fk,department_name, hod, dy_hod,designation, prepared_by_id_fk, consultant_contract_id_fk,
	proof_consultant_contract_id_fk, structure_type_fk, component, drawing_type_fk, contractor_drawing_no, mrvc_drawing_no,
	division_drawing_no, hq_drawing_no, drawing_title, planned_start, planned_finish, revision, consultant_submission, 
	mrvc_reviewed, divisional_approval, hq_approval, gfc_released, as_built_status, as_built_date, remarks;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDesign_id() {
		return design_id;
	}

	public void setDesign_id(String design_id) {
		this.design_id = design_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getDepartment_id_fk() {
		return department_id_fk;
	}

	public void setDepartment_id_fk(String department_id_fk) {
		this.department_id_fk = department_id_fk;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getDy_hod() {
		return dy_hod;
	}

	public void setDy_hod(String dy_hod) {
		this.dy_hod = dy_hod;
	}

	public String getPrepared_by_id_fk() {
		return prepared_by_id_fk;
	}

	public void setPrepared_by_id_fk(String prepared_by_id_fk) {
		this.prepared_by_id_fk = prepared_by_id_fk;
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

	public String getStructure_type_fk() {
		return structure_type_fk;
	}

	public void setStructure_type_fk(String structure_type_fk) {
		this.structure_type_fk = structure_type_fk;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDrawing_type_fk() {
		return drawing_type_fk;
	}

	public void setDrawing_type_fk(String drawing_type_fk) {
		this.drawing_type_fk = drawing_type_fk;
	}

	public String getContractor_drawing_no() {
		return contractor_drawing_no;
	}

	public void setContractor_drawing_no(String contractor_drawing_no) {
		this.contractor_drawing_no = contractor_drawing_no;
	}

	public String getMrvc_drawing_no() {
		return mrvc_drawing_no;
	}

	public void setMrvc_drawing_no(String mrvc_drawing_no) {
		this.mrvc_drawing_no = mrvc_drawing_no;
	}

	public String getDivision_drawing_no() {
		return division_drawing_no;
	}

	public void setDivision_drawing_no(String division_drawing_no) {
		this.division_drawing_no = division_drawing_no;
	}

	public String getHq_drawing_no() {
		return hq_drawing_no;
	}

	public void setHq_drawing_no(String hq_drawing_no) {
		this.hq_drawing_no = hq_drawing_no;
	}

	public String getDrawing_title() {
		return drawing_title;
	}

	public void setDrawing_title(String drawing_title) {
		this.drawing_title = drawing_title;
	}

	public String getPlanned_start() {
		return planned_start;
	}

	public void setPlanned_start(String planned_start) {
		this.planned_start = planned_start;
	}

	public String getPlanned_finish() {
		return planned_finish;
	}

	public void setPlanned_finish(String planned_finish) {
		this.planned_finish = planned_finish;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getConsultant_submission() {
		return consultant_submission;
	}

	public void setConsultant_submission(String consultant_submission) {
		this.consultant_submission = consultant_submission;
	}

	public String getMrvc_reviewed() {
		return mrvc_reviewed;
	}

	public void setMrvc_reviewed(String mrvc_reviewed) {
		this.mrvc_reviewed = mrvc_reviewed;
	}

	public String getDivisional_approval() {
		return divisional_approval;
	}

	public void setDivisional_approval(String divisional_approval) {
		this.divisional_approval = divisional_approval;
	}

	public String getHq_approval() {
		return hq_approval;
	}

	public void setHq_approval(String hq_approval) {
		this.hq_approval = hq_approval;
	}

	public String getGfc_released() {
		return gfc_released;
	}

	public void setGfc_released(String gfc_released) {
		this.gfc_released = gfc_released;
	}

	public String getAs_built_status() {
		return as_built_status;
	}

	public void setAs_built_status(String as_built_status) {
		this.as_built_status = as_built_status;
	}

	public String getAs_built_date() {
		return as_built_date;
	}

	public void setAs_built_date(String as_built_date) {
		this.as_built_date = as_built_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
