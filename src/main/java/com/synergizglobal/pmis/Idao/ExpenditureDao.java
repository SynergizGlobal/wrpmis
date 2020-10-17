package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.SourceOfFund;

public interface ExpenditureDao {

	public List<Expenditure> getWorkList() throws Exception;

	public List<Expenditure> getContractsList()throws Exception;

	public List<Expenditure> getLedgerAccountList()throws Exception;

	public List<Expenditure> getContractorNameList()throws Exception;

	public List<Expenditure> getVoucherTypeList()throws Exception;

	public List<Expenditure> expendituresList(Expenditure obj)throws Exception;

	public List<Expenditure> getVoucherList()throws Exception;

	public Expenditure getExpenditure(Expenditure obj)throws Exception;

	public boolean addExpenditure(Expenditure obj)throws Exception;

	public boolean updateExpenditure(Expenditure obj)throws Exception;

	public boolean deleteExpenditure(Expenditure obj)throws Exception;

}
