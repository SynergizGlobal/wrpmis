package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRLandTypeDao {

	public List<Safety> getRRLandTypeList() throws Exception;

	public boolean addRRLandType(Safety obj) throws Exception;

	public TrainingType getRRLandTypeDetails(TrainingType obj) throws Exception;

	public boolean updateRRLandType(TrainingType obj) throws Exception;

	public boolean deleteRRLandType(TrainingType obj) throws Exception;
}

