package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RevisionStatusDao {

	public List<Risk> getRevisionStatusList() throws Exception;

	public boolean addRevisionStatus(Risk obj) throws Exception;

	public TrainingType getRevisionStatusDetails(TrainingType obj) throws Exception;

	public boolean updateRevisionStatus(TrainingType obj) throws Exception;

	public boolean deleteRevisionStatus(TrainingType obj) throws Exception;
}
