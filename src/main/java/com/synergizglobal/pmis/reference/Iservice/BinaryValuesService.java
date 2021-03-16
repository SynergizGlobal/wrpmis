package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface BinaryValuesService {

	public List<TrainingType> getBinaryValuesList() throws Exception;

	public boolean addBinaryValues(TrainingType obj) throws Exception;

	public TrainingType getBinaryValueDetails(TrainingType obj) throws Exception;

	public boolean updateBinaryValues(TrainingType obj) throws Exception;

	public boolean deleteBinaryValues(TrainingType obj) throws Exception;
}
