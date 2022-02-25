package com.synergizglobal.pmis.model;

public class AlertConditions {
	private String alert_condition_id,alert_type_fk,alert_level_fk,first_condition,first_condition_value,second_condition,second_condition_value;

	public String getAlert_condition_id() {
		return alert_condition_id;
	}

	public void setAlert_condition_id(String alert_condition_id) {
		this.alert_condition_id = alert_condition_id;
	}

	public String getAlert_type_fk() {
		return alert_type_fk;
	}

	public void setAlert_type_fk(String alert_type_fk) {
		this.alert_type_fk = alert_type_fk;
	}

	public String getAlert_level_fk() {
		return alert_level_fk;
	}

	public void setAlert_level_fk(String alert_level_fk) {
		this.alert_level_fk = alert_level_fk;
	}

	public String getSecond_condition() {
		return second_condition;
	}

	public void setSecond_condition(String second_condition) {
		this.second_condition = second_condition;
	}

	public String getSecond_condition_value() {
		return second_condition_value;
	}

	public void setSecond_condition_value(String second_condition_value) {
		this.second_condition_value = second_condition_value;
	}

	public String getFirst_condition() {
		return first_condition;
	}

	public void setFirst_condition(String first_condition) {
		this.first_condition = first_condition;
	}

	public String getFirst_condition_value() {
		return first_condition_value;
	}

	public void setFirst_condition_value(String first_condition_value) {
		this.first_condition_value = first_condition_value;
	}
	
}
