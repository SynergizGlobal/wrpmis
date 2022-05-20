package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.LandReportDao;
import com.synergizglobal.pmis.Iservice.LandReportService;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
@Service
public class LandReportServiceImpl implements LandReportService{

	@Autowired
	LandReportDao dao;

	@Override
	public List<LandAcquisition> getProjectsFilterListInLandReport(LandAcquisition obj) throws Exception {
		return dao.getProjectsFilterListInLandReport(obj);
	}

	@Override
	public List<LandAcquisition> getTypeOfLandListInLandReport(LandAcquisition obj) throws Exception {
		return dao.getTypeOfLandListInLandReport(obj);
	}

	@Override
	public List<LandAcquisition> getSubCategoryOfLandFilterListInLandReport(LandAcquisition obj) throws Exception {
		return dao.getSubCategoryOfLandFilterListInLandReport(obj);
	}

	@Override
	public List<LandAcquisition> getWorksFilterListInLandReport(LandAcquisition obj) throws Exception {
		return dao.getWorksFilterListInLandReport(obj);
	}

	@Override
	public LandAcquisition getLandAcquisitionData(LandAcquisition obj) throws Exception {
		return dao.getLandAcquisitionData(obj);
	}
}
