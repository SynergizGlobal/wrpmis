package com.synergizglobal.pmis.model;

public class BankGuarantee {
	private String contract_id_fk,bg_type_fk,issuing_bank,bank_address,bg_number,bg_value,valid_upto,remarks;

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getBg_type_fk() {
		return bg_type_fk;
	}

	public void setBg_type_fk(String bg_type_fk) {
		this.bg_type_fk = bg_type_fk;
	}

	public String getIssuing_bank() {
		return issuing_bank;
	}

	public void setIssuing_bank(String issuing_bank) {
		this.issuing_bank = issuing_bank;
	}

	public String getBank_address() {
		return bank_address;
	}

	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	public String getBg_number() {
		return bg_number;
	}

	public void setBg_number(String bg_number) {
		this.bg_number = bg_number;
	}

	public String getBg_value() {
		return bg_value;
	}

	public void setBg_value(String bg_value) {
		this.bg_value = bg_value;
	}

	public String getValid_upto() {
		return valid_upto;
	}

	public void setValid_upto(String valid_upto) {
		this.valid_upto = valid_upto;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
