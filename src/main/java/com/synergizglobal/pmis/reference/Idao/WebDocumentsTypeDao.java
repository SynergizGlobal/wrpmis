package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface WebDocumentsTypeDao {

	public TrainingType getWebDocumentsTypeDetails(TrainingType obj) throws Exception;

	public boolean addWebDocumentsType(TrainingType obj) throws Exception;

	public boolean updateWebDocumentsType(TrainingType obj) throws Exception;

	public boolean deleteWebDocumentsType(TrainingType obj) throws Exception;

}
