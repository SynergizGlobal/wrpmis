package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRPhaseDao {

	TrainingType getRRPhaseDetails(TrainingType obj)  throws Exception;

	boolean addRRPhase(TrainingType obj) throws Exception;

	boolean updateRRPhase(TrainingType obj) throws Exception;

	boolean deleteRRPhase(TrainingType obj) throws Exception;

}
