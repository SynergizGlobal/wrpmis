package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ProjectTypeDao {

	List<TrainingType> getProjectType(TrainingType obj) throws Exception;

	boolean addProjectType(TrainingType obj) throws Exception;

	boolean updateProjectType(TrainingType obj) throws Exception;

	boolean deleteProjectType(TrainingType obj) throws Exception;

}
