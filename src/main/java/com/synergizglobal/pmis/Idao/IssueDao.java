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

	List<Issue> getRailwayList() throws Exception;
	
	List<Issue> getContractsListFilter(Issue obj) throws Exception;

	List<Issue> getDepartmentsListFilter(Issue obj) throws Exception;

	List<Issue> getCategoryListFilter(Issue obj) throws Exception;

	List<Issue> getStatusListFilter(Issue obj) throws Exception;

	List<Issue> getWorksListFilter(Issue obj) throws Exception;

	List<Issue> getResponsiblePersonsListFilter(Issue obj) throws Exception;

}
