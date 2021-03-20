package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UserLoginEventTypeService {

	public TrainingType getUserTypeDetails(TrainingType obj) throws Exception;

	public boolean addUserLoginEventType(TrainingType obj) throws Exception;

	public boolean updateUserLoginEventType(TrainingType obj) throws Exception;

	public boolean deleteUserLoginEventType(TrainingType obj) throws Exception;

}
