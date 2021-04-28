package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface FOBFileTypeDao {

	List<TrainingType> getfobFileType(TrainingType obj) throws Exception;

	boolean addFOBFileType(TrainingType obj) throws Exception;

	boolean updateFOBFileType(TrainingType obj) throws Exception;

	boolean deleteFOBFileType(TrainingType obj) throws Exception;

}
