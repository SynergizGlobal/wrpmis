package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ContractTypeDao {

	public List<TrainingType> getContractTypesList() throws Exception;

	public boolean addContractType(TrainingType obj) throws Exception;

	public TrainingType getContractTypeDetails(TrainingType obj) throws Exception;

	public boolean updateContractType(TrainingType obj) throws Exception;

	public boolean deleteContractType(TrainingType obj) throws Exception;
}
