package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface LaStatusDao {

	public List<Safety> getIaStatusList() throws Exception;

	public boolean addLaStatus(Safety obj) throws Exception;

	public TrainingType getLandAcquisitionStatusDetails(TrainingType obj) throws Exception;

	public boolean updatelandAcquisitionStatus(TrainingType obj) throws Exception;

	public boolean deletelandAcquisitionStatus(TrainingType obj) throws Exception;
}
