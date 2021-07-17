package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TemplateUploadDao {

	List<TrainingType> getTemplatesList() throws Exception;

	boolean uploadTemplate(TrainingType obj) throws Exception;

	boolean deleteTemplate(TrainingType obj) throws Exception;

	List<TrainingType> getTemplateHistoryList(TrainingType obj) throws Exception;

}
