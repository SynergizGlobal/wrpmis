package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.DashboardTypeDao;
import com.synergizglobal.pmis.reference.Idao.FinancialYearDao;
import com.synergizglobal.pmis.reference.Iservice.FinancialYearService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class FinancialYearServiceImpl implements FinancialYearService{


	@Autowired
	FinancialYearDao dao;

	@Override
	public TrainingType getFinancialYearDetails(TrainingType obj) throws Exception {
		return dao.getFinancialYearDetails(obj);
	}

	@Override
	public boolean addFinancialYear(Risk obj) throws Exception {
		return dao.addFinancialYear(obj);
	}

	@Override
	public boolean updateFinancialYear(TrainingType obj) throws Exception {
		return dao.updateFinancialYear(obj);
	}

	@Override
	public boolean deleteFinancialYear(TrainingType obj) throws Exception {
		return dao.deleteFinancialYear(obj);
	}

}
