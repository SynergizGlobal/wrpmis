package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.SectionDao;
import com.synergizglobal.wrpmis.reference.Iservice.SectionService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class SectionServiceImpl implements SectionService{

	@Autowired
	SectionDao dao;

	@Override
	public List<TrainingType> getSectionsList() throws Exception {
		return dao.getSectionsList();
	}

	@Override
	public boolean addSection(TrainingType obj) throws Exception {
		return dao.addSection(obj);
	}

	@Override
	public TrainingType getSectionDetails(TrainingType obj) throws Exception {
		return dao.getSectionDetails(obj);
	}

	@Override
	public boolean updateSection(TrainingType obj) throws Exception {
		return dao.updateSection(obj);
	}

	@Override
	public boolean deleteSection(TrainingType obj) throws Exception {
		return dao.deleteSection(obj);
	}
}
