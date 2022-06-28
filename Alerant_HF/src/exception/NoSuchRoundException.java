package exception;

public class NoSuchRoundException extends F1Exception {
    public NoSuchRoundException() {
        super("The given value for race round is invalid");
    }
}
