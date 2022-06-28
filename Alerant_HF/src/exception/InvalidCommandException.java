package exception;

public class InvalidCommandException extends F1Exception {
    public InvalidCommandException() {
        super("No such command");
    }
}
