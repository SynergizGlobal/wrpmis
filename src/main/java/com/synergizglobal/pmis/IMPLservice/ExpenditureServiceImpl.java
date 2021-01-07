package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ExpenditureDao;
import com.synergizglobal.pmis.Iservice.ExpenditureService;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.SourceOfFund;

@Service
public class ExpenditureServiceImpl implements ExpenditureService{

	@Autowired
	ExpenditureDao dao;
	

	@Override
	public List<Expenditure> expendituresList(Expenditure obj) throws Exception {
		return dao.expendituresList(obj);
	}

	@Override
	public List<Expenditure> getVoucherList() throws Exception {
		return dao.getVoucherList();
	}

	@Override
	public Expenditure getExpenditure(Expenditure obj) throws Exception {
		return dao.getExpenditure(obj);
	}

	@Override
	public boolean addExpenditure(Expenditure obj) throws Exception {
		return dao.addExpenditure(obj);
	}

	@Override
	public boolean updateExpenditure(Expenditure obj) throws Exception {
		return dao.updateExpenditure(obj);
	}

	@Override
	public boolean deleteExpenditure(Expenditure obj) throws Exception {
		return dao.deleteExpenditure(obj);
	}

	@Override
	public List<Expenditure> getWorksFilterList(Expenditure obj) throws Exception {
		return dao.getWorksFilterList(obj);
	}

	@Override
	public List<Expenditure> getContractsFilterList(Expenditure obj) throws Exception {
		return dao.getContractsFilterList(obj);
	}

	@Override
	public List<Expenditure> getledgerAccountsList(Expenditure obj) throws Exception {
		return dao.getledgerAccountsList(obj);
	}

	@Override
	public List<Expenditure> getContractorNamesFilterList(Expenditure obj) throws Exception {
		return dao.getContractorNamesFilterList(obj);
	}

	@Override
	public List<Expenditure> getVoucherTypesFilterList(Expenditure obj) throws Exception {
		return dao.getVoucherTypesFilterList(obj);
	}

	@Override
	public List<Expenditure> getProjectsListForExpenditureForm(Expenditure obj) throws Exception {
		return dao.getProjectsListForExpenditureForm(obj);
	}

	@Override
	public List<Expenditure> getWorkListForExpenditureForm(Expenditure obj) throws Exception {
		return dao.getWorkListForExpenditureForm(obj);
	}

	@Override
	public List<Expenditure> getContractsListForExpenditureForm(Expenditure obj) throws Exception {
		return dao.getContractsListForExpenditureForm(obj);
	}
	
	

}
