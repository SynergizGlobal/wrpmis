package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface GeneralStatusDao {

	public List<Safety> getGeneralStatusList() throws Exception;

	public boolean addGeneralStatus(Safety obj) throws Exception;

	public TrainingType getGeneralStatusDetails(TrainingType obj) throws Exception;

	public boolean updateGeneralStatus(TrainingType obj) throws Exception;

	public boolean deleteGeneralStatus(TrainingType obj) throws Exception;
}
