package com.synergizglobal.pmis.model;

import java.util.List;

public class RiskReport {
	private String risk_revision_id,work_id_fk,sub_work,risk_id,date,area,area_item_no,sub_area,sub_area_item_no,revision_id,assessment_date,
	priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,action_taken,atr_date,
	work_id,work_short_name,project_id,project_name,work_name,project_id_fk,estimatedOrRevisedCost,estimatedOrRevisedDate,
	last_assessment_date,risk_score,total_risk_rating,total_high_risks,total_substantial_risks,total_moderate_risks,total_priority_risks,atr_submitted;
	
	private List<RiskReport> areaList;
	private List<RiskReport> subAreaList; 


	public String getLast_assessment_date() {
		return last_assessment_date;
	}

	public void setLast_assessment_date(String last_assessment_date) {
		this.last_assessment_date = last_assessment_date;
	}

	public String getRisk_score() {
		return risk_score;
	}

	public void setRisk_score(String risk_score) {
		this.risk_score = risk_score;
	}

	public String getTotal_risk_rating() {
		return total_risk_rating;
	}

	public void setTotal_risk_rating(String total_risk_rating) {
		this.total_risk_rating = total_risk_rating;
	}

	public String getTotal_high_risks() {
		return total_high_risks;
	}

	public void setTotal_high_risks(String total_high_risks) {
		this.total_high_risks = total_high_risks;
	}

	public String getTotal_substantial_risks() {
		return total_substantial_risks;
	}

	public void setTotal_substantial_risks(String total_substantial_risks) {
		this.total_substantial_risks = total_substantial_risks;
	}

	public String getTotal_moderate_risks() {
		return total_moderate_risks;
	}

	public void setTotal_moderate_risks(String total_moderate_risks) {
		this.total_moderate_risks = total_moderate_risks;
	}

	public String getTotal_priority_risks() {
		return total_priority_risks;
	}

	public void setTotal_priority_risks(String total_priority_risks) {
		this.total_priority_risks = total_priority_risks;
	}

	public String getAtr_submitted() {
		return atr_submitted;
	}

	public void setAtr_submitted(String atr_submitted) {
		this.atr_submitted = atr_submitted;
	}

	public String getArea_item_no() {
		return area_item_no;
	}

	public void setArea_item_no(String area_item_no) {
		this.area_item_no = area_item_no;
	}

	public String getSub_area_item_no() {
		return sub_area_item_no;
	}

	public void setSub_area_item_no(String sub_area_item_no) {
		this.sub_area_item_no = sub_area_item_no;
	}

	public String getRevision_id() {
		return revision_id;
	}

	public void setRevision_id(String revision_id) {
		this.revision_id = revision_id;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getRisk_rating() {
		return risk_rating;
	}

	public void setRisk_rating(String risk_rating) {
		this.risk_rating = risk_rating;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getAssessment_date() {
		return assessment_date;
	}

	public void setAssessment_date(String assessment_date) {
		this.assessment_date = assessment_date;
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

	public String getSub_area() {
		return sub_area;
	}

	public void setSub_area(String sub_area) {
		this.sub_area = sub_area;
	}
	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getAtr_date() {
		return atr_date;
	}

	public void setAtr_date(String atr_date) {
		this.atr_date = atr_date;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public List<RiskReport> getSubAreaList() {
		return subAreaList;
	}

	public void setSubAreaList(List<RiskReport> subAreaList) {
		this.subAreaList = subAreaList;
	}

	public List<RiskReport> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<RiskReport> areaList) {
		this.areaList = areaList;
	}

	public String getEstimatedOrRevisedCost() {
		return estimatedOrRevisedCost;
	}

	public void setEstimatedOrRevisedCost(String estimatedOrRevisedCost) {
		this.estimatedOrRevisedCost = estimatedOrRevisedCost;
	}

	public String getEstimatedOrRevisedDate() {
		return estimatedOrRevisedDate;
	}

	public void setEstimatedOrRevisedDate(String estimatedOrRevisedDate) {
		this.estimatedOrRevisedDate = estimatedOrRevisedDate;
	}

	public String getSub_work() {
		return sub_work;
	}

	public void setSub_work(String sub_work) {
		this.sub_work = sub_work;
	}

	public String getRisk_revision_id() {
		return risk_revision_id;
	}

	public void setRisk_revision_id(String risk_revision_id) {
		this.risk_revision_id = risk_revision_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
