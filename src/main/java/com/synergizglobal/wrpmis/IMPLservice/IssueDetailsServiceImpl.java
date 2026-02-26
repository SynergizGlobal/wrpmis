package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.AlertsDao;
import com.synergizglobal.wrpmis.Idao.IssueDetailsDao;
import com.synergizglobal.wrpmis.Iservice.IssueDetailsService;
import com.synergizglobal.wrpmis.model.Issue;
@Service
public class IssueDetailsServiceImpl implements IssueDetailsService{
	@Autowired
	IssueDetailsDao dao;

	@Override
	public Issue getIssue(Issue obj) throws Exception {
		return dao.getIssue( obj);
	}

	@Override
	public List<Issue> getIssueHistory(Issue obj) throws Exception {
		return dao.getIssueHistory( obj);
	}
}
