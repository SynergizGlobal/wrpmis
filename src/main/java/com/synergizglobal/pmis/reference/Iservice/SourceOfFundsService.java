package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SourceOfFundsService {

	public List<TrainingType> getSourceOfFundsList() throws Exception;

	public boolean addSourceOfFund(TrainingType obj) throws Exception;

	public TrainingType getSourceOfFundsDetails(TrainingType obj) throws Exception;

	public boolean updateSourceOfFunds(TrainingType obj) throws Exception;

	public boolean deleteSourceOfFunds(TrainingType obj) throws Exception;
	
}
