package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ResourceTypeService {

	TrainingType getResourceTypeDetails(TrainingType obj) throws Exception;

	boolean addResourceType(TrainingType obj) throws Exception;

	boolean updateResourceType(TrainingType obj) throws Exception;

	boolean deleteResourceType(TrainingType obj) throws Exception;

}
