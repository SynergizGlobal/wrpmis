package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.reference.Idao.LaLandStatusDao;
import com.synergizglobal.pmis.reference.Iservice.LaLandStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class LaLandStatusServiceImpl implements LaLandStatusService{

	@Autowired
	LaLandStatusDao dao;

	@Override
	public TrainingType getLandAcquisitionStatusDetails(TrainingType obj) throws Exception {
		return dao.getLandAcquisitionStatusDetails(obj);
	}

	@Override
	public boolean updatelandAcquisitionStatus(TrainingType obj) throws Exception {
		return dao.updatelandAcquisitionStatus(obj);
	}

	@Override
	public boolean deletelandAcquisitionStatus(TrainingType obj) throws Exception {
		return dao.deletelandAcquisitionStatus(obj);
	}

	@Override
	public boolean addLaStatus(com.synergizglobal.pmis.reference.model.Safety obj) throws Exception {
		return dao.addLaStatus(obj);
	}
}
