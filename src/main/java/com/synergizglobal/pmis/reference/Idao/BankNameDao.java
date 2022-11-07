package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Bank;

public interface BankNameDao {


	public List<Bank> getBankNamesList() throws Exception;

	public boolean addBankName(Bank obj) throws Exception;

	public boolean updateBankName(Bank obj) throws Exception;

	public Bank getBankNameDetails(Bank obj) throws Exception;

	public boolean deleteBankName(Bank obj) throws Exception;

}
