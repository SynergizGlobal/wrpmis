package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Work;

public interface SourceOfFundService {

	public List<SourceOfFund> getSourceOfFundList()throws Exception;

	public List<SourceOfFund> getRailwayList()throws Exception;

	public List<SourceOfFund> fundsList(SourceOfFund obj)throws Exception;

	public boolean addFunds(SourceOfFund obj)throws Exception;

	public SourceOfFund getFunds(SourceOfFund obj)throws Exception;

	public boolean updateFunds(SourceOfFund obj)throws Exception;

	public boolean deleteFunds(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getWorkList()throws Exception;

	public List<SourceOfFund> getRailwaysList()throws Exception;

	public List<SourceOfFund> getSOFList(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getRailwayList(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getFundWorksList(SourceOfFund obj)throws Exception;
	

}
