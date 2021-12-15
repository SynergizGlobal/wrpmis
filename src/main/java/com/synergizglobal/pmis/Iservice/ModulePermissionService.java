package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.ModulePermission;

public interface ModulePermissionService {

	ModulePermission getModulePermission(ModulePermission obj) throws Exception;

	boolean updateModulePermission(ModulePermission obj) throws Exception;

	List<ModulePermission> getModulePermissionList(ModulePermission obj) throws Exception;

}
