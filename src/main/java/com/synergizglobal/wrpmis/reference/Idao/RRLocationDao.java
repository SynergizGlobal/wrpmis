package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRLocationDao {

	TrainingType getRRLocationDetails(TrainingType obj) throws Exception;

	boolean addRRLocation(TrainingType obj) throws Exception;

	boolean deleteRRLocation(TrainingType obj) throws Exception;

	boolean updateRRLocation(TrainingType obj) throws Exception;

}
