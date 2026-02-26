package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ContarctFileTypeDao {

	List<TrainingType> getcontractFileType(TrainingType obj) throws Exception;

	boolean addContractFileType(TrainingType obj) throws Exception;

	boolean updateContractFileType(TrainingType obj) throws Exception;

	boolean deleteContractFileType(TrainingType obj) throws Exception;

}
