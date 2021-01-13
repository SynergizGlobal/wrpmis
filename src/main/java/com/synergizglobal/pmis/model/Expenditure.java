package com.synergizglobal.pmis.model;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Expenditure {
	

	private String expenditure_id, contract_id_fk,contract_name,contract_id,work_id_fk,work_name,work_id, igst_tds,ledger_account, date, contractor_name, voucher_type, voucher_no, 
	narration, net_paid, gross_work_done, sd_payable, contractor_income_tax, cgst_tds, sgst_tds, vat_wct, mob_advance 
	,interest_on_mob_adv, amount_withheld, remarks,financial_year,financial_year_fk,project_id_fk,project_name,work_short_name,contract_short_name,project_id;

	private MultipartFile expenditureFile;
	
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
}
