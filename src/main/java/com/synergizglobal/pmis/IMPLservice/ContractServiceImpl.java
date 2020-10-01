package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ContractDao;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.User;

@Service
public class ContractServiceImpl implements ContractService{
	
	@Autowired
	ContractDao contractDao;
	
	@Override
	public List<Contract> contractList(Contract obj)throws Exception{
		return contractDao.contractList(obj);
	}
	@Override
	public List<User> setHodList()throws Exception{
		return contractDao.setHodList();
	}
	@Override
	public List<Contract> getContractorList()throws Exception{
		return contractDao.getContractorList();
	}
	
	@Override
	public List<Contract> getContractTypeList()throws Exception{
		return contractDao.getContractTypeList();
	}
	@Override
	public List<Contract> getInsurenceTypeList()throws Exception{
		return contractDao.getInsurenceTypeList();
	}
	@Override
	public boolean addContract(Contract contract)throws Exception{
		return contractDao.addContract(contract);
	}
	@Override
	public List<BankGuarantee> bankGuarantee()throws Exception{
		return contractDao.bankGuarantee();
	}
	@Override
	public List<Insurence> insurenceType()throws Exception{
		return contractDao.insurenceType();
	}
	@Override
	public Contract getcontract(String contractId, Contract obj)throws Exception{
		return contractDao.getcontract(contractId,obj);
	}

}
