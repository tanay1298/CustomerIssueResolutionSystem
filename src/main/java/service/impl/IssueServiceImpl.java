package service.impl;

import enums.IssueStatus;
import enums.IssueType;
import exception.IssueNotFoundException;
import models.Issue;
import service.IssueService;

import java.util.*;
import java.util.stream.Collectors;

public class IssueServiceImpl implements IssueService {
    private Map<String, Issue> issues = new HashMap<>();
    private int                issueCounter = 1;

    @Override
    public Issue createIssue(
            String transactionId,
            IssueType issueType,
            String subject,
            String description,
            String email) {
        String issueId = "I" + issueCounter++;
        Issue issue = new Issue(issueId, transactionId, issueType, subject, description, email);
        issues.put(issueId, issue);
        System.out.println("Issue " + issueId + " created against transaction \"" + transactionId + "\"");
        return issue;
    }

    @Override
    public void updateIssue(String issueId, IssueStatus status, String resolution) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.setStatus(status);
            issue.setResolution(resolution);
            System.out.println(issueId + " status updated to " + status);
        }
        else {
            throw new IssueNotFoundException(issueId);
        }
    }

    @Override
    public void resolveIssue(String issueId, String resolution) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.setStatus(IssueStatus.RESOLVED);
            issue.setResolution(resolution);
            System.out.println(issueId + " issue marked resolved");
        }
        else {
            throw new IssueNotFoundException(issueId);
        }
    }

    @Override
    public List<Issue> getIssues(Map<String, String> filter) {
        return issues.values().stream().filter(issue -> filter.keySet().stream().allMatch(key -> {
            switch (key) {
                case "email":
                    return issue.getEmail().equals(filter.get(key));
                case "type":
                    return issue.getIssueType().name().equals(filter.get(key));
                default:
                    return false;
            }
        })).collect(Collectors.toList());
    }

    @Override
    public Issue getIssueById(String issueId) {
        Issue issue = issues.get(issueId);
        if (issue == null) {
            throw new IssueNotFoundException(issueId);
        }
        return issue;
    }
}
