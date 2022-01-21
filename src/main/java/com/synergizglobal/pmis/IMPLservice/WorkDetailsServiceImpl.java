package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.WorkDetailsDao;
import com.synergizglobal.pmis.Iservice.WorkDetailsService;
import com.synergizglobal.pmis.model.WorkFeatures;

@Service
public class WorkDetailsServiceImpl implements WorkDetailsService {
	@Autowired
	WorkDetailsDao dao;

	@Override
	public List<WorkFeatures> getWorkDetailsList(WorkFeatures obj) throws Exception {
		return dao.getWorkDetailsList(obj);
	}
	
	@Override
	public List<WorkFeatures> getWorkDetails(WorkFeatures obj) throws Exception {
		return dao.getWorkDetails(obj);
	}

	@Override
	public boolean addWorkDetails(WorkFeatures obj) throws Exception {
		return dao.addWorkDetails(obj);
	}

	@Override
	public boolean updateWorkDetails(WorkFeatures obj) throws Exception {
		return dao.updateWorkDetails(obj);
	}

	@Override
	public List<WorkFeatures> getWorksListFilter(WorkFeatures obj) throws Exception {
		return dao.getWorksListFilter(obj);
	}

	@Override
	public List<WorkFeatures> getTitlesList(WorkFeatures obj) throws Exception {
		return dao.getTitlesList(obj);
	}

	@Override
	public List<WorkFeatures> getStatusList(WorkFeatures obj) throws Exception {
		return dao.getStatusList(obj);
	}

	@Override
	public List<WorkFeatures> getWorksList(WorkFeatures obj) throws Exception {
		return dao.getWorksList(obj);
	}

	@Override
	public List<WorkFeatures> getWorksListFilterForSalientFeatures(WorkFeatures obj) throws Exception {
		return dao.getWorksListFilterForSalientFeatures(obj);
	}

	@Override
	public List<WorkFeatures> getWorkSalientFeatures(WorkFeatures obj) throws Exception {
		return dao.getWorkSalientFeatures(obj);
	}

	@Override
	public List<WorkFeatures> getCategoryList(WorkFeatures obj) throws Exception {
		return dao.getCategoryList(obj);
	}

	@Override
	public boolean addWorkSalientFeatures(WorkFeatures obj) throws Exception {
		return dao.addWorkSalientFeatures(obj);
	}

	@Override
	public boolean updateWorkSalientFeatures(WorkFeatures obj) throws Exception {
		return dao.updateWorkSalientFeatures(obj);
	}
}
