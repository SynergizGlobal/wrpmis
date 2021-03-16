package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SourceOfFundsDao;
import com.synergizglobal.pmis.reference.Iservice.SourceOfFundsService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class SourceOfFundsServiceImpl implements SourceOfFundsService{

	@Autowired
	SourceOfFundsDao dao;

	@Override
	public List<TrainingType> getSourceOfFundsList() throws Exception {
		return dao.getSourceOfFundsList();
	}

	@Override
	public boolean addSourceOfFund(TrainingType obj) throws Exception {
		return dao.addSourceOfFund(obj);
	}

	@Override
	public TrainingType getSourceOfFundsDetails(TrainingType obj) throws Exception {
		return dao.getSourceOfFundsDetails(obj);
	}

	@Override
	public boolean updateSourceOfFunds(TrainingType obj) throws Exception {
		return dao.updateSourceOfFunds(obj);
	}

	@Override
	public boolean deleteSourceOfFunds(TrainingType obj) throws Exception {
		return dao.deleteSourceOfFunds(obj);
	}
}
