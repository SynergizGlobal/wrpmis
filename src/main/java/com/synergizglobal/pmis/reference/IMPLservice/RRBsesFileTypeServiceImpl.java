package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRBsesFileTypeDao;
import com.synergizglobal.pmis.reference.Iservice.RRBsesFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class RRBsesFileTypeServiceImpl implements RRBsesFileTypeService{

	@Autowired
	RRBsesFileTypeDao dao;
	
	@Override
	public TrainingType getRRBsesFileTypeList(TrainingType obj) throws Exception {
		return dao.getRRBsesFileTypeList(obj);
	}

	@Override
	public boolean addRRBsesFileType(TrainingType obj) throws Exception {
		return dao.addRRBsesFileType(obj);
	}

	@Override
	public boolean updateRRBsesFileType(TrainingType obj) throws Exception {
		return dao.updateRRBsesFileType(obj);
	}

	@Override
	public boolean deleteRRBsesFileType(TrainingType obj) throws Exception {
		return dao.deleteRRBsesFileType(obj);
	}
	

}
