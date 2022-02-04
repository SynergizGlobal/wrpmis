package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface MotherTongueService {

	TrainingType getMotherTongueDetails(TrainingType obj) throws Exception;

	boolean addMotherTongue(TrainingType obj) throws Exception;

	boolean updateMotherTongue(TrainingType obj) throws Exception;

	boolean deleteMotherTongue(TrainingType obj) throws Exception;

}
