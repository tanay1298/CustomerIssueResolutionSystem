package service;

import enums.IssueStatus;
import enums.IssueType;
import models.Issue;

import java.util.List;
import java.util.Map;

public interface IssueService {
    Issue createIssue(String transactionId, IssueType issueType, String subject, String description, String email);

    void updateIssue(String issueId, IssueStatus status, String resolution);

    void resolveIssue(String issueId, String resolution);

    List<Issue> getIssues(Map<String, String> filter);

    Issue getIssueById(String issueId);
}
