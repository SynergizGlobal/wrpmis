package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ProjectTypeService {

	List<TrainingType> getProjectType(TrainingType obj) throws Exception;

	boolean addProjectType(TrainingType obj) throws Exception;

	boolean updateProjectType(TrainingType obj) throws Exception;

	boolean deleteProjectType(TrainingType obj) throws Exception;

}
