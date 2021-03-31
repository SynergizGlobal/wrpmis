package com.synergizglobal.pmis.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.Idao.IssueDetailsDao;
import com.synergizglobal.pmis.Iservice.IssueDetailsService;
import com.synergizglobal.pmis.model.Issue;
@Service
public class IssueDetailsServiceImpl implements IssueDetailsService{
	@Autowired
	IssueDetailsDao dao;

	@Override
	public Issue getIssue(Issue obj) throws Exception {
		return dao.getIssue( obj);
	}
}
