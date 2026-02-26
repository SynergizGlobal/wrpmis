package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface TypeOfFamilyService {

	TrainingType getTypeOfFamilyDetails(TrainingType obj) throws Exception;

	boolean addTypeOfFamily(TrainingType obj) throws Exception;

	boolean updateTypeOfFamily(TrainingType obj) throws Exception;

	boolean deleteTypeOfFamily(TrainingType obj) throws Exception;

}
