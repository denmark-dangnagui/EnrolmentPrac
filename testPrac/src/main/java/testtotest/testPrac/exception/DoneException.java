package testtotest.testPrac.exception;

public class DoneException extends RuntimeException {
    public DoneException() {
    }

    public DoneException(String message) {
        super(message);
    }
}
