package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.SafetyEquipmentDao;
import com.synergizglobal.wrpmis.Iservice.SafetyEquipmentService;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.SafetyEquipment;
import com.synergizglobal.wrpmis.model.Work;

@Service
public class SafetyEquipmentServiceImpl implements SafetyEquipmentService {
	
	@Autowired
	SafetyEquipmentDao safetyEquipmentDao;

	@Override
	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.getSafetyEquipment(obj);
	}
	@Override
	public SafetyEquipment getSafetyEquipmentDetails(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.getSafetyEquipmentDetails(obj);
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
	public List<SafetyEquipment> getProjectsListForSafetyEquipmentForm(SafetyEquipment obj)throws Exception{
		return safetyEquipmentDao.getProjectsListForSafetyEquipmentForm(obj);

	}
	@Override
	public List<SafetyEquipment> contractListFilterInSafetyEquipment() throws Exception {
		return safetyEquipmentDao.contractListFilterInSafetyEquipment();
	}
	@Override
	public List<SafetyEquipment> getSafetyEquipmentExportList(SafetyEquipment obj) throws Exception {
		return safetyEquipmentDao.getSafetyEquipmentExportList(obj);
	}
	@Override
	public List<SafetyEquipment> getWorkListForSafetyEquipmentForm(SafetyEquipment obj) throws Exception {
		return safetyEquipmentDao.getWorkListForSafetyEquipmentForm(obj);
	}
	@Override
	public List<SafetyEquipment> getContractsListForSafetyEquipmentForm(SafetyEquipment obj) throws Exception {
		return safetyEquipmentDao.getContractsListForSafetyEquipmentForm(obj);
	}

}
