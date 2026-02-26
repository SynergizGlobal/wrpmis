package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRPhaseDao {

	TrainingType getRRPhaseDetails(TrainingType obj)  throws Exception;

	boolean addRRPhase(TrainingType obj) throws Exception;

	boolean updateRRPhase(TrainingType obj) throws Exception;

	boolean deleteRRPhase(TrainingType obj) throws Exception;

}
