package com.synergizglobal.pmis.model;

public class Messages {
	private String message_id,message,user_id_fk,redirect_url,created_date,read_time,message_type,created_date_24hr_format,timeAgo;

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getRead_time() {
		return read_time;
	}

	public void setRead_time(String read_time) {
		this.read_time = read_time;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getCreated_date_24hr_format() {
		return created_date_24hr_format;
	}

	public void setCreated_date_24hr_format(String created_date_24hr_format) {
		this.created_date_24hr_format = created_date_24hr_format;
	}

	public String getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}
	
	
}
