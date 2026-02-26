package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.StructureStatusReportDao;
import com.synergizglobal.wrpmis.Iservice.StructureStatusReportService;
import com.synergizglobal.wrpmis.model.ActivitiesProgressReport;
@Service
public class StructureStatusReportServiceImpl implements StructureStatusReportService{

	@Autowired
	StructureStatusReportDao dao;

	@Override
	public ActivitiesProgressReport getActivitiesStatusReportData(
			ActivitiesProgressReport obj) throws Exception {
		return dao.getActivitiesStatusReportData(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getStructuresList(ActivitiesProgressReport obj) throws Exception {
		return dao.getStructuresList(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getProjectsFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getWorksFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getContractsFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getFobFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsListInActivities() throws Exception {
		return dao.getContractsListInActivities();
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
	public List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractorsFilterListInActivitiesReport(obj);
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
	public List<ActivitiesProgressReport> getAllContractDetails(ActivitiesProgressReport obj) throws Exception {
		return dao.getAllContractDetails(obj);
	}		
	
	@Override
	public List<ActivitiesProgressReport> getStructureRemarks(ActivitiesProgressReport obj) throws Exception {
		return dao.getStructureRemarks(obj);
	}	
	
}
