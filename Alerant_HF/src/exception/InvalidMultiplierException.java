package exception;

public class InvalidMultiplierException extends Exception {
    public InvalidMultiplierException() {
        super("Multiplier can only be 0, 1, 0.5 and 2");
    }
}
