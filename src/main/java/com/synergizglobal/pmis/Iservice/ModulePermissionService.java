package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.ModulePermission;

public interface ModulePermissionService {

	ModulePermission getModulePermission(ModulePermission obj) throws Exception;

	boolean updateModulePermission(ModulePermission obj) throws Exception;

	List<ModulePermission> getModulesList(ModulePermission obj) throws Exception;
	
	public List<ModulePermission> getUserRoles(ModulePermission obj) throws Exception;

	public List<ModulePermission> getUserTypes(ModulePermission obj) throws Exception;

	public List<ModulePermission> getUsers(ModulePermission obj) throws Exception;

	boolean updateUrlPermissions(ModulePermission obj) throws Exception;

	List<ModulePermission> getModulesForFilter(ModulePermission obj) throws Exception;

	List<ModulePermission> getModuleStatusListForFilter(ModulePermission obj) throws Exception;

}
