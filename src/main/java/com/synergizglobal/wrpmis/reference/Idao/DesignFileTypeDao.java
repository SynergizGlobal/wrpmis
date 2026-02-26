package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DesignFileTypeDao {

	TrainingType getDesignFileTypeDetails(TrainingType obj) throws Exception;

	boolean addDesignFileType(TrainingType obj) throws Exception;

	boolean updateDesignFileType(TrainingType obj) throws Exception;

	boolean deleteDesignFileType(TrainingType obj) throws Exception;

}
