package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SubResourceTypeDao;
import com.synergizglobal.pmis.reference.Iservice.SubResourceTypeService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class SubResourceTypeServiceImpl implements SubResourceTypeService{

	@Autowired
	SubResourceTypeDao dao;

	@Override
	public List<TrainingType> getRiskSubResourceType() throws Exception {
		return dao.getRiskSubResourceType();
	}

	@Override
	public List<TrainingType> getRiskResourceType() throws Exception {
		return dao.getRiskResourceType();
	}

	@Override
	public TrainingType getSubResourceTypeDetails(TrainingType obj) throws Exception {
		return dao.getSubResourceTypeDetails(obj);
	}

	@Override
	public boolean addsubResourceType(Risk obj) throws Exception {
		return dao.addsubResourceType(obj);
	}

	@Override
	public boolean updatesubResourceType(TrainingType obj) throws Exception {
		return dao.updatesubResourceType(obj);
	}

	@Override
	public boolean deletesubResourceType(TrainingType obj) throws Exception {
		return dao.deletesubResourceType(obj);
	}
}
