package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

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
	public List<Issue> getPendingIssues(Issue obj) throws Exception {
		return issueDao.getPendingIssues(obj);
	}

}
