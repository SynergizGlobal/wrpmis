package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DivisionDao {

	public List<TrainingType> getDivisionsList() throws Exception;

	public boolean addDivision(TrainingType obj) throws Exception;

	public TrainingType getDivisionDetails(TrainingType obj) throws Exception;

	public boolean updateDivision(TrainingType obj) throws Exception;

	public boolean deleteDivision(TrainingType obj) throws Exception;
}
