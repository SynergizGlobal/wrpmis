package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Activity;

public interface ProgressApprovalDao {

	List<Activity> getApprovableActivities(Activity obj) throws Exception;

}
