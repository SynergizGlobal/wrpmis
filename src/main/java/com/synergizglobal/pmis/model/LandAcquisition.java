package com.synergizglobal.pmis.model;

public class LandAcquisition {
	
	private String work_id, la_id, work_id_fk,work_name,work_short_name,survey_number, village_id,status, status_of, type_of_land, sub_category_of_land, village, taluka, dy_slr, sdo, collector, proposal_submission_date_to_collector, area_of_plot, jm_fee_amount, chainage_from, chainage_to, jm_fee_letter_received_date, jm_fee_paid_date, 
	jm_start_date, jm_completion_date, jm_sheet_date_to_sdo, jm_remarks, jm_approval, issues,
	
	//gov
	id, la_id_fk, area_to_be_acquired, proposal_submission, proposal_submission_status_fk, valuation_date, letter_for_payment, amount_demanded, lfp_status_fk, approval_for_payment, payment_date, amount_paid, payment_status_fk, possession_date, possession_status_fk, special_feature,
    area_acquired, remarks,
    
    //private
    name_of_the_owner,private_area_to_be_acquired, basic_rate, attachment_no, agriculture_tree_nos, agriculture_tree_rate, forest_tree_nos, forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, 
    private_possession_status_fk, private_special_feature, hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2, land_compensation, agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation
    
    //forest
    , forest_area_to_be_acquired, on_line_submission, submission_date_to_dycfo, submission_date_to_ccf_thane, submission_date_to_nodal_officer, submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur, valuation_by_dycfo, demanded_amount, payment_amount,
    forest_approval_for_payment, forest_payment_date, forest_possession_date, forest_possession_status_fk, forest_payment_status_fk, forest_special_feature, attachment_No,
   
    //railway
    railway_area_to_be_acquired, online_submission, submission_date_to_DyCFO, submission_date_to_CCF_Thane, railway_submission_date_to_nodal_officer_CCF_Nagpur, 
    railway_submission_date_to_revenue_secretary_mantralaya, railway_submission_date_to_regional_office_nagpur, date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO, railway_demanded_amount, railway_approval_for_payment, railway_payment_date, railway_payment_amount, payment_status, railway_possession_date, possession_status, railway_special_feature, railway_attachment_no
    ;

	public String getRailway_area_to_be_acquired() {
		return railway_area_to_be_acquired;
	}

	public void setRailway_area_to_be_acquired(String railway_area_to_be_acquired) {
		this.railway_area_to_be_acquired = railway_area_to_be_acquired;
	}

	public String getOnline_submission() {
		return online_submission;
	}

	public void setOnline_submission(String online_submission) {
		this.online_submission = online_submission;
	}

	public String getSubmission_date_to_DyCFO() {
		return submission_date_to_DyCFO;
	}

	public void setSubmission_date_to_DyCFO(String submission_date_to_DyCFO) {
		this.submission_date_to_DyCFO = submission_date_to_DyCFO;
	}

	public String getSubmission_date_to_CCF_Thane() {
		return submission_date_to_CCF_Thane;
	}

	public void setSubmission_date_to_CCF_Thane(String submission_date_to_CCF_Thane) {
		this.submission_date_to_CCF_Thane = submission_date_to_CCF_Thane;
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

	public String getDate_of_approval_by_Rregional_Office_agpur() {
		return date_of_approval_by_Rregional_Office_agpur;
	}

	public void setDate_of_approval_by_Rregional_Office_agpur(String date_of_approval_by_Rregional_Office_agpur) {
		this.date_of_approval_by_Rregional_Office_agpur = date_of_approval_by_Rregional_Office_agpur;
	}

	public String getValuation_by_DyCFO() {
		return valuation_by_DyCFO;
	}

	public void setValuation_by_DyCFO(String valuation_by_DyCFO) {
		this.valuation_by_DyCFO = valuation_by_DyCFO;
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

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getRailway_possession_date() {
		return railway_possession_date;
	}

	public void setRailway_possession_date(String railway_possession_date) {
		this.railway_possession_date = railway_possession_date;
	}

	public String getPossession_status() {
		return possession_status;
	}

	public void setPossession_status(String possession_status) {
		this.possession_status = possession_status;
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

	public String getOn_line_submission() {
		return on_line_submission;
	}

	public void setOn_line_submission(String on_line_submission) {
		this.on_line_submission = on_line_submission;
	}

	public String getSubmission_date_to_dycfo() {
		return submission_date_to_dycfo;
	}

	public void setSubmission_date_to_dycfo(String submission_date_to_dycfo) {
		this.submission_date_to_dycfo = submission_date_to_dycfo;
	}

	public String getSubmission_date_to_ccf_thane() {
		return submission_date_to_ccf_thane;
	}

	public void setSubmission_date_to_ccf_thane(String submission_date_to_ccf_thane) {
		this.submission_date_to_ccf_thane = submission_date_to_ccf_thane;
	}

	public String getSubmission_date_to_nodal_officer() {
		return submission_date_to_nodal_officer;
	}

	public void setSubmission_date_to_nodal_officer(String submission_date_to_nodal_officer) {
		this.submission_date_to_nodal_officer = submission_date_to_nodal_officer;
	}

	public String getSubmission_date_to_revenue_secretary_mantralaya() {
		return submission_date_to_revenue_secretary_mantralaya;
	}

	public void setSubmission_date_to_revenue_secretary_mantralaya(String submission_date_to_revenue_secretary_mantralaya) {
		this.submission_date_to_revenue_secretary_mantralaya = submission_date_to_revenue_secretary_mantralaya;
	}

	public String getSubmission_date_to_regional_office_nagpur() {
		return submission_date_to_regional_office_nagpur;
	}

	public void setSubmission_date_to_regional_office_nagpur(String submission_date_to_regional_office_nagpur) {
		this.submission_date_to_regional_office_nagpur = submission_date_to_regional_office_nagpur;
	}

	public String getDate_of_approval_by_regional_office_nagpur() {
		return date_of_approval_by_regional_office_nagpur;
	}

	public void setDate_of_approval_by_regional_office_nagpur(String date_of_approval_by_regional_office_nagpur) {
		this.date_of_approval_by_regional_office_nagpur = date_of_approval_by_regional_office_nagpur;
	}

	public String getValuation_by_dycfo() {
		return valuation_by_dycfo;
	}

	public void setValuation_by_dycfo(String valuation_by_dycfo) {
		this.valuation_by_dycfo = valuation_by_dycfo;
	}

	public String getDemanded_amount() {
		return demanded_amount;
	}

	public void setDemanded_amount(String demanded_amount) {
		this.demanded_amount = demanded_amount;
	}

	public String getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
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

	public String getAttachment_No() {
		return attachment_No;
	}

	public void setAttachment_No(String attachment_No) {
		this.attachment_No = attachment_No;
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
