package exception;

public class RaceAlreadyExistsException extends F1Exception {
    public RaceAlreadyExistsException() {
        super("Race already exists in this season");
    }
}
