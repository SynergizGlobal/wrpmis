package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ContarctFileTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.ContarctFileTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class ContarctFileTypeServiceImpl implements ContarctFileTypeService{


	@Autowired
	ContarctFileTypeDao dao;

	@Override
	public List<TrainingType> getcontractFileType(TrainingType obj) throws Exception {
		return dao.getcontractFileType(obj);
	}

	@Override
	public boolean addContractFileType(TrainingType obj) throws Exception {
		return dao.addContractFileType(obj);
	}

	@Override
	public boolean updateContractFileType(TrainingType obj) throws Exception {
		return dao.updateContractFileType(obj);
	}

	@Override
	public boolean deleteContractFileType(TrainingType obj) throws Exception {
		return dao.deleteContractFileType(obj);
	}
}
