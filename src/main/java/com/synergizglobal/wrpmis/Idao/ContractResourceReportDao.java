package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.ContractResource;

public interface ContractResourceReportDao {

	List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getHODsListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception;

	ContractResource getContarctResourceReportData(ContractResource obj) throws Exception;

}
