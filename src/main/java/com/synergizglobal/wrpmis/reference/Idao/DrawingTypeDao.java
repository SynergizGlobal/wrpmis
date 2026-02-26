package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DrawingTypeDao {

	public List<TrainingType> getDrawingTypesList() throws Exception;

	public boolean addDrawingType(TrainingType obj) throws Exception;

	public TrainingType getDrawingTypeDetails(TrainingType obj) throws Exception;

	public boolean updateDrawingType(TrainingType obj) throws Exception;

	public boolean deleteDrawingType(TrainingType obj) throws Exception;
}
