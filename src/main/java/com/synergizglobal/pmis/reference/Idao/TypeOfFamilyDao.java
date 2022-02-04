package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TypeOfFamilyDao {

	TrainingType getTypeOfFamilyDetails(TrainingType obj) throws Exception;

	boolean addTypeOfFamily(TrainingType obj) throws Exception;

	boolean updateTypeOfFamily(TrainingType obj) throws Exception;

	boolean deleteTypeOfFamily(TrainingType obj) throws Exception;

}
