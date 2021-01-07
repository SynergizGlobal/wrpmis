package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.IssueDao;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.model.Issue;

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
	public List<Issue> getIssuesCategoryList() throws Exception {
		return issueDao.getIssuesCategoryList();
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
	
}
