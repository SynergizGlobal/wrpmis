package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.reference.model.User;

public interface ModuleDao {

	public List<Safety> getModuleList() throws Exception;
	
	public List<User> getModuleInchargeList() throws Exception;

	public boolean addModule(Safety obj) throws Exception;

	public TrainingType getModuleDetails(TrainingType obj) throws Exception;

	public boolean updateModule(TrainingType obj) throws Exception;

	public boolean deleteModule(TrainingType obj) throws Exception;

	public List<TrainingType> getModuleStatusList() throws Exception;
}
