package com.synergizglobal.pmis.model;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class Risk {
	
	private String risk_id_pk,id, project_name,work_name,project_id_fk, work_id_fk, risk_id, sub_area_fk, date_of_identification,area,risk_revision_id, risk_id_pk_fk, date,
	priority, probability,item_no,priority_fk,work_short_name, impact, owner, responsible_person,assessment_date, risk_action_id,mitigation_plan, action_taken, attachment,
	sub_area, risk_area_fk,classification,atr_date,work_id,risk_rating,status,sub_work,area_item_no,sub_area_item_no,risk_revision_id_fk;

	private MultipartFile riskFile;
	private MultipartFile riskAssessmentFile;

	private String [] risk_revision_ids, risk_id_pk_fks, dates, prioritys, probabilitys, impacts,
	owners, responsible_persons, mitigation_plans, action_takens, attachments,atr_dates,priority_fks,risk_action_ids,assessment_dates;
	
	private int [] rowCounts;
	
	private List<Risk> risks; 
	private List<Risk> riskActions; 
	

	public MultipartFile getRiskFile() {
		return riskFile;
	}

	public void setRiskFile(MultipartFile riskFile) {
		this.riskFile = riskFile;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getRisk_action_id() {
		return risk_action_id;
	}

	public void setRisk_action_id(String risk_action_id) {
		this.risk_action_id = risk_action_id;
	}

	public String[] getRisk_action_ids() {
		return risk_action_ids;
	}

	public void setRisk_action_ids(String[] risk_action_ids) {
		this.risk_action_ids = risk_action_ids;
	}

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
	public void setRowCounts(int[] rowCounts) {
		this.rowCounts = rowCounts;
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

	public String[] getProbabilitys() {
		return probabilitys;
	}

	public void setProbabilitys(String[] probabilitys) {
		this.probabilitys = probabilitys;
	}

	public String[] getImpacts() {
		return impacts;
	}

	public void setImpacts(String[] impacts) {
		this.impacts = impacts;
	}

	public String getPriority_fk() {
		return priority_fk;
	}

	public void setPriority_fk(String priority_fk) {
		this.priority_fk = priority_fk;
	}

	public String[] getPriority_fks() {
		return priority_fks;
	}

	public void setPriority_fks(String[] priority_fks) {
		this.priority_fks = priority_fks;
	}

	public int[] getRowCounts() {
		return rowCounts;
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

	public String getRisk_rating() {
		return risk_rating;
	}

	public void setRisk_rating(String risk_rating) {
		this.risk_rating = risk_rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MultipartFile getRiskAssessmentFile() {
		return riskAssessmentFile;
	}

	public void setRiskAssessmentFile(MultipartFile riskAssessmentFile) {
		this.riskAssessmentFile = riskAssessmentFile;
	}

	public String getSub_work() {
		return sub_work;
	}

	public void setSub_work(String sub_work) {
		this.sub_work = sub_work;
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

	public String getRisk_revision_id_fk() {
		return risk_revision_id_fk;
	}

	public void setRisk_revision_id_fk(String risk_revision_id_fk) {
		this.risk_revision_id_fk = risk_revision_id_fk;
	}

	public String [] getAssessment_dates() {
		return assessment_dates;
	}

	public void setAssessment_dates(String [] assessment_dates) {
		this.assessment_dates = assessment_dates;
	}
}
