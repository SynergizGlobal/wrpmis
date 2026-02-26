package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DeliverablesStatusDao {


	public List<Safety> getDeliverablesStatusList() throws Exception;

	public boolean addDeliverablesStatus(Safety obj) throws Exception;

	public TrainingType getDeliverablesStatusDetails(TrainingType obj) throws Exception;

	public boolean updateDeliverablesStatus(TrainingType obj) throws Exception;

	public boolean deleteDeliverablesStatus(TrainingType obj) throws Exception;
}
