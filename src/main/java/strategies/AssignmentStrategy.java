package strategies;

import models.Agent;
import models.Issue;

import java.util.List;

public interface AssignmentStrategy {
    Agent assignIssue(Issue issue, List<Agent> agents);
}
