package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Form;

public interface FormsAccessDao {

	public List<Form> getFormsList(Form obj) throws Exception;

	public List<Form> getModulesFilterListInForm(Form obj) throws Exception;

	public List<Form> getStatusFilterListInForm(Form obj) throws Exception;

	public Form getForm(Form obj) throws Exception;

	public boolean addForm(Form obj) throws Exception;

	public boolean updateForm(Form obj) throws Exception;

	public List<Form> getUserRolesInFormAccess(Form obj) throws Exception;

	public List<Form> getUserTypesInFormAccess(Form obj) throws Exception;

	public List<Form> getUsersInFormAccess(Form obj) throws Exception;
	
	public List<Form> getModulesListForFormAccess(Form obj) throws Exception;

	public List<Form> getFolderssListForFormAccess(Form obj) throws Exception;

	public List<Form> getStatusListForFormAccess(Form obj) throws Exception;
}
