package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.UtilityShifting;

public interface UserService {

	List<User> getUserRoles() throws Exception;

	List<User> getUserDepartments() throws Exception;

	List<User> getUserReportingToList(User obj) throws Exception;

	List<User> getUsersList(User obj) throws Exception;

	String addUser(User obj) throws Exception;

	User getUser(User obj) throws Exception;
	
	boolean updateUser(User obj) throws Exception;
	
	boolean deleteUser(User obj) throws Exception;

	int uploadUsers(List<User> usersList) throws Exception;

	List<User> getPmisKeys() throws Exception;

	String checkPMISKeyAvailability(User obj) throws Exception;

	List<User> getUserRolesFilter(User obj) throws Exception;

	List<User> getUserDepartmentsFilter(User obj) throws Exception;

	List<User> getUserReportingToListFilter(User obj) throws Exception;

	List<User> getUsersExportList(User obj) throws Exception;

	List<User> getUserTypes() throws Exception;

	List<User> getUserTypesFilter(User obj) throws Exception;

	List<User> getReportingToUserId(String reporting_to_id_srfk) throws Exception;

	List<User> getAllUsersList(User obj) throws Exception;
	List<User> getResponsiblePersonUsers(User obj) throws Exception;

	List<Contract> getContractsList(User obj) throws Exception;

	List<Structure> getStructuresList(User obj) throws Exception;

	List<Risk> getRiskList(User obj) throws Exception;

	List<LandAcquisition> getLandList(User obj) throws Exception;

	List<UtilityShifting> getUtilityList(User obj) throws Exception;

	List<RandRMain> getRRList(User obj) throws Exception;

	List<User> getModuleList(User obj) throws Exception;

	List<User> getModuleSList(User obj) throws Exception;

	
}
