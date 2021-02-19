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
	public Contract getContract(Contract obj)throws Exception{
		return contractDao.getContract(obj);
	}
	@Override
	public List<Contract> getContractStatusType()throws Exception{
		return contractDao.getContractStatusType();
	}
	@Override
	public boolean updateContract(Contract contract)throws Exception{
		return contractDao.updateContract(contract);

	}
	@Override
	public List<Contract> getDepartmentList()throws Exception{
		return contractDao.getDepartmentList();

	}
	@Override
	public List<Contract> getContractorsList() throws Exception {
		return contractDao.getContractorsList();
	}
	@Override
	public List<Contract> contractorsFilterList(Contract obj) throws Exception {
		return contractDao.contractorsFilterList(obj); 
	}
	@Override
	public List<Contract> departmentsFilterList(Contract obj) throws Exception {
		return contractDao.departmentsFilterList(obj);
	}
	@Override
	public List<Contract> worksFilterList(Contract obj) throws Exception {
		return contractDao.worksFilterList(obj);
	}
	@Override
	public List<Contract> getProjectsFilterList(Contract obj) throws Exception {
		return contractDao.getProjectsFilterList(obj);
	}
	@Override
	public List<Contract> getProjectsListForContractForm(Contract obj) throws Exception {
		return contractDao.getProjectsListForContractForm(obj);
	}
	@Override
	public List<Contract> getWorkListForContractForm(Contract obj) throws Exception {
		return contractDao.getWorkListForContractForm(obj);
	}
	@Override
	public List<Contract> getDesignationsFilterList(Contract obj) throws Exception {
		return contractDao.getDesignationsFilterList(obj);
	}
	@Override
	public List<Contract> getDyHODDesignationsFilterList(Contract obj) throws Exception {
		return contractDao.getDyHODDesignationsFilterList(obj);
	}
	@Override
	public List<User> setDyHodList() throws Exception {
		return contractDao.setDyHodList();
	}
	@Override
	public List<Contract> getDepartmentsList(Contract obj) throws Exception {
		return contractDao.getDepartmentsList(obj);
	}
	

}
