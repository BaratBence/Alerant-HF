package exception;

public class NoSuchRoundException extends Exception {
    public NoSuchRoundException() {
        super("The given value for race round is invalid");
    }
}
