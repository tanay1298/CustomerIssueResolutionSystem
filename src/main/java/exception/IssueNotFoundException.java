package exception;

public class IssueNotFoundException extends RuntimeException {
    public IssueNotFoundException(String issueId) {
        super("Issue with ID " + issueId + " not found.");
    }
}
