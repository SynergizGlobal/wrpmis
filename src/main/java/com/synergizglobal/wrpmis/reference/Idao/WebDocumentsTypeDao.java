package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface WebDocumentsTypeDao {

	public TrainingType getWebDocumentsTypeDetails(TrainingType obj) throws Exception;

	public boolean addWebDocumentsType(TrainingType obj) throws Exception;

	public boolean updateWebDocumentsType(TrainingType obj) throws Exception;

	public boolean deleteWebDocumentsType(TrainingType obj) throws Exception;

}
