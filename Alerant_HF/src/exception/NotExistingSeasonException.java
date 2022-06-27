package exception;

public class NotExistingSeasonException extends Exception {
    public NotExistingSeasonException() {
        super("No such season currently");
    }
}
