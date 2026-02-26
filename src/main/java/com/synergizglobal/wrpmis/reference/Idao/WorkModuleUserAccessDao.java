package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.LandAcquisition;
import com.synergizglobal.wrpmis.reference.model.WorkModuleUserAccess;

public interface WorkModuleUserAccessDao {


	public List<WorkModuleUserAccess> getWorkModuleUserAccesssList() throws Exception;

	public boolean addWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception;

	public boolean updateWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception;

	public WorkModuleUserAccess getWorkModuleUserAccessDetails(WorkModuleUserAccess obj) throws Exception;

	public boolean deleteWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception;

	public List<WorkModuleUserAccess> getProjectsList(WorkModuleUserAccess obj)  throws Exception;

	public List<WorkModuleUserAccess> getWorksList(WorkModuleUserAccess obj)  throws Exception;

	public List<WorkModuleUserAccess> getModulesList(WorkModuleUserAccess obj)  throws Exception;

	public List<WorkModuleUserAccess> getWorkModuleWiseUsers(WorkModuleUserAccess obj) throws Exception;

	public List<WorkModuleUserAccess> getUsersDetails(WorkModuleUserAccess obj)  throws Exception;

	public boolean addUserAccessforExecutionContracts(WorkModuleUserAccess obj) throws Exception;

	public List<WorkModuleUserAccess> getSelectedExecutionContracts(WorkModuleUserAccess obj) throws Exception;
	


}
