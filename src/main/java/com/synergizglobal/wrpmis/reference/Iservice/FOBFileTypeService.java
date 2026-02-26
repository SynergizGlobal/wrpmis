package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface FOBFileTypeService {

	TrainingType getfobFileType(TrainingType obj) throws Exception;

	boolean addFOBFileType(TrainingType obj) throws Exception;

	boolean updateFOBFileType(TrainingType obj) throws Exception;

	boolean deleteFOBFileType(TrainingType obj) throws Exception;

}
