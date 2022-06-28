package exception;

public class InvalidCommandCallException extends F1Exception {
    public InvalidCommandCallException() {
        super("Command can only be called after certain other command");
    }
}
