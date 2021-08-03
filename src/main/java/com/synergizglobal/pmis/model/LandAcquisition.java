package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class LandAcquisition {
	
	private String work_id, la_id, project_id,project_id_fk,project_name,work_id_fk,work_name,work_short_name,survey_number, la_sub_category_fk,la_sub_category,la_category,village_id,status, status_of, type_of_land, sub_category_of_land, village, taluka, dy_slr, sdo, collector, proposal_submission_date_to_collector, area_of_plot, jm_fee_amount, chainage_from, chainage_to, jm_fee_letter_received_date, jm_fee_paid_date, 
	jm_start_date, jm_completion_date,is_there_issue,category_id, jm_sheet_date_to_sdo, jm_remarks, jm_approval, issues,attachment,category,issue_description,created_by_user_id_fk,issue_priority_id,issue_category_id,hod_user_id_fk,user_name
	,jm_fee_amount_units,value,unit,
	//gov
	id, la_id_fk, area_to_be_acquired, proposal_submission, proposal_submission_status_fk, valuation_date, letter_for_payment, amount_demanded, lfp_status_fk, approval_for_payment, payment_date, amount_paid, payment_status_fk, possession_date, possession_status_fk, special_feature,
    area_acquired, remarks,gov_remarks,amount_demanded_units,amount_paid_units,
    
    //private
    name_of_the_owner,private_area_to_be_acquired,private_area_acquired, basic_rate,private_attachment_no, attachment_no, agriculture_tree_nos, agriculture_tree_rate, forest_tree_nos, forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, 
    private_possession_status_fk, private_special_feature, hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2, land_compensation, agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation,
    forest_tree_survey, forest_tree_valuation, forest_tree_valuation_status_fk, horticulture_tree_survey, horticulture_tree_valuation, structure_survey,private_remarks,
    structure_valuation, borewell_survey, borewell_valuation, horticulture_tree_valuation_status_fk, structure_valuation_status_fk, borewell_valuation_status_fk, 
    rfp_to_adtp_status_fk, date_of_rfp_to_adtp, date_of_rate_fixation_of_land, sdo_demand_for_payment, date_of_approval_for_payment, payment_amount, private_payment_date
    ,payment_amount_units,basic_rate_units,agriculture_tree_rate_units,forest_tree_rate_units
    
    //forest
    ,forest_area_to_be_acquired,forest_area_acquired, forest_online_submission, forest_submission_date_to_dycfo,  forest_submission_date_to_ccf_thane, forest_submission_date_to_nodal_officer, forest_submission_date_to_revenue_secretary_mantralaya, forest_submission_date_to_regional_office_nagpur, forest_date_of_approval_by_regional_office_nagpur, 
    forest_valuation_by_dycfo, forest_demanded_amount, forest_payment_amount,forest_survey_number,forest_remarks,
    forest_approval_for_payment, forest_payment_date, forest_possession_date, forest_possession_status_fk, forest_payment_status_fk, forest_special_feature, forest_attachment_No,
    demanded_amount_units_forest,payment_amount_units_forest,
    //railway
    railway_area_to_be_acquired,railway_remarks,  railway_online_submission,railway_area_acquired,  railway_submission_date_to_DyCFO,  railway_submission_date_to_CCF_Thane, railway_submission_date_to_nodal_officer_CCF_Nagpur, 
    railway_submission_date_to_revenue_secretary_mantralaya, railway_submission_date_to_regional_office_nagpur,  railway_date_of_approval_by_Rregional_Office_agpur,  railway_valuation_by_DyCFO, railway_demanded_amount, railway_approval_for_payment, railway_payment_date, railway_payment_amount, railway_payment_status, railway_possession_date,  railway_possession_status, railway_special_feature, railway_attachment_no
    ,demanded_amount_units,payment_amount_units_railway;
	
	private List<MultipartFile> laFiles;
	private List<LandAcquisition> laFilesList;
	private String[] laFileNames;
	
	public String getPayment_amount_units_railway() {
		return payment_amount_units_railway;
	}

	public void setPayment_amount_units_railway(String payment_amount_units_railway) {
		this.payment_amount_units_railway = payment_amount_units_railway;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getJm_fee_amount_units() {
		return jm_fee_amount_units;
	}

	public void setJm_fee_amount_units(String jm_fee_amount_units) {
		this.jm_fee_amount_units = jm_fee_amount_units;
	}

	public String getAmount_demanded_units() {
		return amount_demanded_units;
	}

	public void setAmount_demanded_units(String amount_demanded_units) {
		this.amount_demanded_units = amount_demanded_units;
	}

	public String getAmount_paid_units() {
		return amount_paid_units;
	}

	public void setAmount_paid_units(String amount_paid_units) {
		this.amount_paid_units = amount_paid_units;
	}

	public String getPayment_amount_units() {
		return payment_amount_units;
	}

	public void setPayment_amount_units(String payment_amount_units) {
		this.payment_amount_units = payment_amount_units;
	}

	public String getBasic_rate_units() {
		return basic_rate_units;
	}

	public void setBasic_rate_units(String basic_rate_units) {
		this.basic_rate_units = basic_rate_units;
	}

	public String getAgriculture_tree_rate_units() {
		return agriculture_tree_rate_units;
	}

	public void setAgriculture_tree_rate_units(String agriculture_tree_rate_units) {
		this.agriculture_tree_rate_units = agriculture_tree_rate_units;
	}

	public String getForest_tree_rate_units() {
		return forest_tree_rate_units;
	}

	public void setForest_tree_rate_units(String forest_tree_rate_units) {
		this.forest_tree_rate_units = forest_tree_rate_units;
	}

	public String getDemanded_amount_units_forest() {
		return demanded_amount_units_forest;
	}

	public void setDemanded_amount_units_forest(String demanded_amount_units_forest) {
		this.demanded_amount_units_forest = demanded_amount_units_forest;
	}

	public String getPayment_amount_units_forest() {
		return payment_amount_units_forest;
	}

	public void setPayment_amount_units_forest(String payment_amount_units_forest) {
		this.payment_amount_units_forest = payment_amount_units_forest;
	}

	public String getDemanded_amount_units() {
		return demanded_amount_units;
	}

	public void setDemanded_amount_units(String demanded_amount_units) {
		this.demanded_amount_units = demanded_amount_units;
	}

	public List<MultipartFile> getLaFiles() {
		return laFiles;
	}

	public void setLaFiles(List<MultipartFile> laFiles) {
		this.laFiles = laFiles;
	}

	public List<LandAcquisition> getLaFilesList() {
		return laFilesList;
	}

	public void setLaFilesList(List<LandAcquisition> laFilesList) {
		this.laFilesList = laFilesList;
	}

	public String[] getLaFileNames() {
		return laFileNames;
	}

	public void setLaFileNames(String[] laFileNames) {
		this.laFileNames = laFileNames;
	}

	public String getGov_remarks() {
		return gov_remarks;
	}

	public void setGov_remarks(String gov_remarks) {
		this.gov_remarks = gov_remarks;
	}

	public String getPrivate_remarks() {
		return private_remarks;
	}

	public void setPrivate_remarks(String private_remarks) {
		this.private_remarks = private_remarks;
	}

	public String getForest_remarks() {
		return forest_remarks;
	}

	public void setForest_remarks(String forest_remarks) {
		this.forest_remarks = forest_remarks;
	}

	public String getRailway_remarks() {
		return railway_remarks;
	}

	public void setRailway_remarks(String railway_remarks) {
		this.railway_remarks = railway_remarks;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getForest_survey_number() {
		return forest_survey_number;
	}

	public void setForest_survey_number(String forest_survey_number) {
		this.forest_survey_number = forest_survey_number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIssue_description() {
		return issue_description;
	}

	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
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

	public String getHod_user_id_fk() {
		return hod_user_id_fk;
	}

	public void setHod_user_id_fk(String hod_user_id_fk) {
		this.hod_user_id_fk = hod_user_id_fk;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	private MultipartFile landAcquisitionFile;
	
	
	public String getIs_there_issue() {
		return is_there_issue;
	}

	public void setIs_there_issue(String is_there_issue) {
		this.is_there_issue = is_there_issue;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	
	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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

	public MultipartFile getLandAcquisitionFile() {
		return landAcquisitionFile;
	}

	public void setLandAcquisitionFile(MultipartFile landAcquisitionFile) {
		this.landAcquisitionFile = landAcquisitionFile;
	}

	public String getPrivate_area_acquired() {
		return private_area_acquired;
	}

	public void setPrivate_area_acquired(String private_area_acquired) {
		this.private_area_acquired = private_area_acquired;
	}

	public String getPrivate_attachment_no() {
		return private_attachment_no;
	}

	public void setPrivate_attachment_no(String private_attachment_no) {
		this.private_attachment_no = private_attachment_no;
	}

	public String getForest_tree_survey() {
		return forest_tree_survey;
	}

	public void setForest_tree_survey(String forest_tree_survey) {
		this.forest_tree_survey = forest_tree_survey;
	}

	public String getForest_tree_valuation() {
		return forest_tree_valuation;
	}

	public void setForest_tree_valuation(String forest_tree_valuation) {
		this.forest_tree_valuation = forest_tree_valuation;
	}

	public String getForest_tree_valuation_status_fk() {
		return forest_tree_valuation_status_fk;
	}

	public void setForest_tree_valuation_status_fk(String forest_tree_valuation_status_fk) {
		this.forest_tree_valuation_status_fk = forest_tree_valuation_status_fk;
	}

	public String getHorticulture_tree_survey() {
		return horticulture_tree_survey;
	}

	public void setHorticulture_tree_survey(String horticulture_tree_survey) {
		this.horticulture_tree_survey = horticulture_tree_survey;
	}

	public String getHorticulture_tree_valuation() {
		return horticulture_tree_valuation;
	}

	public void setHorticulture_tree_valuation(String horticulture_tree_valuation) {
		this.horticulture_tree_valuation = horticulture_tree_valuation;
	}

	public String getStructure_survey() {
		return structure_survey;
	}

	public void setStructure_survey(String structure_survey) {
		this.structure_survey = structure_survey;
	}

	public String getStructure_valuation() {
		return structure_valuation;
	}

	public void setStructure_valuation(String structure_valuation) {
		this.structure_valuation = structure_valuation;
	}

	public String getBorewell_survey() {
		return borewell_survey;
	}

	public void setBorewell_survey(String borewell_survey) {
		this.borewell_survey = borewell_survey;
	}

	public String getBorewell_valuation() {
		return borewell_valuation;
	}

	public void setBorewell_valuation(String borewell_valuation) {
		this.borewell_valuation = borewell_valuation;
	}

	public String getHorticulture_tree_valuation_status_fk() {
		return horticulture_tree_valuation_status_fk;
	}

	public void setHorticulture_tree_valuation_status_fk(String horticulture_tree_valuation_status_fk) {
		this.horticulture_tree_valuation_status_fk = horticulture_tree_valuation_status_fk;
	}

	public String getStructure_valuation_status_fk() {
		return structure_valuation_status_fk;
	}

	public void setStructure_valuation_status_fk(String structure_valuation_status_fk) {
		this.structure_valuation_status_fk = structure_valuation_status_fk;
	}

	public String getBorewell_valuation_status_fk() {
		return borewell_valuation_status_fk;
	}

	public void setBorewell_valuation_status_fk(String borewell_valuation_status_fk) {
		this.borewell_valuation_status_fk = borewell_valuation_status_fk;
	}

	public String getRfp_to_adtp_status_fk() {
		return rfp_to_adtp_status_fk;
	}

	public void setRfp_to_adtp_status_fk(String rfp_to_adtp_status_fk) {
		this.rfp_to_adtp_status_fk = rfp_to_adtp_status_fk;
	}

	public String getDate_of_rfp_to_adtp() {
		return date_of_rfp_to_adtp;
	}

	public void setDate_of_rfp_to_adtp(String date_of_rfp_to_adtp) {
		this.date_of_rfp_to_adtp = date_of_rfp_to_adtp;
	}

	public String getDate_of_rate_fixation_of_land() {
		return date_of_rate_fixation_of_land;
	}

	public void setDate_of_rate_fixation_of_land(String date_of_rate_fixation_of_land) {
		this.date_of_rate_fixation_of_land = date_of_rate_fixation_of_land;
	}

	public String getSdo_demand_for_payment() {
		return sdo_demand_for_payment;
	}

	public void setSdo_demand_for_payment(String sdo_demand_for_payment) {
		this.sdo_demand_for_payment = sdo_demand_for_payment;
	}

	public String getDate_of_approval_for_payment() {
		return date_of_approval_for_payment;
	}

	public void setDate_of_approval_for_payment(String date_of_approval_for_payment) {
		this.date_of_approval_for_payment = date_of_approval_for_payment;
	}

	public String getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}

	public String getPrivate_payment_date() {
		return private_payment_date;
	}

	public void setPrivate_payment_date(String private_payment_date) {
		this.private_payment_date = private_payment_date;
	}

	public String getForest_valuation_by_dycfo() {
		return forest_valuation_by_dycfo;
	}

	public void setForest_valuation_by_dycfo(String forest_valuation_by_dycfo) {
		this.forest_valuation_by_dycfo = forest_valuation_by_dycfo;
	}

	public String getForest_demanded_amount() {
		return forest_demanded_amount;
	}

	public void setForest_demanded_amount(String forest_demanded_amount) {
		this.forest_demanded_amount = forest_demanded_amount;
	}

	public String getForest_payment_amount() {
		return forest_payment_amount;
	}

	public void setForest_payment_amount(String forest_payment_amount) {
		this.forest_payment_amount = forest_payment_amount;
	}

	public String getForest_attachment_No() {
		return forest_attachment_No;
	}

	public void setForest_attachment_No(String forest_attachment_No) {
		this.forest_attachment_No = forest_attachment_No;
	}

	public String getForest_submission_date_to_dycfo() {
		return forest_submission_date_to_dycfo;
	}

	public void setForest_submission_date_to_dycfo(String forest_submission_date_to_dycfo) {
		this.forest_submission_date_to_dycfo = forest_submission_date_to_dycfo;
	}

	public String getForest_submission_date_to_ccf_thane() {
		return forest_submission_date_to_ccf_thane;
	}

	public void setForest_submission_date_to_ccf_thane(String forest_submission_date_to_ccf_thane) {
		this.forest_submission_date_to_ccf_thane = forest_submission_date_to_ccf_thane;
	}

	public String getForest_online_submission() {
		return forest_online_submission;
	}

	public void setForest_online_submission(String forest_online_submission) {
		this.forest_online_submission = forest_online_submission;
	}

	public String getForest_area_acquired() {
		return forest_area_acquired;
	}

	public void setForest_area_acquired(String forest_area_acquired) {
		this.forest_area_acquired = forest_area_acquired;
	}

	public String getLa_sub_category_fk() {
		return la_sub_category_fk;
	}

	public void setLa_sub_category_fk(String la_sub_category_fk) {
		this.la_sub_category_fk = la_sub_category_fk;
	}

	public String getLa_sub_category() {
		return la_sub_category;
	}

	public void setLa_sub_category(String la_sub_category) {
		this.la_sub_category = la_sub_category;
	}

	public String getLa_category() {
		return la_category;
	}

	public void setLa_category(String la_category) {
		this.la_category = la_category;
	}

	public String getRailway_area_to_be_acquired() {
		return railway_area_to_be_acquired;
	}

	

	public String getRailway_submission_date_to_nodal_officer_CCF_Nagpur() {
		return railway_submission_date_to_nodal_officer_CCF_Nagpur;
	}

	public void setRailway_submission_date_to_nodal_officer_CCF_Nagpur(
			String railway_submission_date_to_nodal_officer_CCF_Nagpur) {
		this.railway_submission_date_to_nodal_officer_CCF_Nagpur = railway_submission_date_to_nodal_officer_CCF_Nagpur;
	}

	public String getRailway_submission_date_to_revenue_secretary_mantralaya() {
		return railway_submission_date_to_revenue_secretary_mantralaya;
	}

	public void setRailway_submission_date_to_revenue_secretary_mantralaya(
			String railway_submission_date_to_revenue_secretary_mantralaya) {
		this.railway_submission_date_to_revenue_secretary_mantralaya = railway_submission_date_to_revenue_secretary_mantralaya;
	}

	public String getRailway_submission_date_to_regional_office_nagpur() {
		return railway_submission_date_to_regional_office_nagpur;
	}

	public void setRailway_submission_date_to_regional_office_nagpur(
			String railway_submission_date_to_regional_office_nagpur) {
		this.railway_submission_date_to_regional_office_nagpur = railway_submission_date_to_regional_office_nagpur;
	}


	public String getRailway_demanded_amount() {
		return railway_demanded_amount;
	}

	public void setRailway_demanded_amount(String railway_demanded_amount) {
		this.railway_demanded_amount = railway_demanded_amount;
	}

	public String getRailway_approval_for_payment() {
		return railway_approval_for_payment;
	}

	public void setRailway_approval_for_payment(String railway_approval_for_payment) {
		this.railway_approval_for_payment = railway_approval_for_payment;
	}

	public String getRailway_payment_date() {
		return railway_payment_date;
	}

	public void setRailway_payment_date(String railway_payment_date) {
		this.railway_payment_date = railway_payment_date;
	}

	public String getRailway_payment_amount() {
		return railway_payment_amount;
	}

	public void setRailway_payment_amount(String railway_payment_amount) {
		this.railway_payment_amount = railway_payment_amount;
	}

	
	public String getRailway_online_submission() {
		return railway_online_submission;
	}

	public void setRailway_online_submission(String railway_online_submission) {
		this.railway_online_submission = railway_online_submission;
	}

	public String getRailway_area_acquired() {
		return railway_area_acquired;
	}

	public void setRailway_area_acquired(String railway_area_acquired) {
		this.railway_area_acquired = railway_area_acquired;
	}

	public String getRailway_submission_date_to_DyCFO() {
		return railway_submission_date_to_DyCFO;
	}

	public void setRailway_submission_date_to_DyCFO(String railway_submission_date_to_DyCFO) {
		this.railway_submission_date_to_DyCFO = railway_submission_date_to_DyCFO;
	}

	public String getRailway_submission_date_to_CCF_Thane() {
		return railway_submission_date_to_CCF_Thane;
	}

	public void setRailway_submission_date_to_CCF_Thane(String railway_submission_date_to_CCF_Thane) {
		this.railway_submission_date_to_CCF_Thane = railway_submission_date_to_CCF_Thane;
	}

	public String getRailway_date_of_approval_by_Rregional_Office_agpur() {
		return railway_date_of_approval_by_Rregional_Office_agpur;
	}

	public void setRailway_date_of_approval_by_Rregional_Office_agpur(
			String railway_date_of_approval_by_Rregional_Office_agpur) {
		this.railway_date_of_approval_by_Rregional_Office_agpur = railway_date_of_approval_by_Rregional_Office_agpur;
	}

	public String getRailway_valuation_by_DyCFO() {
		return railway_valuation_by_DyCFO;
	}

	public void setRailway_valuation_by_DyCFO(String railway_valuation_by_DyCFO) {
		this.railway_valuation_by_DyCFO = railway_valuation_by_DyCFO;
	}

	public String getRailway_payment_status() {
		return railway_payment_status;
	}

	public void setRailway_payment_status(String railway_payment_status) {
		this.railway_payment_status = railway_payment_status;
	}

	public String getRailway_possession_status() {
		return railway_possession_status;
	}

	public void setRailway_possession_status(String railway_possession_status) {
		this.railway_possession_status = railway_possession_status;
	}

	public void setRailway_area_to_be_acquired(String railway_area_to_be_acquired) {
		this.railway_area_to_be_acquired = railway_area_to_be_acquired;
	}

	public String getRailway_possession_date() {
		return railway_possession_date;
	}

	public void setRailway_possession_date(String railway_possession_date) {
		this.railway_possession_date = railway_possession_date;
	}

	
	public String getRailway_special_feature() {
		return railway_special_feature;
	}

	public void setRailway_special_feature(String railway_special_feature) {
		this.railway_special_feature = railway_special_feature;
	}

	public String getRailway_attachment_no() {
		return railway_attachment_no;
	}

	public void setRailway_attachment_no(String railway_attachment_no) {
		this.railway_attachment_no = railway_attachment_no;
	}

	public String getPrivate_area_to_be_acquired() {
		return private_area_to_be_acquired;
	}

	public void setPrivate_area_to_be_acquired(String private_area_to_be_acquired) {
		this.private_area_to_be_acquired = private_area_to_be_acquired;
	}

	public String getForest_area_to_be_acquired() {
		return forest_area_to_be_acquired;
	}

	public void setForest_area_to_be_acquired(String forest_area_to_be_acquired) {
		this.forest_area_to_be_acquired = forest_area_to_be_acquired;
	}

	

	public String getForest_submission_date_to_nodal_officer() {
		return forest_submission_date_to_nodal_officer;
	}

	public void setForest_submission_date_to_nodal_officer(String forest_submission_date_to_nodal_officer) {
		this.forest_submission_date_to_nodal_officer = forest_submission_date_to_nodal_officer;
	}


	public String getForest_submission_date_to_revenue_secretary_mantralaya() {
		return forest_submission_date_to_revenue_secretary_mantralaya;
	}

	public void setForest_submission_date_to_revenue_secretary_mantralaya(
			String forest_submission_date_to_revenue_secretary_mantralaya) {
		this.forest_submission_date_to_revenue_secretary_mantralaya = forest_submission_date_to_revenue_secretary_mantralaya;
	}

	public String getForest_submission_date_to_regional_office_nagpur() {
		return forest_submission_date_to_regional_office_nagpur;
	}

	public void setForest_submission_date_to_regional_office_nagpur(
			String forest_submission_date_to_regional_office_nagpur) {
		this.forest_submission_date_to_regional_office_nagpur = forest_submission_date_to_regional_office_nagpur;
	}

	
	public String getForest_date_of_approval_by_regional_office_nagpur() {
		return forest_date_of_approval_by_regional_office_nagpur;
	}

	public void setForest_date_of_approval_by_regional_office_nagpur(
			String forest_date_of_approval_by_regional_office_nagpur) {
		this.forest_date_of_approval_by_regional_office_nagpur = forest_date_of_approval_by_regional_office_nagpur;
	}

	
	

	public String getForest_approval_for_payment() {
		return forest_approval_for_payment;
	}

	public void setForest_approval_for_payment(String forest_approval_for_payment) {
		this.forest_approval_for_payment = forest_approval_for_payment;
	}

	public String getForest_payment_date() {
		return forest_payment_date;
	}

	public void setForest_payment_date(String forest_payment_date) {
		this.forest_payment_date = forest_payment_date;
	}

	public String getForest_possession_date() {
		return forest_possession_date;
	}

	public void setForest_possession_date(String forest_possession_date) {
		this.forest_possession_date = forest_possession_date;
	}

	public String getForest_possession_status_fk() {
		return forest_possession_status_fk;
	}

	public void setForest_possession_status_fk(String forest_possession_status_fk) {
		this.forest_possession_status_fk = forest_possession_status_fk;
	}

	public String getForest_payment_status_fk() {
		return forest_payment_status_fk;
	}

	public void setForest_payment_status_fk(String forest_payment_status_fk) {
		this.forest_payment_status_fk = forest_payment_status_fk;
	}

	public String getForest_special_feature() {
		return forest_special_feature;
	}

	public void setForest_special_feature(String forest_special_feature) {
		this.forest_special_feature = forest_special_feature;
	}


	public String getName_of_the_owner() {
		return name_of_the_owner;
	}

	public void setName_of_the_owner(String name_of_the_owner) {
		this.name_of_the_owner = name_of_the_owner;
	}

	public String getBasic_rate() {
		return basic_rate;
	}

	public void setBasic_rate(String basic_rate) {
		this.basic_rate = basic_rate;
	}

	public String getAttachment_no() {
		return attachment_no;
	}

	public void setAttachment_no(String attachment_no) {
		this.attachment_no = attachment_no;
	}

	public String getAgriculture_tree_nos() {
		return agriculture_tree_nos;
	}

	public void setAgriculture_tree_nos(String agriculture_tree_nos) {
		this.agriculture_tree_nos = agriculture_tree_nos;
	}

	public String getAgriculture_tree_rate() {
		return agriculture_tree_rate;
	}

	public void setAgriculture_tree_rate(String agriculture_tree_rate) {
		this.agriculture_tree_rate = agriculture_tree_rate;
	}

	public String getForest_tree_nos() {
		return forest_tree_nos;
	}

	public void setForest_tree_nos(String forest_tree_nos) {
		this.forest_tree_nos = forest_tree_nos;
	}

	public String getForest_tree_rate() {
		return forest_tree_rate;
	}

	public void setForest_tree_rate(String forest_tree_rate) {
		this.forest_tree_rate = forest_tree_rate;
	}

	public String getConsent_from_owner() {
		return consent_from_owner;
	}

	public void setConsent_from_owner(String consent_from_owner) {
		this.consent_from_owner = consent_from_owner;
	}

	public String getLegal_search_report() {
		return legal_search_report;
	}

	public void setLegal_search_report(String legal_search_report) {
		this.legal_search_report = legal_search_report;
	}

	public String getDate_of_registration() {
		return date_of_registration;
	}

	public void setDate_of_registration(String date_of_registration) {
		this.date_of_registration = date_of_registration;
	}

	public String getDate_of_possession() {
		return date_of_possession;
	}

	public void setDate_of_possession(String date_of_possession) {
		this.date_of_possession = date_of_possession;
	}

	public String getPrivate_possession_status_fk() {
		return private_possession_status_fk;
	}

	public void setPrivate_possession_status_fk(String private_possession_status_fk) {
		this.private_possession_status_fk = private_possession_status_fk;
	}

	public String getPrivate_special_feature() {
		return private_special_feature;
	}

	public void setPrivate_special_feature(String private_special_feature) {
		this.private_special_feature = private_special_feature;
	}

	public String getHundred_percent_Solatium() {
		return hundred_percent_Solatium;
	}

	public void setHundred_percent_Solatium(String hundred_percent_Solatium) {
		this.hundred_percent_Solatium = hundred_percent_Solatium;
	}

	public String getExtra_25_percent() {
		return extra_25_percent;
	}

	public void setExtra_25_percent(String extra_25_percent) {
		this.extra_25_percent = extra_25_percent;
	}

	public String getTotal_rate_divide_m2() {
		return total_rate_divide_m2;
	}

	public void setTotal_rate_divide_m2(String total_rate_divide_m2) {
		this.total_rate_divide_m2 = total_rate_divide_m2;
	}

	public String getLand_compensation() {
		return land_compensation;
	}

	public void setLand_compensation(String land_compensation) {
		this.land_compensation = land_compensation;
	}

	public String getAgriculture_tree_compensation() {
		return agriculture_tree_compensation;
	}

	public void setAgriculture_tree_compensation(String agriculture_tree_compensation) {
		this.agriculture_tree_compensation = agriculture_tree_compensation;
	}

	public String getForest_tree_compensation() {
		return forest_tree_compensation;
	}

	public void setForest_tree_compensation(String forest_tree_compensation) {
		this.forest_tree_compensation = forest_tree_compensation;
	}

	public String getStructure_compensation() {
		return structure_compensation;
	}

	public void setStructure_compensation(String structure_compensation) {
		this.structure_compensation = structure_compensation;
	}

	public String getBorewell_compensation() {
		return borewell_compensation;
	}

	public void setBorewell_compensation(String borewell_compensation) {
		this.borewell_compensation = borewell_compensation;
	}

	public String getTotal_compensation() {
		return total_compensation;
	}

	public void setTotal_compensation(String total_compensation) {
		this.total_compensation = total_compensation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLa_id_fk() {
		return la_id_fk;
	}

	public void setLa_id_fk(String la_id_fk) {
		this.la_id_fk = la_id_fk;
	}

	public String getArea_to_be_acquired() {
		return area_to_be_acquired;
	}

	public void setArea_to_be_acquired(String area_to_be_acquired) {
		this.area_to_be_acquired = area_to_be_acquired;
	}

	public String getProposal_submission() {
		return proposal_submission;
	}

	public void setProposal_submission(String proposal_submission) {
		this.proposal_submission = proposal_submission;
	}

	public String getProposal_submission_status_fk() {
		return proposal_submission_status_fk;
	}

	public void setProposal_submission_status_fk(String proposal_submission_status_fk) {
		this.proposal_submission_status_fk = proposal_submission_status_fk;
	}

	public String getValuation_date() {
		return valuation_date;
	}

	public void setValuation_date(String valuation_date) {
		this.valuation_date = valuation_date;
	}

	public String getLetter_for_payment() {
		return letter_for_payment;
	}

	public void setLetter_for_payment(String letter_for_payment) {
		this.letter_for_payment = letter_for_payment;
	}

	public String getAmount_demanded() {
		return amount_demanded;
	}

	public void setAmount_demanded(String amount_demanded) {
		this.amount_demanded = amount_demanded;
	}

	public String getLfp_status_fk() {
		return lfp_status_fk;
	}

	public void setLfp_status_fk(String lfp_status_fk) {
		this.lfp_status_fk = lfp_status_fk;
	}

	public String getApproval_for_payment() {
		return approval_for_payment;
	}

	public void setApproval_for_payment(String approval_for_payment) {
		this.approval_for_payment = approval_for_payment;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(String amount_paid) {
		this.amount_paid = amount_paid;
	}

	public String getPayment_status_fk() {
		return payment_status_fk;
	}

	public void setPayment_status_fk(String payment_status_fk) {
		this.payment_status_fk = payment_status_fk;
	}

	public String getPossession_date() {
		return possession_date;
	}

	public void setPossession_date(String possession_date) {
		this.possession_date = possession_date;
	}

	public String getPossession_status_fk() {
		return possession_status_fk;
	}

	public void setPossession_status_fk(String possession_status_fk) {
		this.possession_status_fk = possession_status_fk;
	}

	public String getSpecial_feature() {
		return special_feature;
	}

	public void setSpecial_feature(String special_feature) {
		this.special_feature = special_feature;
	}

	public String getArea_acquired() {
		return area_acquired;
	}

	public void setArea_acquired(String area_acquired) {
		this.area_acquired = area_acquired;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_of() {
		return status_of;
	}

	public void setStatus_of(String status_of) {
		this.status_of = status_of;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
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

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getLa_id() {
		return la_id;
	}

	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}

	public String getSurvey_number() {
		return survey_number;
	}

	public void setSurvey_number(String survey_number) {
		this.survey_number = survey_number;
	}

	public String getVillage_id() {
		return village_id;
	}

	public void setVillage_id(String village_id) {
		this.village_id = village_id;
	}

	public String getType_of_land() {
		return type_of_land;
	}

	public void setType_of_land(String type_of_land) {
		this.type_of_land = type_of_land;
	}

	public String getSub_category_of_land() {
		return sub_category_of_land;
	}

	public void setSub_category_of_land(String sub_category_of_land) {
		this.sub_category_of_land = sub_category_of_land;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTaluka() {
		return taluka;
	}

	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}

	public String getDy_slr() {
		return dy_slr;
	}

	public void setDy_slr(String dy_slr) {
		this.dy_slr = dy_slr;
	}

	public String getSdo() {
		return sdo;
	}

	public void setSdo(String sdo) {
		this.sdo = sdo;
	}

	public String getCollector() {
		return collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
	}

	public String getProposal_submission_date_to_collector() {
		return proposal_submission_date_to_collector;
	}

	public void setProposal_submission_date_to_collector(String proposal_submission_date_to_collector) {
		this.proposal_submission_date_to_collector = proposal_submission_date_to_collector;
	}

	public String getArea_of_plot() {
		return area_of_plot;
	}

	public void setArea_of_plot(String area_of_plot) {
		this.area_of_plot = area_of_plot;
	}

	public String getJm_fee_amount() {
		return jm_fee_amount;
	}

	public void setJm_fee_amount(String jm_fee_amount) {
		this.jm_fee_amount = jm_fee_amount;
	}

	public String getChainage_from() {
		return chainage_from;
	}

	public void setChainage_from(String chainage_from) {
		this.chainage_from = chainage_from;
	}

	public String getChainage_to() {
		return chainage_to;
	}

	public void setChainage_to(String chainage_to) {
		this.chainage_to = chainage_to;
	}

	public String getJm_fee_letter_received_date() {
		return jm_fee_letter_received_date;
	}

	public void setJm_fee_letter_received_date(String jm_fee_letter_received_date) {
		this.jm_fee_letter_received_date = jm_fee_letter_received_date;
	}

	public String getJm_fee_paid_date() {
		return jm_fee_paid_date;
	}

	public void setJm_fee_paid_date(String jm_fee_paid_date) {
		this.jm_fee_paid_date = jm_fee_paid_date;
	}

	public String getJm_start_date() {
		return jm_start_date;
	}

	public void setJm_start_date(String jm_start_date) {
		this.jm_start_date = jm_start_date;
	}

	public String getJm_completion_date() {
		return jm_completion_date;
	}

	public void setJm_completion_date(String jm_completion_date) {
		this.jm_completion_date = jm_completion_date;
	}

	public String getJm_sheet_date_to_sdo() {
		return jm_sheet_date_to_sdo;
	}

	public void setJm_sheet_date_to_sdo(String jm_sheet_date_to_sdo) {
		this.jm_sheet_date_to_sdo = jm_sheet_date_to_sdo;
	}

	public String getJm_remarks() {
		return jm_remarks;
	}

	public void setJm_remarks(String jm_remarks) {
		this.jm_remarks = jm_remarks;
	}

	public String getJm_approval() {
		return jm_approval;
	}

	public void setJm_approval(String jm_approval) {
		this.jm_approval = jm_approval;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

}
