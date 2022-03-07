package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;

public interface RiskService {
	

	public List<Risk> getWorksList(Risk obj) throws Exception;
	public List<Risk> getSubWorksList(Risk obj) throws Exception;

	public int[] uploadRiskAssessments(List<Risk> risksList, Risk obj) throws Exception;	

	public Risk getRiskAssessment(Risk obj) throws Exception;

	public boolean updateRiskAssessment(Risk obj) throws Exception;
	public boolean checkRiskAssessment(String subwork,String Date ) throws Exception;

	public List<Risk> getRiskAssessmentList(Risk obj) throws Exception;
	public List<Risk> getRisks(Risk obj) throws Exception;

	public List<Risk> getSubWorksFilterListInRiskAssessmnt(Risk obj) throws Exception;

	public List<Risk> getAreasFilterListInRiskAssessment(Risk obj) throws Exception;

	public List<Risk> getAssessmentDatesFilterListInRiskAssessment(Risk obj) throws Exception;

	public List<Risk> getRiskAssessmentDates(Risk obj) throws Exception;

	public List<Risk> getRiskAssessmentUploadsList(Risk obj) throws Exception;

	public List<Risk> getSubWorksListFromRiskUploads(Risk obj) throws Exception;

	public boolean saveRiskAssessmentUploadFile(Risk risk) throws Exception;

	public List<Risk> getSubWorkHodFilterListInRiskAssessmnt(Risk obj) throws Exception;
	
	public Risk getLastUpdatedRiskAssessmentFile(Risk obj) throws Exception;
	
	

}
