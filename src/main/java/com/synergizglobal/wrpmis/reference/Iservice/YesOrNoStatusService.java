package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface YesOrNoStatusService {

	public TrainingType getYesOrNoStatusDetails(TrainingType obj) throws Exception;

	public boolean addYesOrNoStatus(TrainingType obj) throws Exception;

	public boolean updateYesOrNoStatus(TrainingType obj) throws Exception;

	public boolean deleteYesOrNoStatus(TrainingType obj) throws Exception;

}
