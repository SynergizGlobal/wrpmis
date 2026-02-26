package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface LaLandStatusDao {

	TrainingType getLandAcquisitionStatusDetails(TrainingType obj) throws Exception;

	boolean addLaStatus(Safety obj) throws Exception;

	boolean updatelandAcquisitionStatus(TrainingType obj) throws Exception;

	boolean deletelandAcquisitionStatus(TrainingType obj) throws Exception;

}
