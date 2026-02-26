package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.SourceOfFundDao;
import com.synergizglobal.wrpmis.Iservice.SourceOfFundService;
import com.synergizglobal.wrpmis.model.FOB;
import com.synergizglobal.wrpmis.model.SourceOfFund;

@Service
public class SourceOfFundServiceImpl implements SourceOfFundService{
	
	@Autowired
	SourceOfFundDao dao;

	@Override
	public List<SourceOfFund> getSourceOfFundList() throws Exception {
		return dao.getSourceOfFundList();
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


	@Override
	public List<SourceOfFund> getRailwaysList() throws Exception {
		return dao.getRailwaysList();
	}

	@Override
	public List<SourceOfFund> getSOFList(SourceOfFund obj) throws Exception {
		return dao.getSOFList(obj);
	}

	@Override
	public List<SourceOfFund> getRailwayList(SourceOfFund obj) throws Exception {
		return dao.getRailwayList(obj);
	}

	@Override
	public List<SourceOfFund> getFundWorksList(SourceOfFund obj) throws Exception {
		return dao.getFundWorksList(obj);
	}

	@Override
	public List<SourceOfFund> getProjectsListForSourceOfFundForm(SourceOfFund obj) throws Exception {
		return dao.getProjectsListForSourceOfFundForm(obj);
	}

	@Override
	public List<SourceOfFund> getWorkListForSourceOfFundForm(SourceOfFund obj) throws Exception {
		return dao.getWorkListForSourceOfFundForm(obj);
	}

	@Override
	public int getTotalRecords(SourceOfFund obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<SourceOfFund> getSourceOfFundList(SourceOfFund obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return dao.getSourceOfFundList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<SourceOfFund> getUnitsList(SourceOfFund obj) throws Exception {
		return dao.getUnitsList(obj);
	}

	

}
