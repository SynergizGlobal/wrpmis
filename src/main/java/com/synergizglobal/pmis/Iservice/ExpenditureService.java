package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.SourceOfFund;

public interface ExpenditureService {


	public List<Expenditure> expendituresList(Expenditure obj)throws Exception;

	public List<Expenditure> getVoucherList()throws Exception;

	public Expenditure getExpenditure(Expenditure obj)throws Exception;

	public boolean addExpenditure(Expenditure obj)throws Exception;

	public boolean updateExpenditure(Expenditure obj)throws Exception;

	public boolean deleteExpenditure(Expenditure obj)throws Exception;

	public List<Expenditure> getWorksFilterList(Expenditure obj)throws Exception;

	public List<Expenditure> getContractsFilterList(Expenditure obj)throws Exception;

	public List<Expenditure> getledgerAccountsList(Expenditure obj)throws Exception;

	public List<Expenditure> getContractorNamesFilterList(Expenditure obj)throws Exception;

	public List<Expenditure> getVoucherTypesFilterList(Expenditure obj)throws Exception;


	

}
