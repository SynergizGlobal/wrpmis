package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface MotherTongueService {

	TrainingType getMotherTongueDetails(TrainingType obj) throws Exception;

	boolean addMotherTongue(TrainingType obj) throws Exception;

	boolean updateMotherTongue(TrainingType obj) throws Exception;

	boolean deleteMotherTongue(TrainingType obj) throws Exception;

}
