package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface StructureFileTypeService {

	List<TrainingType> getStructureFileType(TrainingType obj) throws Exception;

	boolean addStructureFileType(TrainingType obj) throws Exception;

	boolean updateStructureFileType(TrainingType obj) throws Exception;

	boolean deleteStructureFileType(TrainingType obj) throws Exception;

}
