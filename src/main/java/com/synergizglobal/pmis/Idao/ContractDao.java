package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.User;

public interface ContractDao {

	public List<Contract> contractList(Contract obj)throws Exception;

	public List<User> setHodList()throws Exception;

	public List<Contract> getContractorList()throws Exception;

	public List<Contract> getContractTypeList()throws Exception;

	public List<Contract> getInsurenceTypeList()throws Exception;

	public boolean addContract(Contract contract)throws Exception;

	public List<BankGuarantee> bankGuarantee()throws Exception;

	public List<Insurence> insurenceType()throws Exception;

	public Contract getcontract(String contractId, Contract obj)throws Exception;

	public List<Contract> getContractStatusType()throws Exception;

	public boolean updateContract(Contract contract)throws Exception;



}
