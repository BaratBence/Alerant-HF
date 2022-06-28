package exception;

public class NotExistingSeasonException extends F1Exception {
    public NotExistingSeasonException() {
        super("No such season currently");
    }
}
