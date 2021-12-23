package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface LAFileTypeDao {

	TrainingType getLAFileTypeDetails(TrainingType obj) throws Exception;

	boolean addLAFileType(TrainingType obj) throws Exception;

	boolean updateLAFileType(TrainingType obj) throws Exception;

	boolean deleteLAFileType(TrainingType obj) throws Exception;

}
