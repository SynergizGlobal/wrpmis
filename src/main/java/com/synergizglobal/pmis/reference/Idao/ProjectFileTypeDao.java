package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ProjectFileTypeDao {

	List<TrainingType> getProjectFileType(TrainingType obj) throws Exception;

	boolean addProjectFileType(TrainingType obj) throws Exception;

	boolean updateProjectFileType(TrainingType obj) throws Exception;

	boolean deleteProjectFileType(TrainingType obj) throws Exception;

}
