package exception;

public class AgentNotFoundException extends RuntimeException {
    public AgentNotFoundException() {
        super("No available agent found for the given issue.");
    }
}
