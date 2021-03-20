package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface AlertTypeDao {

	public TrainingType getAlertTypeDetails(TrainingType obj) throws Exception;

	public boolean addAlertType(TrainingType obj) throws Exception;

	public boolean updateAlertType(TrainingType obj) throws Exception;

	public boolean deleteAlertType(TrainingType obj) throws Exception;

}
