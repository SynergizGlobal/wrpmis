package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TypeOfFamilyService {

	TrainingType getTypeOfFamilyDetails(TrainingType obj) throws Exception;

	boolean addTypeOfFamily(TrainingType obj) throws Exception;

	boolean updateTypeOfFamily(TrainingType obj) throws Exception;

	boolean deleteTypeOfFamily(TrainingType obj) throws Exception;

}
