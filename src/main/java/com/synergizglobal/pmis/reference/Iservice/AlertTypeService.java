package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface AlertTypeService {

	public TrainingType getAlertTypeDetails(TrainingType obj) throws Exception;

	public boolean addAlertType(TrainingType obj) throws Exception;

	public boolean updateAlertType(TrainingType obj) throws Exception;

	public boolean deleteAlertType(TrainingType obj) throws Exception;

}
