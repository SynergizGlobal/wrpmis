package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.FormsAccessDao;
import com.synergizglobal.pmis.Iservice.FormsAccessService;
import com.synergizglobal.pmis.model.Form;

@Service
public class FormsAccessServiceImpl implements FormsAccessService{
	
	@Autowired
	FormsAccessDao dao;
	
	@Override
	public List<Form> getFormsList(Form obj) throws Exception {
		return dao.getFormsList(obj);
	}

	@Override
	public List<Form> getModulesFilterListInForm(Form obj) throws Exception {
		return dao.getModulesFilterListInForm(obj);
	}

	@Override
	public List<Form> getStatusFilterListInForm(Form obj) throws Exception {
		return dao.getStatusFilterListInForm(obj);
	}

	@Override
	public List<Form> getModulesListForFormAccess(Form obj) throws Exception {
		return dao.getModulesListForFormAccess(obj);
	}
	
	@Override
	public List<Form> getAllModules() throws Exception {
		return dao.getAllModules();
	}		

	@Override
	public List<Form> getFolderssListForFormAccess(Form obj) throws Exception {
		return dao.getFolderssListForFormAccess(obj);
	}

	@Override
	public List<Form> getStatusListForFormAccess(Form obj) throws Exception {
		return dao.getStatusListForFormAccess(obj);
	}

	@Override
	public Form getForm(Form obj) throws Exception {
		return dao.getForm(obj);
	}

	@Override
	public boolean addForm(Form obj) throws Exception {
		return dao.addForm(obj);
	}

	@Override
	public boolean updateForm(Form obj) throws Exception {
		return dao.updateForm(obj);
	}

	@Override
	public List<Form> getUserRolesInFormAccess(Form obj) throws Exception {
		return dao.getUserRolesInFormAccess(obj);
	}

	@Override
	public List<Form> getUserTypesInFormAccess(Form obj) throws Exception {
		return dao.getUserTypesInFormAccess(obj);
	}

	@Override
	public List<Form> getUsersInFormAccess(Form obj) throws Exception {
		return dao.getUsersInFormAccess(obj);
	}

	@Override
	public boolean updateAccessForm(Form obj) throws Exception {
		return dao.updateAccessForm(obj);
	}

}
