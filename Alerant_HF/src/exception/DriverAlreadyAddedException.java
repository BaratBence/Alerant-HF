package exception;

public class DriverAlreadyAddedException extends F1Exception {
    public DriverAlreadyAddedException() {
        super("Driver already added to the finishers");
    }
}
