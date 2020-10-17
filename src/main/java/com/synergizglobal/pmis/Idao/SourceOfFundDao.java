package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.SourceOfFund;

public interface SourceOfFundDao {

	public List<SourceOfFund> getSourceOfFundList()throws Exception;

	public List<SourceOfFund> getRailwayListList()throws Exception;

	public List<SourceOfFund> fundsList(SourceOfFund obj)throws Exception;

	public boolean addFunds(SourceOfFund obj)throws Exception;

	public SourceOfFund getFunds(SourceOfFund obj)throws Exception;

	public boolean updateFunds(SourceOfFund obj)throws Exception;

	public boolean deleteFunds(SourceOfFund obj)throws Exception;



}
