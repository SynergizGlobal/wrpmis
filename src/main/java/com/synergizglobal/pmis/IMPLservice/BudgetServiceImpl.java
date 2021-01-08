package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.BudgetDao;
import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.model.Budget;

@Service
public class BudgetServiceImpl implements BudgetService{

	@Autowired
	BudgetDao budgetDao;
	
	/*
	 * @Override public List<Budget> getFinancialYearsList() throws Exception {
	 * return budgetDao.getFinancialYearsList(); }
	 */

	/*
	 * @Override public List<Budget> getProjectsList() throws Exception { return
	 * budgetDao.getProjectsList();
	 * 
	 * }
	 */

	@Override
	public List<Budget> budgetList(Budget obj) throws Exception {
		return budgetDao.budgetList(obj);
	}
	
	@Override
	public Budget getBudget(Budget obj)throws Exception{
		return budgetDao.getBudget(obj);
	}

	@Override
	public boolean addBudget(Budget budget) throws Exception {
		return budgetDao.addBudget(budget);

	}

	@Override
	public boolean updateBudget(Budget budget) throws Exception {
		return budgetDao.updateBudget(budget);

	}

	@Override
	public boolean deleteBudget(Budget obj) throws Exception {
		return budgetDao.deleteBudget(obj);

	}
	 
	@Override
	public List<Budget> getFinancialYearList() throws Exception {
		return budgetDao.getFinancialYearList();
	}

	@Override
	public List<Budget> getBudgetWorksList(Budget obj) throws Exception {
		return budgetDao.getBudgetWorksList(obj);
	}

	@Override
	public List<Budget> getBudgetProjectsList(Budget obj) throws Exception {
		return budgetDao.getBudgetProjectsList(obj);
	}

	@Override
	public List<Budget> getFinancialYearsList(Budget obj) throws Exception {
		return budgetDao.getFinancialYearsList(obj);
	}

	@Override
	public List<Budget> getBudgetExportList(Budget obj) throws Exception {
		return budgetDao.getBudgetExportList(obj);
	}

	@Override
	public List<Budget> getProjectsListForBudgetForm(Budget obj) throws Exception {
		return budgetDao.getProjectsListForBudgetForm(obj);
	}

	@Override
	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception {
		return budgetDao.getWorkListForBudgetForm(obj);
	}

}
