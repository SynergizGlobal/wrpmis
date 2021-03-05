package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ContractReportDao;
import com.synergizglobal.pmis.Iservice.ContractReportService;
import com.synergizglobal.pmis.model.Contract;

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
	public List<Contract> getContractsBankGuaranteeForReport(Contract obj) throws Exception {
		return dao.getContractsBankGuaranteeForReport(obj);
	}

	@Override
	public List<Contract> getContractsInsuranceForReport(Contract obj) throws Exception {
		return dao.getContractsInsuranceForReport(obj);
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

}
