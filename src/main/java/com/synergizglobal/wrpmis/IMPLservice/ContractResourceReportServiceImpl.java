package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ContractResourceReportDao;
import com.synergizglobal.wrpmis.Iservice.ContractResourceReportService;
import com.synergizglobal.wrpmis.model.ContractResource;

@Service
public class ContractResourceReportServiceImpl implements ContractResourceReportService{

	@Autowired
	ContractResourceReportDao dao;

	@Override
	public List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getProjectsListForContractResourceForm(obj);
	}

	@Override
	public List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getWorkListForContractResourceForm(obj);
	}

	@Override
	public List<ContractResource> getHODsListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getHODsListForContractResourceForm(obj);
	}

	@Override
	public List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception {
		return dao.getContractsListForContractResourceForm(obj);
	}

	@Override
	public ContractResource getContarctResourceReportData(ContractResource obj) throws Exception {
		return dao.getContarctResourceReportData(obj);
	}
}
