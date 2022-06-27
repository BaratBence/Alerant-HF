package exception;

public class WrongArgumentCountException extends Exception {
    public WrongArgumentCountException() {
        super("Wrong argument count");
    }
}
