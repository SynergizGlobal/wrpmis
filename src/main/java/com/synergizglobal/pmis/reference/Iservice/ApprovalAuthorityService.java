package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ApprovalAuthorityService {

	TrainingType getApprovalAuthorityDetails(TrainingType obj) throws Exception;

	boolean addApprovalAuthority(TrainingType obj) throws Exception;

	boolean updateApprovalAuthority(TrainingType obj) throws Exception;

	boolean deleteApprovalAuthority(TrainingType obj) throws Exception;

}
