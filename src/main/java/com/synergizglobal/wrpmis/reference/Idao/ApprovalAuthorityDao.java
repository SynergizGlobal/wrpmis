package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ApprovalAuthorityDao {

	TrainingType getApprovalAuthorityDetails(TrainingType obj) throws Exception;

	boolean addApprovalAuthority(TrainingType obj) throws Exception;

	boolean updateApprovalAuthority(TrainingType obj) throws Exception;

	boolean deleteApprovalAuthority(TrainingType obj) throws Exception;

}
