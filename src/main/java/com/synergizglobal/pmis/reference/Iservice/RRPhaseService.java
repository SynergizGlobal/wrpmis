package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRPhaseService {

	TrainingType getRRPhaseDetails(TrainingType obj) throws Exception;

	boolean addRRPhase(TrainingType obj) throws Exception;

	boolean updateRRPhase(TrainingType obj) throws Exception;

	boolean deleteRRPhase(TrainingType obj) throws Exception;

}
