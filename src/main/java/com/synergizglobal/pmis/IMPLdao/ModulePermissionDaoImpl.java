package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.ModulePermissionDao;
import com.synergizglobal.pmis.model.ModulePermission;
@Repository
public class ModulePermissionDaoImpl implements ModulePermissionDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public ModulePermission getModulePermission(ModulePermission obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateModulePermission(ModulePermission obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ModulePermission> getModulePermissionList(ModulePermission obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
