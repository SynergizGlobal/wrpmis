package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;

public interface IssuesReportDao {

	List<Issue> getWorksListInIssuesReport(Issue obj) throws Exception;

	List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception;

	List<Issue> getPendingIssues(Issue obj) throws Exception;

}
