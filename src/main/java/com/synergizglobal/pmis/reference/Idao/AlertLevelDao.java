package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface AlertLevelDao {

	public List<TrainingType> getAlertLevelList() throws Exception;

	public TrainingType getAlertLevelDetails(TrainingType obj) throws Exception;

	public boolean addAlertLevel(TrainingType obj) throws Exception;

	public boolean updateAlertLevel(TrainingType obj) throws Exception;

	public boolean deleteAlertLevel(TrainingType obj) throws Exception;

}
