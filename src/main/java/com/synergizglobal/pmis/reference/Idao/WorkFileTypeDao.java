package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface WorkFileTypeDao {

	List<TrainingType> getWorkFileType(TrainingType obj)throws Exception;

	boolean addWorkFileType(TrainingType obj)throws Exception;

	boolean updateWorkFileType(TrainingType obj)throws Exception;

	boolean deleteWorkFileType(TrainingType obj)throws Exception;

}
