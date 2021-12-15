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
	public List<ModulePermission> getModulePermissionList(ModulePermission obj) throws Exception {
		return dao.getModulePermissionList(obj);
	}
}
