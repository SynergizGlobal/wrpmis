package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.User;

public interface ContractDao {

	public List<Contract> contractList(Contract obj)throws Exception;

	public List<User> setHodList()throws Exception;

	public List<Contract> getContractTypeList()throws Exception;

	public List<Contract> getInsurenceTypeList()throws Exception;

	public String addContract(Contract contract)throws Exception;

	public List<BankGuarantee> bankGuarantee()throws Exception;

	public List<Insurence> insurenceType()throws Exception;

	public Contract getContract(Contract obj)throws Exception;

	public List<Contract> getContractStatusType(Contract obj)throws Exception;

	public String updateContract(Contract contract)throws Exception;

	public List<Contract> getDepartmentList()throws Exception;
	
	public List<Contract> getContractorsList()throws Exception; 

	public List<Contract> contractorsFilterList(Contract obj)throws Exception;

	public List<Contract> worksFilterList(Contract obj)throws Exception;

	public List<Contract> getProjectsFilterList(Contract obj) throws Exception;

	public List<Contract> getProjectsListForContractForm(Contract obj) throws Exception;

	public List<Contract> getWorkListForContractForm(Contract obj) throws Exception;

	public List<Contract> getDesignationsFilterList(Contract obj) throws Exception;

	public List<Contract> getDyHODDesignationsFilterList(Contract obj) throws Exception;
	
	public List<Contract> getContractStatusFilterListInContract(Contract obj) throws Exception;

	public List<Contract> getStatusFilterListInContract(Contract obj) throws Exception;

	public List<User> getDyHodList() throws Exception;

	public List<Contract> getDepartmentsList(Contract obj) throws Exception;

	public int getTotalRecords(Contract obj, String searchParameter) throws Exception;

	public List<Contract> getContractsList(Contract obj, int startIndex, int offset, String searchParameter) throws Exception;

	public List<Contract> getHodList(Contract obj) throws Exception;

	public List<Contract> getDyHodList(Contract obj) throws Exception;

	public List<Contract> getContractFileTypeList(Contract obj) throws Exception;

	public List<Contract> getResponsiblePeopleList(Contract obj) throws Exception;

	public List<Contract> getExecutivesListForContractForm(Contract obj) throws Exception;

	public List<Contract> getUnitsList(Contract obj) throws Exception;

	public List<Contract> getContractStatus() throws Exception;
	
	public List<Contract> contractRevisionsList(Contract contract) throws Exception;

	public List<Contract> contractBGList(Contract contract) throws Exception;

	public List<Contract> contractInsuranceList(Contract contract) throws Exception;

	public List<Contract> contractMilestoneList(Contract contract) throws Exception;

	public List<Contract> contractListForExport(Contract contract) throws Exception;

	public List<Contract> detailsOfContracts(Contract obj) throws Exception;

	public List<Contract> getDepartmentsFilterListInContract(Contract obj) throws Exception;

	public List<Contract> getBankNameList(Contract obj) throws Exception;
	
	public List<Contract> getDOCStatusFilterListInContract(Contract obj)throws Exception;
	public List<Contract> getDOCWorksFilterList(Contract obj)throws Exception;
	public List<Contract> getDOCDepartmentsFilterListInContract(Contract obj)throws Exception;	


}
