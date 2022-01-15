package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface LaLandStatusService {

	TrainingType getLandAcquisitionStatusDetails(TrainingType obj) throws Exception;

	boolean addLaStatus(Safety obj) throws Exception;

	boolean updatelandAcquisitionStatus(TrainingType obj) throws Exception;

	boolean deletelandAcquisitionStatus(TrainingType obj) throws Exception;

}
