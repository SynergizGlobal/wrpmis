package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SourceOfFundDao;
import com.synergizglobal.pmis.Iservice.SourceOfFundService;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.SourceOfFund;

@Service
public class SourceOfFundServiceImpl implements SourceOfFundService{
	
	@Autowired
	SourceOfFundDao dao;

	@Override
	public List<SourceOfFund> getSourceOfFundList() throws Exception {
		return dao.getSourceOfFundList();
	}

	@Override
	public List<SourceOfFund> getRailwayListList() throws Exception {
		return dao.getRailwayListList();
	}

	@Override
	public List<SourceOfFund> fundsList(SourceOfFund obj) throws Exception {
		return dao.fundsList(obj);
	}

	@Override
	public boolean addFunds(SourceOfFund obj) throws Exception {
		return dao.addFunds(obj);
	}

	@Override
	public SourceOfFund getFunds(SourceOfFund obj) throws Exception {
		return dao.getFunds(obj);
	}

	@Override
	public boolean updateFunds(SourceOfFund obj) throws Exception {
		return dao.updateFunds(obj);
	}

	@Override
	public boolean deleteFunds(SourceOfFund obj) throws Exception {
		return dao.deleteFunds(obj);
	}
	

}
