package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;

public interface IssuesReportService {
	
	List<Issue> getWorksListInIssuesReport(Issue obj) throws Exception;

	List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception;
	
	List<Issue> getHODListInIssuesReport(Issue obj) throws Exception;

	List<Issue> getPendingIssues(Issue obj) throws Exception;

	String getEmailIdsOfHodDyHodManagement() throws Exception;
}
