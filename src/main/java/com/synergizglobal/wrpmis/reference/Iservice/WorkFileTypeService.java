package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface WorkFileTypeService {

	List<TrainingType> getWorkFileType(TrainingType obj) throws Exception;

	boolean addWorkFileType(TrainingType obj)throws Exception;

	boolean updateWorkFileType(TrainingType obj)throws Exception;

	boolean deleteWorkFileType(TrainingType obj)throws Exception;

}
