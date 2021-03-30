package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;

public interface BudgetDao {

	public List<Budget> budgetList(Budget obj)throws Exception;

	public Budget getBudget(Budget obj)throws Exception;

	public boolean addBudget(Budget budget)throws Exception;

	public boolean updateBudget(Budget budget)throws Exception;

	public boolean deleteBudget(Budget obj)throws Exception;

	public List<Budget> getFinancialYearList()throws Exception;

	public List<Budget> getBudgetWorksList(Budget obj)throws Exception;

	public List<Budget> getBudgetProjectsList(Budget obj)throws Exception;

	public List<Budget> getFinancialYearsList(Budget obj)throws Exception;

	public List<Budget> getBudgetExportList(Budget obj) throws Exception;
	
	public List<Budget> getProjectsListForBudgetForm(Budget obj) throws Exception;

	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception;

	public int getTotalRecords(Budget obj, String searchParameter) throws Exception;

	public List<Budget> getBudgetList(Budget obj, int startIndex, int offset, String searchParameter) throws Exception;

}
