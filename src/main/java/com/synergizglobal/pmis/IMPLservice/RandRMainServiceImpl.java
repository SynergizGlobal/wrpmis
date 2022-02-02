package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DashboardsAccessDao;
import com.synergizglobal.pmis.Idao.RandRMainDao;
import com.synergizglobal.pmis.Iservice.RandRMainService;
import com.synergizglobal.pmis.model.RandRMain;

@Service
public class RandRMainServiceImpl implements RandRMainService{
	@Autowired
	RandRMainDao dao;

	@Override
	public List<RandRMain> getWorksFilterListInRR(RandRMain obj) throws Exception {
		return dao.getWorksFilterListInRR(obj);
	}

	@Override
	public List<RandRMain> getStatusFilterListInRR(RandRMain obj) throws Exception {
		return dao.getStatusFilterListInRR(obj);
	}

	@Override
	public List<RandRMain> getLocationsFilterListInRR(RandRMain obj) throws Exception {
		return dao.getLocationsFilterListInRR(obj);
	}

	@Override
	public List<RandRMain> getTypeofUseFilterListInRR(RandRMain obj) throws Exception {
		return dao.getTypeofUseFilterListInRR(obj);
	}

	@Override
	public List<RandRMain> getStructuresFilterListInRR(RandRMain obj) throws Exception {
		return dao.getStructuresFilterListInRR(obj);
	}

	@Override
	public List<RandRMain> getPhasesFilterListInRR(RandRMain obj) throws Exception {
		return dao.getPhasesFilterListInRR(obj);
	}

	@Override
	public int getTotalRecords(RandRMain obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<RandRMain> getRRList(RandRMain obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return dao.getRRList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<RandRMain> getProjectsListForRRForm(RandRMain obj) throws Exception {
		return dao.getProjectsListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getWorkListForRRForm(RandRMain obj) throws Exception {
		return dao.getWorkListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getDocTypeListForRRForm(RandRMain obj) throws Exception {
		return dao.getDocTypeListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getPhaseListForRRForm(RandRMain obj) throws Exception {
		return dao.getPhaseListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getStructureListForRRForm(RandRMain obj) throws Exception {
		return dao.getStructureListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getLocationListForRRForm(RandRMain obj) throws Exception {
		return dao.getLocationListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getSubLocationListForRRForm(RandRMain obj) throws Exception {
		return dao.getSubLocationListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getTypeofUseListForRRForm(RandRMain obj) throws Exception {
		return dao.getTypeofUseListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getVerificationByListForRRForm(RandRMain obj) throws Exception {
		return dao.getVerificationByListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getUnitsListForRRForm(RandRMain obj) throws Exception {
		return dao.getUnitsListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getStatusListForRRForm(RandRMain obj) throws Exception {
		return dao.getStatusListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getOccupancyStatusListForRRForm(RandRMain obj) throws Exception {
		return dao.getOccupancyStatusListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getGenderListForRRForm(RandRMain obj) throws Exception {
		return dao.getGenderListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getTenureStatusListForRRForm(RandRMain obj) throws Exception {
		return dao.getTenureStatusListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getCasteListForRRForm(RandRMain obj) throws Exception {
		return dao.getCasteListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getMotherTongueListForRRForm(RandRMain obj) throws Exception {
		return dao.getMotherTongueListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getTypeofFamilyListForRRForm(RandRMain obj) throws Exception {
		return dao.getTypeofFamilyListForRRForm(obj);
	}

	@Override
	public List<RandRMain> getMaritualStatusListForRRForm(RandRMain obj) throws Exception {
		return dao.getMaritualStatusListForRRForm(obj);
	}

	@Override
	public RandRMain getRandRMainForm(RandRMain rr) throws Exception {
		return dao.getRandRMainForm(rr);
	}

	@Override
	public boolean addRR(RandRMain obj) throws Exception {
		return dao.addRR(obj);
	}

	@Override
	public boolean updateRR(RandRMain obj) throws Exception {
		return dao.updateRR(obj);
	}

	@Override
	public List<RandRMain> getRRIdListForRRForm(RandRMain obj) throws Exception {
		return dao.getRRIdListForRRForm(obj);
	}

}
