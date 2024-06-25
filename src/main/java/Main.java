import enums.IssueType;
import exception.AgentNotFoundException;
import exception.IssueNotFoundException;
import models.Agent;
import models.Issue;
import service.AgentService;
import service.IssueService;
import service.impl.AgentServiceImpl;
import service.impl.IssueServiceImpl;
import strategies.AssignmentStrategy;
import strategies.FirstAvailableStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AssignmentStrategy strategy = new FirstAvailableStrategy();
        IssueService issueService = new IssueServiceImpl();
        AgentService agentService =
                new AgentServiceImpl(strategy, issueService);

        try {
            // creating issues
            Issue issue1 = issueService.createIssue("T1",
                    IssueType.PAYMENT_RELATED,
                    "Payment Failed",
                    "My payment failed but money is debited",
                    "testUser1@test.com");

            Issue issue2 = issueService.createIssue("T2",
                    IssueType.MUTUAL_FUND_RELATED,
                    "Purchase Failed",
                    "Unable to purchase Mutual Fund",
                    "testUser2@test.com");

            Issue issue3 = issueService.createIssue("T3",
                    IssueType.PAYMENT_RELATED,
                    "Payment Failed",
                    "My payment failed but money is debited",
                    "testUser2@test.com");

            // adding agents
            Agent agent1 = agentService.addAgent("agent1@test.com",
                    "Agent 1",
                    Arrays.asList(IssueType.PAYMENT_RELATED, IssueType.GOLD_RELATED));
            Agent agent2 = agentService.addAgent("agent2@test.com", "Agent 2", Arrays.asList(IssueType.PAYMENT_RELATED));

            // assigning issues
            agentService.assignIssue(issue1.getId());
            agentService.assignIssue(issue2.getId());
            agentService.assignIssue(issue3.getId());

            // get issues by email
            List<Issue> issuesByEmail = issueService.getIssues(Map.of("email", "testUser2@test.com"));
            System.out.println("Issues by email 'testUser2@test.com': " + issuesByEmail);

            // get issues by type
            List<Issue> paymentIssues = issueService.getIssues(Map.of("type", IssueType.PAYMENT_RELATED.name()));
            System.out.println("Payment related issues: " + paymentIssues);

            // check agents work history
            Map<String, List<Issue>> agentsWorkHistory = agentService.viewAgentsWorkHistory();
            System.out.println("Agents work history: " + agentsWorkHistory);
        }
        catch (IssueNotFoundException | AgentNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
