package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.UtilityShiftingDao;
import com.synergizglobal.pmis.Iservice.UtilityShiftingService;
import com.synergizglobal.pmis.model.UtilityShifting;

@Service
public class UtilityShiftingServiceImpl implements UtilityShiftingService {
	@Autowired
	UtilityShiftingDao utilityShiftingDao;
	

	@Override
	public List<UtilityShifting> getWorksListFilter(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getWorksListFilter(obj);
	}

	@Override
	public List<UtilityShifting> getLocationListFilter(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getLocationListFilter(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityCategoryListFilter(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityCategoryListFilter(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityTypeListFilter(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityTypeListFilter(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityStatusListFilter(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityStatusListFilter(obj);
	}

	@Override
	public List<UtilityShifting> getProjectsListForUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getProjectsListForUtilityShifting(obj);
	}

	@Override
	public List<UtilityShifting> getWorkListForUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getWorkListForUtilityShifting(obj);
	}

	@Override
	public List<UtilityShifting> getContractsListForUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getContractsListForUtilityShifting(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityTypeList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityTypeList(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityCategoryList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityCategoryList(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityExecutionAgencyList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityExecutionAgencyList(obj);
	}

	@Override
	public List<UtilityShifting> getImpactedContractList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getImpactedContractList(obj);
	}

	@Override
	public List<UtilityShifting> getRequirementStageList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getRequirementStageList(obj);
	}

	@Override
	public List<UtilityShifting> getUnitListForUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUnitListForUtilityShifting(obj);
	}

	@Override
	public List<UtilityShifting> getUtilityTypeListForUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityTypeListForUtilityShifting(obj);
	}

	@Override
	public List<UtilityShifting> getStatusListForUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getStatusListForUtilityShifting(obj);
	}
	
	@Override
	public boolean addUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.addUtilityShifting(obj);
	}	
	
	@Override
	public UtilityShifting getUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityShifting(obj);
	}

	@Override
	public boolean updateUtilityShifting(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.updateUtilityShifting(obj);
	}	

	@Override
	public int getTotalRecords(UtilityShifting obj, String searchParameter) throws Exception {
		return utilityShiftingDao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<UtilityShifting> getUtilityShiftingList(UtilityShifting obj, int startIndex, int offset, String searchParameter) throws Exception {
		return utilityShiftingDao.getUtilityShiftingList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<UtilityShifting> getRDetailsList(String utility_shifting_id) throws Exception {
		return utilityShiftingDao.getRDetailsList(utility_shifting_id);
	}

	@Override
	public List<UtilityShifting> getUtilityShiftingList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityShiftingList(obj);
	}
	@Override
	public List<UtilityShifting> getUtilityShiftingStatus(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityShiftingStatus(obj);
	}

	@Override
	public String[] uploadUtilityShiftingData(List<UtilityShifting> ussList, UtilityShifting us) throws Exception {
		return utilityShiftingDao.uploadUtilityShiftingData(ussList,us);
	}

	@Override
	public List<UtilityShifting> getUtilityShiftingUploadsList(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.getUtilityShiftingUploadsList(obj);
	}

	@Override
	public boolean saveUSDataUploadFile(UtilityShifting obj) throws Exception {
		return utilityShiftingDao.saveUSDataUploadFile(obj);
	}	
	
}
