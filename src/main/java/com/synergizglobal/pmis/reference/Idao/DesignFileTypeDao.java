package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface DesignFileTypeDao {

	TrainingType getDesignFileTypeDetails(TrainingType obj) throws Exception;

	boolean addDesignFileType(TrainingType obj) throws Exception;

	boolean updateDesignFileType(TrainingType obj) throws Exception;

	boolean deleteDesignFileType(TrainingType obj) throws Exception;

}
