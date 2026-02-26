package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface FOBFileTypeDao {

	TrainingType getfobFileType(TrainingType obj) throws Exception;

	boolean addFOBFileType(TrainingType obj) throws Exception;

	boolean updateFOBFileType(TrainingType obj) throws Exception;

	boolean deleteFOBFileType(TrainingType obj) throws Exception;

}
