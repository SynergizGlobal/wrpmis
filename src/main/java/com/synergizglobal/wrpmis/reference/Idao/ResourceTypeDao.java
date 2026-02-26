package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ResourceTypeDao {

	TrainingType getResourceTypeDetails(TrainingType obj) throws Exception;

	boolean addResourceType(TrainingType obj) throws Exception;

	boolean updateResourceType(TrainingType obj) throws Exception;

	boolean deleteResourceType(TrainingType obj) throws Exception;


}
