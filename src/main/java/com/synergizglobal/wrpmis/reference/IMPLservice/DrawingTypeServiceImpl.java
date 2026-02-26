package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DrawingTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.DrawingTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class DrawingTypeServiceImpl implements DrawingTypeService{

	@Autowired
	DrawingTypeDao dao;

	@Override
	public List<TrainingType> getDrawingTypesList() throws Exception {
		return dao.getDrawingTypesList();
	}

	@Override
	public boolean addDrawingType(TrainingType obj) throws Exception {
		return dao.addDrawingType(obj);
	}

	@Override
	public TrainingType getDrawingTypeDetails(TrainingType obj) throws Exception {
		return dao.getDrawingTypeDetails(obj);
	}

	@Override
	public boolean updateDrawingType(TrainingType obj) throws Exception {
		return dao.updateDrawingType(obj);
	}

	@Override
	public boolean deleteDrawingType(TrainingType obj) throws Exception {
		return dao.deleteDrawingType(obj);
	}

}
