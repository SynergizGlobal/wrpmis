package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRLocationService {

	TrainingType getRRLocationDetails(TrainingType obj) throws Exception;

	boolean addRRLocation(TrainingType obj) throws Exception;

	boolean deleteRRLocation(TrainingType obj) throws Exception;

	boolean updateRRLocation(TrainingType obj) throws Exception;

}
