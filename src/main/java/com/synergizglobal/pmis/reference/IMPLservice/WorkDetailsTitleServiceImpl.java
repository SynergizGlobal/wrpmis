package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.model.WorkFeatures;
import com.synergizglobal.pmis.reference.Idao.WorkDetailsTitleDao;
import com.synergizglobal.pmis.reference.Iservice.WorkDetailsTitleService;

@Service
public class WorkDetailsTitleServiceImpl implements WorkDetailsTitleService{
	@Autowired
	WorkDetailsTitleDao dao;

	@Override
	public WorkFeatures getTitleDetails(WorkFeatures obj) throws Exception {
		return dao.getTitleDetails(obj);
	}

	@Override
	public boolean addTitle(WorkFeatures obj) throws Exception {
		return dao.addTitle(obj);
	}

	@Override
	public boolean updateTitle(WorkFeatures obj) throws Exception {
		return dao.updateTitle(obj);
	}

	@Override
	public boolean deleteTitle(WorkFeatures obj) throws Exception {
		return dao.deleteTitle(obj);
	}
}
