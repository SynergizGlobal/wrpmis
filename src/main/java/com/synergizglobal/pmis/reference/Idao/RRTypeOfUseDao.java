package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRTypeOfUseDao {

	TrainingType getRRTypeOfUseDetails(TrainingType obj) throws Exception;

	boolean addRRTypeOfUse(TrainingType obj) throws Exception;

	boolean updateRRTypeOfUse(TrainingType obj) throws Exception;

	boolean deleteRRTypeOfUse(TrainingType obj) throws Exception;

}
