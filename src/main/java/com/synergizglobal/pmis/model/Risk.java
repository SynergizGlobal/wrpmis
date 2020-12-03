package com.synergizglobal.pmis.model;
import java.util.List;


public class Risk {
	
	private String risk_id_pk,id, project_name,work_name,project_id_fk, work_id_fk, risk_id, sub_area_fk, date_of_identification,area,risk_revision_id, risk_id_pk_fk, date,
	priority, probability_fk,item_no, impact_fk, owner, responsible_person,assessment_date, mitigation_plan, action_taken, attachment,sub_area, risk_area_fk,classification,atr_date,work_id;


	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public String getId() {
		return id;
	}

	public String getAssessment_date() {
		return assessment_date;
	}

	public void setAssessment_date(String assessment_date) {
		this.assessment_date = assessment_date;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String [] risk_revision_ids, risk_id_pk_fks, dates, prioritys, probability_fks, impact_fks,
	owners, responsible_persons, mitigation_plans, action_takens, attachments,atr_dates,rowCounts;
	
	private List<Risk> risks; 
	private List<Risk> riskActions; 
	
	
	public String[] getRowCounts() {
		return rowCounts;
	}

	public void setRowCounts(String[] rowCounts) {
		this.rowCounts = rowCounts;
	}

	public List<Risk> getRiskActions() {
		return riskActions;
	}

	public void setRiskActions(List<Risk> riskActions) {
		this.riskActions = riskActions;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getAtr_date() {
		return atr_date;
	}

	public void setAtr_date(String atr_date) {
		this.atr_date = atr_date;
	}

	public String[] getAtr_dates() {
		return atr_dates;
	}

	public void setAtr_dates(String[] atr_dates) {
		this.atr_dates = atr_dates;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}


	public List<Risk> getRisks() {
		return risks;
	}

	public void setRisks(List<Risk> risks) {
		this.risks = risks;
	}

	public String[] getRisk_revision_ids() {
		return risk_revision_ids;
	}

	public void setRisk_revision_ids(String[] risk_revision_ids) {
		this.risk_revision_ids = risk_revision_ids;
	}

	public String[] getRisk_id_pk_fks() {
		return risk_id_pk_fks;
	}

	public void setRisk_id_pk_fks(String[] risk_id_pk_fks) {
		this.risk_id_pk_fks = risk_id_pk_fks;
	}

	public String[] getDates() {
		return dates;
	}

	public void setDates(String[] dates) {
		this.dates = dates;
	}

	public String[] getPrioritys() {
		return prioritys;
	}

	public void setPrioritys(String[] prioritys) {
		this.prioritys = prioritys;
	}

	public String[] getProbability_fks() {
		return probability_fks;
	}

	public void setProbability_fks(String[] probability_fks) {
		this.probability_fks = probability_fks;
	}

	public String[] getImpact_fks() {
		return impact_fks;
	}

	public void setImpact_fks(String[] impact_fks) {
		this.impact_fks = impact_fks;
	}

	public String[] getOwners() {
		return owners;
	}

	public void setOwners(String[] owners) {
		this.owners = owners;
	}

	public String[] getResponsible_persons() {
		return responsible_persons;
	}

	public void setResponsible_persons(String[] responsible_persons) {
		this.responsible_persons = responsible_persons;
	}

	public String[] getMitigation_plans() {
		return mitigation_plans;
	}

	public void setMitigation_plans(String[] mitigation_plans) {
		this.mitigation_plans = mitigation_plans;
	}

	public String[] getAction_takens() {
		return action_takens;
	}

	public void setAction_takens(String[] action_takens) {
		this.action_takens = action_takens;
	}

	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}

	public String getRisk_id_pk() {
		return risk_id_pk;
	}

	public void setRisk_id_pk(String risk_id_pk) {
		this.risk_id_pk = risk_id_pk;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getRisk_id() {
		return risk_id;
	}

	public void setRisk_id(String risk_id) {
		this.risk_id = risk_id;
	}

	public String getSub_area_fk() {
		return sub_area_fk;
	}

	public void setSub_area_fk(String sub_area_fk) {
		this.sub_area_fk = sub_area_fk;
	}

	public String getDate_of_identification() {
		return date_of_identification;
	}

	public void setDate_of_identification(String date_of_identification) {
		this.date_of_identification = date_of_identification;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRisk_revision_id() {
		return risk_revision_id;
	}

	public void setRisk_revision_id(String risk_revision_id) {
		this.risk_revision_id = risk_revision_id;
	}

	public String getRisk_id_pk_fk() {
		return risk_id_pk_fk;
	}

	public void setRisk_id_pk_fk(String risk_id_pk_fk) {
		this.risk_id_pk_fk = risk_id_pk_fk;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProbability_fk() {
		return probability_fk;
	}

	public void setProbability_fk(String probability_fk) {
		this.probability_fk = probability_fk;
	}

	public String getImpact_fk() {
		return impact_fk;
	}

	public void setImpact_fk(String impact_fk) {
		this.impact_fk = impact_fk;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getResponsible_person() {
		return responsible_person;
	}

	public void setResponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
	}

	public String getMitigation_plan() {
		return mitigation_plan;
	}

	public void setMitigation_plan(String mitigation_plan) {
		this.mitigation_plan = mitigation_plan;
	}

	public String getAction_taken() {
		return action_taken;
	}

	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getSub_area() {
		return sub_area;
	}

	public void setSub_area(String sub_area) {
		this.sub_area = sub_area;
	}

	public String getRisk_area_fk() {
		return risk_area_fk;
	}

	public void setRisk_area_fk(String risk_area_fk) {
		this.risk_area_fk = risk_area_fk;
	}

}
