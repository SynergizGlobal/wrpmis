package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ContractResourceDao;
import com.synergizglobal.pmis.Iservice.ContractResourceService;
import com.synergizglobal.pmis.model.ContractResource;
@Service
public class ContractResourceServiceImpl implements ContractResourceService{

	@Autowired
	ContractResourceDao dao;

	@Override
	public List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getProjectsListForContractResourceForm(obj);
	}

	@Override
	public List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getWorkListForContractResourceForm(obj);
	}

	@Override
	public List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getContractsListForContractResourceForm(obj);
	}

	@Override
	public boolean addResource(ContractResource obj) throws Exception {
		return dao.addResource(obj);
	}
}
