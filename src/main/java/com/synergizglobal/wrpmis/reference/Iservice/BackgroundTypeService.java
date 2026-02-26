package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface BackgroundTypeService {

	public List<TrainingType> getBackgroundTypesList() throws Exception;

	public boolean addBackgroundType(TrainingType obj) throws Exception;

	public boolean updateBackgroundType(TrainingType obj) throws Exception;

	public TrainingType getBankGuaranteeDetails(TrainingType obj) throws Exception;

	public boolean deleteBankGuaranteeType(TrainingType obj) throws Exception;
}
