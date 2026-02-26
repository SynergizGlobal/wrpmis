package com.synergizglobal.wrpmis.model;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Expenditure {
	

	private String expenditure_id,id,unit,value,contract_id_fk,contract_name,contract_id,work_id_fk,work_name,work_id, igst_tds,ledger_account, date, contractor_name, voucher_type, voucher_no, 
	narration, net_paid, gross_work_done, sd_payable, contractor_income_tax, cgst_tds, sgst_tds, vat_wct, mob_advance 
	,interest_on_mob_adv, amount_withheld, remarks,financial_year,financial_year_fk,project_id_fk,project_name,work_short_name,contract_short_name,project_id,
	net_paid_units,gross_work_done_units,sd_payable_units,contractor_income_tax_units,cgst_tds_units,sgst_tds_units,igst_tds_units,vat_wct_units,mob_advance_units,interest_on_mob_adv_units,amount_withheld_units,
	sd_units,gross_units,contractor_income_units,cgst_units,sgst_units,igst_units,vat_units,mob_units,interest_units,withheld_units,created_by_user_id_fk,user_id,designation,user_name
	,cess_on_building,
	est_charges_on_cess,
	cgst_output,
	sgst_output,
	cess_on_building_units,
	est_charges_on_cess_units,
	cgst_output_units,
	sgst_output_units,
	awarded_cost,tds,mob_adv_recovered,mob_adv_pending;

	private MultipartFile expenditureFile;
	
	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSd_units() {
		return sd_units;
	}

	public void setSd_units(String sd_units) {
		this.sd_units = sd_units;
	}

	public String getGross_units() {
		return gross_units;
	}

	public void setGross_units(String gross_units) {
		this.gross_units = gross_units;
	}

	public String getContractor_income_units() {
		return contractor_income_units;
	}

	public void setContractor_income_units(String contractor_income_units) {
		this.contractor_income_units = contractor_income_units;
	}

	public String getCgst_units() {
		return cgst_units;
	}

	public void setCgst_units(String cgst_units) {
		this.cgst_units = cgst_units;
	}

	public String getSgst_units() {
		return sgst_units;
	}

	public void setSgst_units(String sgst_units) {
		this.sgst_units = sgst_units;
	}

	public String getIgst_units() {
		return igst_units;
	}

	public void setIgst_units(String igst_units) {
		this.igst_units = igst_units;
	}

	public String getVat_units() {
		return vat_units;
	}

	public void setVat_units(String vat_units) {
		this.vat_units = vat_units;
	}

	public String getMob_units() {
		return mob_units;
	}

	public void setMob_units(String mob_units) {
		this.mob_units = mob_units;
	}

	public String getInterest_units() {
		return interest_units;
	}

	public void setInterest_units(String interest_units) {
		this.interest_units = interest_units;
	}

	public String getWithheld_units() {
		return withheld_units;
	}

	public void setWithheld_units(String withheld_units) {
		this.withheld_units = withheld_units;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNet_paid_units() {
		return net_paid_units;
	}

	public void setNet_paid_units(String net_paid_units) {
		this.net_paid_units = net_paid_units;
	}

	public String getGross_work_done_units() {
		return gross_work_done_units;
	}

	public void setGross_work_done_units(String gross_work_done_units) {
		this.gross_work_done_units = gross_work_done_units;
	}

	public String getSd_payable_units() {
		return sd_payable_units;
	}

	public void setSd_payable_units(String sd_payable_units) {
		this.sd_payable_units = sd_payable_units;
	}

	public String getContractor_income_tax_units() {
		return contractor_income_tax_units;
	}

	public void setContractor_income_tax_units(String contractor_income_tax_units) {
		this.contractor_income_tax_units = contractor_income_tax_units;
	}

	public String getCgst_tds_units() {
		return cgst_tds_units;
	}

	public void setCgst_tds_units(String cgst_tds_units) {
		this.cgst_tds_units = cgst_tds_units;
	}

	public String getSgst_tds_units() {
		return sgst_tds_units;
	}

	public void setSgst_tds_units(String sgst_tds_units) {
		this.sgst_tds_units = sgst_tds_units;
	}

	public String getIgst_tds_units() {
		return igst_tds_units;
	}

	public void setIgst_tds_units(String igst_tds_units) {
		this.igst_tds_units = igst_tds_units;
	}

	public String getVat_wct_units() {
		return vat_wct_units;
	}

	public void setVat_wct_units(String vat_wct_units) {
		this.vat_wct_units = vat_wct_units;
	}

	public String getMob_advance_units() {
		return mob_advance_units;
	}

	public void setMob_advance_units(String mob_advance_units) {
		this.mob_advance_units = mob_advance_units;
	}

	public String getInterest_on_mob_adv_units() {
		return interest_on_mob_adv_units;
	}

	public void setInterest_on_mob_adv_units(String interest_on_mob_adv_units) {
		this.interest_on_mob_adv_units = interest_on_mob_adv_units;
	}

	public String getAmount_withheld_units() {
		return amount_withheld_units;
	}

	public void setAmount_withheld_units(String amount_withheld_units) {
		this.amount_withheld_units = amount_withheld_units;
	}

	public MultipartFile getExpenditureFile() {
		return expenditureFile;
	}

	public void setExpenditureFile(MultipartFile expenditureFile) {
		this.expenditureFile = expenditureFile;
	}

	public String getIgst_tds() {
		return igst_tds;
	}

	public void setIgst_tds(String igst_tds) {
		this.igst_tds = igst_tds;
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

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getFinancial_year_fk() {
		return financial_year_fk;
	}

	public void setFinancial_year_fk(String financial_year_fk) {
		this.financial_year_fk = financial_year_fk;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getExpenditure_id() {
		return expenditure_id;
	}

	public void setExpenditure_id(String expenditure_id) {
		this.expenditure_id = expenditure_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getLedger_account() {
		return ledger_account;
	}

	public void setLedger_account(String ledger_account) {
		this.ledger_account = ledger_account;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
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

	public String getNet_paid() {
		return net_paid;
	}

	public void setNet_paid(String net_paid) {
		this.net_paid = net_paid;
	}

	public String getGross_work_done() {
		return gross_work_done;
	}

	public void setGross_work_done(String gross_work_done) {
		this.gross_work_done = gross_work_done;
	}

	public String getSd_payable() {
		return sd_payable;
	}

	public void setSd_payable(String sd_payable) {
		this.sd_payable = sd_payable;
	}

	public String getContractor_income_tax() {
		return contractor_income_tax;
	}

	public void setContractor_income_tax(String contractor_income_tax) {
		this.contractor_income_tax = contractor_income_tax;
	}

	public String getCgst_tds() {
		return cgst_tds;
	}

	public void setCgst_tds(String cgst_tds) {
		this.cgst_tds = cgst_tds;
	}

	public String getSgst_tds() {
		return sgst_tds;
	}

	public void setSgst_tds(String sgst_tds) {
		this.sgst_tds = sgst_tds;
	}

	public String getVat_wct() {
		return vat_wct;
	}

	public void setVat_wct(String vat_wct) {
		this.vat_wct = vat_wct;
	}

	public String getMob_advance() {
		return mob_advance;
	}

	public void setMob_advance(String mob_advance) {
		this.mob_advance = mob_advance;
	}

	public String getInterest_on_mob_adv() {
		return interest_on_mob_adv;
	}

	public void setInterest_on_mob_adv(String interest_on_mob_adv) {
		this.interest_on_mob_adv = interest_on_mob_adv;
	}

	public String getAmount_withheld() {
		return amount_withheld;
	}

	public void setAmount_withheld(String amount_withheld) {
		this.amount_withheld = amount_withheld;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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

	public String getCess_on_building() {
		return cess_on_building;
	}

	public void setCess_on_building(String cess_on_building) {
		this.cess_on_building = cess_on_building;
	}

	public String getEst_charges_on_cess() {
		return est_charges_on_cess;
	}

	public void setEst_charges_on_cess(String est_charges_on_cess) {
		this.est_charges_on_cess = est_charges_on_cess;
	}

	public String getCgst_output() {
		return cgst_output;
	}

	public void setCgst_output(String cgst_output) {
		this.cgst_output = cgst_output;
	}

	public String getSgst_output() {
		return sgst_output;
	}

	public void setSgst_output(String sgst_output) {
		this.sgst_output = sgst_output;
	}

	public String getCess_on_building_units() {
		return cess_on_building_units;
	}

	public void setCess_on_building_units(String cess_on_building_units) {
		this.cess_on_building_units = cess_on_building_units;
	}

	public String getEst_charges_on_cess_units() {
		return est_charges_on_cess_units;
	}

	public void setEst_charges_on_cess_units(String est_charges_on_cess_units) {
		this.est_charges_on_cess_units = est_charges_on_cess_units;
	}

	public String getCgst_output_units() {
		return cgst_output_units;
	}

	public void setCgst_output_units(String cgst_output_units) {
		this.cgst_output_units = cgst_output_units;
	}

	public String getSgst_output_units() {
		return sgst_output_units;
	}

	public void setSgst_output_units(String sgst_output_units) {
		this.sgst_output_units = sgst_output_units;
	}

	public String getAwarded_cost() {
		return awarded_cost;
	}

	public void setAwarded_cost(String awarded_cost) {
		this.awarded_cost = awarded_cost;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getMob_adv_recovered() {
		return mob_adv_recovered;
	}

	public void setMob_adv_recovered(String mob_adv_recovered) {
		this.mob_adv_recovered = mob_adv_recovered;
	}

	public String getMob_adv_pending() {
		return mob_adv_pending;
	}

	public void setMob_adv_pending(String mob_adv_pending) {
		this.mob_adv_pending = mob_adv_pending;
	}

	

	
}
