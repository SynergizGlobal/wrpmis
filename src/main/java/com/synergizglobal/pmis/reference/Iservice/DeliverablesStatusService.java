package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface DeliverablesStatusService {

	public List<Safety> getDeliverablesStatusList() throws Exception;

	public boolean addDeliverablesStatus(Safety obj) throws Exception;

	public TrainingType getDeliverablesStatusDetails(TrainingType obj) throws Exception;

	public boolean updateDeliverablesStatus(TrainingType obj) throws Exception;

	public boolean deleteDeliverablesStatus(TrainingType obj) throws Exception;
}
