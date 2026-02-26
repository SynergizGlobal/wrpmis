package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface WebDocumentsTypeService {

	public TrainingType getWebDocumentsTypeDetails(TrainingType obj) throws Exception;

	public boolean addWebDocumentsType(TrainingType obj) throws Exception;

	public boolean updateWebDocumentsType(TrainingType obj) throws Exception;

	public boolean deleteWebDocumentsType(TrainingType obj) throws Exception;

}
