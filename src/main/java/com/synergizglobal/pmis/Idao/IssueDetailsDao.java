package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;

public interface IssueDetailsDao {

	public Issue getIssue(Issue obj) throws Exception;

	public List<Issue> getIssueHistory(Issue obj) throws Exception;

}
