package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.IssueDao;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.User;

@Service
public class IssueServiceImpl implements IssueService {
	@Autowired
	IssueDao issueDao;
	
	@Override
	public List<Issue> getIssuesList(Issue obj) throws Exception {
		return issueDao.getIssuesList(obj);
	}

	@Override
	public List<Issue> getIssuesStatusList() throws Exception {
		return issueDao.getIssuesStatusList();
	}

	@Override
	public List<Issue> getIssuesPriorityList() throws Exception {
		return issueDao.getIssuesPriorityList();
	}

	@Override
	public List<Issue> getIssuesCategoryList(Issue obj) throws Exception {
		return issueDao.getIssuesCategoryList(obj);
	}

	@Override
	public boolean addIssue(Issue obj) throws Exception {
		return issueDao.addIssue(obj);
	}

	@Override
	public Issue getIssue(Issue obj) throws Exception {
		return issueDao.getIssue(obj);
	}

	@Override
	public boolean updateIssue(Issue obj) throws Exception {
		return issueDao.updateIssue(obj);
	}

	@Override
	public boolean deleteIssue(Issue obj) throws Exception {
		return issueDao.deleteIssue(obj);
	}

	@Override
	public List<Issue> getDepartmentList() throws Exception {
		return issueDao.getDepartmentList();
	}

	@Override
	public List<Issue> getRailwayList() throws Exception {
		return issueDao.getRailwayList();
	}
	
	@Override
	public List<Issue> getContractsListFilter(Issue obj) throws Exception {
		return issueDao.getContractsListFilter(obj);
	}

	@Override
	public List<Issue> getDepartmentsListFilter(Issue obj) throws Exception {
		return issueDao.getDepartmentsListFilter(obj);
	}

	@Override
	public List<Issue> getCategoryListFilter(Issue obj) throws Exception {
		return issueDao.getCategoryListFilter(obj);
	}

	@Override
	public List<Issue> getStatusListFilter(Issue obj) throws Exception {
		return issueDao.getStatusListFilter(obj);
	}

	@Override
	public List<Issue> getWorksListFilter(Issue obj) throws Exception {
		return issueDao.getWorksListFilter(obj);
	}

	@Override
	public List<Issue> getResponsiblePersonsListFilter(Issue obj) throws Exception {
		return issueDao.getResponsiblePersonsListFilter(obj);
	}

	@Override
	public List<Issue> getProjectsListForIssueForm(Issue obj) throws Exception {
		return issueDao.getProjectsListForIssueForm(obj);
	}

	@Override
	public List<Issue> getWorkListForIssueForm(Issue obj) throws Exception {
		return issueDao.getWorkListForIssueForm(obj);
	}

	@Override
	public List<Issue> getContractsListForIssueForm(Issue obj) throws Exception {
		return issueDao.getContractsListForIssueForm(obj);
	}

	@Override
	public List<Issue> getHODListFilterInIssue(Issue obj) throws Exception {
		return issueDao.getHODListFilterInIssue(obj);
	}

	@Override
	public List<Issue> getReportedByList() throws Exception {
		return issueDao.getReportedByList();
	}

	@Override
	public List<Issue> getResponsiblePersonList(Issue obj) throws Exception {
		return issueDao.getResponsiblePersonList(obj);
	}

	@Override
	public List<Issue> getEscalatedToList() throws Exception {
		return issueDao.getEscalatedToList();
	}

	@Override
	public boolean readIssueMessage(String message_id) throws Exception {
		return issueDao.readIssueMessage(message_id);
	}

	@Override
	public List<Issue> getIssueTitlesList(Issue obj) throws Exception {
		return issueDao.getIssueTitlesList(obj);
	}

	@Override
	public List<Issue> getOtherOrganizationsList() throws Exception {
		return issueDao.getOtherOrganizationsList();
	}

	@Override
	public List<Issue> getIssueFileTypes() throws Exception {
		return issueDao.getIssueFileTypes();
	}
	@Override
	public List<Issue> getPendingIssues(Issue obj) throws Exception {
		return issueDao.getPendingIssues(obj);
	}

	@Override
	public List<Issue> getStructures(Issue obj) throws Exception {
		return issueDao.getStructures(obj);
	}

	@Override
	public List<Issue> getComponents(Issue obj) throws Exception {
		return issueDao.getComponents(obj);
	}	
}
