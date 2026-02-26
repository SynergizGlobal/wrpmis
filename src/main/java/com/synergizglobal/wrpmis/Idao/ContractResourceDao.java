package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.ContractResource;

public interface ContractResourceDao {

	List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception;

	boolean addResource(ContractResource obj) throws Exception;

	List<ContractResource> getResourceTypeListForContractResourceForm(ContractResource obj) throws Exception;

	ContractResource getContarctResourceReportData(ContractResource obj) throws Exception;

	List<ContractResource> getSubResourceTypeListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getUnitsListForContractResourceForm() throws Exception;

}
