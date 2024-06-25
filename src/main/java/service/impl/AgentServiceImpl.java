package service.impl;

import enums.IssueStatus;
import enums.IssueType;
import exception.AgentNotFoundException;
import exception.IssueNotFoundException;
import models.Agent;
import models.Issue;
import service.AgentService;
import service.IssueService;
import strategies.AssignmentStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class AgentServiceImpl implements AgentService {
    private Map<String, Agent> agents = new HashMap<>();
    private AssignmentStrategy assignmentStrategy;
    private IssueService       issueService;

    public AgentServiceImpl(AssignmentStrategy assignmentStrategy, IssueService issueService) {
        this.assignmentStrategy = assignmentStrategy;
        this.issueService = issueService;
    }

    @Override
    public Agent addAgent(String email, String name, List<IssueType> expertise) {
        Agent agent = new Agent(email, name, expertise);
        agents.put(email, agent);
        System.out.println("Agent " + name + " created");
        return agent;
    }

    @Override
    public void assignIssue(String issueId) {
        Issue issue = issueService.getIssueById(issueId);
        if (issue == null) {
            throw new IssueNotFoundException(issueId);
        }

        List<Agent> availableAgents = agents.values().stream()
                .filter(agent -> agent.getCurrentIssue() == null && agent.getExpertise().contains(issue.getIssueType()))
                .collect(Collectors.toList());

        Agent assignedAgent = assignmentStrategy.assignIssue(issue, availableAgents);
        if (assignedAgent != null) {
            assignedAgent.setCurrentIssue(issue);
            issue.setStatus(IssueStatus.IN_PROGRESS);
            System.out.println("Issue " + issueId + " assigned to agent " + assignedAgent.getName());
        }
        else {
            throw new AgentNotFoundException();
        }
    }

    @Override
    public Map<String, List<Issue>> viewAgentsWorkHistory() {
        Map<String, List<Issue>> workHistory = new HashMap<>();
        for (Agent agent : agents.values()) {
            workHistory.put(agent.getEmail(), agent.getWorkHistory());
        }
        return workHistory;
    }
}
