package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Expenditure;

public interface ExpenditureDao {

	public List<Expenditure> getExpendituresList(Expenditure obj, int startIndex, int offset, String searchParameter)throws Exception;

	public List<Expenditure> getVoucherList()throws Exception;

	public Expenditure getExpenditure(Expenditure obj)throws Exception;

	public boolean addExpenditure(Expenditure obj)throws Exception;

	public boolean updateExpenditure(Expenditure obj)throws Exception;

	public boolean deleteExpenditure(Expenditure obj)throws Exception;

	public List<Expenditure> getWorksFilterList(Expenditure obj) throws Exception;

	public List<Expenditure> getContractsFilterList(Expenditure obj)throws Exception;

	public List<Expenditure> getledgerAccountsList(Expenditure obj)throws Exception;

	public List<Expenditure> getContractorNamesFilterList(Expenditure obj)throws Exception;

	public List<Expenditure> getVoucherTypesFilterList(Expenditure obj) throws Exception;

	public List<Expenditure> getProjectsListForExpenditureForm(Expenditure obj) throws Exception;

	public List<Expenditure> getWorkListForExpenditureForm(Expenditure obj) throws Exception;

	public List<Expenditure> getContractsListForExpenditureForm(Expenditure obj) throws Exception;

	public int uploadExpenditures(List<Expenditure> expendituresList) throws Exception;

	public int getTotalRecords(Expenditure obj, String searchParameter) throws Exception;

	public List<Expenditure> getExpendituresListForExport(Expenditure obj) throws Exception;

}
