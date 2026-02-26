package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.PMISProgressDao;
import com.synergizglobal.wrpmis.Iservice.PMISProgressService;
import com.synergizglobal.wrpmis.model.Document;
import com.synergizglobal.wrpmis.model.StripChart;

@Service
public class PMISProgressServiceImpl implements PMISProgressService{

	@Autowired
	PMISProgressDao dao;

	@Override
	public List<StripChart> getMileStoneFilterList(StripChart obj) throws Exception {
		return dao.getMileStoneFilterList(obj);
	}

	@Override
	public List<StripChart> getMileStonesFilterList(StripChart obj) throws Exception {
		return dao.getMileStonesFilterList(obj);
	}

	@Override
	public boolean updateProgressForm(StripChart obj) throws Exception {
		return dao.updateProgressForm(obj);
	}


	@Override
	public List<StripChart> getContractsFilterList(StripChart obj) throws Exception {
		return dao.getContractsFilterList(obj);
	}

	@Override
	public List<StripChart> getProjectsFilterList(StripChart obj) throws Exception {
		return dao.getProjectsFilterList(obj);
	}

	@Override
	public List<StripChart> getWorksFilterList(StripChart obj) throws Exception {
		return dao.getWorksFilterList(obj);
	}
}
