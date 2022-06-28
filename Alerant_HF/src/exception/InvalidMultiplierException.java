package exception;

public class InvalidMultiplierException extends F1Exception {
    public InvalidMultiplierException() {
        super("Multiplier can only be 0, 1, 0.5 and 2");
    }
}
