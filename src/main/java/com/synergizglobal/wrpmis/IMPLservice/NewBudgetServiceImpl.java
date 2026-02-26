package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.NewBudgetDao;
import com.synergizglobal.wrpmis.Iservice.NewBudgetService;
import com.synergizglobal.wrpmis.model.Budget;

@Service
public class NewBudgetServiceImpl implements NewBudgetService{

	@Autowired
	NewBudgetDao newBudgetDao;
	
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
		return newBudgetDao.budgetList(obj);
	}
	
	@Override
	public Budget getNewBudget(Budget obj)throws Exception{
		return newBudgetDao.getNewBudget(obj);
	}

	@Override
	public boolean addBudget(Budget budget) throws Exception {
		return newBudgetDao.addBudget(budget);

	}

	@Override
	public boolean updateBudget(Budget budget) throws Exception {
		return newBudgetDao.updateBudget(budget);

	}

	@Override
	public boolean deleteBudget(Budget obj) throws Exception {
		return newBudgetDao.deleteBudget(obj);

	}
	 
	@Override
	public List<Budget> getFinancialYearList() throws Exception {
		return newBudgetDao.getFinancialYearList();
	}

	@Override
	public List<Budget> getNewBudgetWorksList(Budget obj) throws Exception {
		return newBudgetDao.getNewBudgetWorksList(obj);
	}

	@Override
	public List<Budget> getNewBudgetProjectsList(Budget obj) throws Exception {
		return newBudgetDao.getNewBudgetProjectsList(obj);
	}
	
	@Override
	public List<Budget> getNewBudgetContractsList(Budget obj) throws Exception {
		return newBudgetDao.getNewBudgetContractsList(obj);
	}	

	@Override
	public List<Budget> getFinancialYearsList(Budget obj) throws Exception {
		return newBudgetDao.getFinancialYearsList(obj);
	}

	@Override
	public List<Budget> getNewBudgetExportList(Budget obj) throws Exception {
		return newBudgetDao.getNewBudgetExportList(obj);
	}

	@Override
	public List<Budget> getProjectsListForBudgetForm(Budget obj) throws Exception {
		return newBudgetDao.getProjectsListForBudgetForm(obj);
	}

	@Override
	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception {
		return newBudgetDao.getWorkListForBudgetForm(obj);
	}

	@Override
	public int getTotalRecords(Budget obj, String searchParameter) throws Exception {
		return newBudgetDao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Budget> getNewBudgetList(Budget obj, int startIndex, int offset, String searchParameter) throws Exception {
		return newBudgetDao.getNewBudgetList(obj,startIndex,offset,searchParameter);
	}
	
	@Override
	public List<Budget> getContractsListForBudgetForm(Budget obj) throws Exception {
		return newBudgetDao.getContractsListForBudgetForm(obj);
	}	
	
	

}
