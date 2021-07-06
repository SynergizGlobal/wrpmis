package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Activity;

public interface ProgressApprovalService {

	List<Activity> getApprovableActivities(Activity obj) throws Exception;

}
