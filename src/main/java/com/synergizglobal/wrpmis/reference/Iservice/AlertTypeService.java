package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface AlertTypeService {

	public TrainingType getAlertTypeDetails(TrainingType obj) throws Exception;

	public boolean addAlertType(TrainingType obj) throws Exception;

	public boolean updateAlertType(TrainingType obj) throws Exception;

	public boolean deleteAlertType(TrainingType obj) throws Exception;

}
