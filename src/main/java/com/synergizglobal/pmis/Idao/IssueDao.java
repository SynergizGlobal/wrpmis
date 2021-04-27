package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.User;

public interface IssueDao {

	List<Issue> getIssuesList(Issue obj) throws Exception;

	List<Issue> getIssuesStatusList() throws Exception;

	List<Issue> getIssuesPriorityList() throws Exception;

	List<Issue> getIssuesCategoryList(Issue obj) throws Exception;

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

	List<Issue> getProjectsListForIssueForm(Issue obj) throws Exception;

	List<Issue> getWorkListForIssueForm(Issue obj) throws Exception;

	List<Issue> getContractsListForIssueForm(Issue obj) throws Exception;

	List<Issue> getHODListFilterInIssue(Issue obj) throws Exception;
	
	List<Issue> getReportedByList() throws Exception;

	List<Issue> getResponsiblePersonList(Issue obj) throws Exception;

	List<Issue> getEscalatedToList() throws Exception;

	boolean readIssueMessage(Issue obj) throws Exception;

	List<Issue> getIssueTitlesList(Issue obj) throws Exception;

	List<Issue> getOtherOrganizationsList() throws Exception;

	List<Issue> getIssueFileTypes() throws Exception;

}
