package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface AlertLevelService {

	public List<TrainingType> getAlertLevelList() throws Exception;

	public TrainingType getAlertLevelDetails(TrainingType obj) throws Exception;

	public boolean addAlertLevel(TrainingType obj) throws Exception;

	public boolean updateAlertLevel(TrainingType obj) throws Exception;

	public boolean deleteAlertLevel(TrainingType obj) throws Exception;

}
