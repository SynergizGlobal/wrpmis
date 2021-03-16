package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRApprovalStatusDao {

	public List<Safety> getRRApprovalStatusList() throws Exception;

	public boolean addRRApprovalStatus(Safety obj) throws Exception;

	public TrainingType getRApprovalStatusDetails(TrainingType obj) throws Exception;

	public boolean updateRRApprovalStatus(TrainingType obj) throws Exception;

	public boolean deleteRRApprovalStatus(TrainingType obj) throws Exception;
}
