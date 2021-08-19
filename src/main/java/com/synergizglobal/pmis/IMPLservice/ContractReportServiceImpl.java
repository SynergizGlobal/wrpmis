package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ContractReportDao;
import com.synergizglobal.pmis.Iservice.ContractReportService;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Issue;

@Service
public class ContractReportServiceImpl implements ContractReportService{
	@Autowired
	ContractReportDao dao;
	
	@Override
	public List<Contract> getHODListInContractReport(Contract obj) throws Exception {
		return dao.getHODListInContractReport(obj);
	}

	@Override
	public List<Contract> getWorksListInContractReport(Contract obj) throws Exception {
		return dao.getWorksListInContractReport(obj);
	}

	@Override
	public List<Contract> getContractorsListInContractReport(Contract obj) throws Exception {
		return dao.getContractorsListInContractReport(obj);
	}

	@Override
	public List<Contract> getContractStatusListInContractReport(Contract obj) throws Exception {
		return dao.getContractStatusListInContractReport(obj);
	}

	@Override
	public Map<String,List<Contract>> getContractsListForReport(Contract obj) throws Exception {
		return dao.getContractsListForReport(obj);
	}

	@Override
	public Map<String,List<Contract>> getContractsBankGuaranteeForReport(Contract obj) throws Exception {
		return dao.getContractsBankGuaranteeForReport(obj);
	}

	@Override
	public Map<String,List<Contract>> getContractsInsuranceForReport(Contract obj) throws Exception {
		return dao.getContractsInsuranceForReport(obj);
	}
	
	@Override
	public Map<String,List<Contract>> getContractsDocReport(Contract obj) throws Exception {
		return dao.getContractsDocReport(obj);
	}

	@Override
	public Map<String,List<Contract>> getContractsDocBGInsuranceForReport(Contract obj) throws Exception {
		return dao.getContractsDocBGInsuranceForReport(obj);
	}
	@Override
	public Map<String,List<Contract>> getContractsDocBGInsuranceForAutoEmailReport(Contract obj) throws Exception {
		return dao.getContractsDocBGInsuranceForAutoEmailReport(obj);
	}

	@Override
	public List<Contract> getContractListInContractReport(Contract obj) throws Exception {
		return dao.getContractListInContractReport(obj);
	}

	@Override
	public Contract getContractDetailsForReport(Contract obj) throws Exception {
		return dao.getContractDetailsForReport(obj);
	}

	@Override
	public Contract getProgressDetailsAsOnDate(Contract obj) throws Exception {
		return dao.getProgressDetailsAsOnDate(obj);
	}

	@Override
	public List<Contract> getMilestoneDetailsForReport(Contract obj) throws Exception {
		return dao.getMilestoneDetailsForReport(obj);
	}

	@Override
	public List<Contract> getBGDetailsForReport(Contract obj) throws Exception {
		return dao.getBGDetailsForReport(obj);
	}

	@Override
	public List<Contract> getInsuranceDetailsForReport(Contract obj) throws Exception {
		return dao.getInsuranceDetailsForReport(obj);
	}

	@Override
	public Contract getContractClosureDetails(Contract obj) throws Exception {
		return dao.getContractClosureDetails(obj);
	}

	@Override
	public Contract getContractorDetails(Contract obj) throws Exception {
		return dao.getContractorDetails(obj);
	}

	@Override
	public List<Contract> getKeyPersonnelForReport(Contract obj) throws Exception {
		return dao.getKeyPersonnelForReport(obj);
	}
	@Override
	public List<Contract> getStatusofWorkItems(Contract obj) throws Exception {
		return dao.getStatusofWorkItems(obj);
	}	
	
	@Override
	public String getEmailIdsOfDepartments(String management) throws Exception {
		return dao.getEmailIdsOfDepartments(management);
	}

}
