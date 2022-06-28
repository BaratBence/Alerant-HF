package exception;

public class InvalidRoundException extends F1Exception {
    public InvalidRoundException() {
        super("Round already exists in the season");
    }
}
