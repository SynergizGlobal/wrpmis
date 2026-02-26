package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface StructureFileTypeDao {

	List<TrainingType> getStructureFileType(TrainingType obj) throws Exception;

	boolean addStructureFileType(TrainingType obj) throws Exception;

	boolean updateStructureFileType(TrainingType obj) throws Exception;

	boolean deleteStructureFileType(TrainingType obj) throws Exception;

}
