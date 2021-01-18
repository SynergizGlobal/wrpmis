package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.LandAcquisitionDao;
import com.synergizglobal.pmis.Iservice.LandAcquisitionService;
import com.synergizglobal.pmis.model.LandAcquisition;

@Service
public class LandAcquisitionServiceImpl implements LandAcquisitionService{

	@Autowired
	LandAcquisitionDao dao;

	@Override
	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionList(obj);
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
}
