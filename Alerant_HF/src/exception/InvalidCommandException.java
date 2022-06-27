package exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("No such command");
    }
}
