package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface WebDocumentCategoryService {

	public TrainingType getWebDocumentsCategoryDetails(TrainingType obj) throws Exception;

	public boolean addWebDocumentsCategory(Risk obj) throws Exception;

	public boolean updateWebDocumentsCategory(TrainingType obj) throws Exception;

	public boolean deleteWebDocumentsCategory(TrainingType obj) throws Exception;

	public List<TrainingType> getDocumentType(TrainingType obj) throws Exception;

}
