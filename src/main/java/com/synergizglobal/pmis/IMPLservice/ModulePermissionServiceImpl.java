package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ModulePermissionDao;
import com.synergizglobal.pmis.Iservice.ModulePermissionService;
import com.synergizglobal.pmis.model.ModulePermission;

@Service
public class ModulePermissionServiceImpl implements ModulePermissionService{
	@Autowired
	ModulePermissionDao dao;

	@Override
	public ModulePermission getModulePermission(ModulePermission obj) throws Exception {
		return dao.getModulePermission(obj);
	}

	@Override
	public boolean updateModulePermission(ModulePermission obj) throws Exception {
		return dao.updateModulePermission(obj);
	}

	@Override
	public List<ModulePermission> getModulesList(ModulePermission obj) throws Exception {
		return dao.getModulesList(obj);
	}
	
	@Override
	public List<ModulePermission> getUserRoles(ModulePermission obj) throws Exception {
		return dao.getUserRoles(obj);
	}

	@Override
	public List<ModulePermission> getUserTypes(ModulePermission obj) throws Exception {
		return dao.getUserTypes(obj);
	}

	@Override
	public List<ModulePermission> getUsers(ModulePermission obj) throws Exception {
		return dao.getUsers(obj);
	}

	@Override
	public boolean updateUrlPermissions(ModulePermission obj) throws Exception {
		return dao.updateUrlPermissions(obj);
	}
	
}
