package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ProjectFileTypeService {

	List<TrainingType> getProjectFileType(TrainingType obj) throws Exception;

	boolean addProjectFileType(TrainingType obj) throws Exception;

	boolean updateProjectFileType(TrainingType obj) throws Exception;

	boolean deleteProjectFileType(TrainingType obj) throws Exception;

}
