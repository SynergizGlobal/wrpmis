package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

public interface BudgetDao {

	public List<Work> getFinancialYearList()throws Exception;

	public List<Project> getProjectsList()throws Exception;

	public List<Budget> budgetList(Budget obj)throws Exception;

	public Budget getBudget(Budget obj)throws Exception;

	public boolean addBudget(Budget budget)throws Exception;

	public boolean updateBudget(Budget budget)throws Exception;

	public boolean deleteBudget(Budget obj)throws Exception;



}
