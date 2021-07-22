package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface TemplateUploadService {

	List<TrainingType> getTemplatesList() throws Exception;

	boolean uploadTemplate(TrainingType obj) throws Exception;

	boolean deleteTemplate(TrainingType obj) throws Exception;


}
