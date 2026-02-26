package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface LaLandStatusService {

	TrainingType getLandAcquisitionStatusDetails(TrainingType obj) throws Exception;

	boolean addLaStatus(Safety obj) throws Exception;

	boolean updatelandAcquisitionStatus(TrainingType obj) throws Exception;

	boolean deletelandAcquisitionStatus(TrainingType obj) throws Exception;

}
