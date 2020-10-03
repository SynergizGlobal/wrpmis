package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SafetyDao;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.model.Safety;

@Service
public class SafetyServiceImpl implements SafetyService {
	@Autowired
	SafetyDao safetyDao;
	
	@Override
	public List<Safety> getSafetyList(Safety obj) throws Exception {
		return safetyDao.getSafetyList(obj);
	}

	@Override
	public List<Safety> getSafetyStatusList() throws Exception {
		return safetyDao.getSafetyStatusList();
	}

	@Override
	public List<Safety> getSafetyImpactList() throws Exception {
		return safetyDao.getSafetyImpactList();
	}

	@Override
	public List<Safety> getSafetyCategoryList() throws Exception {
		return safetyDao.getSafetyCategoryList();
	}

	@Override
	public List<Safety> getSafetyRootCauseList() throws Exception {
		return safetyDao.getSafetyRootCauseList();
	}

	@Override
	public boolean addSafety(Safety obj) throws Exception {
		return safetyDao.addSafety(obj);
	}

	@Override
	public Safety getSafety(Safety obj) throws Exception {
		return safetyDao.getSafety(obj);
	}

	@Override
	public boolean updateSafety(Safety obj) throws Exception {
		return safetyDao.updateSafety(obj);
	}

	@Override
	public boolean deleteSafety(Safety obj) throws Exception {
		return safetyDao.deleteSafety(obj);
	}

	@Override
	public List<Safety> getDepartmentList() throws Exception {
		return safetyDao.getDepartmentList();
	}

	@Override
	public List<Safety> getContractsListFromSafety() throws Exception {
		return safetyDao.getContractsListFromSafety();
	}

	@Override
	public List<Safety> getDepartmentsListFromSafety() throws Exception {
		return safetyDao.getDepartmentsListFromSafety();
	}

	@Override
	public List<Safety> getCategoryListFromSafety() throws Exception {
		return safetyDao.getCategoryListFromSafety();
	}

	@Override
	public List<Safety> getStatusListFromSafety() throws Exception {
		return safetyDao.getStatusListFromSafety();
	}
	
}
