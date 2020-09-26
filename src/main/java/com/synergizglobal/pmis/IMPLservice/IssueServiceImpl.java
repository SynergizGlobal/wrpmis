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
	public List<Issue> getActivityList() throws Exception {
		return issueDao.getActivityList();
	}

	@Override
	public List<Issue> getDepartmentList() throws Exception {
		return issueDao.getDepartmentList();
	}

}
