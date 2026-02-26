package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SourceOfFundsDao {
	
	public List<TrainingType> getSourceOfFundsList() throws Exception;

	public boolean addSourceOfFund(TrainingType obj) throws Exception;

	public TrainingType getSourceOfFundsDetails(TrainingType obj) throws Exception;

	public boolean updateSourceOfFunds(TrainingType obj) throws Exception;

	public boolean deleteSourceOfFunds(TrainingType obj) throws Exception;
}
