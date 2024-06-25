package models;

import enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    private String          email;
    private String          name;
    private List<IssueType> expertise;
    private Issue           currentIssue;
    private List<Issue>     workHistory = new ArrayList<>();

    public Agent(String email, String name, List<IssueType> expertise) {
        this.email = email;
        this.name = name;
        this.expertise = expertise;
        this.currentIssue = null;
        this.workHistory = new ArrayList<>();
    }
}
