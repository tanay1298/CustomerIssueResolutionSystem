package strategies;

import models.Agent;
import models.Issue;

import java.util.List;

public class FirstAvailableStrategy implements AssignmentStrategy {
    @Override
    public Agent assignIssue(Issue issue, List<Agent> agents) {
        for (Agent agent : agents) {
            if (agent.getCurrentIssue() == null && agent.getExpertise().contains(issue.getIssueType())) {
                return agent;
            }
        }
        return null;
    }
}
