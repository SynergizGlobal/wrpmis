package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.LandAcquisitionDao;
import com.synergizglobal.pmis.Iservice.LandAcquisitionService;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.LandAcquisition;

@Service
public class LandAcquisitionServiceImpl implements LandAcquisitionService{

	@Autowired
	LandAcquisitionDao dao;

	@Override
	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj, int startIndex, int offset, String searchParameter) throws Exception {
		return dao.getLandAcquisitionList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionWorksList(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionWorksList(obj);
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionVillagesList(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionVillagesList(obj);
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionTypesOfLandsList(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionTypesOfLandsList(obj);
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionSubCategoryList(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionSubCategoryList(obj);
	}

	@Override
	public List<LandAcquisition> getStatusList() throws Exception {
		return dao.getStatusList();
	}

	@Override
	public LandAcquisition getLandAcquisitionForm(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionForm(obj);
	}

	@Override
	public boolean addLandAcquisition(LandAcquisition obj) throws Exception {
		return dao.addLandAcquisition(obj);
	}

	@Override
	public List<LandAcquisition> getWorkListForLAForm(LandAcquisition obj) throws Exception {
		return dao.getWorkListForLAForm(obj);
	}

	@Override
	public List<LandAcquisition> getProjectsList(LandAcquisition obj) throws Exception {
		return dao.getProjectsList(obj);
	}

	@Override
	public List<LandAcquisition> getLandsListForLAForm(LandAcquisition obj) throws Exception {
		return dao.getLandsListForLAForm(obj);
	}

	@Override
	public List<LandAcquisition> getSubCategorysListForLAForm(LandAcquisition obj) throws Exception {
		return dao.getSubCategorysListForLAForm(obj);
	}

	@Override
	public List<LandAcquisition> getSubCategoryList(LandAcquisition obj) throws Exception {
		return dao.getSubCategoryList(obj);
	}

	@Override
	public List<LandAcquisition> getLandsList(LandAcquisition obj) throws Exception {
		return dao.getLandsList(obj);
	}

	@Override
	public List<LandAcquisition> getIssueCatogoriesList() throws Exception {
		return dao.getIssueCatogoriesList();
	}

	@Override
	public boolean updateLandAcquisition(LandAcquisition obj) throws Exception {
		return dao.updateLandAcquisition(obj);
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionProjectsList(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionProjectsList(obj);
	}

	@Override
	public int getTotalRecords(LandAcquisition obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<LandAcquisition> getUnitsList() throws Exception {
		return dao.getUnitsList();
	}


}
