package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.SourceOfFund;

public interface SourceOfFundDao {

	public List<SourceOfFund> fundsList(SourceOfFund obj)throws Exception;

	public boolean addFunds(SourceOfFund obj)throws Exception;

	public SourceOfFund getFunds(SourceOfFund obj)throws Exception;

	public boolean updateFunds(SourceOfFund obj)throws Exception;

	public boolean deleteFunds(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getRailwaysList()throws Exception;

	public List<SourceOfFund> getSOFList(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getRailwayList(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getFundWorksList(SourceOfFund obj)throws Exception;

	public List<SourceOfFund> getSourceOfFundList()throws Exception;
	
	public List<SourceOfFund> getProjectsListForSourceOfFundForm(SourceOfFund obj) throws Exception;

	public List<SourceOfFund> getWorkListForSourceOfFundForm(SourceOfFund obj) throws Exception;

	public int getTotalRecords(SourceOfFund obj, String searchParameter) throws Exception;

	public List<SourceOfFund> getSourceOfFundList(SourceOfFund obj, int startIndex, int offset, String searchParameter) throws Exception;

	public List<SourceOfFund> getUnitsList(SourceOfFund obj) throws Exception;

}
