package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DivisionService {
	
	public List<TrainingType> getDivisionsList() throws Exception;

	public boolean addDivision(TrainingType obj) throws Exception;

	public TrainingType getDivisionDetails(TrainingType obj) throws Exception;

	public boolean updateDivision(TrainingType obj) throws Exception;

	public boolean deleteDivision(TrainingType obj) throws Exception;

}
