package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ApprovalAuthorityDao {

	TrainingType getApprovalAuthorityDetails(TrainingType obj) throws Exception;

	boolean addApprovalAuthority(TrainingType obj) throws Exception;

	boolean updateApprovalAuthority(TrainingType obj) throws Exception;

	boolean deleteApprovalAuthority(TrainingType obj) throws Exception;

}
