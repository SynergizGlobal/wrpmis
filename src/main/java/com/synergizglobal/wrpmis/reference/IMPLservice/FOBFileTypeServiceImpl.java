package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.FOBFileTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.FOBFileTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class FOBFileTypeServiceImpl implements FOBFileTypeService{
	
	@Autowired
	FOBFileTypeDao dao;

	@Override
	public TrainingType getfobFileType(TrainingType obj) throws Exception {
		return dao.getfobFileType(obj);
	}

	@Override
	public boolean addFOBFileType(TrainingType obj) throws Exception {
		return dao.addFOBFileType(obj);
	}

	@Override
	public boolean updateFOBFileType(TrainingType obj) throws Exception {
		return dao.updateFOBFileType(obj);
	}

	@Override
	public boolean deleteFOBFileType(TrainingType obj) throws Exception {
		return dao.deleteFOBFileType(obj);
	}
}
