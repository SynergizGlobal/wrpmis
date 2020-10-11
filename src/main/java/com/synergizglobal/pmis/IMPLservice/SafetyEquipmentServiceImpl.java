package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SafetyEquipmentDao;
import com.synergizglobal.pmis.Iservice.SafetyEquipmentService;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Work;

@Service
public class SafetyEquipmentServiceImpl implements SafetyEquipmentService {
	
	@Autowired
	SafetyEquipmentDao safetyEquipmentDao;

	@Override
	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.getSafetyEquipment(obj);
	}
	@Override
	public SafetyEquipment getSafetyDetails(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.getSafetyDetails(obj);
	}
	@Override
	public boolean addSafetyEquipment(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.addSafetyEquipment(obj);
	}
	
	@Override
	public boolean updateSafetyEquipment(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.updateSafetyEquipment(obj);
	}
	
	@Override
	public boolean deleteSafetyEquipment(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.deleteSafetyEquipment(obj);

	}
	@Override
	public List<Work> getworkList()throws Exception{
		return safetyEquipmentDao.getworkList();

	}
	@Override
	public List<Project> getProjectsList()throws Exception{
		return safetyEquipmentDao.getProjectsList();

	}

}
