package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRTenureStatusService {

	TrainingType getRRTenureStatusDetails(TrainingType obj) throws Exception;

	boolean addRRTenureStatus(TrainingType obj) throws Exception;

	boolean updateRRTenureStatus(TrainingType obj) throws Exception;

	boolean deleteRRTenureStatus(TrainingType obj) throws Exception;

}
