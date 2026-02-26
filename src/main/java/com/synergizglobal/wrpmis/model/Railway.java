package com.synergizglobal.wrpmis.model;

public class Railway {
	private String  railway_id,railway_name,executed_by_id_fk;

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getExecuted_by_id_fk() {
		return executed_by_id_fk;
	}

	public void setExecuted_by_id_fk(String executed_by_id_fk) {
		this.executed_by_id_fk = executed_by_id_fk;
	}

	public String getRailway_name() {
		return railway_name;
	}

	public void setRailway_name(String railway_name) {
		this.railway_name = railway_name;
	}

}
