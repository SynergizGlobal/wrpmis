package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.WorkContractModuleStatus;

public interface WorkContractModuleStatusDao {

	
	public List<WorkContractModuleStatus> workStatusList(WorkContractModuleStatus obj)throws Exception;

	public WorkContractModuleStatus getWorkStatus(WorkContractModuleStatus wObj)throws Exception;

	public boolean addWorkstatus(WorkContractModuleStatus obj)throws Exception;

	public boolean updateWorkStatus(WorkContractModuleStatus obj)throws Exception;

	public List<WorkContractModuleStatus> getModulesList()throws Exception;

	List<WorkContractModuleStatus> getProjectsListForWorkContractModuleStatusForm(WorkContractModuleStatus obj) throws Exception;

	List<WorkContractModuleStatus> getWorkListForWorkContractModuleStatusForm(WorkContractModuleStatus obj) throws Exception;

	List<WorkContractModuleStatus> getContractsListForWorkContractModuleStatusForm(WorkContractModuleStatus obj) throws Exception;

	public List<WorkContractModuleStatus> getWorkStatusWorksList(WorkContractModuleStatus obj)throws Exception;

	public List<WorkContractModuleStatus> getWorkStatusContractsList(WorkContractModuleStatus obj)throws Exception;

	public List<WorkContractModuleStatus> getWorkStatusProjectsList(WorkContractModuleStatus obj)throws Exception;
	

}
