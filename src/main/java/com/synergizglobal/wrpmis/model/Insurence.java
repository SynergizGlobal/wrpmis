package com.synergizglobal.wrpmis.model;

public class Insurence {
	private String contract_id_fk, insurance_type_fk, issuing_agency, agency_address, insurance_number, insurance_value, valid_upto, remarks;

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getInsurance_type_fk() {
		return insurance_type_fk;
	}

	public void setInsurance_type_fk(String insurance_type_fk) {
		this.insurance_type_fk = insurance_type_fk;
	}

	public String getIssuing_agency() {
		return issuing_agency;
	}

	public void setIssuing_agency(String issuing_agency) {
		this.issuing_agency = issuing_agency;
	}

	public String getAgency_address() {
		return agency_address;
	}

	public void setAgency_address(String agency_address) {
		this.agency_address = agency_address;
	}

	public String getInsurance_number() {
		return insurance_number;
	}

	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}

	public String getInsurance_value() {
		return insurance_value;
	}

	public void setInsurance_value(String insurance_value) {
		this.insurance_value = insurance_value;
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
