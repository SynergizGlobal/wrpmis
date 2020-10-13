package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;

public interface IssueDao {

	List<Issue> getIssuesList(Issue obj) throws Exception;

	List<Issue> getIssuesStatusList() throws Exception;

	List<Issue> getIssuesPriorityList() throws Exception;

	List<Issue> getIssuesCategoryList() throws Exception;

	boolean addIssue(Issue obj) throws Exception;

	Issue getIssue(Issue obj) throws Exception;

	boolean updateIssue(Issue obj) throws Exception;

	boolean deleteIssue(Issue obj) throws Exception;

	List<Issue> getDepartmentList() throws Exception;
	
	List<Issue> getContractsListFromIssue() throws Exception;

	List<Issue> getDepartmentsListFromIssue() throws Exception;

	List<Issue> getCategoryListFromIssue() throws Exception;

	List<Issue> getStatusListFromIssue() throws Exception;

	List<Issue> getRailwayList() throws Exception;

}
