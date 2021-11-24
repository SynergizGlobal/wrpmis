package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesProgressReportDao;
import com.synergizglobal.pmis.Iservice.ActivitiesProgressReportService;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;

@Service
public class ActivitiesProgressReportServiceImpl implements ActivitiesProgressReportService{
	@Autowired
	ActivitiesProgressReportDao dao;
	
	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getProjectsFilterListInActivitiesReport(obj);
	}
	
	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getWorksFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractsFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getFobFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractorsFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getHodFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getDyhodFilterListInActivitiesReport(obj);
	}

	@Override
	public Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getActivitiesReportData(obj);
	}
	
	@Override
	public String getActivitiesRemarks(String structure,String from_date) throws Exception {
		return dao.getActivitiesRemarks(structure,from_date);
	}

	@Override
	public String getReportforthePeriodActivitiesRemarks(String structure,String from_date,String to_date) throws Exception {
		return dao.getReportforthePeriodActivitiesRemarks(structure,from_date,to_date);
	}
	
	@Override
	public String getContractorName(String contract_id) throws Exception {
		return dao.getContractorName(contract_id);
	}	
	
	@Override
	public String getWorkName(String work_id) throws Exception {
		return dao.getWorkName(work_id);
	}
	@Override
	public String getContractName(String contract_id) throws Exception {
		return dao.getContractName(contract_id);
	}

	@Override
	public List<ActivitiesProgressReport> getContarctDetaisl(ActivitiesProgressReport obj) throws Exception {
		return dao.getContarctDetaisl(obj);
	}	
	
	@Override
	public List<ActivitiesProgressReport> getStructureRemarks(ActivitiesProgressReport obj) throws Exception {
		return dao.getStructureRemarks(obj);
	}	
}
