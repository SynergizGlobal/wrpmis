package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

public interface BudgetService {

	/*
	 * public List<Budget> getFinancialYearsList()throws Exception;
	 */
	/*
	 * public List<Budget> getProjectsList()throws Exception;
	 */
	public List<Budget> budgetList(Budget obj)throws Exception;

	public Budget getBudget(Budget obj)throws Exception;

	public boolean addBudget(Budget budget)throws Exception;

	public boolean updateBudget(Budget budget)throws Exception;

	public boolean deleteBudget(Budget obj)throws Exception;

	public List<Budget> getWorksList()throws Exception;

	public List<Budget> getFinancialYearList()throws Exception;

	public List<Budget> getProjectList()throws Exception;

	public List<Budget> getBudgetWorksList(Budget obj)throws Exception;

	public List<Budget> getBudgetProjectsList(Budget obj)throws Exception;

	public List<Budget> getFinancialYearsList(Budget obj)throws Exception;

	
	

}
