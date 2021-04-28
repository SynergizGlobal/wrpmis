package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface FOBFileTypeService {

	List<TrainingType> getfobFileType(TrainingType obj) throws Exception;

	boolean addFOBFileType(TrainingType obj) throws Exception;

	boolean updateFOBFileType(TrainingType obj) throws Exception;

	boolean deleteFOBFileType(TrainingType obj) throws Exception;

}
