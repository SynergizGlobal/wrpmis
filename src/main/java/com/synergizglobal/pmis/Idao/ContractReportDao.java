package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Contract;

public interface ContractReportDao {
	List<Contract> getHODListInContractReport(Contract obj) throws Exception;

	List<Contract> getWorksListInContractReport(Contract obj) throws Exception;

	List<Contract> getContractorsListInContractReport(Contract obj) throws Exception;

	List<Contract> getContractsListForReport(Contract obj) throws Exception;

	List<Contract> getContractsBankGuaranteeForReport(Contract obj) throws Exception;

	List<Contract> getContractsInsuranceForReport(Contract obj) throws Exception;
}
