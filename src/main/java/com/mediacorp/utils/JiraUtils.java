package com.mediacorp.utils;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraUtils {
	
	public JiraClient jira;
	
	public String project;
	
	public JiraUtils(String jiraUrl, String userName,String password, String projectName) {
		BasicCredentials credentials = new BasicCredentials(userName, password);
		jira = new JiraClient(jiraUrl,credentials);
		this.project=projectName;
	}
	
	public void CreateJiraTicket(String issueType, String summary, String description, String reporterName ) throws JiraException {
		
			FluentCreate fluentCreate = jira.createIssue(project, issueType);
			fluentCreate.field(Field.SUMMARY, summary);
			fluentCreate.field(Field.DESCRIPTION, description);
			Issue newIssue = fluentCreate.execute();
			System.out.println("New Issue Created in JIRA with ID:- "+newIssue);
		
	}

}
