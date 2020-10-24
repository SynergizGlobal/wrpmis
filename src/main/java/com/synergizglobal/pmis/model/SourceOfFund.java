package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class SourceOfFund {
	
	private String funds_id, work_id_fk,work_id,project_id,project_id_fk,work_name,project_name, source_of_funds_fk, sub_category_railway_id_fk,railway_name, funding_date,
					fund_amount, remarks, bank_account, voucher_type, voucher_no, narration, ledger_account,railway_id,attachment,work_short_name;

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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	private MultipartFile fundFile;

	
	public MultipartFile getFundFile() {
		return fundFile;
	}

	public void setFundFile(MultipartFile fundFile) {
		this.fundFile = fundFile;
	}

	public String getRailway_name() {
		return railway_name;
	}

	public void setRailway_name(String railway_name) {
		this.railway_name = railway_name;
	}

	public String getFunds_id() {
		return funds_id;
	}

	public void setFunds_id(String funds_id) {
		this.funds_id = funds_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getSource_of_funds_fk() {
		return source_of_funds_fk;
	}

	public void setSource_of_funds_fk(String source_of_funds_fk) {
		this.source_of_funds_fk = source_of_funds_fk;
	}

	public String getSub_category_railway_id_fk() {
		return sub_category_railway_id_fk;
	}

	public void setSub_category_railway_id_fk(String sub_category_railway_id_fk) {
		this.sub_category_railway_id_fk = sub_category_railway_id_fk;
	}

	public String getFunding_date() {
		return funding_date;
	}

	public void setFunding_date(String funding_date) {
		this.funding_date = funding_date;
	}

	public String getFund_amount() {
		return fund_amount;
	}

	public void setFund_amount(String fund_amount) {
		this.fund_amount = fund_amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getVoucher_type() {
		return voucher_type;
	}

	public void setVoucher_type(String voucher_type) {
		this.voucher_type = voucher_type;
	}

	public String getVoucher_no() {
		return voucher_no;
	}

	public void setVoucher_no(String voucher_no) {
		this.voucher_no = voucher_no;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getLedger_account() {
		return ledger_account;
	}

	public void setLedger_account(String ledger_account) {
		this.ledger_account = ledger_account;
	}

}
