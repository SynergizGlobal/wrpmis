package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ModuleService {

	public List<Safety> getModuleList() throws Exception;

	public boolean addModule(Safety obj) throws Exception;

	public TrainingType getModuleDetails(TrainingType obj) throws Exception;

	public boolean updateModule(TrainingType obj) throws Exception;

	public boolean deleteModule(TrainingType obj) throws Exception;


}
