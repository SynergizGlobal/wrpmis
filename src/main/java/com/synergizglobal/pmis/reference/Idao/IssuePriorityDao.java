package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface IssuePriorityDao {

	public List<Safety> getIssuePriorityList() throws Exception;

	public boolean addIssuePriority(Safety obj) throws Exception;

	public TrainingType getIssuePriorityDetails(TrainingType obj) throws Exception;

	public boolean updateIssuePriority(TrainingType obj) throws Exception;

	public boolean deleteIssuePriority(TrainingType obj) throws Exception;
}

