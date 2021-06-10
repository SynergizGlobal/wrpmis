package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.Issue;

public interface IssuesReportDao {

	List<Issue> getWorksListInIssuesReport(Issue obj) throws Exception;

	List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception;
	
	List<Issue> getHODListInIssuesReport(Issue obj) throws Exception;

	Map<String,Map<String,List<Issue>>> getPendingIssues(Issue obj) throws Exception;

	String getEmailIdsOfHodDyHodManagement() throws Exception;

	List<Issue> getIssuesSummaryData(Issue obj) throws Exception;

}
