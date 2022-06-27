package exception;

public class InvalidCommandCallException extends Exception {
    public InvalidCommandCallException() {
        super("Command can only be called after certain other command");
    }
}
