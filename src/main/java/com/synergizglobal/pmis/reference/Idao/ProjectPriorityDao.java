package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ProjectPriorityDao {

	public List<Safety> getProjectPriorityList() throws Exception;

	public boolean addProjectPriority(Safety obj) throws Exception;

	public TrainingType getProjectPriorityDetails(TrainingType obj) throws Exception;

	public boolean updateProjectPriority(TrainingType obj) throws Exception;

	public boolean deleteProjectPriority(TrainingType obj) throws Exception;
}
