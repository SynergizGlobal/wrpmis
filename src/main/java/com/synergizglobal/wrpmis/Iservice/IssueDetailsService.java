package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.Issue;

public interface IssueDetailsService {

	public Issue getIssue(Issue obj) throws Exception;

	public List<Issue> getIssueHistory(Issue obj) throws Exception;

}
