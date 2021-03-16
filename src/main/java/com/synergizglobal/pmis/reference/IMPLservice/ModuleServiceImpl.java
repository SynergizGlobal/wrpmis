package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.ModuleDao;
import com.synergizglobal.pmis.reference.Iservice.ModuleService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class ModuleServiceImpl implements ModuleService{

	@Autowired
	ModuleDao dao;

	@Override
	public List<Safety> getModuleList() throws Exception {
		return dao.getModuleList();
	}

	@Override
	public boolean addModule(Safety obj) throws Exception {
		return dao.addModule(obj);
	}

	@Override
	public TrainingType getModuleDetails(TrainingType obj) throws Exception {
		return dao.getModuleDetails(obj);
	}

	@Override
	public boolean updateModule(TrainingType obj) throws Exception {
		return dao.updateModule(obj);
	}

	@Override
	public boolean deleteModule(TrainingType obj) throws Exception {
		return dao.deleteModule(obj);
	}
}
