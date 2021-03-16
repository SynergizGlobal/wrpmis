package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRStatusDao {
	
	public List<Safety> getRRStatusList() throws Exception;

	public boolean addRRStatus(Safety obj) throws Exception;

	public TrainingType getRRStatusDetails(TrainingType obj) throws Exception;

	public boolean updateRRStatus(TrainingType obj) throws Exception;

	public boolean deleteRRStatus(TrainingType obj) throws Exception;

}
