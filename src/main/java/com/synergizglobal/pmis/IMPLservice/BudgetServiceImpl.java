package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.BudgetDao;
import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

@Service
public class BudgetServiceImpl implements BudgetService{

	@Autowired
	BudgetDao budgetDao;
	
	@Override
	public List<Work> getFinancialYearList() throws Exception {
		return budgetDao.getFinancialYearList();
	}

	@Override
	public List<Project> getProjectsList() throws Exception {
		return budgetDao.getProjectsList();

	}

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
	public List<Budget> getWorkList() throws Exception {
		return budgetDao.getWorkList();

	}


	


}
