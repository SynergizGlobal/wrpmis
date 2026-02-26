package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ContractTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.ContractTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class ContractTypeServiceImpl implements ContractTypeService {

	@Autowired
	ContractTypeDao dao;

	@Override
	public List<TrainingType> getContractTypesList() throws Exception {
		return dao.getContractTypesList();
	}

	@Override
	public boolean addContractType(TrainingType obj) throws Exception {
		return dao.addContractType(obj);
	}

	@Override
	public TrainingType getContractTypeDetails(TrainingType obj) throws Exception {
		return dao.getContractTypeDetails(obj);
	}

	@Override
	public boolean updateContractType(TrainingType obj) throws Exception {
		return dao.updateContractType(obj);
	}

	@Override
	public boolean deleteContractType(TrainingType obj) throws Exception {
		return dao.deleteContractType(obj);
	}
}
