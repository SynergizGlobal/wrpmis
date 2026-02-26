package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ContractorSpecializationsDao;
import com.synergizglobal.wrpmis.reference.Iservice.ContractorSpecializationService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class ContractorSpecializationServiceImpl implements ContractorSpecializationService{


	@Autowired
	ContractorSpecializationsDao dao;

	@Override
	public List<TrainingType> getContractorSpecializationsList() throws Exception {
		return dao.getContractorSpecializationsList();
	}

	@Override
	public boolean addContractorSpecialization(TrainingType obj) throws Exception {
		return dao.addContractorSpecialization(obj);
	}

	@Override
	public TrainingType getContractorSpecializationDetails(TrainingType obj) throws Exception {
		return dao.getContractorSpecializationDetails(obj);
	}

	@Override
	public boolean updateContractorSpecialization(TrainingType obj) throws Exception {
		return dao.updateContractorSpecialization(obj);
	}

	@Override
	public boolean deleteContractorSpecialization(TrainingType obj) throws Exception {
		return dao.deleteContractorSpecialization(obj);
	}
}
