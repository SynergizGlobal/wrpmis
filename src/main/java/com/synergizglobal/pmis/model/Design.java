package com.synergizglobal.pmis.model;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Design {
	
	private String design_id, contract_id_fk, department_id_fk,department_name, work_name,hod, dy_hod,designation, prepared_by_id_fk, consultant_contract_id_fk,
	proof_consultant_contract_id_fk,contract_name, structure_type_fk, component, drawing_type_fk, contractor_drawing_no, mrvc_drawing_no,project_id_fk,
	division_drawing_no, hq_drawing_no, drawing_title, planned_start, planned_finish, revision,clearance_to_consultant, consultant_submission,work_id_fk,department_fk,work_short_name,
	mrvc_reviewed, divisional_approval, hq_approval, gfc_released, as_built_status, as_built_date, remarks,submited_to_proof_consultant_fk,approval_by_proof_consultant_fk,
	 revision_status_fk,revision_date,revision_remarks,divisional_submission_fk,hq_submission_fk,attachment,is_there_issue,issue_description,issue_priority_id,
	 issue_category_id,created_by_user_id_fk,contract_short_name,submitted_to_division,submitted_to_hq,query_raised_by_division,query_replied_to_division,query_raised_by_hq,
	 query_replied_to_hq,crs_sanction_fk,submitted_for_crs_sanction,query_raised_for_crs_sanction,query_replied_for_crs_sanction,crs_sanction_approved,
	 project_id,work_id,project_name,contract_id,required_date,revision_status,status,uploaded_by_user_id_fk,design_data_id, railway_id,uploaded_file, user_id,uploaded_on,user_role_code,user_name;
	
	private String id, design_file_id,design_id_fk,submission_purpose,structure_id_fk,design_file_type,submssionpurpose,design_file_types,name,structure, stage_fk,design_status_submit, approving_railway,approval_authority_fk,submitted_by, submitted_to, submitted_date, submssion_purpose,design_file_type_fk,current;
	private String []ids, design_id_fks, design_file_ids,stage_fks,designDocumentFileNames,designDocumentNames, submitted_bys, submitted_tos, submitted_dates, submssion_purposes,design_file_type_fks,currents;
	
	private String[] revisions,revision_dates, consultant_submissions,design_file_typess, mrvc_revieweds, divisional_approvals, hq_approvals, revision_status_fks, remarkss;
	
	private MultipartFile designFile;
	private MultipartFile[] designDocumentFiles;
	
	private List<Design> designRevisions;
	private List<Design> designStatusList;
	private List<MultipartFile> designFiles;
	private List<Design> designFilesList;
	private String[] designFileNames;

	public String[] getRevision_dates() {
		return revision_dates;
	}

	public void setRevision_dates(String[] revision_dates) {
		this.revision_dates = revision_dates;
	}

	public String getDesign_file_type() {
		return design_file_type;
	}

	public void setDesign_file_type(String design_file_type) {
		this.design_file_type = design_file_type;
	}

	public String getRequired_date() {
		return required_date;
	}

	public void setRequired_date(String required_date) {
		this.required_date = required_date;
	}

	public List<Design> getDesignStatusList() {
		return designStatusList;
	}

	public void setDesignStatusList(List<Design> designStatusList) {
		this.designStatusList = designStatusList;
	}

	public String getSubmission_purpose() {
		return submission_purpose;
	}

	public void setSubmission_purpose(String submission_purpose) {
		this.submission_purpose = submission_purpose;
	}

	public String getStructure_id_fk() {
		return structure_id_fk;
	}

	public void setStructure_id_fk(String structure_id_fk) {
		this.structure_id_fk = structure_id_fk;
	}



	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getSubmssionpurpose() {
		return submssionpurpose;
	}

	public void setSubmssionpurpose(String submssionpurpose) {
		this.submssionpurpose = submssionpurpose;
	}

	public String getDesign_status_submit() {
		return design_status_submit;
	}

	public void setDesign_status_submit(String design_status_submit) {
		this.design_status_submit = design_status_submit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesign_file_types() {
		return design_file_types;
	}

	public void setDesign_file_types(String design_file_types) {
		this.design_file_types = design_file_types;
	}

	public String[] getDesign_file_typess() {
		return design_file_typess;
	}

	public void setDesign_file_typess(String[] design_file_typess) {
		this.design_file_typess = design_file_typess;
	}

	public String getDesign_file_id() {
		return design_file_id;
	}

	public void setDesign_file_id(String design_file_id) {
		this.design_file_id = design_file_id;
	}

	public String[] getDesign_file_ids() {
		return design_file_ids;
	}

	public void setDesign_file_ids(String[] design_file_ids) {
		this.design_file_ids = design_file_ids;
	}

	public String[] getDesignDocumentFileNames() {
		return designDocumentFileNames;
	}

	public void setDesignDocumentFileNames(String[] designDocumentFileNames) {
		this.designDocumentFileNames = designDocumentFileNames;
	}

	public String[] getDesignDocumentNames() {
		return designDocumentNames;
	}

	public void setDesignDocumentNames(String[] designDocumentNames) {
		this.designDocumentNames = designDocumentNames;
	}

	public MultipartFile[] getDesignDocumentFiles() {
		return designDocumentFiles;
	}

	public void setDesignDocumentFiles(MultipartFile[] designDocumentFiles) {
		this.designDocumentFiles = designDocumentFiles;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getDesign_id_fks() {
		return design_id_fks;
	}

	public void setDesign_id_fks(String[] design_id_fks) {
		this.design_id_fks = design_id_fks;
	}

	public String[] getStage_fks() {
		return stage_fks;
	}

	public void setStage_fks(String[] stage_fks) {
		this.stage_fks = stage_fks;
	}

	public String[] getSubmitted_bys() {
		return submitted_bys;
	}

	public void setSubmitted_bys(String[] submitted_bys) {
		this.submitted_bys = submitted_bys;
	}

	public String[] getSubmitted_tos() {
		return submitted_tos;
	}

	public void setSubmitted_tos(String[] submitted_tos) {
		this.submitted_tos = submitted_tos;
	}

	public String[] getSubmitted_dates() {
		return submitted_dates;
	}

	public void setSubmitted_dates(String[] submitted_dates) {
		this.submitted_dates = submitted_dates;
	}

	public String[] getSubmssion_purposes() {
		return submssion_purposes;
	}

	public void setSubmssion_purposes(String[] submssion_purposes) {
		this.submssion_purposes = submssion_purposes;
	}

	public String[] getDesign_file_type_fks() {
		return design_file_type_fks;
	}

	public void setDesign_file_type_fks(String[] design_file_type_fks) {
		this.design_file_type_fks = design_file_type_fks;
	}

	public String[] getCurrents() {
		return currents;
	}

	public void setCurrents(String[] currents) {
		this.currents = currents;
	}

	public String getApproving_railway() {
		return approving_railway;
	}

	public void setApproving_railway(String approving_railway) {
		this.approving_railway = approving_railway;
	}

	public String getApproval_authority_fk() {
		return approval_authority_fk;
	}

	public void setApproval_authority_fk(String approval_authority_fk) {
		this.approval_authority_fk = approval_authority_fk;
	}

	public String getDesign_file_type_fk() {
		return design_file_type_fk;
	}

	public void setDesign_file_type_fk(String design_file_type_fk) {
		this.design_file_type_fk = design_file_type_fk;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesign_id_fk() {
		return design_id_fk;
	}

	public void setDesign_id_fk(String design_id_fk) {
		this.design_id_fk = design_id_fk;
	}

	public String getStage_fk() {
		return stage_fk;
	}

	public void setStage_fk(String stage_fk) {
		this.stage_fk = stage_fk;
	}

	public String getSubmitted_by() {
		return submitted_by;
	}

	public void setSubmitted_by(String submitted_by) {
		this.submitted_by = submitted_by;
	}

	public String getSubmitted_to() {
		return submitted_to;
	}

	public void setSubmitted_to(String submitted_to) {
		this.submitted_to = submitted_to;
	}

	public String getSubmitted_date() {
		return submitted_date;
	}

	public void setSubmitted_date(String submitted_date) {
		this.submitted_date = submitted_date;
	}

	public String getSubmssion_purpose() {
		return submssion_purpose;
	}

	public void setSubmssion_purpose(String submssion_purpose) {
		this.submssion_purpose = submssion_purpose;
	}

	

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public MultipartFile getDesignFile() {
		return designFile;
	}

	public void setDesignFile(MultipartFile designFile) {
		this.designFile = designFile;
	}

	public List<MultipartFile> getDesignFiles() {
		return designFiles;
	}

	public void setDesignFiles(List<MultipartFile> designFiles) {
		this.designFiles = designFiles;
	}

	public List<Design> getDesignFilesList() {
		return designFilesList;
	}

	public void setDesignFilesList(List<Design> designFilesList) {
		this.designFilesList = designFilesList;
	}

	public String[] getDesignFileNames() {
		return designFileNames;
	}

	public void setDesignFileNames(String[] designFileNames) {
		this.designFileNames = designFileNames;
	}

	public String getDesign_data_id() {
		return design_data_id;
	}

	public void setDesign_data_id(String design_data_id) {
		this.design_data_id = design_data_id;
	}

	public String getUploaded_file() {
		return uploaded_file;
	}

	public void setUploaded_file(String uploaded_file) {
		this.uploaded_file = uploaded_file;
	}

	public String getUploaded_on() {
		return uploaded_on;
	}

	public void setUploaded_on(String uploaded_on) {
		this.uploaded_on = uploaded_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getQuery_raised_by_division() {
		return query_raised_by_division;
	}

	public void setQuery_raised_by_division(String query_raised_by_division) {
		this.query_raised_by_division = query_raised_by_division;
	}

	public String getQuery_replied_to_division() {
		return query_replied_to_division;
	}

	public void setQuery_replied_to_division(String query_replied_to_division) {
		this.query_replied_to_division = query_replied_to_division;
	}

	public String getQuery_raised_by_hq() {
		return query_raised_by_hq;
	}

	public void setQuery_raised_by_hq(String query_raised_by_hq) {
		this.query_raised_by_hq = query_raised_by_hq;
	}

	public String getQuery_replied_to_hq() {
		return query_replied_to_hq;
	}

	public void setQuery_replied_to_hq(String query_replied_to_hq) {
		this.query_replied_to_hq = query_replied_to_hq;
	}

	public String getCrs_sanction_fk() {
		return crs_sanction_fk;
	}

	public void setCrs_sanction_fk(String crs_sanction_fk) {
		this.crs_sanction_fk = crs_sanction_fk;
	}

	public String getSubmitted_for_crs_sanction() {
		return submitted_for_crs_sanction;
	}

	public void setSubmitted_for_crs_sanction(String submitted_for_crs_sanction) {
		this.submitted_for_crs_sanction = submitted_for_crs_sanction;
	}

	public String getQuery_raised_for_crs_sanction() {
		return query_raised_for_crs_sanction;
	}

	public void setQuery_raised_for_crs_sanction(String query_raised_for_crs_sanction) {
		this.query_raised_for_crs_sanction = query_raised_for_crs_sanction;
	}

	public String getQuery_replied_for_crs_sanction() {
		return query_replied_for_crs_sanction;
	}

	public void setQuery_replied_for_crs_sanction(String query_replied_for_crs_sanction) {
		this.query_replied_for_crs_sanction = query_replied_for_crs_sanction;
	}

	public String getCrs_sanction_approved() {
		return crs_sanction_approved;
	}

	public void setCrs_sanction_approved(String crs_sanction_approved) {
		this.crs_sanction_approved = crs_sanction_approved;
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

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getRevision_date() {
		return revision_date;
	}

	public void setRevision_date(String revision_date) {
		this.revision_date = revision_date;
	}

	
	public String getSubmited_to_proof_consultant_fk() {
		return submited_to_proof_consultant_fk;
	}

	public void setSubmited_to_proof_consultant_fk(String submited_to_proof_consultant_fk) {
		this.submited_to_proof_consultant_fk = submited_to_proof_consultant_fk;
	}

	public String getApproval_by_proof_consultant_fk() {
		return approval_by_proof_consultant_fk;
	}

	public void setApproval_by_proof_consultant_fk(String approval_by_proof_consultant_fk) {
		this.approval_by_proof_consultant_fk = approval_by_proof_consultant_fk;
	}

	public String getIssue_description() {
		return issue_description;
	}

	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}

	public String getIssue_priority_id() {
		return issue_priority_id;
	}

	public void setIssue_priority_id(String issue_priority_id) {
		this.issue_priority_id = issue_priority_id;
	}

	public String getIssue_category_id() {
		return issue_category_id;
	}

	public void setIssue_category_id(String issue_category_id) {
		this.issue_category_id = issue_category_id;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getDivisional_submission_fk() {
		return divisional_submission_fk;
	}

	public void setDivisional_submission_fk(String divisional_submission_fk) {
		this.divisional_submission_fk = divisional_submission_fk;
	}

	public String getHq_submission_fk() {
		return hq_submission_fk;
	}

	public void setHq_submission_fk(String hq_submission_fk) {
		this.hq_submission_fk = hq_submission_fk;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List<Design> getDesignRevisions() {
		return designRevisions;
	}

	public void setDesignRevisions(List<Design> designRevisions) {
		this.designRevisions = designRevisions;
	}

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getRevision_status_fk() {
		return revision_status_fk;
	}

	public void setRevision_status_fk(String revision_status_fk) {
		this.revision_status_fk = revision_status_fk;
	}

	public String getRevision_remarks() {
		return revision_remarks;
	}

	public void setRevision_remarks(String revision_remarks) {
		this.revision_remarks = revision_remarks;
	}

	public String[] getRevisions() {
		return revisions;
	}

	public void setRevisions(String[] revisions) {
		this.revisions = revisions;
	}

	public String[] getConsultant_submissions() {
		return consultant_submissions;
	}

	public void setConsultant_submissions(String[] consultant_submissions) {
		this.consultant_submissions = consultant_submissions;
	}

	public String[] getMrvc_revieweds() {
		return mrvc_revieweds;
	}

	public void setMrvc_revieweds(String[] mrvc_revieweds) {
		this.mrvc_revieweds = mrvc_revieweds;
	}

	public String[] getDivisional_approvals() {
		return divisional_approvals;
	}

	public void setDivisional_approvals(String[] divisional_approvals) {
		this.divisional_approvals = divisional_approvals;
	}

	public String[] getHq_approvals() {
		return hq_approvals;
	}

	public void setHq_approvals(String[] hq_approvals) {
		this.hq_approvals = hq_approvals;
	}

	public String[] getRevision_status_fks() {
		return revision_status_fks;
	}

	public void setRevision_status_fks(String[] revision_status_fks) {
		this.revision_status_fks = revision_status_fks;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
	}
	
	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

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

	public String getIs_there_issue() {
		return is_there_issue;
	}

	public void setIs_there_issue(String is_there_issue) {
		this.is_there_issue = is_there_issue;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getSubmitted_to_division() {
		return submitted_to_division;
	}

	public void setSubmitted_to_division(String submitted_to_division) {
		this.submitted_to_division = submitted_to_division;
	}

	public String getSubmitted_to_hq() {
		return submitted_to_hq;
	}

	public void setSubmitted_to_hq(String submitted_to_hq) {
		this.submitted_to_hq = submitted_to_hq;
	}

	public boolean checkNullOrEmpty() throws IllegalAccessException {
		boolean flag = true;
		try {
			for (Field f : getClass().getDeclaredFields())
		        if (!StringUtils.isEmpty(f.get(this)))
		        	flag = false;
		} catch (Exception e) {
			
		}
	    
	    return flag;            
	}

	public String getRevision_status() {
		return revision_status;
	}

	public void setRevision_status(String revision_status) {
		this.revision_status = revision_status;
	}

	public String getClearance_to_consultant() {
		return clearance_to_consultant;
	}

	public void setClearance_to_consultant(String clearance_to_consultant) {
		this.clearance_to_consultant = clearance_to_consultant;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
