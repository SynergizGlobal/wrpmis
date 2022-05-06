package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;

public interface NewBudgetService {

	public List<Budget> budgetList(Budget obj)throws Exception;

	public Budget getNewBudget(Budget obj)throws Exception;

	public boolean addBudget(Budget budget)throws Exception;

	public boolean updateBudget(Budget budget)throws Exception;

	public boolean deleteBudget(Budget obj)throws Exception;

	public List<Budget> getFinancialYearList()throws Exception;


	public List<Budget> getNewBudgetWorksList(Budget obj)throws Exception;

	public List<Budget> getNewBudgetProjectsList(Budget obj)throws Exception;
	public List<Budget> getNewBudgetContractsList(Budget obj)throws Exception;

	public List<Budget> getFinancialYearsList(Budget obj)throws Exception;

	public List<Budget> getNewBudgetExportList(Budget obj) throws Exception;

	public List<Budget> getProjectsListForBudgetForm(Budget obj) throws Exception;

	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception;
	public List<Budget> getContractsListForBudgetForm(Budget obj) throws Exception;

	public int getTotalRecords(Budget obj, String searchParameter) throws Exception;

	public List<Budget> getNewBudgetList(Budget obj, int startIndex, int offset, String searchParameter) throws Exception;	

}
