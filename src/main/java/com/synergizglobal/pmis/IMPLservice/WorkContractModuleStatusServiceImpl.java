package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.WorkContractModuleStatusDao;
import com.synergizglobal.pmis.Iservice.WorkContractModuleStatusService;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

@Service
public class WorkContractModuleStatusServiceImpl implements WorkContractModuleStatusService {

	@Autowired
	WorkContractModuleStatusDao dao;
	
	@Override
	public List<WorkContractModuleStatus> getContractsList() throws Exception {
		return dao.getContractsList();
	}

	@Override
	public List<WorkContractModuleStatus> workStatusList(WorkContractModuleStatus obj) throws Exception {
		return dao.workStatusList(obj);
	}

	@Override
	public WorkContractModuleStatus getWorkStatus(WorkContractModuleStatus wObj) throws Exception {
		return dao.getWorkStatus(wObj);
	}

	@Override
	public boolean addWorkstatus(WorkContractModuleStatus obj) throws Exception {
		return dao.addWorkstatus(obj);
	}

	@Override
	public boolean updateWorkStatus(WorkContractModuleStatus obj) throws Exception {
		return dao.updateWorkStatus(obj);
	}

	@Override
	public List<WorkContractModuleStatus> getModulesList() throws Exception {
		return dao.getModulesList();
	}

	@Override
	public List<WorkContractModuleStatus> getProjectsList() throws Exception {
		return dao.getProjectsList();
	}

	@Override
	public List<WorkContractModuleStatus> getWorkStatusWorksList(WorkContractModuleStatus obj) throws Exception {
		return dao.getWorkStatusWorksList(obj);
	}

	@Override
	public List<WorkContractModuleStatus> getWorkStatusContractsList(WorkContractModuleStatus obj) throws Exception {
		return dao.getWorkStatusContractsList(obj);
	}

	@Override
	public List<WorkContractModuleStatus> getWorkStatusProjectsList(WorkContractModuleStatus obj) throws Exception {
		return dao.getWorkStatusProjectsList(obj);
	}

}
