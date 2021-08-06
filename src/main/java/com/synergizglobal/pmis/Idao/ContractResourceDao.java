package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.ContractResource;

public interface ContractResourceDao {

	List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception;

	List<ContractResource> getStructuresListForContractResourceForm(ContractResource obj) throws Exception;

	boolean addResource(ContractResource obj) throws Exception;

}
