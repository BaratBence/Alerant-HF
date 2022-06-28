package exception;

public class DuplicatedFinishingPositionException extends F1Exception {
    public DuplicatedFinishingPositionException() {
        super("There are two equaled finishing positions in the same race");
    }
}
