package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

public interface WorkContractModuleStatusDao {

	public List<WorkContractModuleStatus> getContractsList() throws Exception;

	public List<WorkContractModuleStatus> workStatusList(WorkContractModuleStatus obj)throws Exception;

	public WorkContractModuleStatus getWorkStatus(WorkContractModuleStatus wObj)throws Exception;

	public boolean addWorkstatus(WorkContractModuleStatus obj)throws Exception;
	

}
