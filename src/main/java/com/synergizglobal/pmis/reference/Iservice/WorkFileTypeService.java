package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface WorkFileTypeService {

	List<TrainingType> getWorkFileType(TrainingType obj) throws Exception;

	boolean addWorkFileType(TrainingType obj)throws Exception;

	boolean updateWorkFileType(TrainingType obj)throws Exception;

	boolean deleteWorkFileType(TrainingType obj)throws Exception;

}
