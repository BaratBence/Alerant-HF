package exception;

public class DuplicatedFinishingPositionException extends Exception {
    public DuplicatedFinishingPositionException() {
        super("There are two equaled finishing positions in the same race");
    }
}
