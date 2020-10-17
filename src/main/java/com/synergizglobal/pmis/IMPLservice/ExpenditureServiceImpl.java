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
	public List<Expenditure> getWorksList() throws Exception {
		return dao.getWorksList();
	}

	@Override
	public List<Expenditure> getContractsList() throws Exception {
		return dao.getContractsList();
	}

	@Override
	public List<Expenditure> getLedgerAccountsList() throws Exception {
		return dao.getLedgerAccountsList();
	}

	@Override
	public List<Expenditure> getContractorNamesList() throws Exception {
		return dao.getContractorNamesList();
	}

	@Override
	public List<Expenditure> getVoucherTypesList() throws Exception {
		return dao.getVoucherTypesList();
	}

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
	
	

}
