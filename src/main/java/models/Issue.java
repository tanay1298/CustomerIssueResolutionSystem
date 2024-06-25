package models;

import enums.IssueStatus;
import enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private String      id;
    private String      transactionId;
    private IssueType   issueType;
    private String      subject;
    private String      description;
    private String      email;
    private IssueStatus status;
    private String      resolution;

    public Issue(
            String id, String transactionId, IssueType issueType, String subject, String description, String email) {
        this.id = id;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = IssueStatus.OPEN;
    }
}
