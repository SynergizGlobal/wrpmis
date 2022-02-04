package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface MotherTongueDao {

	TrainingType getMotherTongueDetails(TrainingType obj) throws Exception;

	boolean addMotherTongue(TrainingType obj) throws Exception;

	boolean updateMotherTongue(TrainingType obj) throws Exception;

	boolean deleteMotherTongue(TrainingType obj) throws Exception;

}
