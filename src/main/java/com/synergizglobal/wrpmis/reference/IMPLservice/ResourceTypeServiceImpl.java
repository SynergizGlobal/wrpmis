package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ResourceTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.ResourceTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService{

	@Autowired
	ResourceTypeDao dao;

	@Override
	public TrainingType getResourceTypeDetails(TrainingType obj) throws Exception {
		return dao.getResourceTypeDetails(obj);
	}

	@Override
	public boolean addResourceType(TrainingType obj) throws Exception {
		return dao.addResourceType(obj);
	}

	@Override
	public boolean updateResourceType(TrainingType obj) throws Exception {
		return dao.updateResourceType(obj);
	}

	@Override
	public boolean deleteResourceType(TrainingType obj) throws Exception {
		return dao.deleteResourceType(obj);
	}
}
