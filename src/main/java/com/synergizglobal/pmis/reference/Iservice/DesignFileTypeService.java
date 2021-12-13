package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface DesignFileTypeService {

	TrainingType getDesignFileTypeDetails(TrainingType obj) throws Exception;

	boolean addDesignFileType(TrainingType obj) throws Exception;

	boolean updateDesignFileType(TrainingType obj) throws Exception;

	boolean deleteDesignFileType(TrainingType obj) throws Exception;

}
