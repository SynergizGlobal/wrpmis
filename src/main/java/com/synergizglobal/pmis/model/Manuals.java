package com.synergizglobal.pmis.model;

public class Manuals {
	
	private String manuals_id, manual_name, manual_folder_fk, attachment,user_role_name_fk;

	public String getUser_role_name_fk() {
		return user_role_name_fk;
	}

	public void setUser_role_name_fk(String user_role_name_fk) {
		this.user_role_name_fk = user_role_name_fk;
	}

	public String getManuals_id() {
		return manuals_id;
	}

	public void setManuals_id(String manuals_id) {
		this.manuals_id = manuals_id;
	}

	public String getManual_name() {
		return manual_name;
	}

	public void setManual_name(String manual_name) {
		this.manual_name = manual_name;
	}

	public String getManual_folder_fk() {
		return manual_folder_fk;
	}

	public void setManual_folder_fk(String manual_folder_fk) {
		this.manual_folder_fk = manual_folder_fk;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}
