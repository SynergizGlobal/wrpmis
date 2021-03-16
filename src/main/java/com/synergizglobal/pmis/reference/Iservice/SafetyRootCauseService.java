package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SafetyRootCauseService {

	public List<Safety> getSafetyRootCauseList() throws Exception;

	public boolean addSafetyRootCause(Safety obj) throws Exception;

	public TrainingType getSafetyRootCauseDetails(TrainingType obj) throws Exception;

	public boolean updateSafetyRootCause(TrainingType obj) throws Exception;

	public boolean deleteSafetyRootCause(TrainingType obj) throws Exception;
}
