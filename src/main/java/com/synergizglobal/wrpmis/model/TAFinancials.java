package com.synergizglobal.wrpmis.model;

import java.util.List;

public class TAFinancials {
	
	private String work_id_fk, ID,project_id_fk, work_name,contract_name,contract_id_fk,contract_short_name, unit, value,work_short_name,financial_id, 
	contract_id,month, planned, actual, ta_contract_id_fk,payment_received, planned_unit,actual_unit,payment_unit,
	designation,user_id,user_name;

	private String[] IDs, months, planneds, actuals, payment_receiveds, planned_units, actual_units, payment_received_units;
	
	private List<TAFinancials> taFinancials;


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	public String getPlanned_unit() {
		return planned_unit;
	}

	public void setPlanned_unit(String planned_unit) {
		this.planned_unit = planned_unit;
	}

	public String getActual_unit() {
		return actual_unit;
	}

	public void setActual_unit(String actual_unit) {
		this.actual_unit = actual_unit;
	}

	public String getPayment_unit() {
		return payment_unit;
	}

	public void setPayment_unit(String payment_unit) {
		this.payment_unit = payment_unit;
	}

	public String[] getPlanned_units() {
		return planned_units;
	}

	public void setPlanned_units(String[] planned_units) {
		this.planned_units = planned_units;
	}

	public String[] getActual_units() {
		return actual_units;
	}

	public void setActual_units(String[] actual_units) {
		this.actual_units = actual_units;
	}

	public String[] getPayment_received_units() {
		return payment_received_units;
	}

	public void setPayment_received_units(String[] payment_received_units) {
		this.payment_received_units = payment_received_units;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getFinancial_id() {
		return financial_id;
	}

	public void setFinancial_id(String financial_id) {
		this.financial_id = financial_id;
	}

	public String getTa_contract_id_fk() {
		return ta_contract_id_fk;
	}

	public void setTa_contract_id_fk(String ta_contract_id_fk) {
		this.ta_contract_id_fk = ta_contract_id_fk;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public List<TAFinancials> getTaFinancials() {
		return taFinancials;
	}

	public void setTaFinancials(List<TAFinancials> taFinancials) {
		this.taFinancials = taFinancials;
	}

	public String[] getIDs() {
		return IDs;
	}

	public void setIDs(String[] iDs) {
		IDs = iDs;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public String[] getPlanneds() {
		return planneds;
	}

	public void setPlanneds(String[] planneds) {
		this.planneds = planneds;
	}

	public String[] getActuals() {
		return actuals;
	}

	public void setActuals(String[] actuals) {
		this.actuals = actuals;
	}

	public String[] getPayment_receiveds() {
		return payment_receiveds;
	}

	public void setPayment_receiveds(String[] payment_receiveds) {
		this.payment_receiveds = payment_receiveds;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPlanned() {
		return planned;
	}

	public void setPlanned(String planned) {
		this.planned = planned;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getPayment_received() {
		return payment_received;
	}

	public void setPayment_received(String payment_received) {
		this.payment_received = payment_received;
	}

}
