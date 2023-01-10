package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.reference.model.WorkModuleUserAccess;

public interface WorkModuleUserAccessService {

	public List<WorkModuleUserAccess> getWorkModuleUserAccesssList() throws Exception;

	public boolean addWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception;
	
	public boolean addUserAccessforExecutionContracts(WorkModuleUserAccess obj) throws Exception;

	public boolean updateWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception;

	public WorkModuleUserAccess getWorkModuleUserAccessDetails(WorkModuleUserAccess obj) throws Exception;

	public boolean deleteWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception;
	
	public List<WorkModuleUserAccess> getProjectsList(WorkModuleUserAccess obj) throws Exception;
	
	public List<WorkModuleUserAccess> getWorksList(WorkModuleUserAccess obj) throws Exception;
	
	public List<WorkModuleUserAccess> getModulesList(WorkModuleUserAccess obj) throws Exception;
	
	public List<WorkModuleUserAccess> getWorkModuleWiseUsers(WorkModuleUserAccess obj) throws Exception;
	
	public List<WorkModuleUserAccess> getSelectedExecutionContracts(WorkModuleUserAccess obj) throws Exception;
	
	public List<WorkModuleUserAccess> getUsersDetails(WorkModuleUserAccess obj) throws Exception;
	
	
}
