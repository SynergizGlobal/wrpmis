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
	public List<Expenditure> getWorkList() throws Exception {
		return dao.getWorkList();
	}

	@Override
	public List<Expenditure> getContractsList() throws Exception {
		return dao.getContractsList();
	}

	@Override
	public List<Expenditure> getLedgerAccountList() throws Exception {
		return dao.getLedgerAccountList();
	}

	@Override
	public List<Expenditure> getContractorNameList() throws Exception {
		return dao.getContractorNameList();
	}

	@Override
	public List<Expenditure> getVoucherTypeList() throws Exception {
		return dao.getVoucherTypeList();
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
