package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface AsBuiltStatusService {

	public TrainingType getAsBuiltStatusDetails(TrainingType obj) throws Exception;

	public boolean addAsBuiltStatus(TrainingType obj) throws Exception;

	public boolean updateAsBuiltStatus(TrainingType obj) throws Exception;

	public boolean deleteAsBuiltStatus(TrainingType obj) throws Exception;

}
