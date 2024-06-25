package service;

import enums.IssueType;
import models.Agent;
import models.Issue;

import java.util.List;
import java.util.Map;

public interface AgentService {
    Agent addAgent(String email, String name, List<IssueType> expertise);

    void assignIssue(String issueId);

    Map<String, List<Issue>> viewAgentsWorkHistory();
}
