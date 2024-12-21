package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.IssuesReportDao;
import com.synergizglobal.pmis.Iservice.IssuesReportService;
import com.synergizglobal.pmis.model.Issue;

@Service
public class IssuesReportServiceImpl implements IssuesReportService{

	@Autowired
	IssuesReportDao issueDao;

	@Override
	public List<Issue> getWorksListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getWorksListInIssuesReport(obj);
	}

	@Override
	public List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getContractsListInIssuesReport(obj);
	}

	@Override
	public List<Issue> getHODListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getHODListInIssuesReport(obj);
	}

	@Override
	public Map<String,Map<String,List<Issue>>> getPendingIssues(Issue obj) throws Exception {
		return issueDao.getPendingIssues(obj);
	}

	@Override
	public String getEmailIdsOfHodDyHodManagement() throws Exception {
		return issueDao.getEmailIdsOfHodDyHodManagement();
	}

	@Override
	public List<Issue> getIssuesSummaryData(Issue obj) throws Exception {
		return issueDao.getIssuesSummaryData(obj);
	}
	
	@Override
	public List<Issue> IssuesSummaryData(Issue obj) throws Exception {
		return issueDao.IssuesSummaryData(obj);
	}	

	@Override
	public List<Issue> getStatusListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getStatusListInIssuesReport(obj);
	}

	@Override
	public List<Issue> getTitlesListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getTitlesListInIssuesReport(obj);
	}

	@Override
	public List<Issue> getLocationsListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getLocationsListInIssuesReport(obj);
	}

	@Override
	public List<Issue> getCategoriesListInIssuesReport(Issue obj) throws Exception {
		return issueDao.getCategoriesListInIssuesReport(obj);
	}

	@Override
	public boolean processAndSendReminder(Issue unresolvedIssue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Issue> getUnresolvedIssues() {
		return issueDao.getUnresolvedIssues();
	}

}
