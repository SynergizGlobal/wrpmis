package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRPhaseService {

	TrainingType getRRPhaseDetails(TrainingType obj) throws Exception;

	boolean addRRPhase(TrainingType obj) throws Exception;

	boolean updateRRPhase(TrainingType obj) throws Exception;

	boolean deleteRRPhase(TrainingType obj) throws Exception;

}
