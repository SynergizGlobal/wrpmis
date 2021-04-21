package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.IssueCategoryTitleDao;
import com.synergizglobal.pmis.reference.Iservice.IssueCategoryTitleService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class IssueCategoryTitleServiceImpl implements IssueCategoryTitleService{
	
	@Autowired
	IssueCategoryTitleDao dao;

	@Override
	public List<TrainingType> gtIssueCategoryDetails(TrainingType obj) throws Exception {
		return dao.gtIssueCategoryDetails(obj);
	}

	@Override
	public List<TrainingType> getIssueCategoryTitle(TrainingType obj) throws Exception {
		return dao.getIssueCategoryTitle(obj);

	}

	@Override
	public boolean addIssueCategoryTitle(TrainingType obj) throws Exception {
		return dao.addIssueCategoryTitle(obj);

	}

	@Override
	public boolean updateIssueCategoryTitle(TrainingType obj) throws Exception {
		return dao.updateIssueCategoryTitle(obj);

	}

	@Override
	public boolean deleteIssueCategoryTitle(TrainingType obj) throws Exception {
		return dao.deleteIssueCategoryTitle(obj);

	}
}
