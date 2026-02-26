package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.IssueCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.IssueCategoryService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class IssueCategoryServiceImpl implements IssueCategoryService{

	@Autowired
	IssueCategoryDao dao;

	@Override
	public List<Safety> getIssueCategoryList() throws Exception {
		return dao.getIssueCategoryList();
	}

	@Override
	public boolean addIssueCategory(Safety obj) throws Exception {
		return dao.addIssueCategory(obj);
	}

	@Override
	public TrainingType getIssueCategoryDetails(TrainingType obj) throws Exception {
		return dao.getIssueCategoryDetails(obj);
	}

	@Override
	public boolean updateIssueCategory(TrainingType obj) throws Exception {
		return dao.updateIssueCategory(obj);
	}

	@Override
	public boolean deleteIssueCategory(TrainingType obj) throws Exception {
		return dao.deleteIssueCategory(obj);
	}
}
