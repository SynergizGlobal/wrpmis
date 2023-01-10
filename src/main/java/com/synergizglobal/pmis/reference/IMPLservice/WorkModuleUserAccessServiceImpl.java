package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.WorkModuleUserAccessDao;
import com.synergizglobal.pmis.reference.Iservice.WorkModuleUserAccessService;
import com.synergizglobal.pmis.reference.model.WorkModuleUserAccess;
@Service
public class WorkModuleUserAccessServiceImpl implements WorkModuleUserAccessService{

	@Autowired
	WorkModuleUserAccessDao dao;

	@Override
	public List<WorkModuleUserAccess> getWorkModuleUserAccesssList() throws Exception {
		return dao.getWorkModuleUserAccesssList();
	}

	@Override
	public boolean addWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception {
		return dao.addWorkModuleUserAccess(obj);
	}

	@Override
	public boolean updateWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception {
		return dao.updateWorkModuleUserAccess(obj);
	}

	@Override
	public WorkModuleUserAccess getWorkModuleUserAccessDetails(WorkModuleUserAccess obj) throws Exception {
		return dao.getWorkModuleUserAccessDetails(obj);
	}

	@Override
	public boolean deleteWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception {
		return dao.deleteWorkModuleUserAccess(obj);
	}
	
	@Override
	public List<WorkModuleUserAccess> getProjectsList(WorkModuleUserAccess obj) throws Exception
	{
		return dao.getProjectsList(obj);
	}
	
	@Override
	public List<WorkModuleUserAccess> getWorksList(WorkModuleUserAccess obj) throws Exception
	{
		return dao.getWorksList(obj);
	}
	
	@Override
	public List<WorkModuleUserAccess> getModulesList(WorkModuleUserAccess obj) throws Exception
	{
		return dao.getModulesList(obj);
	}
	
	@Override
	public List<WorkModuleUserAccess> getWorkModuleWiseUsers(WorkModuleUserAccess obj) throws Exception
	{
		return dao.getWorkModuleWiseUsers(obj);
	}
	
	@Override
	public List<WorkModuleUserAccess> getUsersDetails(WorkModuleUserAccess obj) throws Exception
	{
		return dao.getUsersDetails(obj);
	}

	@Override
	public boolean addUserAccessforExecutionContracts(WorkModuleUserAccess obj) throws Exception {
		return dao.addUserAccessforExecutionContracts(obj);
	}
	
	public List<WorkModuleUserAccess> getSelectedExecutionContracts(WorkModuleUserAccess obj) throws Exception
	{
		return dao.getSelectedExecutionContracts(obj);
	}
	
}
