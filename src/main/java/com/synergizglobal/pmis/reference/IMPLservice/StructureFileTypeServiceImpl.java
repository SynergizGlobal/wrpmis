package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.StructureFileTypeDao;
import com.synergizglobal.pmis.reference.Iservice.StructureFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class StructureFileTypeServiceImpl implements StructureFileTypeService{
	
	@Autowired
	StructureFileTypeDao dao;

	@Override
	public List<TrainingType> getStructureFileType(TrainingType obj) throws Exception {
		return dao.getStructureFileType(obj);
	}

	@Override
	public boolean addStructureFileType(TrainingType obj) throws Exception {
		return dao.addStructureFileType(obj);
	}

	@Override
	public boolean updateStructureFileType(TrainingType obj) throws Exception {
		return dao.updateStructureFileType(obj);
	}

	@Override
	public boolean deleteStructureFileType(TrainingType obj) throws Exception {
		return dao.deleteStructureFileType(obj);
	}
}
