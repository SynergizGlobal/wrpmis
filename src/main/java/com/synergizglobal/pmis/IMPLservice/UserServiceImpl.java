package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.UserDao;
import com.synergizglobal.pmis.Iservice.UserService;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.UtilityShifting;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUserRoles() throws Exception{
		return userDao.getUserRoles();
	}

	@Override
	public List<User> getUserDepartments() throws Exception {
		return userDao.getUserDepartments();
	}

	@Override
	public List<User> getUserReportingToList(User obj) throws Exception {
		return userDao.getUserReportingToList(obj);
	}

	@Override
	public List<User> getUsersList(User obj) throws Exception {
		return userDao.getUsersList(obj);
	}

	@Override
	public String addUser(User obj) throws Exception {
		return userDao.addUser(obj);
	}

	@Override
	public User getUser(User obj) throws Exception {
		return userDao.getUser(obj);
	}

	@Override
	public boolean updateUser(User obj) throws Exception {
		return userDao.updateUser(obj);
	}

	@Override
	public boolean deleteUser(User obj) throws Exception {
		return userDao.deleteUser(obj);
	}

	@Override
	public int uploadUsers(List<User> usersList) throws Exception {
		return userDao.uploadUsers(usersList);
	}

	@Override
	public List<User> getPmisKeys() throws Exception {
		return userDao.getPmisKeys();
	}

	@Override
	public String checkPMISKeyAvailability(User obj) throws Exception {
		return userDao.checkPMISKeyAvailability(obj);
	}

	@Override
	public List<User> getUserRolesFilter(User obj) throws Exception {
		return userDao.getUserRolesFilter(obj);
	}

	@Override
	public List<User> getUserDepartmentsFilter(User obj) throws Exception {
		return userDao.getUserDepartmentsFilter(obj);
	}

	@Override
	public List<User> getUserReportingToListFilter(User obj) throws Exception {
		return userDao.getUserReportingToListFilter(obj);
	}

	@Override
	public List<User> getUsersExportList(User obj) throws Exception {
		return userDao.getUsersExportList(obj);
	}

	@Override
	public List<User> getUserTypes() throws Exception {
		return userDao.getUserTypes();
	}

	@Override
	public List<User> getUserTypesFilter(User obj) throws Exception {
		return userDao.getUserTypesFilter(obj);
	}

	@Override
	public List<User> getReportingToUserId(String reporting_to_id_srfk) throws Exception {
		return userDao.getReportingToUserId(reporting_to_id_srfk);
	}
	
	@Override
	public List<User> getAllUsersList(User obj) throws Exception {
		return userDao.getAllUsersList(obj);
	}
	@Override
	public List<User> getResponsiblePersonUsers(User obj) throws Exception {
		return userDao.getResponsiblePersonUsers(obj);
	}

	@Override
	public List<Contract> getContractsList(User obj) throws Exception {
		return userDao.getContractsList( obj);
	}

	@Override
	public List<Structure> getStructuresList(User obj) throws Exception {
		return userDao.getStructuresList( obj);
	}

	@Override
	public List<Risk> getRiskList(User obj) throws Exception {
		return userDao.getRiskList( obj);
	}

	@Override
	public List<LandAcquisition> getLandList(User obj) throws Exception {
		return userDao.getLandList( obj);
	}

	@Override
	public List<UtilityShifting> getUtilityList(User obj) throws Exception {
		return userDao.getUtilityList( obj);
	}

	@Override
	public List<RandRMain> getRRList(User obj) throws Exception {
		return userDao.getRRList( obj);
	}

	@Override
	public List<User> getModuleList(User obj) throws Exception {
		return userDao.getModuleList( obj);
	}

	@Override
	public List<User> getModuleSList(User obj) throws Exception {
		return userDao.getModuleSList( obj);
	}

	@Override
	public List<User> getStructuresByContractId(User obj) throws Exception {
		return userDao.getStructuresByContractId( obj);
	}	

}
