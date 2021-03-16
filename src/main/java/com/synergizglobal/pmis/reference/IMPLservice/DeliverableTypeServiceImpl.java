package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.DeliverableTypeDao;
import com.synergizglobal.pmis.reference.Iservice.DeliverableTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class DeliverableTypeServiceImpl implements DeliverableTypeService{

	@Autowired
	DeliverableTypeDao dao;

	@Override
	public List<TrainingType> getDeliverableTypesList() throws Exception {
		return dao.getDeliverableTypesList();
	}

	@Override
	public boolean addDeliverableType(TrainingType obj) throws Exception {
		return dao.addDeliverableType(obj);
	}

	@Override
	public TrainingType getDeliverableTypeDetails(TrainingType obj) throws Exception {
		return dao.getDeliverableTypeDetails(obj);
	}

	@Override
	public boolean updateDeliverableType(TrainingType obj) throws Exception {
		return dao.updateDeliverableType(obj);
	}

	@Override
	public boolean deleteDeliverableType(TrainingType obj) throws Exception {
		return dao.deleteDeliverableType(obj);
	}
}
