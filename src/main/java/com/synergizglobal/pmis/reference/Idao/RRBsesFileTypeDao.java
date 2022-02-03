package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRBsesFileTypeDao {

	boolean addRRBsesFileType(TrainingType obj) throws Exception;

	boolean updateRRBsesFileType(TrainingType obj) throws Exception;

	boolean deleteRRBsesFileType(TrainingType obj) throws Exception;

	TrainingType getRRBsesFileTypeList(TrainingType obj) throws Exception;

}
